package gxlu.flow.module.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiDetail;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiDetailRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.service.ApiCategoryService;
import gxlu.flow.module.api.service.ApiDetailService;

@Service
public class ApiDetailServiceImpl implements ApiDetailService{
	
	@Autowired
	private ApiInfoRespository apiInfoRespository;
	 
	
	@Autowired
	private ApiCategoryRespository apiCategoryRespository;
	
	@Autowired
	private ApiInfoCategoryRespository apiInfoCategoryRespository;
	
	@Autowired
	private ApiDetailRespository apiDetailRespository;

	@Override
	public ApiDetail findOne(Long id) {
		// TODO Auto-generated method stub
		return apiDetailRespository.findOne(id);
	}

	@Override
	public List<ApiDetail> findAll() {
		// TODO Auto-generated method stub
		return apiDetailRespository.findAll();
	}

	@Override
	public void save(ApiDetail t) {
		apiDetailRespository.save(t);
		
	}

	@Override
	public void edit(ApiDetail t) {
		apiDetailRespository.saveAndFlush(t);
		
	}

	@Override
	public void delete(Long id) {
		apiDetailRespository.delete(id);
		
	}

	@Override
	public Page<ApiDetail> findByPage(PageReqInfo pageinfo) {
		
		return apiDetailRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	@Override
	public ApiDetail queryApiInfo(Long apiid) {
		// TODO Auto-generated method stub
		return apiDetailRespository.queryApiInfo(apiid);
	}

	
}