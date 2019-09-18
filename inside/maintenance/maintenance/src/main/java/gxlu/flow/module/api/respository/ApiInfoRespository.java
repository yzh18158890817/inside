package gxlu.flow.module.api.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiInfo;
import gxlu.flow.module.api.entity.ApiRapiMembership;

@Repository
public interface ApiInfoRespository extends JpaRepository<ApiInfo, Long>,JpaSpecificationExecutor<ApiInfo>{
	
	
	@Query(value="select a.* from api_info a where a.id in (:apiId) and a.name like CONCAT('%',:name,'%') order by a.sequence,status asc,modifytime desc,CONVERT (a.name USING gbk) COLLATE gbk_chinese_ci ASC LIMIT :pagenum,:pagesize ",nativeQuery = true)
	List<ApiInfo> querysetOne(@Param("apiId")List<Long>apiId,@Param("name")String name, @Param("pagenum")int pagenum, @Param("pagesize")int pagesize);
	
	@Query(value="select a.* from api_info a where a.id in (:apiId) order by a.sequence,a.status asc,a.modifytime desc,CONVERT (a.name USING gbk) COLLATE gbk_chinese_ci ASC LIMIT :pagenum,:pagesize ",nativeQuery = true)
	List<ApiInfo> querysetTwo(@Param("apiId")List<Long>apiId,@Param("pagenum") int pagenum, @Param("pagesize") int pagesize);
	
	@Query(value="select a from ApiInfo a where a.uri=:apiurl")
	ApiInfo queryUrl(@Param("apiurl")String apiurl);
	
	@Query(value="select a from ApiInfo a where a.name=:name")
	ApiInfo queryName(@Param("name")String name);
	
	@Query(value="select a.* from api_info a where a.id in (:apiId) and a.name like CONCAT('%',:name,'%') order by status asc,modifytime desc,CONVERT (a.name USING gbk) COLLATE gbk_chinese_ci ASC ",nativeQuery = true)
	List<ApiInfo> querysetOnetotal(@Param("apiId")List<Long> apiId, @Param("name")String name);
	
	@Query(value="select a.* from api_info a where a.id in (:apiId) order by a.status asc,a.modifytime desc,CONVERT (a.name USING gbk) COLLATE gbk_chinese_ci ASC",nativeQuery = true)
	List<ApiInfo> querysetTwototal(@Param("apiId")List<Long> apiId);
	
	@Query(value="select a.* from api_info a where a.sequence=:sequence",nativeQuery = true)
	ApiInfo querysequence(@Param("sequence")int sequence);
	
	
//	@Query(value="select s from Sys_provinces s where s.type=:type or s.type=:num ")
//	List<Sys_provinces> getprovinces(@Param("type")int type,@Param("num")int num);

}
