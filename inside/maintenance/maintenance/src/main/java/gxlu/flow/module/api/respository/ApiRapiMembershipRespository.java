package gxlu.flow.module.api.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiRapiMembership;

@Repository
public interface ApiRapiMembershipRespository extends JpaRepository<ApiRapiMembership, Long>,JpaSpecificationExecutor<ApiRapiMembership>{
	@Query(value="select SUM(a.applytimes) from ApiRapiMembership a where a.apiurl.id=:apaiid and a.membershipid=:memberid")
	int queryTotal(@Param("apaiid")Long apaiid,@Param("memberid")int memberid);
	
	@Query(value="select SUM(a.usedtimes) from ApiRapiMembership a where a.apiurl.id=:apaiid and a.membershipid=:memberid")
	int queryUsedtimes(@Param("apaiid")Long apaiid, @Param("memberid")int memberid);
	
	@Query(value="select SUM(a.remaindertimes) from ApiRapiMembership a where a.apiurl.id=:apaiid and a.membershipid=:memberid")
	int queryRemaindertimes(@Param("apaiid")Long apaiid, @Param("memberid")int memberid);
	
	@Query(value="select a from ApiRapiMembership a where a.membershipid=:memberid order by a.apiurl.sequence")
	List<ApiRapiMembership> queryApiInfo(@Param("memberid") int memberid);



}
