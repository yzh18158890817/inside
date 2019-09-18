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
import gxlu.flow.module.api.entity.ApiServiceerrorcode;
import gxlu.flow.module.api.entity.ApiSystemerrorcode;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.respository.ApiRequestparameterRespository;
import gxlu.flow.module.api.respository.ApiResponsedataRespository;
import gxlu.flow.module.api.respository.ApiServiceerrorcodeRespository;
import gxlu.flow.module.api.respository.ApiSystemerrorcodeRespository;
import gxlu.flow.module.api.service.ApiInfoService;
import gxlu.flow.module.api.service.ApiRequestparameterService;
import gxlu.flow.module.api.service.ApiResponsedataService;
import gxlu.flow.module.api.service.ApiServiceerrorcodeService;
import gxlu.flow.module.api.service.ApiSystemerrorcodeService;

@Service
public class ApiSystemerrorcodeServiceImpl implements ApiSystemerrorcodeService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;
	
	@Autowired
	private ApiResponsedataRespository apiResponsedataRespository;
	
	@Autowired
	private ApiServiceerrorcodeRespository apiServiceerrorcodeRespository;
	
	@Autowired
	private  ApiSystemerrorcodeRespository  apiSystemerrorcodeRespository;
	

	@Override
	public List<ApiSystemerrorcode> queryApi(Long id) {
		// TODO Auto-generated method stub
		return apiSystemerrorcodeRespository.queryApi(id.intValue());
	}


	@Override
	public ApiSystemerrorcode findOne(Long id) {
		// TODO Auto-generated method stub
		return apiSystemerrorcodeRespository.findOne(id);
	}


	@Override
	public List<ApiSystemerrorcode> findAll() {
		// TODO Auto-generated method stub
		return apiSystemerrorcodeRespository.findAll();
	}


	@Override
	public void save(ApiSystemerrorcode t) {
		apiSystemerrorcodeRespository.save(t);
		
	}


	@Override
	public void edit(ApiSystemerrorcode t) {
		apiSystemerrorcodeRespository.saveAndFlush(t);
		
	}


	@Override
	public void delete(Long id) {
		apiSystemerrorcodeRespository.delete(id);
		
	}


	@Override
	public Page<ApiSystemerrorcode> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiSystemerrorcodeRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}


	
}