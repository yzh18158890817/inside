package gxlu.flow.module.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiInfoCategory;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.service.ApiInfoCategoryService;

@Service
public class ApiInfoCategoryServiceImpl implements ApiInfoCategoryService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;
	@Override
	public ApiInfoCategory findOne(Long id) {
		// TODO Auto-generated method stub
		return apiInfoCategoryRespository.findOne(id);
	}

	@Override
	public List<ApiInfoCategory> findAll() {
		// TODO Auto-generated method stub
		return apiInfoCategoryRespository.findAll();
	}

	@Override
	public void save(ApiInfoCategory t) {
		apiInfoCategoryRespository.save(t);
		
	}

	@Override
	public void edit(ApiInfoCategory t) {
		apiInfoCategoryRespository.saveAndFlush(t);
		
	}

	@Override
	public void delete(Long id) {
		apiInfoCategoryRespository.delete(id);
		
	}

	@Override
	public Page<ApiInfoCategory> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiInfoCategoryRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	@Override
	public void updateCategory(int apiid) {
		apiInfoCategoryRespository.updateCategory(apiid);
		
	}

	@Override
	public ApiInfoCategory queryCategory(int apicategoryid, int apiid) {
		
		return apiInfoCategoryRespository.queryCategory(apicategoryid,apiid);
	}

	@Override
	public List<ApiInfoCategory> queryIsenabled(int apiid) {
		
		return apiInfoCategoryRespository.queryIsenabled(apiid);
	}

	@Override
	public List<ApiInfoCategory> queryApiCategory(Long categoryid) {
		
		return apiInfoCategoryRespository.queryApicategoryid(categoryid.intValue());
	}

	@Override
	public List<ApiInfoCategory> queryApiInfoCategory(Long categoryid) {
	
		return apiInfoCategoryRespository.queryApiInfoCategory(categoryid.intValue());
	}

	
	
}