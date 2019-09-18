package gxlu.flow.module.api.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiResponsedata;

@Repository
public interface ApiResponsedataRespository extends JpaRepository<ApiResponsedata, Long>,JpaSpecificationExecutor<ApiResponsedata>{
	@Query(value="select a from ApiResponsedata a where a.apiid=:id")
	List<ApiResponsedata> queryApi(@Param("id")int id);
	
	
//	@Query(value="select s from Sys_provinces s where s.type=:type or s.type=:num ")
//	List<Sys_provinces> getprovinces(@Param("type")int type,@Param("num")int num);

}
