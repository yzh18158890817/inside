package gxlu.flow.module.api.service;

import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiRequestparameter;

public interface ApiRequestparameterService extends BaseService<ApiRequestparameter>{

	List<ApiRequestparameter> queryApi(Long id);


	

}
