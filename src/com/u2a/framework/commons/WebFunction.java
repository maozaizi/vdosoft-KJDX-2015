package com.u2a.framework.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.brick.dao.IBaseDAO;
import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.brick.manager.AppCaller;
import com.brick.manager.BeanFactory;
import com.brick.manager.ContextUtil;
import com.u2a.framework.util.HelperApp;
import com.u2a.wsdd.common.Constants;
@SuppressWarnings("unchecked")
public class WebFunction {
	 
	/**
	 * @Description 根据编码获取列表
	 * @param dataItemCode编码
	 * @return IMap<String,Object>
	 * @author panghaichao
	 * @date Feb 28, 2012 3:53:37 PM
	 */

	public static List<IMap> getDataDictionary(String dataItemCode,IBaseDAO db) {
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		IMap listBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemCode", dataItemCode);
		List<IMap> listId = db.getList(tempBean, "getIdByCode","com.DataItemBaseInfo");
		List<IMap> list = null;
		if(listId.size()>0){
			String dataItemId=(String)listId.get(0).get("dataItemId");
			listBean.put("dataItemId", dataItemId);
			list = db.getList(listBean, "getChildsById","com.DataItemBaseInfo");
		}
		
		return list;
	}
	/**
	 * 获取数据字典项
	 * @param code 字典项code
	 * @return
	 */
	public static List getDataItem(String code) {
		IBaseDAO dao = (IBaseDAO) ContextUtil.getSpringBean(HelperApp.getDaoName());
		List<IMap> item = (List<IMap>)getDataDictionary(code,dao);		
		return item;
	}
	 
    
   
}
