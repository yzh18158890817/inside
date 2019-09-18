package gxlu.flow.module.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiRequestparameter;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.respository.ApiRequestparameterRespository;
import gxlu.flow.module.api.service.ApiInfoService;
import gxlu.flow.module.api.service.ApiRequestparameterService;

@Service
public class ApiRequestparameterServiceImpl implements ApiRequestparameterService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;
	
	@Autowired
	private ApiRequestparameterRespository apiRequestparameterRespository;

	@Override
	public ApiRequestparameter findOne(Long id) {
		// TODO Auto-generated method stub
		return apiRequestparameterRespository.findOne(id);
	}

	@Override
	public List<ApiRequestparameter> findAll() {
		// TODO Auto-generated method stub
		return apiRequestparameterRespository.findAll();
	}

	@Override
	public void save(ApiRequestparameter t) {
		apiRequestparameterRespository.save(t);
		
	}

	@Override
	public void edit(ApiRequestparameter t) {
		apiRequestparameterRespository.saveAndFlush(t);
		
	}

	@Override
	public void delete(Long id) {
		apiRequestparameterRespository.delete(id);
		
	}

	@Override
	public Page<ApiRequestparameter> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiRequestparameterRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	@Override
	public List<ApiRequestparameter> queryApi(Long id) {
		// TODO Auto-generated method stub
		return apiRequestparameterRespository.queryApi(id.intValue());
	}
	
	
	
}