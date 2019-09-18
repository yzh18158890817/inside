package gxlu.flow.module.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import gxlu.flow.framework.page.PageReqInfo;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiOrder;
import gxlu.flow.module.api.entity.ApiRapiMembership;
import gxlu.flow.module.api.entity.ApiRequestlog;
import gxlu.flow.module.api.respository.ApiCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoCategoryRespository;
import gxlu.flow.module.api.respository.ApiInfoRespository;
import gxlu.flow.module.api.respository.ApiOrderRespository;
import gxlu.flow.module.api.respository.ApiRapiMembershipRespository;
import gxlu.flow.module.api.respository.ApiRequestlogRespository;
import gxlu.flow.module.api.service.ApiCategoryService;
import gxlu.flow.module.api.service.ApiOrderService;
import gxlu.flow.module.api.service.ApiRapiMembershipService;
import gxlu.flow.module.api.service.ApiRequestlogService;

@Service
public class ApiRapiMembershipServiceImpl implements ApiRapiMembershipService{
	
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
	public ApiRapiMembership findOne(Long id) {
		// TODO Auto-generated method stub
		return apiRapiMembershipRespository.findOne(id);
	}

	@Override
	public List<ApiRapiMembership> findAll() {
		// TODO Auto-generated method stub
		return apiRapiMembershipRespository.findAll();
	}

	@Override
	public void save(ApiRapiMembership t) {
		apiRapiMembershipRespository.save(t);
		
	}

	@Override
	public void edit(ApiRapiMembership t) {
		// TODO Auto-generated method stub
		apiRapiMembershipRespository.saveAndFlush(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		apiRapiMembershipRespository.delete(id);
	}

	@Override
	public Page<ApiRapiMembership> findByPage(PageReqInfo pageinfo) {
		// TODO Auto-generated method stub
		return apiRapiMembershipRespository.findAll(pageinfo.getWhereClause(),pageinfo.getPageable());
	}

	@Override
	public int queryTotal(Long apaiid, Long memberid) {
		// TODO Auto-generated method stub
		return apiRapiMembershipRespository.queryTotal(apaiid, memberid.intValue());
	}

	@Override
	public int queryUsedtimes(Long apaiid, Long memberid) {
		// TODO Auto-generated method stub
		 return  apiRapiMembershipRespository.queryUsedtimes(apaiid, memberid.intValue());
	}

	@Override
	public int queryRemaindertimes(Long apaiid, Long memberid) {
		// TODO Auto-generated method stub
		 return apiRapiMembershipRespository.queryRemaindertimes(apaiid, memberid.intValue());
	}

	@Override
	public List<ApiRapiMembership> queryApiInfo(int memberid) {
		
		return apiRapiMembershipRespository.queryApiInfo(memberid);
	}

	
}