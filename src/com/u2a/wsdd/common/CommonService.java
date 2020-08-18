package com.u2a.wsdd.common;

import java.util.ArrayList;
import java.util.List;

import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.u2a.framework.util.PropUtils;

public class CommonService {

	/** 
	 * @Description 
	 * @param @param fileName  properties文件名
	 * @param @param key	   properties文件中的Key
	 * @return IMap   
	 * @author panghaichao
	 * @date 2012年12月27日 10:10:45
	 */ 
		
	public static IMap addField(String fileName,String key){
		PropUtils p=new PropUtils(fileName);
		String field =p.getProperty(key);
		String[] arr = field.split("#");
		IMap data = new DataMap();
		 for (int i = 0; i < arr.length; i++) {
            String[] brr = arr[i].split("=");
            for (int j = 0; j < brr.length; j++) {
                data.put(brr[0], brr[1]);
            }
		 }
		 return data;
	}
	
	/** property 属性类型 0文本框 1下拉菜单 2Textarea文本框
	 * type all 所有字段 one 1种字段
	 * @Description 从properties文件中获取所有字段
	 * @return List<IMap>   
	 */ 
	@SuppressWarnings("unchecked")
	public static List<IMap> getAllFields(String fileName,String property,String type){
		final List<IMap> fieldList = new ArrayList<IMap>();
		final List<IMap> fieldTextList = new ArrayList<IMap>();
		final List<IMap> fieldTextareaList = new ArrayList<IMap>();
		fieldList.add(CommonService.addField(fileName,"field_one"));
		fieldList.add(CommonService.addField(fileName,"field_two"));
		fieldList.add(CommonService.addField(fileName,"field_three"));
		fieldList.add(CommonService.addField(fileName,"field_four"));
		fieldList.add(CommonService.addField(fileName,"field_five"));
		fieldList.add(CommonService.addField(fileName,"field_six"));
		fieldList.add(CommonService.addField(fileName,"seven"));
		fieldList.add(CommonService.addField(fileName,"eight"));
		fieldList.add(CommonService.addField(fileName,"nine"));
		fieldList.add(CommonService.addField(fileName,"ten"));
		fieldList.add(CommonService.addField(fileName,"eleven"));
		fieldList.add(CommonService.addField(fileName,"twelve"));
		fieldList.add(CommonService.addField(fileName,"thirteen"));
		fieldList.add(CommonService.addField(fileName,"fourteen"));
		fieldList.add(CommonService.addField(fileName,"fifteen"));
		fieldList.add(CommonService.addField(fileName,"sixteen"));
		fieldList.add(CommonService.addField(fileName,"seveteen"));
		fieldList.add(CommonService.addField(fileName,"eighteen"));
		fieldList.add(CommonService.addField(fileName,"nineteen"));
		fieldList.add(CommonService.addField(fileName,"twenty"));
		fieldList.add(CommonService.addField(fileName,"twenty_one"));
		fieldList.add(CommonService.addField(fileName,"twenty_two"));
		fieldList.add(CommonService.addField(fileName,"twenty_three"));
		fieldList.add(CommonService.addField(fileName,"twenty_four"));
		fieldList.add(CommonService.addField(fileName,"twenty_five"));
		fieldList.add(CommonService.addField(fileName,"twenty_six"));
		fieldList.add(CommonService.addField(fileName,"twenty_seven"));
		fieldList.add(CommonService.addField(fileName,"twenty_eight"));
		fieldList.add(CommonService.addField(fileName,"twenty_nine"));
		fieldList.add(CommonService.addField(fileName,"thirty"));
		if("one".equals(type)){
			for(int i=0;fieldList.size()>i;i++){
				final IMap field=fieldList.get(i);
				if("1".equals(field.get("isQy"))){
					if("2".equals(field.get("property"))){
						fieldTextareaList.add(field);
				    }else{
				    	fieldTextList.add(field);
				    }
			  }
			}
			if("2".equals(property)){
				return fieldTextareaList;
		    }else{
		    	return fieldTextList;
		    }
		}else{
			return fieldList;
		}
	}
	
	/** 
	 * @Description 从新载入properties文件中获取所有字段
	 * @param @param allField
	 * @param @return    
	 * @return List   
	 */ 
	public static List getFieldList(String fileName,List<IMap> allField,IMap in){
		List list = new ArrayList();
		List<IMap> field = allField;
		List<IMap> allfield = getAllFields(fileName,null,"all");
		for (int i = 0; i < field.size(); i++) {
			IMap fieldMap = (IMap)field.get(i);
			    for(int a=0;allfield.size()>a;a++){
			    	if(fieldMap.get("name").equals(allfield.get(a).get("name"))){
						fieldMap.put("value",in.get(allfield.get(a).get("name")));
						list.add(fieldMap);
					}
			    }
			}
		return list;
	}
}
