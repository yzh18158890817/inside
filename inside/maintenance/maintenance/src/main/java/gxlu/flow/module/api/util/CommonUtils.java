package gxlu.flow.module.api.util;

import gxlu.flow.framework.constant.SysConst;
import gxlu.flow.framework.util.JsonComparator;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CommonUtils {

	public static String convertUploadFilePath(String path) {
		File path1 = new File(path);
		path = path1.getParent() + File.separator + SysConst.UPLOADFILES + File.separator;
		return path;
	}
	//根据指定字段排序
	 public static void sortJsonArrayByDate(JSONArray array,String dateName){
	    List<JSONObject> list = new ArrayList<JSONObject> ();
	        JSONObject jsonObj = null;
	        for (int i = 0; i < array.size(); i++) {
	            jsonObj = (JSONObject)array.get(i);
	            list.add(jsonObj);
	        }
	        //排序操作
	        JsonComparator pComparator =  new JsonComparator(dateName);
	        Collections.sort(list, pComparator);
	        
	        //把数据放回去 
	        array.clear();
	        for (int i = 0; i < list.size(); i++) {
	            jsonObj = list.get(i);
	            array.add(jsonObj);
	        }
	   }
}
