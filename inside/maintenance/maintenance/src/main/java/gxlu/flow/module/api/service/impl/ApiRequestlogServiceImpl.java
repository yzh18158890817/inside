package gxlu.flow.module.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiRequestlog;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.respository.ApiOrderRespository;
import gxlu.flow.module.api.respository.ApiRapiMembershipRespository;
import gxlu.flow.module.api.respository.ApiRequestlogRespository;
import gxlu.flow.module.api.service.ApiCategoryService;
import gxlu.flow.module.api.service.ApiRequestlogService;

@Service
public class ApiRequestlogServiceImpl implements ApiRequestlogService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;
	
	@Autowired
	private ApiRequestlogRespository apiRequestlogRespository;
	
	@Autowired
	private ApiRapiMembershipRespository apiRapiMembershipRespository;
	
	@Autowired
	private  ApiOrderRespository apiOrderRespository;

	@Override
	public ApiRequestlog findOne(Long id) {
		// TODO Auto-generated method stub
		return apiRequestlogRespository.findOne(id);
	}

	@Override
	public List<ApiRequestlog> findAll() {
		// TODO Auto-generated method stub
		return apiRequestlogRespository.findAll();
	}

	@Override
	public void save(ApiRequestlog t) {
		apiRequestlogRespository.save(t);
		
	}

	@Override
	public void edit(ApiRequestlog t) {
		apiRequestlogRespository.saveAndFlush(t);
		
	}

	@Override
	public void delete(Long id) {
		apiRequestlogRespository.delete(id);
		
	}

	@Override
	public Page<ApiRequestlog> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiRequestlogRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	
}