package gxlu.flow.module.api.service;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiDetail;

public interface ApiDetailService extends BaseService<ApiDetail>{

	ApiDetail queryApiInfo(Long apiid);

}
