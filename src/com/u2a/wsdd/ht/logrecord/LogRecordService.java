package com.u2a.wsdd.ht.logrecord;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.brick.api.Service;
import com.brick.dao.IBaseDAO;
import com.brick.dao.Page;
import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.brick.data.OrderBean;
import com.brick.manager.BeanFactory;
import com.brick.util.PageUtils;
import com.brick.util.Util;
import com.u2a.framework.util.DateTimeUtil;
import com.u2a.framework.util.HelperApp;
import com.u2a.framework.util.HelperDB;
import com.u2a.wsdd.common.Constants;

@SuppressWarnings("unchecked")
public class LogRecordService extends Service {
	
	static final String TABLE_NAME = "com.LogRecord";

	/**
	 * 保存日志信息
	 */
	public static void saveOperationLog(LogBean bean,IBaseDAO db) {
		Date date = DateTimeUtil.getLocalDate();
		IMap<String, Object> insertbean = BeanFactory
				.getClassBean(TABLE_NAME);
		insertbean.put("logRecordId", HelperApp.getAutoIncrementPKID(HelperApp
				.getPackageName(), TABLE_NAME));
		HelperDB.setDate("operatorTime", insertbean, date);
		insertbean.put("userId", bean.getUserId());
		insertbean.put("userName", bean.getUserName());
		insertbean.put("reason", bean.getReason());
		insertbean.put("remark", bean.getRemark());
		db.insert(insertbean);
	}
	
	
}
