package gxlu.flow.module.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.service.ApiCategoryService;

@Service
public class ApiCategoryServiceImpl implements ApiCategoryService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;

	@Override
	public ApiCategory findOne(Long id) {
		// TODO Auto-generated method stub
		return apiCategoryRespository.findOne(id);
	}

	@Override
	public List<ApiCategory> findAll() {
		// TODO Auto-generated method stub
		return apiCategoryRespository.findAll();
	}

	@Override
	public void save(ApiCategory t) {
		apiCategoryRespository.save(t);
		
	}

	@Override
	public void edit(ApiCategory t) {
		apiCategoryRespository.saveAndFlush(t);
		
	}

	@Override
	public void delete(Long id) {
		apiCategoryRespository.delete(id);
		
	}

	@Override
	public Page<ApiCategory> findByPage(PageReqInfo pageinfo) {
		
		return apiCategoryRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	@Override
	public List<ApiCategory> queryCategory() {
		
		return apiCategoryRespository.queryCategory();
	}
	
}