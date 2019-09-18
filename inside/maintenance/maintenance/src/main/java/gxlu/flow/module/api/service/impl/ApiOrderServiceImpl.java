package gxlu.flow.module.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiOrder;
import gxlu.flow.module.api.entity.ApiRequestlog;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.respository.ApiOrderRespository;
import gxlu.flow.module.api.respository.ApiRapiMembershipRespository;
import gxlu.flow.module.api.respository.ApiRequestlogRespository;
import gxlu.flow.module.api.service.ApiCategoryService;
import gxlu.flow.module.api.service.ApiOrderService;
import gxlu.flow.module.api.service.ApiRequestlogService;

@Service
public class ApiOrderServiceImpl implements ApiOrderService{
	
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
	public ApiOrder findOne(Long id) {
		// TODO Auto-generated method stub
		return apiOrderRespository.findOne(id);
	}

	@Override
	public List<ApiOrder> findAll() {
		// TODO Auto-generated method stub
		return apiOrderRespository.findAll();
	}

	@Override
	public void save(ApiOrder t) {
		// TODO Auto-generated method stub
		apiOrderRespository.save(t);
	}

	@Override
	public void edit(ApiOrder t) {
		// TODO Auto-generated method stub
		apiOrderRespository.saveAndFlush(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		apiOrderRespository.delete(id);
	}

	@Override
	public Page<ApiOrder> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiOrderRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	

	
	
}