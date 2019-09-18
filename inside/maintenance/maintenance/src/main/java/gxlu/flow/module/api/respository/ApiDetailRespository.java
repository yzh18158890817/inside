package gxlu.flow.module.api.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiDetail;

@Repository
public interface ApiDetailRespository extends JpaRepository<ApiDetail, Long>,JpaSpecificationExecutor<ApiDetail>{
	@Query("select a from ApiDetail a where a.apiid.id=:apiid")
	ApiDetail queryApiInfo(@Param("apiid")Long apiid);

	


}
