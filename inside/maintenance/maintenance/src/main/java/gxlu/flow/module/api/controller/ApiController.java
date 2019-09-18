package gxlu.flow.module.api.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.Replace;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import gxlu.flow.framework.constant.SysConst;
import gxlu.flow.framework.page.PageInfo;
import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.framework.page.WHERE;
import gxlu.flow.framework.util.CommonUtils;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiDetail;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiInfoCategory;
import gxlu.flow.module.api.entity.ApiOrder;
import gxlu.flow.module.api.entity.ApiRapiMembership;
import gxlu.flow.module.api.entity.ApiRequestlog;
import gxlu.flow.module.api.entity.ApiRequestparameter;
import gxlu.flow.module.api.entity.ApiResponsedata;
import gxlu.flow.module.api.entity.ApiScenes;
import gxlu.flow.module.api.entity.ApiServiceerrorcode;
import gxlu.flow.module.api.entity.ApiSystemerrorcode;
import gxlu.flow.module.api.entity.EolinkerApi;
import gxlu.flow.module.api.entity.Membership;
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
import gxlu.flow.module.sysmanager.entity.SysUser;

@RestController
@RequestMapping("/Api")
public class ApiController {
	
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
	
	private static String PAGE="/maintenance/";
	
	private static String ADD="add";
	
	private static String EDIT="edit";
	
	//外网
	private String URL="http://61.152.172.130:9080/eolinker_os/interface/Api/";
	
	//内网
	//private String URL="http://172.17.10.148:8090/eolinker_os/interface/Api/";
	
	private String queryMemberURL="http://172.17.12.21:18796/auth/authFeign/";
	@RequestMapping("/queryset")
	public PageInfo queryset(@RequestBody JSONObject requestObj) {
		JSONObject obj=new JSONObject();
		PageInfo pageInfo=null;
		//apiStatus  0.启动  1.维护  2.弃用
		//apiProtocol 0.http 1.https
		//apiRequestType  0.POST 1.GET 2.PUT  3.DELETE  4.HEAD 5.OPTIONS 6.PATCH
		try {
			pageInfo=apiInfoService.queryset(requestObj);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}
	/**
	 * 查询eolink 接口详情
	 * @param requestObj
	 * @return
	 */
	@RequestMapping("/queryEolinkApi")
	public JSONObject queryEolinkApi(@RequestBody JSONObject requestObj) {
		try {
			HttpApiUtil http=new HttpApiUtil();
			JSONObject object= new JSONObject();
			//请求参数
			String requestInfo=null;
			//返回参数
			String resultInfo=null;
			
			object.put("projectID", 5);
			object.put("groupID", -1);
			object.put("apiID",requestObj.getIntValue("apiid"));
			String datas=http.doHttpPost(object.toString(),URL+"getApi");
			ApiInfo apiInfo=new ApiInfo();
			JSONObject parseObject = JSONObject.parseObject(datas);
			if(parseObject.getString("statusCode").equals("000000")) {
				JSONObject json = parseObject.getJSONObject("apiInfo");
				JSONObject baseInfo = json.getJSONObject("baseInfo");
				apiInfo.setId(baseInfo.getLong("apiID"));
				apiInfo.setName(baseInfo.getString("apiName"));
				apiInfo.setUri(baseInfo.getString("apiURI"));
				apiInfo.setProtocol(baseInfo.getIntValue("apiProtocol"));
				apiInfo.setStatus(baseInfo.getIntValue("apiStatus"));
			
				apiInfo.setStatus(baseInfo.getIntValue("apiStatus"));
				apiInfo.setMethod(baseInfo.getIntValue("apiRequestType"));
				apiInfo.setResponsedata(baseInfo.getString("apiSuccessMock"));
				apiInfo.setAbbreviation(baseInfo.getString("apiName"));
				requestInfo=json.getString("requestInfo");
				resultInfo=json.getString("resultInfo");
			}
			requestObj.put("apiInfo", apiInfo);
			requestObj.put("requestInfo",requestInfo);
			requestObj.put("resultInfo",resultInfo);
			requestObj.put("pageType",ADD);
			requestObj.put("code", 1);
			requestObj.put("message","成功");
			
		}catch(Exception e) {
			requestObj.put("code",2);
			requestObj.put("message","失败");
			e.printStackTrace();
		}
		return requestObj;
	}
	/**
	 * api配置编辑页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/getViewSet/{id}")
	public ModelAndView getViewSet(@PathVariable Long id,HttpServletRequest request,ModelAndView model) {
try {
			
			ApiInfo apiInfo=null;
			
			if(id<0) {
				JSONObject object=new JSONObject();
				object.put("projectID", 5);
				object.put("groupID",-1);
				object.put("orderBy",3);
				object.put("asc",0);
				apiInfo =new ApiInfo();
				List<EolinkerApi> eolinkerApi=new ArrayList<EolinkerApi>();
				apiInfo.setId(-1L);
				HttpApiUtil http=new HttpApiUtil();
				String json=http.doHttpPost(object.toString(), URL+"getAllApiList");
				JSONObject parseObject = JSONObject.parseObject(json);
				if(parseObject.getString("statusCode").equals("000000")) {
					JSONArray array = parseObject.getJSONArray("apiList");
					if(array.size()>0) {
						
					 eolinkerApi = JSONObject.parseArray(array.toString(), EolinkerApi.class);
					}
				}
					model.addObject("eolinkerApi",eolinkerApi);
				
					model.addObject("pageType", ADD);
			}else {
				 apiInfo = apiInfoService.findOne(id);
				 List<ApiRequestparameter> requestInfo = apiRequestparameterService.queryApi(id);
				 List<ApiResponsedata> resultInfo = apiResponsedataService.queryApi(id);
				 
				 
				 model.addObject("pageType", EDIT);
				 model.addObject("requestInfo",requestInfo);
				model.addObject("resultInfo",resultInfo);
			}
			//获取类型
			List<ApiCategory> apiCategoryList = apiCategoryService.findAll();
			String updatetime=null;
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(apiInfo.getModifyTime()!=null) {
			 updatetime = sim.format(apiInfo.getModifyTime());
			}
			ApiDetail apiDetail = apiDetailService.queryApiInfo(apiInfo.getId());
			if(apiDetail==null) {
				 apiDetail=new ApiDetail();
			}
			model.addObject("apiCategoryList",apiCategoryList);
			model.addObject("apiDetail",apiDetail);
			model.addObject("updatetime", updatetime);
			model.addObject("apiInfo", apiInfo);
			model.setViewName(PAGE+"/api/apiInfo");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	//查询  参数表和服务错误码表
	@RequestMapping("/queryApi")
	public JSONObject queryApi(@RequestBody JSONObject requestObj) {
		try {
			Long apiid = requestObj.getLong("apiid");
			
			 List<ApiRequestparameter> requestparameter=apiRequestparameterService.queryApi(apiid);
			 
			 List<ApiServiceerrorcode> apiService = apiServiceerrorcodeService.queryApi(apiid);
			 
			 List<ApiSystemerrorcode> apiSystem = apiSystemerrorcodeService.queryApi(apiid);

			 
			 List<ApiResponsedata> apiResponsedata=apiResponsedataService.queryApi(apiid);
			 List<ApiScenes> apiScenesList = apiScenesService.queryApi(apiid);		 
			 requestObj.put("requestparameterList", requestparameter);
			 requestObj.put("apiResponsedataList", apiResponsedata);
			 requestObj.put("apiSystemList",apiSystem);
			 requestObj.put("apiServiceList",apiService);
			 requestObj.put("apiScenesList",apiScenesList);
			 requestObj.put("code", 1);
			 requestObj.put("message","成功");
			 
		}catch(Exception e) {
			requestObj.put("code",2);
			 requestObj.put("message","失败");
			e.printStackTrace();
		}
		return requestObj;
	}
	/**
	 * 保存Api配置
	 * @return
	 */
	@RequestMapping("/saveApiSet")
	public JSONObject saveApiSet(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		JSONObject object=new JSONObject();
		try {

			 object=apiInfoService.saveApiSet( requestObj,request);

		}catch(Exception e) {
			object.put("code", 2);
			object.put("message","失败");
			e.printStackTrace();
		}
		return object;
	}
	//分类列表
	@RequestMapping("queryCategory")
	public JSONObject queryCategory() {
		JSONObject obj=new JSONObject();
		try {
			//List<ApiCategory> apiCategory=apiCategoryService.queryCategory();
			List<ApiCategory> apiCategory=apiCategoryService.findAll();
			obj.put("apiCategoryList", apiCategory);
			obj.put("code", 1);
			obj.put("message","成功");
		}catch(Exception e) {
			obj.put("code",2);
			obj.put("message","失败");
			e.printStackTrace();
		}
		return obj;
	}
	
	//分类页面
	@GetMapping("/getViewCategory/{id}")
	public ModelAndView getViewCategory(@PathVariable Long id,ModelAndView model) {
		
		try {
			ApiCategory apiCategory=null;
			if(id<1) {
					apiCategory=new ApiCategory();
				apiCategory.setId(-1L);
			}else {
				apiCategory=apiCategoryService.findOne(id);
			}
			model.addObject("apiCategory", apiCategory);
			model.setViewName(PAGE+"/api/apiCategoryInfo");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return model;
	}
	
	//查询分类属性
	@RequestMapping("/findCategory")
	public JSONObject findCategory(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		try {
			ApiCategory apiCategory = apiCategoryService.findOne(requestObj.getLong("categoryid"));
			requestObj.put("apiCategory",apiCategory);
			requestObj.put("code", 1);
			requestObj.put("message","成功");
		}catch(Exception e) {
			requestObj.put("code",2);
			requestObj.put("message","失败");
			e.printStackTrace();
		}
		return requestObj;
	}

	//新建分类
	@RequestMapping("/saveCategory")
	public JSONObject saveCategory(@RequestBody JSONObject requestObj,HttpServletRequest request) {
		SysUser user =(SysUser) request.getSession().getAttribute("userObj");
		Long id =null;
		ApiCategory apiCategory=null;
		JSONObject obj=new JSONObject();
		try {
		if(!"".equals(requestObj.getString("categoryid")) && requestObj.getString("categoryid")!=null) {
			apiCategory=apiCategoryService.findOne(requestObj.getLong("categoryid"));
			apiCategory.setModifyTime(new Date());
			apiCategory.setModifier(user.getUser_Name());
			
			
		}else {
			apiCategory=new ApiCategory();
			 apiCategory.setCreatTime(new Date());
			apiCategory.setCreator(user.getUser_Name());
		}
		apiCategory.setName(requestObj.getString("categoryname"));
		
		apiCategoryService.save(apiCategory);
		obj.put("code", 1);
		obj.put("message", "成功");
		}catch(Exception e) {
			obj.put("code", 2);
			obj.put("message", "失败");
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * 保存请求参数
	 */
	@RequestMapping("/saveRequestparameter")
	public JSONObject saveRequestparameter(@RequestBody JSONObject json,HttpServletRequest request) {
		try {
			ApiRequestparameter  apiRequestparameter=null;
			SysUser user = (SysUser) request.getSession().getAttribute("userObj");
			ApiDetail apiDetail = apiDetailService.queryApiInfo(json.getLong("apiid"));
			if(apiDetail.getStatus()==1) {
				json.put("code", 3);
				json.put("message","当前接口为上架状态，无法进行修改");
				return json;
			}
			
			if(!"".equals(json.getString("requestid")) && json.getLong("requestid")!=null) {
				apiRequestparameter=apiRequestparameterService.findOne(json.getLong("requestid"));
				apiRequestparameter.setModifier(user.getUser_Name());
				apiRequestparameter.setModifyTime(new Date());
			}else {
				apiRequestparameter=new ApiRequestparameter();
				apiRequestparameter.setCreatTime(new Date());
				apiRequestparameter.setCreator(user.getUser_Name());
			}
			
			
			apiRequestparameter.setApiid(json.getIntValue("apiid"));
			apiRequestparameter.setName(json.getString("name"));
			apiRequestparameter.setRemark(json.getString("remark"));
			apiRequestparameter.setType(json.getString("type"));
			apiRequestparameter.setMustfill(json.getString("agree"));
			
			apiRequestparameterService.save(apiRequestparameter);
			json.put("code",1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 删除请求参数
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRequest")
	public JSONObject deleteRequest(@RequestBody JSONObject json,HttpServletRequest request) {
		
		try {
			apiRequestparameterService.delete(json.getLongValue("requestid"));
			json.put("code", 1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 保存返回参数
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveResponsedata")
	public JSONObject saveResponsedata(@RequestBody JSONObject json,HttpServletRequest request) {
		try {
			ApiResponsedata  apiResponsedata=null;
			SysUser user = (SysUser) request.getSession().getAttribute("userObj");
			if(!"".equals(json.getString("responseid")) && json.getLong("responseid")!=null) {

				apiResponsedata=apiResponsedataService.findOne(json.getLong("responseid"));
				apiResponsedata.setModifier(user.getUser_Name());
				apiResponsedata.setModifyTime(new Date());
			}else {
				apiResponsedata=new ApiResponsedata();
				apiResponsedata.setCreatTime(new Date());
				apiResponsedata.setCreator(user.getUser_Name());
			}
			 
			apiResponsedata.setApiid(json.getIntValue("apiid"));
			apiResponsedata.setName(json.getString("name"));
			apiResponsedata.setRemark(json.getString("remark"));
			apiResponsedata.setType(json.getString("type"));
			
			
			apiResponsedataService.save(apiResponsedata);
			json.put("code",1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 保存系统级错误码
	 * @param json
	 * @param request
	 * @return
	 */
	/**
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveSystemError")
	public JSONObject saveSystemError(@RequestBody JSONObject json,HttpServletRequest request) {
		try {
			ApiSystemerrorcode  apiSystemerrorcode=null;
			SysUser user = (SysUser) request.getSession().getAttribute("userObj");
			if(!"".equals(json.getString("id")) && json.getLong("id")!=null) {

				apiSystemerrorcode=apiSystemerrorcodeService.findOne(json.getLong("id"));
				apiSystemerrorcode.setModifier(user.getUser_Name());
				apiSystemerrorcode.setModifyTime(new Date());
			}else {
				apiSystemerrorcode=new ApiSystemerrorcode();
				apiSystemerrorcode.setCreatTime(new Date());
				apiSystemerrorcode.setCreator(user.getUser_Name());
			}
			 
			apiSystemerrorcode.setApiid(json.getIntValue("apiid"));
			apiSystemerrorcode.setErrorcode(json.getString("code"));
			apiSystemerrorcode.setRemark(json.getString("remark"));

			apiSystemerrorcodeService.save(apiSystemerrorcode);
			json.put("code",1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 服务级错误码
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveServiceError")
	public JSONObject saveServiceError(@RequestBody JSONObject json,HttpServletRequest request) {
		try {
			ApiServiceerrorcode  apiServiceerrorcode=null;
			SysUser user = (SysUser) request.getSession().getAttribute("userObj");
			if(!"".equals(json.getString("id")) && json.getLong("id")!=null) {

				apiServiceerrorcode=apiServiceerrorcodeService.findOne(json.getLong("id"));
				apiServiceerrorcode.setModifier(user.getUser_Name());
				apiServiceerrorcode.setModifyTime(new Date());
			}else {
				apiServiceerrorcode=new ApiServiceerrorcode();
				apiServiceerrorcode.setCreatTime(new Date());
				apiServiceerrorcode.setCreator(user.getUser_Name());
			}
			 
			apiServiceerrorcode.setApiid(json.getIntValue("apiid"));
			apiServiceerrorcode.setErrorcode(json.getString("code"));
			apiServiceerrorcode.setRemark(json.getString("remark"));
			
			
			
			apiServiceerrorcodeService.save(apiServiceerrorcode);
			json.put("code",1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 删除返回参数
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteResponse")
	public JSONObject deleteResponse(@RequestBody JSONObject json,HttpServletRequest request) {
		
		try {
			apiResponsedataService.delete(json.getLongValue("responseid"));
			json.put("code", 1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 删除服务错误码
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteApiSystem")
	public JSONObject deleteApiSystem(@RequestBody JSONObject json,HttpServletRequest request) {
		
		try {
			apiSystemerrorcodeService.delete(json.getLongValue("responseid"));
			json.put("code", 1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 删除系统错误码
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteApiService")
	public JSONObject deleteApiService(@RequestBody JSONObject json,HttpServletRequest request) {
		
		try {
			apiServiceerrorcodeService.delete(json.getLongValue("responseid"));
			json.put("code", 1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code",2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 查询api复选框所选中的类
	 */
	@RequestMapping("/queryApiInfocategory")
	public JSONObject queryApiInfocategory(@RequestBody JSONObject json) {
		try {
			List<ApiInfoCategory> apiInfoCategoryList =apiInfoCategoryService.queryIsenabled(json.getIntValue("apiid"));
			json.put("apiInfoCategoryList",apiInfoCategoryList);
			json.put("code", 1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code", 2);
			json.put("message","失败");
			e.printStackTrace();
		}

		return json;
	}
	/**
	 * 修改弃用-维护-弃用状态
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/setState")
	public JSONObject setState(@RequestBody JSONObject json,HttpServletRequest request) {
		try {
			ApiInfo apiInfo = apiInfoService.findOne(json.getLong("apiid"));
			ApiDetail apiDetail = apiDetailService.queryApiInfo(json.getLong("apiid"));
			if(json.getIntValue("state")!=1) {
			if(apiDetail.getStatus()==1) {
				json.put("code", 3);
				json.put("message","当前所选择的接口已上架，无法将状态更改为【维护】或者【弃用】");
				return json;
			}
			}
			apiInfo.setStatus(json.getIntValue("state"));
			SysUser user =(SysUser) request.getSession().getAttribute("userObj");
			apiInfo.setModifier(user.getUser_Name());
			apiInfo.setModifyTime(new Date());
			apiInfoService.save(apiInfo);
			
			json.put("code", 1);
			json.put("message","成功");
		}catch(Exception e) {
			json.put("code", 2);
			json.put("message","失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 查询Api上架
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryDetail")
	public PageInfo queryDetail(@RequestBody JSONObject json,HttpServletRequest request) {
		PageInfo pageInfo=null;
		JSONObject object=new JSONObject();
		try {
			if(!"".equals(json.getString("name")) && json.getString("name")!=null) {
				object.put("name;"+WHERE.LIKE, json.getString("name"));
			}
			object.put("status;"+WHERE.EQUAL, json.getIntValue("state"));
			PageReqInfo<ApiDetail> pageRequest=new PageReqInfo<ApiDetail>(json,object);
			pageRequest.setColumnNames(new String[]{"modifyTime"});
			pageRequest.setOrderTypes(new String[] {"desc"});
			Page<ApiDetail> boqualificationBase = apiDetailService.findByPage(pageRequest);
			pageInfo=new PageInfo(boqualificationBase);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return pageInfo;
	}
	
	/**
	 *修改状态  下架
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping("/soldOut")
	public JSONObject soldOut(@RequestBody JSONObject json,HttpServletRequest request) {
		
		JSONObject object=new JSONObject();
		try {
			ApiDetail apiDetail = apiDetailService.findOne(json.getLong("id"));
			apiDetail.setStatus(0);
			apiDetailService.save(apiDetail);
			object.put("code", 1);
			object.put("message","成功");
		}catch(Exception e) {
			object.put("code", 2);
			object.put("message","失败");
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * 查询Api上架编辑页面
	 * @param json
	 * @param request
	 * @return
	 */
	@GetMapping("/getViewDetail/{id}")
	public ModelAndView getViewDetail(@PathVariable Long id,ModelAndView model,HttpServletRequest request) {
		try {
			ApiDetail apiDetail = apiDetailService.findOne(id);
			List<ApiResponsedata> responseList = apiResponsedataService.queryApi(apiDetail.getApiid().getId());
			List<ApiRequestparameter> requestList = apiRequestparameterService.queryApi(apiDetail.getApiid().getId());
			List<ApiSystemerrorcode> systemList= apiSystemerrorcodeService.queryApi(apiDetail.getApiid().getId());
			List<ApiServiceerrorcode> serviceList = apiServiceerrorcodeService.queryApi(apiDetail.getApiid().getId());
			List<ApiInfoCategory> categoryList = apiInfoCategoryService.queryIsenabled(apiDetail.getApiid().getId().intValue());
			
			List<ApiScenes> apiScenesList = apiScenesService.queryApi(apiDetail.getApiid().getId());
			
			ApiInfo apiInfo=apiDetail.getApiid();
			for(ApiInfoCategory list:categoryList) {
				ApiCategory apiCategory = apiCategoryService.findOne((long)list.getApicategoryid());
				list.setApiCategoryname(apiCategory.getName());
			}
			String updatetime=null;
			if(apiDetail.getModifyTime()!=null) {
				SimpleDateFormat format=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				updatetime=format.format(apiDetail.getModifyTime());
			}
			
			model.addObject("categoryList", categoryList);
			model.addObject("apiInfo", apiInfo);
			model.addObject("responseList", responseList);
			model.addObject("requestList", requestList);
			model.addObject("systemList", systemList);
			model.addObject("apiScenesList", apiScenesList);
			model.addObject("serviceList", serviceList);
			model.addObject("apiDetail", apiDetail);
			model.addObject("updatetime", updatetime);
			
			model.setViewName(PAGE+"/api/apiDetailInfo");
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	
	
	/**
	 * 保存详情
	 */
	  @RequestMapping("/saveapiDetail") 
	  public JSONObject saveapiDetail(@RequestParam(value = "detailstatus", required = false) String detailstatus,
			  @RequestParam(value = "unitPrice", required = false) String unitPrice,
			  @RequestParam(value = "chargingcategory", required = false) String chargingcategory,
			  @RequestParam(value = "unitpricedescriptionContext", required = false) String unitpricedescriptionContext,
			  @RequestParam(value = "interfaceDescriptionContext", required = false) String interfaceDescriptionContext,
			  @RequestParam(value = "apiDetailid", required = false) String apiDetailid,
			  @RequestParam(value = "functionUrl", required = false) String functionUrl,
			  @RequestParam(value = "introductionContext", required = false) String introductionContext,
			  @RequestParam(value = "portUrl", required = false) String portUrl,
			  MultipartFile portPicture,MultipartFile functionpicture,HttpServletRequest request) {
		  		JSONObject json=new JSONObject();
		  		try {
		  			
		  			SysUser user =(SysUser) request.getSession().getAttribute("userObj");
		  			
		  			String portPicturePath=null;
		  			String functionpicturePath=null;
		  			if(portPicture!=null) {
		  				portPicturePath = picturePath(SysConst.pictureurl,portPicture, request);
		  			}else {
		  				portPicturePath=portUrl;
		  			}
		  			
		  			if(functionpicture!=null) {
		  				functionpicturePath = picturePath(SysConst.example,functionpicture, request);
		  			}else {
		  				functionpicturePath=functionUrl;
		  			}
		  			
		  			Long id = Long.valueOf(apiDetailid);
		  			ApiDetail apiDetail = apiDetailService.findOne(id);
		  			if(apiDetail!=null) {	
		  			apiDetail.setInterfaceDescription(interfaceDescriptionContext);
		  			
		  			
		  			apiDetail.setChargingcategory(chargingcategory);
		  			
		  			double parseDouble = Double.parseDouble(unitPrice);
		  			apiDetail.setUnitPrice(parseDouble);
		  			apiDetail.setUnitpricedescription(unitpricedescriptionContext);
		  			apiDetail.setPictureurl(portPicturePath);
		  			apiDetail.setIntroduction(introductionContext);
		  			apiDetail.setExample(functionpicturePath);
		  			apiDetail.setStatus(1);
		  			
				/*
				 * int parseInt = Integer.parseInt(detailstatus); apiDetail.setStatus(parseInt);
				 */
		  		
		  			apiDetail.setModifyTime(new Date());
		  			apiDetail.setModifier(user.getUser_Name());
		  			
		  			apiDetailService.save(apiDetail);
		  				json.put("code",1);
		  				json.put("message","成功");
		  			}else {
		  				json.put("code",2);
		  				json.put("message","失败");
		  			}
		  			
		  		}catch(Exception e) {
		  			json.put("code",2);
	  				json.put("message","失败");
		  			e.printStackTrace();
		  		}
		  
				return json;
	  }
	private String picturePath(String string,MultipartFile file,HttpServletRequest request) {
		try {
			if(file!=null) {
				String path=request.getSession().getServletContext().getRealPath("/");
				path=CommonUtils.convertUploadFilePath(path);
				File f1=new File(path+SysConst.apiDetail+string);
				if(!f1.exists()) {
					f1.mkdirs();
				}
				String savepath = SysConst.apiDetail+ string+ File.separator + UUID.randomUUID().toString()
						+ file.getOriginalFilename(); 
				// 文件保存路径
				String filePath = path + savepath;
				// 转存文件
				File f = new File(filePath);
				file.transferTo(f);
				return savepath;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * api查看会员
	 */
	@RequestMapping("/queryMember")
	 public PageInfo queryMember(@RequestBody JSONObject requestObj) {
		PageInfo pageInfo=null;
		try {
		HttpApiUtil post=new HttpApiUtil();
		JSONObject object=new JSONObject();
		List<Membership> membershipList=new ArrayList<Membership>();
		
		String telephone="";
		String name="";
		if(requestObj.getString("telephone")!=null) {
			telephone="\"+"+telephone+"+\"";
		}
		if(requestObj.getString("name")!=null) {
			name="\""+requestObj.getString("name")+"\"";
		}

		String jsonString=post.doHttpPost("{telephone:\""+telephone+"\",name:\""+name+"\"}",queryMemberURL+"findAuthMembershipData");
		JSONObject json=object.parseObject(jsonString);
		if(json.getString("code").equals("1")) {
			JSONArray jsonArray = json.getJSONArray("data");
			for(int i=0;i<jsonArray.size();i++) {
				Membership membership=new Membership();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				int id = jsonObject.getIntValue("id");
				String telephones = jsonObject.getString("telephone");
				membership.setTelephone(telephones);
				membership.setId(id);
				JSONObject jsonvalue = jsonObject.getJSONObject("authAccountInfo");
				if(jsonvalue!=null ) {
					String names = jsonvalue.getString("name");
					membership.setMembershipName(names);
					
				}
				membershipList.add(membership);
			}
		}
		 pageInfo=new PageInfo((long)membershipList.size(),membershipList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return pageInfo;
	}
	/**
	 * 获取调用接口详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/getViewInterface/{memberid}")
	public ModelAndView  getViewInterface(@PathVariable Long memberid,ModelAndView model) {
		try {
			
			Membership membership=queryMember(memberid);
			List<ApiCategory> apiCategoryList = apiCategoryService.findAll();
			List<ApiRapiMembership> apiRapiMembership=apiRapiMembershipService.queryApiInfo(memberid.intValue());
			model.addObject("apiRapiMembership", apiRapiMembership);
			model.addObject("apiCategoryList", apiCategoryList);
			model.addObject("membership",membership);
			model.setViewName(PAGE+"api/interfaceCallinfo");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	 
	/**
	 * 根据用户id查询
	 * api_r_api_membership表
	 */
	@RequestMapping("/queryInterfaceCallInfo")
	public PageInfo queryInterfaceCallInfo(@RequestBody JSONObject requestObj) {
		JSONObject obj=new JSONObject();
		PageInfo pageInfo=null;
		try {
			if(!"".equals(requestObj.getString("category")) && requestObj.getString("category")!=null) {
				List<ApiInfoCategory> apiinfocategory=apiInfoCategoryService.queryApiCategory(requestObj.getLong("category"));
				List<Long> apiInfoid=new ArrayList<Long>();
				for(ApiInfoCategory list:apiinfocategory) {
					if(list.getIsenabled()>0) {
					apiInfoid.add((long)list.getApiid());
					}
				}
				
				obj.put("apiurl.id;"+WHERE.IN,apiInfoid);
			}

		obj.put("membershipid;"+WHERE.EQUAL, requestObj.getIntValue("id"));
		if(!"".equals(requestObj.getString("apiid")) && requestObj.getString("apiid")!=null) {
			obj.put("apiurl.id;"+WHERE.EQUAL,requestObj.getLong("apiid"));
		}
		PageReqInfo<ApiRapiMembership>pageReqInfo=new PageReqInfo<ApiRapiMembership>(requestObj,obj);
		pageReqInfo.setColumnNames(new String[] {"apiurl.sequence"});
		pageReqInfo.setOrderTypes(new String[] {"asc"});
		Page<ApiRapiMembership> apiRapiMembership = apiRapiMembershipService.findByPage(pageReqInfo);
		pageInfo=new PageInfo(apiRapiMembership);
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return pageInfo;
	}
	/**
	 * 总次数
	 * @param requestObj
	 * @param model
	 * @return
	 */
	@RequestMapping("/totalView/{apaiid}/{memberid}")
	public ModelAndView totalView(@PathVariable Long apaiid,@PathVariable Long memberid,ModelAndView model) {
		try {
		int total=apiRapiMembershipService.queryTotal(apaiid,memberid);
		int usedtimes=apiRapiMembershipService.queryUsedtimes(apaiid,memberid);
		int remaindertimes=apiRapiMembershipService.queryRemaindertimes(apaiid,memberid);
		model.addObject("total",total);
		model.addObject("apaiid",apaiid);
		model.addObject("memberid",memberid);
		model.addObject("usedtimes",usedtimes);
		model.addObject("remaindertimes",remaindertimes);
		model.setViewName(PAGE+"/api/apiorder");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping("/queryOrder")
	public PageInfo queryOrder(@RequestBody JSONObject requestObj) {
		PageInfo pageInfo=null;
		try {
			JSONObject obj=new JSONObject();
					obj.put("apiinfo.id;"+WHERE.EQUAL, requestObj.getLong("apiid"));
					obj.put("membershipid;"+WHERE.EQUAL, requestObj.getIntValue("memberid"));
			PageReqInfo<ApiOrder> pageReqInfo=new PageReqInfo(requestObj,obj);
			pageReqInfo.setColumnNames(new String[] {"creatTime"});
			pageReqInfo.setOrderTypes(new String[] {"desc"});
			Page<ApiOrder> findByPage = apiOrderService.findByPage(pageReqInfo);
			 pageInfo=new PageInfo(findByPage);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}
	/**
	 * 获取已使用次数页面
	 */
	@RequestMapping("/getUsedtimes/{apaiid}/{memberid}")
	public ModelAndView getUsedtimes(@PathVariable Long apaiid,@PathVariable Long memberid,ModelAndView model) {
		try {
			
			Membership membership = queryMember(memberid);
			ApiInfo apiInfo = apiInfoService.findOne(apaiid);
			model.addObject("membership", membership);
			model.addObject("apiInfo", apiInfo);
			model.setViewName(PAGE+"api/usedtimes");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 获取已调用table
	 */
	@RequestMapping("queryUsedtimes")
	public PageInfo queryUsedtimes(@RequestBody JSONObject requestObj) {
		PageInfo pageInfo=null;
		try {
			ApiInfo apiInfo = apiInfoService.findOne(requestObj.getLong("apiid"));
			JSONObject obj=new JSONObject();
			obj.put("apiurl;"+WHERE.EQUAL,apiInfo.getUri());
			obj.put("membershipid;"+WHERE.EQUAL,requestObj.getIntValue("memberid"));
			PageReqInfo<ApiRequestlog> pageReqInfo=new PageReqInfo(requestObj,obj);
			pageReqInfo.setColumnNames(new String[] {"requesttime"});
			pageReqInfo.setOrderTypes(new String[] {"desc"});
			Page<ApiRequestlog> findByPage = apiRequestlogService.findByPage(pageReqInfo);
			pageInfo=new PageInfo(findByPage);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}
	/**
	 * 调用接口获取会员信息
	 * @param memberid
	 * @return
	 */
	public Membership queryMember(Long memberid) {
		Membership membership=new Membership();
		try {
			JSONObject object=new JSONObject();
			HttpApiUtil post=new HttpApiUtil();
			String jsonString=post.doHttpPost("{id:"+memberid+"}",queryMemberURL+"findAuthMembershipData");
			JSONObject json=object.parseObject(jsonString);
			if(json.getString("code").equals("1")) {
				JSONArray jsonArray = json.getJSONArray("data");
				for(int i=0;i<jsonArray.size();i++) {
					membership=new Membership();
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					int ids = jsonObject.getIntValue("id");
					String telephones = jsonObject.getString("telephone");
					membership.setTelephone(telephones);
					membership.setId(ids);
					JSONObject jsonvalue = jsonObject.getJSONObject("authAccountInfo");
					if(jsonvalue!=null ) {
						String names = jsonvalue.getString("name");
						membership.setMembershipName(names);
						
					}
					
				}
			}
		}catch(Exception e) {
			
		}
		return membership;
	}
	/**
	 * 预览页面
	 * @param apiid
	 * @param model
	 * @return
	 */
	@RequestMapping("/preview/{apiid}")
	public ModelAndView preview(@PathVariable Long apiid, ModelAndView model,HttpServletRequest request) {
		try {
		
			
			List<ApiRequestparameter> requestparameter = apiRequestparameterService.queryApi(apiid);
			List<ApiResponsedata> responsedata = apiResponsedataService.queryApi(apiid);
			List<ApiServiceerrorcode> serviceerrorcode = apiServiceerrorcodeService.queryApi(apiid);
			List<ApiSystemerrorcode> systemerrorcode = apiSystemerrorcodeService.queryApi(apiid);
			List<ApiScenes> apiScenesList = apiScenesService.queryApi(apiid);
			
			ApiInfo apiInfo = apiInfoService.findOne(apiid);
			model.addObject("requestparameter", requestparameter);
			model.addObject("serviceerrorcode", serviceerrorcode);
			model.addObject("systemerrorcode", systemerrorcode);
			model.addObject("apiInfo", apiInfo);
			model.addObject("responsedata", responsedata);
			model.addObject("apiScenesList", apiScenesList);
			
			model.setViewName(PAGE+"/api/preview");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
