package gxlu.flow.module.api.service;

import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiResponsedata;

public interface ApiResponsedataService extends BaseService<ApiResponsedata>{

	List<ApiResponsedata> queryApi(Long id);


}
