package gxlu.flow.module.api.service;

import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiCategory;

public interface ApiCategoryService extends BaseService<ApiCategory>{

	List<ApiCategory> queryCategory();

	

}
