package gxlu.flow.module.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import gxlu.flow.framework.page.PageInfo;
import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.framework.page.WHERE;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiDetail;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiInfoCategory;
import gxlu.flow.module.api.entity.ApiOrder;
import gxlu.flow.module.api.entity.ApiRequestparameter;
import gxlu.flow.module.api.entity.ApiResponsedata;
import gxlu.flow.module.api.entity.ApiScenes;
import gxlu.flow.module.api.entity.ApiServiceerrorcode;
import gxlu.flow.module.api.entity.ApiSystemerrorcode;
import gxlu.flow.module.api.service.ApiCategoryService;
import gxlu.flow.module.api.service.ApiDetailService;
import gxlu.flow.module.api.service.ApiInfoCategoryService;
import gxlu.flow.module.api.service.ApiInfoService;
import gxlu.flow.module.api.service.ApiOrderService;
import gxlu.flow.module.api.service.ApiRapiMembershipService;
import gxlu.flow.module.api.service.ApiRequestlogService;
import gxlu.flow.module.api.service.ApiRequestparameterService;
import gxlu.flow.module.api.service.ApiResponsedataService;
import gxlu.flow.module.api.service.ApiScenesService;
import gxlu.flow.module.api.service.ApiServiceerrorcodeService;
import gxlu.flow.module.api.service.ApiSystemerrorcodeService;
import gxlu.flow.module.api.util.HttpApiUtil;
import gxlu.flow.module.api.util.WebResult;
import net.sf.ehcache.search.impl.BaseResult;

@RestController
@RequestMapping("/Web")
public class WebApiController {
	@Resource
	private ApiCategoryService apiCategoryService;

	@Resource
	private ApiScenesService apiScenesService;
	
	@Resource
	private ApiInfoCategoryService apiInfoCategoryService;

	@Resource
	private ApiInfoService apiInfoService;

	@Resource
	private ApiResponsedataService apiResponsedataService;

	@Resource
	private ApiRequestparameterService apiRequestparameterService;

	@Resource
	private ApiServiceerrorcodeService apiServiceerrorcodeService;

	@Resource
	private ApiSystemerrorcodeService apiSystemerrorcodeService;

	@Resource
	private ApiDetailService apiDetailService;

	@Resource
	private ApiRequestlogService apiRequestlogService;

	@Resource
	private ApiOrderService apiOrderService;

	@Resource
	private ApiRapiMembershipService apiRapiMembershipService;

	/**
	 * 登陆
	 * 
	 * @param requestObj
	 * @return
	 */
	@RequestMapping("/login")
	public WebResult login(@RequestBody JSONObject requestObj) {
		WebResult webResult = new WebResult();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 分类
	 */
	@RequestMapping("/queryApiCategory")
	public WebResult queryApiCategory() {
		WebResult webResult = new WebResult();
		try {
			List<ApiCategory> apiCategoryList = apiCategoryService.findAll();
			webResult = new WebResult(apiCategoryList);
			webResult.setCode(1);
			webResult.setMessage("成功");
		} catch (Exception e) {
			webResult.setCode(2);
			webResult.setMessage("失败");
			e.printStackTrace();
		}
		return webResult;
	}

	/**
	 * 获取api接口列表
	 */
	@RequestMapping("/queryApiDetail")
	public WebResult queryApiDetail(@RequestBody JSONObject requestObj) {
		WebResult webResult = new WebResult();
		JSONObject obj = new JSONObject();
		PageInfo pageInfo = null;
		List<Long> apiid = new ArrayList<Long>();
		List<ApiInfoCategory> apiCategory=new ArrayList<ApiInfoCategory>();
		try {
			if(!"".equals(requestObj.getString("apiname"))&&requestObj.getString("apiname")!=null) {
				obj.put("name;"+WHERE.LIKE, requestObj.getString("apiname"));
			}
			if(requestObj.getIntValue("apiCategoryid")!=0) {
			 apiCategory = apiInfoCategoryService
					.queryApiCategory(requestObj.getLong("apiCategoryid"));
			}else if(requestObj.getIntValue("apiCategoryid")==0) {
				apiCategory=apiInfoCategoryService.findAll();
			}
			if (apiCategory.size() > 0) {
				for (ApiInfoCategory list : apiCategory) {
					if (list.getIsenabled() > 0) {
						apiid.add((long) list.getApiid());
					}

				}
			} else {
				apiid.add(0L);
			}
			obj.put("apiid.id;" + WHERE.IN, apiid);
			//0.启用
			obj.put("apiid.status;" + WHERE.EQUAL, 0);
			obj.put("status;" + WHERE.EQUAL, 1);
			PageReqInfo<ApiDetail> pageReqInfo = new PageReqInfo(requestObj, obj);
			if (requestObj.getIntValue("rank") == 2) {
				pageReqInfo.setColumnNames(new String[] {"apiid.sequence", "modifyTime" });
				pageReqInfo.setOrderTypes(new String[] {"asc","desc" });
			}
			pageReqInfo.setColumnNames(new String[] {"apiid.sequence", "modifyTime" });
			pageReqInfo.setOrderTypes(new String[] {"asc","desc" });
			Page<ApiDetail> findByPage = apiDetailService.findByPage(pageReqInfo);

			pageInfo = new PageInfo(findByPage);

			webResult = new WebResult(pageInfo);
			webResult.setCode(1);
			webResult.setMessage("成功");
		} catch (Exception e) {
			webResult.setCode(2);
			webResult.setMessage("失败");
			e.printStackTrace();
		}
		return webResult;
	}

	/**
	 * 查询api页面
	 */
	@RequestMapping("getApiDetail")
	public WebResult getApiDetail(@RequestBody JSONObject requestObj) {
		WebResult webResult = new WebResult();
		try {
			ApiDetail apiDetail = apiDetailService.findOne(requestObj.getLong("id"));
			webResult = new WebResult(apiDetail);
			webResult.setCode(1);
			webResult.setMessage("成功");
		} catch (Exception e) {
			webResult.setCode(2);
			webResult.setMessage("失败");
			e.printStackTrace();
		}
		return webResult;
	}

	/**
	 * 接口详情
	 */
	@GetMapping("/details/{apiid}")
	public WebResult queryApiDetails(@PathVariable Long apiid) {
		WebResult webResult = new WebResult();
		JSONObject json = new JSONObject();
		try {

			List<ApiRequestparameter> requestparameter = apiRequestparameterService.queryApi(apiid);
			List<ApiResponsedata> responsedata = apiResponsedataService.queryApi(apiid);
			List<ApiServiceerrorcode> serviceerrorcode = apiServiceerrorcodeService.queryApi(apiid);
			List<ApiSystemerrorcode> systemerrorcode = apiSystemerrorcodeService.queryApi(apiid);
			ApiInfo apiInfo = apiInfoService.findOne(apiid);
			List<ApiScenes> apiScenesList = apiScenesService.queryApi(apiid);
			ApiDetail apiDetail = apiDetailService.queryApiInfo(apiid);
			json.put("requestparameter", requestparameter);
			json.put("responsedata", responsedata);
			json.put("serviceerrorcode", serviceerrorcode);
			json.put("systemerrorcode", systemerrorcode);
			json.put("apiScenesList", apiScenesList);
			json.put("apiInfo", apiInfo);
			json.put("apiDetail", apiDetail);
			webResult = new WebResult(json);
			webResult.setCode(1);
			webResult.setMessage("成功");
		} catch (Exception e) {
			webResult.setCode(2);
			webResult.setMessage("失败");
			e.printStackTrace();
		}
		return webResult;
	}

	/**
	 * 支付回调
	 */
	@RequestMapping("/alipay")
	public void alipay(HttpServletRequest request, HttpServletResponse response) {
		WebResult webResult = new WebResult();
		try {
			response.sendRedirect("http://localhost:8082");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/alipay/notifyUrl", method = RequestMethod.POST)
	@ResponseBody
	public WebResult alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1.从支付宝回调的request域中取值
		// 获取支付宝返回的参数集合
		WebResult webResult = new WebResult();
		/*
		 * Map<String, String[]> aliParams = request.getParameterMap(); //用以存放转化后的参数集合
		 * Map<String, Object> conversionParams = new HashMap<String, Object>();
		 * 
		 * for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();)
		 * { String key = iter.next(); String[] values = aliParams.get(key); String
		 * valueStr = ""; for (int i = 0; i < values.length; i++) { valueStr = (i ==
		 * values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
		 * 
		 * } // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化 // valueStr = new
		 * String(valueStr.getBytes("ISO-8859-1"), "uft-8"); conversionParams.put(key,
		 * valueStr); } HttpApiUtil http=new HttpApiUtil(); String post =
		 * http.doHttpPost(new JSONObject(conversionParams).toJSONString(),
		 * "172.17.170.13:18666/alipay/notifyUrl"); if(post!=null && post!="") {
		 * JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(webResult));
		 * if(json.getIntValue("code")!=1) { webResult.setCode(2);
		 * webResult.setMessage("支付失败"); return webResult; }
		 * if("fail".equals(json.getJSONObject("data").getString("result"))) {
		 * webResult.setCode(2); webResult.setMessage("支付失败"); return webResult; }
		 */
		ApiOrder apiOrder = new ApiOrder();
		String orderIds = "|47|ewe|1|0.3|<p>啊实打实大苏打<//p>|apiDetail//pictureurl\\d51c5fd8-e2d7-4bdf-b1bb-b540bd7e5279policy.PNG|10|150|5000|admin";
		String[] split = orderIds.split("\\|");
		for (int i = 0; i < split.length; i++) {
			if (i == 1) {
				ApiInfo apiinfo = apiInfoService.findOne(Long.parseLong(split[i]));
				apiOrder.setApiinfo(apiinfo);
			}
			if (i == 2) {
				apiOrder.setApiname(split[i]);
			}
			if (i == 3) {
				apiOrder.setChargingcategory(Integer.parseInt(split[i]));
			}
			
			if (i == 4) {
				apiOrder.setUnitprice(Double.parseDouble(split[i]) );
			}
			
			if (i == 5) {
				apiOrder.setUnitpricedescription(split[i]);
			}
			
			if (i == 6) {
				apiOrder.setPictureurl(split[i]);
			}
			
			if (i == 7) {
				apiOrder.setMembershipid(Integer.parseInt(split[i]));
			}
			
			if (i == 8) {
				apiOrder.setAmountmoney(Double.parseDouble(split[i]));
			}
			if (i == 9) {
				apiOrder.setTotaltimes(Integer.parseInt(split[i]));
				
			}
			
			if (i == 10) {
				apiOrder.setCreator(split[i]);
				apiOrder.setCreatTime(new Date());
			}
			
		}
		apiOrderService.save(apiOrder);
		/* } */

		return webResult;
	}
}
