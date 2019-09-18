package gxlu.flow.module.api.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiCategory;

@Repository
public interface ApiCategoryRespository extends JpaRepository<ApiCategory, Long>,JpaSpecificationExecutor<ApiCategory>{
	@Query(value="select a.* from api_category a order by creattime desc",nativeQuery = true)
	List<ApiCategory> queryCategory();
//	@Query(value="select s from Sys_provinces s where s.type=:type or s.type=:num ")
//	List<Sys_provinces> getprovinces(@Param("type")int type,@Param("num")int num);


}
