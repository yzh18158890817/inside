package gxlu.flow.module.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import gxlu.flow.framework.page.PageInfo;
import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiDetail;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiInfoCategory;
import gxlu.flow.module.api.entity.ApiRapiMembership;
import gxlu.flow.module.api.entity.ApiRequestparameter;
import gxlu.flow.module.api.entity.ApiResponsedata;
import gxlu.flow.module.api.entity.ApiScenes;
import gxlu.flow.module.api.entity.ApiServiceerrorcode;
import gxlu.flow.module.api.entity.ApiSystemerrorcode;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.service.ApiDetailService;
import gxlu.flow.module.api.service.ApiInfoCategoryService;
import gxlu.flow.module.api.service.ApiInfoService;
import gxlu.flow.module.api.service.ApiRequestparameterService;
import gxlu.flow.module.api.service.ApiResponsedataService;
import gxlu.flow.module.api.service.ApiScenesService;
import gxlu.flow.module.api.service.ApiServiceerrorcodeService;
import gxlu.flow.module.api.service.ApiSystemerrorcodeService;
import gxlu.flow.module.sysmanager.entity.SysUser;

@Service
public class ApiInfoServiceImpl implements ApiInfoService {
	private static String ADD = "add";

	private static String EDIT = "edit";

	@Autowired
	private ApiInfoRespository apiInfoRespository;

	@Autowired
	private ApiInfoService apiInfoService;
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;

	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;

	@Autowired
	private ApiInfoCategoryService apiInfoCategoryService;

	@Autowired
	private ApiDetailService apiDetailService;

	@Autowired
	private ApiRequestparameterService apiRequestparameterService;

	@Autowired
	private ApiResponsedataService apiResponsedataService;

	@Autowired
	private ApiServiceerrorcodeService apiServiceerrorcodeService;

	@Autowired
	private ApiSystemerrorcodeService apiSystemerrorcodeService;
	
	@Autowired
	private ApiScenesService apiScenesService;

	@Override
	public ApiInfo findOne(Long id) {
		// TODO Auto-generated method stub
		return apiInfoRespository.findOne(id);
	}

	@Override
	public List<ApiInfo> findAll() {
		// TODO Auto-generated method stub
		return apiInfoRespository.findAll();
	}

	@Override
	public void save(ApiInfo t) {
		apiInfoRespository.save(t);

	}

	@Override
	public void edit(ApiInfo t) {
		apiInfoRespository.saveAndFlush(t);

	}

	@Override
	public void delete(Long id) {
		apiInfoRespository.delete(id);

	}

	@Override
	public Page<ApiInfo> findByPage(PageReqInfo pageinfo) {

		return apiInfoRespository.findAll(pageinfo.getWhereClause(), pageinfo.getPageable());
	}

	@Override
	public PageInfo queryset(JSONObject requestObj) {
		PageInfo pageInfo = new PageInfo();
		List<ApiInfo> queryset = new ArrayList<ApiInfo>();
		List<ApiInfo> querysetTotal = new ArrayList<ApiInfo>();
		List<Long> apiId = new ArrayList<Long>();
		List<ApiInfoCategory> category = new ArrayList<ApiInfoCategory>();
		// 根据类型查询api
		if (!"".equals(requestObj.getString("apiCategoryid")) && requestObj.getString("apiCategoryid") != null) {
			int apiCategoryid = requestObj.getIntValue("apiCategoryid");
			if (apiCategoryid > 0) {
				category = apiInfoCategoryRespository.queryApicategoryid(apiCategoryid);
			} else if (apiCategoryid == 0) {
				category = apiInfoCategoryRespository.findAll();
			}
			if (category.size() > 0) {
				for (ApiInfoCategory list : category) {
					if (list.getIsenabled() > 0) {
						apiId.add((long) list.getApiid());
					}
				}
			} else {
				apiId.add(0L);
			}

		} else {
			apiId.add(0L);
		}

		if (apiId.size() < 1) {
			return pageInfo;
		}
		int pagenum = requestObj.getIntValue("start");
		int pagesize = requestObj.getIntValue("length");

		if (!"".equals(requestObj.getString("name")) && requestObj.getString("name") != null) {
			String name = requestObj.getString("name");

			queryset = apiInfoRespository.querysetOne(apiId, name, pagenum, pagesize);
			querysetTotal = apiInfoRespository.querysetOnetotal(apiId, name);
		} else {
			queryset = apiInfoRespository.querysetTwo(apiId, pagenum, pagesize);
			querysetTotal = apiInfoRespository.querysetTwototal(apiId);
		}
		pageInfo = new PageInfo((long) querysetTotal.size(), queryset);
		return pageInfo;

	}

	@Transactional
	public JSONObject saveApiSet(JSONObject requestObj, HttpServletRequest request) {
		ApiInfo apiInfo = null;
		JSONObject object = new JSONObject();
		Integer sequence=requestObj.getInteger("sequence");
		SysUser user = (SysUser) request.getSession().getAttribute("userObj");
		try {
			ApiInfo apiName = apiInfoRespository.queryName(requestObj.getString("apiname"));
			ApiInfo apiUri = apiInfoRespository.queryUrl(requestObj.getString("uri"));
			ApiInfo api=null;
			if(sequence!=null) {
				 api = apiInfoService.querysequence(sequence);
			}
			
			//notarize 1.点击确定按钮
			if (!"1".equals(requestObj.getString("notarize"))) {
				// 如果序号有其他api
				if (api != null) {
					if(api.getName()!=apiUri.getName()) {
					object.put("code", 4);
					object.put("message", "当前序号已存在，确定替换【" + api.getAbbreviation() + "】的序号？");
					return object;
					}
				}
			}else {
				ApiInfo apione=null;
				if (requestObj.getString("pageType").equals(EDIT)) {
					sequence=api.getSequence();
					 apione = apiInfoService.findOne(requestObj.getLong("apiid"));
					api.setSequence(apione.getSequence());
					
				}else {
					sequence=api.getSequence();
					api.setSequence(999999);
				}
				
				apiInfoService.save(api);
			}
			if (requestObj.getString("pageType").equals(EDIT)) {

				ApiDetail apiDetail = apiDetailService.queryApiInfo(requestObj.getLong("apiid"));
				if (apiDetail.getStatus() == 1) {
					object.put("code", 3);
					object.put("message", "当前接口为上架状态，无法进行修改");
					return object;
				}

				apiInfo = apiInfoRespository.findOne(requestObj.getLong("apiid"));
				if (apiUri != null) {
					if (!apiUri.getId().equals(requestObj.getLong("apiid"))) {
						if (apiUri.getUri().equals(requestObj.getString("uri"))) {
							object.put("code", 3);
							object.put("message", "接口uri已存在，请重新输入");
							return object;
						}
					}
				}
				if (apiName != null) {
					if (!apiUri.getId().equals(requestObj.getLong("apiid"))) {
						if (apiName.getName().equals(requestObj.getString("apiname"))) {
							object.put("code", 3);
							object.put("message", "接口名称已存在，请重新输入");
							return object;
						}
					}
				}

				JSONArray systemError = requestObj.getJSONArray("systemError");
				JSONArray serviceError = requestObj.getJSONArray("serviceError");
				JSONArray responseBody = requestObj.getJSONArray("responseBody");
				JSONArray requestparameter = requestObj.getJSONArray("requestparameter");
				JSONArray apiScenes = requestObj.getJSONArray("apiScenes");
				if (!"".equals(requestObj.getString("requestparam")) && requestObj.getString("requestparam") != null) {
					String[] requestparam = requestObj.getString("requestparam").split(",");
					for (int i = 0; i < requestparam.length; i++) {
						apiRequestparameterService.delete(Long.parseLong(requestparam[i]));
					}
				}

				if (!"".equals(requestObj.getString("serviceparam")) && requestObj.getString("serviceparam") != null) {
					String[] serviceparam = requestObj.getString("serviceparam").split(",");
					for (int i = 0; i < serviceparam.length; i++) {
						apiServiceerrorcodeService.delete(Long.parseLong(serviceparam[i]));
					}
				}

				if (!"".equals(requestObj.getString("systemparam")) && requestObj.getString("systemparam") != null) {
					String[] systemparam = requestObj.getString("systemparam").split(",");
					for (int i = 0; i < systemparam.length; i++) {
						apiSystemerrorcodeService.delete(Long.parseLong(systemparam[i]));
					}
				}

				if (!"".equals(requestObj.getString("responseparam"))
						&& requestObj.getString("responseparam") != null) {
					String[] responseparam = requestObj.getString("responseparam").split(",");
					for (int i = 0; i < responseparam.length; i++) {
						apiResponsedataService.delete(Long.parseLong(responseparam[i]));
					}
				}
				
				if (!"".equals(requestObj.getString("apiScenesparam"))
						&& requestObj.getString("apiScenesparam") != null) {
					String[] apiScenesparam = requestObj.getString("apiScenesparam").split(",");
					for (int i = 0; i < apiScenesparam.length; i++) {
						apiScenesService.delete(Long.parseLong(apiScenesparam[i]));
					}
				}
				if(apiScenes.size()>0) {
					for(int i=0; i<apiScenes.size();i++) {
						ApiScenes apiScenesparamter=new ApiScenes();
						JSONObject param = apiScenes.getJSONObject(i);
						if (!"".equals(param.getString("id")) && param.getString("id") != null) {
							apiScenesparamter = apiScenesService.findOne(param.getLong("id"));
						}

						apiScenesparamter.setApiid(requestObj.getIntValue("apiid"));
						apiScenesparamter.setTitle(param.getString("title"));
						apiScenesparamter.setRemark(param.getString("remark"));
						apiScenesparamter.setCreatTime(new Date());
						apiScenesparamter.setCreator(user.getUser_Name());
						apiScenesService.save(apiScenesparamter);
					}
				}

				if (requestparameter.size() > 0) {
					for (int i = 0; i < requestparameter.size(); i++) {
						JSONObject param = requestparameter.getJSONObject(i);
						ApiRequestparameter requestparamter = new ApiRequestparameter();
						if (!"".equals(param.getString("id")) && param.getString("id") != null) {
							requestparamter = apiRequestparameterService.findOne(param.getLong("id"));
						}

						requestparamter.setApiid(requestObj.getIntValue("apiid"));
						requestparamter.setName(param.getString("name"));
						requestparamter.setMustfill(param.getString("agree"));
						requestparamter.setRemark(param.getString("remark"));
						requestparamter.setType(param.getString("type"));
						requestparamter.setCreatTime(new Date());
						requestparamter.setCreator(user.getUser_Name());
						apiRequestparameterService.save(requestparamter);
					}
				}
				if (responseBody.size() > 0) {
					for (int i = 0; i < responseBody.size(); i++) {
						ApiResponsedata response = new ApiResponsedata();
						JSONObject responsejson = responseBody.getJSONObject(i);
						if (!"".equals(responsejson.getString("id")) && responsejson.getString("id") != null) {
							response = apiResponsedataService.findOne(responsejson.getLong("id"));
						}

						response.setApiid(requestObj.getIntValue("apiid"));
						response.setName(responsejson.getString("name"));
						response.setMustfill(responsejson.getString("agree"));
						response.setType(responsejson.getString("type"));
						response.setRemark(responsejson.getString("remark"));
						response.setCreatTime(new Date());
						response.setCreator(user.getUser_Name());
						apiResponsedataService.save(response);
					}
				}

				if (serviceError.size() > 0) {
					for (int i = 0; i < serviceError.size(); i++) {
						JSONObject param = serviceError.getJSONObject(i);
						ApiServiceerrorcode service = new ApiServiceerrorcode();
						if (!"".equals(param.getString("id")) && param.getString("id") != null) {
							service = apiServiceerrorcodeService.findOne(param.getLong("id"));
						}
						service.setApiid(requestObj.getIntValue("apiid"));
						service.setErrorcode(param.getString("code"));
						service.setRemark(param.getString("remark"));
						service.setCreatTime(new Date());
						service.setCreator(user.getUser_Name());
						apiServiceerrorcodeService.save(service);
					}
				}

				if (systemError.size() > 0) {
					for (int i = 0; i < systemError.size(); i++) {
						JSONObject param = systemError.getJSONObject(i);
						ApiSystemerrorcode systemerror = new ApiSystemerrorcode();
						if (!"".equals(param.getString("id")) && param.getString("id") != null) {
							systemerror = apiSystemerrorcodeService.findOne(param.getLong("id"));
						}
						systemerror.setApiid(requestObj.getIntValue("apiid"));
						systemerror.setErrorcode(param.getString("code"));
						systemerror.setRemark(param.getString("remark"));
						systemerror.setCreator(user.getUser_Name());
						systemerror.setCreatTime(new Date());
						apiSystemerrorcodeService.save(systemerror);

					}
				}
				
				

			} else {
				JSONArray systemError = requestObj.getJSONArray("systemError");
				JSONArray serviceError = requestObj.getJSONArray("serviceError");
				JSONArray responseBody = requestObj.getJSONArray("responseBody");
				JSONArray requestparameter = requestObj.getJSONArray("requestparameter");
				JSONArray apiScenes = requestObj.getJSONArray("apiScenes");
				
				if(apiScenes!=null) {
					for(int i=0; i<apiScenes.size();i++) {
						ApiScenes apiScenesparamter=new ApiScenes();
						JSONObject param = apiScenes.getJSONObject(i);
						

						apiScenesparamter.setApiid(requestObj.getIntValue("apiid"));
						apiScenesparamter.setTitle(param.getString("title"));
						apiScenesparamter.setRemark(param.getString("remark"));
						apiScenesparamter.setCreatTime(new Date());
						apiScenesparamter.setCreator(user.getUser_Name());
						apiScenesService.save(apiScenesparamter);
					}
				}
				if (requestparameter.size() > 0) {
					for (int i = 0; i < requestparameter.size(); i++) {
						ApiRequestparameter requestparam = new ApiRequestparameter();
						JSONObject param = requestparameter.getJSONObject(i);
						requestparam.setApiid(requestObj.getIntValue("apiid"));
						requestparam.setName(param.getString("name"));
						requestparam.setMustfill(param.getString("agree"));
						requestparam.setType(param.getString("type"));
						requestparam.setRemark(param.getString("remark"));
						requestparam.setCreatTime(new Date());
						requestparam.setCreator(user.getUser_Name());
						apiRequestparameterService.save(requestparam);
					}
				}
				if (responseBody.size() > 0) {
					for (int i = 0; i < responseBody.size(); i++) {
						ApiResponsedata response = new ApiResponsedata();
						JSONObject responsejson = responseBody.getJSONObject(i);
						response.setApiid(requestObj.getIntValue("apiid"));
						response.setName(responsejson.getString("name"));
						response.setMustfill(responsejson.getString("agree"));
						response.setType(responsejson.getString("type"));
						response.setCreatTime(new Date());

						response.setRemark(responsejson.getString("remark"));
						response.setCreator(user.getUser_Name());
						apiResponsedataService.save(response);
					}
				}

				if (serviceError.size() > 0) {
					for (int i = 0; i < serviceError.size(); i++) {
						JSONObject param = serviceError.getJSONObject(i);
						ApiServiceerrorcode service = new ApiServiceerrorcode();
						service.setApiid(requestObj.getIntValue("apiid"));
						service.setErrorcode(param.getString("code"));
						service.setRemark(param.getString("remark"));
						service.setCreatTime(new Date());
						service.setCreator(user.getUser_Name());
						apiServiceerrorcodeService.save(service);
					}
				}

				if (systemError.size() > 0) {
					for (int i = 0; i < systemError.size(); i++) {
						JSONObject param = systemError.getJSONObject(i);
						ApiSystemerrorcode systemerror = new ApiSystemerrorcode();
						systemerror.setApiid(requestObj.getIntValue("apiid"));
						systemerror.setErrorcode(param.getString("code"));
						systemerror.setRemark(param.getString("remark"));
						systemerror.setCreator(user.getUser_Name());
						systemerror.setCreatTime(new Date());
						apiSystemerrorcodeService.save(systemerror);

					}
				}
				apiInfo = new ApiInfo();
				apiInfo.setCreator(user.getUser_Name());
				apiInfo.setCreatTime(new Date());
				if (apiName != null) {
					if (apiName.getName().equals(requestObj.getString("apiname"))) {
						object.put("code", 3);
						object.put("message", "接口名称已存在，请重新输入");
						return object;
					}
				}

				if (apiUri != null) {
					if (apiUri.getUri().equals(requestObj.getString("uri"))) {
						object.put("code", 3);
						object.put("message", "接口uri已存在，请重新输入");
						return object;
					}
				}

			}
			apiInfo.setModifier(user.getUser_Name());
			apiInfo.setModifyTime(new Date());
			apiInfo.setId(requestObj.getLong("apiid"));
			apiInfo.setName(requestObj.getString("apiname"));
			apiInfo.setUri(requestObj.getString("uri"));
			apiInfo.setStatus(requestObj.getIntValue("status"));
			apiInfo.setMethod(requestObj.getIntValue("method"));
			apiInfo.setProtocol(requestObj.getIntValue("protocol"));
			apiInfo.setResponsedata(requestObj.getString("responsedata"));
			apiInfo.setRequestdata(requestObj.getString("requestdata"));
			apiInfo.setRequest(requestObj.getString("request"));
			apiInfo.setFormat(requestObj.getIntValue("format"));
			if(sequence==null) {
				apiInfo.setSequence(999999);
			}else {
				apiInfo.setSequence(sequence);
			}
			
			apiInfo.setAbbreviation(requestObj.getString("abbreviation"));
			apiInfoRespository.saveAndFlush(apiInfo);

			JSONArray array = requestObj.getJSONArray("setting");
			// 将所有选中的值设为无效
			apiInfoCategoryService.updateCategory(apiInfo.getId().intValue());
			for (int i = 0; i < array.size(); i++) {
				JSONObject json = array.getJSONObject(i);
				if (json.getString("name").equals("category")) {

					ApiInfoCategory apiInfoCategory = apiInfoCategoryService.queryCategory(json.getIntValue("value"),
							apiInfo.getId().intValue());
					if (apiInfoCategory != null) {
						apiInfoCategory.setIsenabled(1);
					} else {
						apiInfoCategory = new ApiInfoCategory();
						apiInfoCategory.setIsenabled(1);
						apiInfoCategory.setApicategoryid(json.getIntValue("value"));
						apiInfoCategory.setApiid(apiInfo.getId().intValue());
					}
					apiInfoCategoryService.save(apiInfoCategory);
				}
			}
			ApiDetail apiDetail = apiDetailService.queryApiInfo(apiInfo.getId());
			if (apiDetail != null) {
				apiDetail.setCreatTime(new Date());
				apiDetail.setCreator(user.getUser_Name());
				apiDetail.setModifier(user.getUser_Name());
				apiDetail.setModifyTime(new Date());
				apiDetail.setName(apiInfo.getName());
				apiDetail.setApiid(apiInfo);
				apiDetail.setStatus(0);
				apiDetail.setAbbreviation(apiInfo.getAbbreviation());
				apiDetailService.edit(apiDetail);
			} else {
				apiDetail = new ApiDetail();
				apiDetail.setCreatTime(new Date());
				apiDetail.setCreator(user.getUser_Name());
				apiDetail.setModifier(user.getUser_Name());
				apiDetail.setModifyTime(new Date());
				apiDetail.setName(apiInfo.getName());
				apiDetail.setApiid(apiInfo);
				apiDetail.setStatus(0);
				apiDetail.setAbbreviation(apiInfo.getAbbreviation());
				apiDetailService.save(apiDetail);
			}

			object.put("apiInfo", apiInfo);
			object.put("code", 1);
			object.put("message", "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public ApiInfo queryUrl(String apiurl) {
		// TODO Auto-generated method stub
		return apiInfoRespository.queryUrl(apiurl);
	}

	@Override
	public ApiInfo querysequence(int sequence) {
		// TODO Auto-generated method stub
		return apiInfoRespository.querysequence(sequence);
	}

}