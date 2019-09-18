package gxlu.flow.module.api.service;
import java.util.List;

import gxlu.flow.framework.base.service.BaseService;
import gxlu.flow.module.api.entity.ApiInfoCategory;

public interface ApiInfoCategoryService extends BaseService<ApiInfoCategory>{
	/**
	 * 重置
	 * @param intValue
	 */
	void updateCategory(int apiid);
	
	/**
	 * 查询之前是否保存过分类
	 * @param intValue
	 * @param apiid
	 * @return
	 */
	ApiInfoCategory queryCategory(int apicategoryid, int apiid);
	
	/**
	 * 查询api有效的类
	 * @param intValue
	 * @return
	 */
	List<ApiInfoCategory> queryIsenabled(int apiid);
	
	/**
	 * 根据分类id查询 
	 * @param long1
	 * @return
	 */
	List<ApiInfoCategory> queryApiCategory(Long categoryid);
	
	/**
	 * 根据分类id查询有效的接口
	 * @param long1
	 * @return
	 */
	List<ApiInfoCategory> queryApiInfoCategory(Long categoryid);
	
	
}
