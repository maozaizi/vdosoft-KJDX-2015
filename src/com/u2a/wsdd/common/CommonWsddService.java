package com.u2a.wsdd.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.brick.api.Service;
import com.brick.dao.Page;
import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.brick.manager.BeanFactory;
import com.u2a.framework.commons.Constants;
import com.u2a.framework.commons.OperateHelper;
import com.u2a.framework.util.HelperApp;
import com.u2a.framework.util.HelperDB;
import com.u2a.framework.util.JsonUtil;
import com.u2a.wsdd.ht.logrecord.LogBean;
import com.u2a.wsdd.ht.logrecord.LogRecordService;

public class CommonWsddService extends Service{

	 
	/**获得订单列表信息(订单审核)
	 * @Description 
	 * @param @param in
	 * @param @return    
	 * @return IMap   
	 * @date Jul 12, 2013 9:00:56 AM
	 */
	public IMap getLogRecord(IMap in) {
		// 设置结果集
	IMap logRecordMap = new DataMap();
		
		// 设置结果集
		IMap result = new DataMap();
	
		// 查询岗位信息
//		List<IMap> logRecordList = new ArrayList<IMap>();
//		logRecordList = db.getList(logRecordMap, "getLogRecord", "com.LogRecord");
//		IMap<String, Object> result = new DataMap<String, Object>();
//		// 返回时要展开的节点
//		result.put("logRecord", logRecordList);
		
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		//分页参数
		Integer currentPageNO = (String) in.get("currentPageNO")==null?0:Integer.parseInt((String) in.get("currentPageNO"));
		Integer perCount =(String) in.get("perCount")==null?0:Integer.parseInt((String) in.get("perCount"));
		//查询分页结果
		Page page = db.pageQuery(in,"getLogRecord","com.LogRecord",currentPageNO,perCount);
		//回写
		page.setAction(request);
		result.put("page", page);
		return result;
	}
	 
	
	/**
	 * 获取配置信息
	 * @param in
	 * @return
	 */
	public IMap getConfiguration(IMap in) {
		IMap result = new DataMap();
		
		IMap configurationMap = BeanFactory.getClassBean("com.configuration");
		List configuration=db.getList(configurationMap, null);
		result.put("configuration", configuration);

		return result;
	}
	
	/**
	 * 获取配置信息
	 * @param in
	 * @return
	 */
	public IMap updateConfiguration(IMap in) {
		IMap result = new DataMap();
		
		IMap configuration = (IMap) in.get("configuration"); 
		// 从session中获取用户信息
		IMap userMap = (IMap) in.get("userMap");
		HelperDB.setModifyInfo(HelperApp.getUserName(userMap), configuration);
		db.update(configuration);
		
		String url = in.get("url").toString();
		result.put("method.url", url);
		result.put("method.infoMsg", OperateHelper.getUpdateMsg());
		return result;
	}
}
