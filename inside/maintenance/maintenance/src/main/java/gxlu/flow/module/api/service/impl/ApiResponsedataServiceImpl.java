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
import gxlu.flow.module.api.entity.ApiResponsedata;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.respository.ApiRequestparameterRespository;
import gxlu.flow.module.api.respository.ApiResponsedataRespository;
import gxlu.flow.module.api.service.ApiInfoService;
import gxlu.flow.module.api.service.ApiRequestparameterService;
import gxlu.flow.module.api.service.ApiResponsedataService;

@Service
public class ApiResponsedataServiceImpl implements ApiResponsedataService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;
	
	@Autowired
	private ApiResponsedataRespository apiResponsedataRespository;

	@Override
	public ApiResponsedata findOne(Long id) {
		// TODO Auto-generated method stub
		return apiResponsedataRespository.findOne(id);
	}

	@Override
	public List<ApiResponsedata> findAll() {
		// TODO Auto-generated method stub
		return apiResponsedataRespository.findAll();
	}

	@Override
	public void save(ApiResponsedata t) {
		apiResponsedataRespository.save(t);
		
	}

	@Override
	public void edit(ApiResponsedata t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		apiResponsedataRespository.delete(id);
		
	}

	@Override
	public Page<ApiResponsedata> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiResponsedataRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	@Override
	public List<ApiResponsedata> queryApi(Long id) {
		// TODO Auto-generated method stub
		return apiResponsedataRespository.queryApi(id.intValue());
	}

}