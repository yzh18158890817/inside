package gxlu.flow.module.api.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gxlu.flow.module.api.entity.ApiRequestlog;

@Repository
public interface ApiRequestlogRespository extends JpaRepository<ApiRequestlog, Long>,JpaSpecificationExecutor<ApiRequestlog>{



}
