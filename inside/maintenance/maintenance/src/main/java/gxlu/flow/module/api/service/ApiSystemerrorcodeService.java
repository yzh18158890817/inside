package gxlu.flow.module.api.service;

import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiCategory;
import gxlu.flow.module.api.entity.ApiRequestparameter;
import gxlu.flow.module.api.entity.ApiServiceerrorcode;
import gxlu.flow.module.api.entity.ApiSystemerrorcode;

public interface ApiSystemerrorcodeService extends BaseService<ApiSystemerrorcode>{

	List<ApiSystemerrorcode> queryApi(Long id);

}
