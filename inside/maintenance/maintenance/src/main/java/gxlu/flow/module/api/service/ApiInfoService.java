package gxlu.flow.module.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.framework.page.PageInfo;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiRapiMembership;

public interface ApiInfoService extends BaseService<ApiInfo>{
	
	PageInfo queryset(JSONObject requestObj);

	JSONObject saveApiSet(JSONObject requestObj, HttpServletRequest request);

	ApiInfo queryUrl(String apiurl);

	ApiInfo querysequence(int sequence);

	

}
