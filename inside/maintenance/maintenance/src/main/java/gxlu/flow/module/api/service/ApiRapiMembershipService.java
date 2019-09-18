package gxlu.flow.module.api.service;

import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiRapiMembership;

public interface ApiRapiMembershipService extends BaseService<ApiRapiMembership>{
	//总次数
	int queryTotal(Long apaiid, Long memberid);
	//已使用次数
	int queryUsedtimes(Long apaiid, Long memberid);
	//剩下次数
	int queryRemaindertimes(Long apaiid, Long memberid);
	/*
	 * 通过会员id查询配置api
	 */
	List<ApiRapiMembership> queryApiInfo(int memberid);


	

}
