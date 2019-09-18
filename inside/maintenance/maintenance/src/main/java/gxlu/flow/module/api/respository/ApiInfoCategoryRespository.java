package gxlu.flow.module.api.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiInfoCategory;

@Repository
public interface ApiInfoCategoryRespository extends JpaRepository<ApiInfoCategory, Long>,JpaSpecificationExecutor<ApiInfoCategory>{
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update api_r_api_category a set a.isenabled=0 where a.apiid=?1",nativeQuery = true)
	void updateCategory(@Param("apiid")int apiid);
//	@Query(value="select s from Sys_provinces s where s.type=:type or s.type=:num ")
//	List<Sys_provinces> getprovinces(@Param("type")int type,@Param("num")int num);
	
	@Query(value="select s from ApiInfoCategory s where s.apicategoryid=:apicategoryid and s.apiid=:apiid")
	ApiInfoCategory queryCategory(@Param("apicategoryid")int apicategoryid,@Param("apiid")int apiid);
	
	@Query(value="select s from ApiInfoCategory s where s.isenabled=1 and s.apiid=:apiid")
	List<ApiInfoCategory> queryIsenabled(@Param("apiid")int apiid);

	@Query(value="select a.* from api_r_api_category a where a.apicategoryid=:apiCategoryid",nativeQuery = true)
	List<ApiInfoCategory> queryApicategoryid(@Param("apiCategoryid")int apiCategoryid);
	
	@Query(value="select a.* from api_r_api_category a where a.apicategoryid=:categoryid",nativeQuery = true)
	List<ApiInfoCategory> queryApiInfoCategory(@Param("categoryid")int categoryid);
	
	

}
