package com.tiexue.mcp.manage.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TransToPage {

	/**
	 * 转化成下拉框options
	 * @param isDefault
	 * @param key
	 * @param maps
	 * @return
	 */
	public static String mapToOptions(boolean isDefault,int key,Map<Integer,String> maps){
		String result="";
		if(isDefault){
			result += " <option value=''></option>";
		}
		for (Map.Entry<Integer,String> map : maps.entrySet()) {
			result += String.format(" <option value='%s' %s >%s</option>", map.getKey(), map.getKey() == key ? "selected=selected" : "", map.getValue());
		}
		return result;
	}
	

}
