package gxlu.flow.module.api.service;

import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiRequestparameter;
import gxlu.flow.module.api.entity.ApiServiceerrorcode;

public interface ApiServiceerrorcodeService extends BaseService<ApiServiceerrorcode>{

	List<ApiServiceerrorcode> queryApi(Long id);

}
