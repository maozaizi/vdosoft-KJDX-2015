package com.u2a.wsdd.ht.push;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.brick.api.Service;
import com.brick.dao.Page;
import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.brick.manager.BeanFactory;
import com.u2a.framework.util.HelperApp;
import com.u2a.framework.util.HelperDB;
import com.u2a.wsdd.ht.logrecord.LogBean;
import com.u2a.wsdd.ht.logrecord.LogRecordService;
@SuppressWarnings("unchecked")
/**
 * 推送信息管理
 * 崔佳华
 */
public class PushService extends Service{
	
	/**
	 * 推送信息列表
	 * @param in
	 * @return
	 */
	public IMap getPushList(IMap in) {
		// 设置结果集
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		//分页参数
		Integer currentPageNO = (String) in.get("currentPageNO")==null?0:Integer.parseInt((String) in.get("currentPageNO"));
		Integer perCount =(String) in.get("perCount")==null?0:Integer.parseInt((String) in.get("perCount"));
		//查询分页结果
		Page page = db.pageQuery(in,"getPushList","com.Push",currentPageNO,perCount);
		//回写
		page.setAction(request);
		result.put("page", page);
		return result;
	}
	
	/**导向添加推送信息页面
	 * @Description 
	 * @param @param in
	 * @param @return    
	 * @return IMap   
	 * @date Jul 12, 2013 9:00:56 AM
	 */
	public IMap toAddPush(IMap in) {
		// 设置结果集
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		//分页参数
		Integer currentPageNO = (String) in.get("currentPageNO")==null?0:Integer.parseInt((String) in.get("currentPageNO"));
		Integer perCount =(String) in.get("perCount")==null?0:Integer.parseInt((String) in.get("perCount"));
		//查询分页结果
		Page page = db.pageQuery(in,"commodityList",null,currentPageNO,perCount);
		//回写
		page.setAction(request);
		result.put("page", page);
		return result;
	}
	/**
	 * 添加推送信息
	 * @param in
	 * @return
	 */
	public IMap addPush(IMap in) {
		IMap result = new DataMap();
		IMap userMap = (IMap) in.get("userMap");
		IMap push = BeanFactory.getClassBean("com.Push");
		push.put("commodityName", in.get("commodityName"));
		push.put("commodityId", in.get("commodityId"));
		push.put("pushState",0);
		push.put("pushId",  HelperApp.getAutoIncrementPKID(HelperApp
				.getPackageName(), "com.Push"));
		HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), push);
		db.insert(push);
//		日志
//		LogBean log = new LogBean(userMap, push.get("pushId").toString(), push.getClassName(), "",
//				"在推送管理功能下新增了推送信息,编号："+push.get("pushId"),  
//				"");    //url
//		LogRecordService.saveOperationLog(log, db);
		String url = in.get("url").toString();
		result.put("method.url", url);
		result.put("method.infoMsg", "添加成功！");
		return result;
	}
	/**导向修改推送信息页面
	 * @Description 
	 * @param @param in
	 * @param @return    
	 * @return IMap   
	 * @date Jul 12, 2013 9:00:56 AM
	 */
	public IMap toUpdatePush(IMap in) {
		// 设置结果集
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		//分页参数
		Integer currentPageNO = (String) in.get("currentPageNO")==null?0:Integer.parseInt((String) in.get("currentPageNO"));
		Integer perCount =(String) in.get("perCount")==null?0:Integer.parseInt((String) in.get("perCount"));
		//查询分页结果
		Page page = db.pageQuery(in,"getPushCommodity",null,currentPageNO,perCount);
		//回写
		page.setAction(request);
		//获得要修改的推送信息
		IMap push = BeanFactory.getClassBean("com.Push");
		push.put("pushId",in.get("pushId"));
		push=db.get(push);
		result.put("page", page);
		result.put("push", push);
		return result;
	}
	/**
	 * 修改推送信息
	 * @param in
	 * @return
	 */
	public IMap updatePush(IMap in) {
		IMap result = new DataMap();
		IMap userMap = (IMap) in.get("userMap");
		IMap push = (IMap) in.get("push");
		HelperDB.setModifyInfo(HelperApp.getUserName(userMap), push);
		db.update(push);
		String url = in.get("url").toString();
		result.put("method.url", url);
		//pushState状态为1的时候是进行推送，为0的时候是修改
		if("1".equals(push.get("pushState"))){
			result.put("method.infoMsg", "推送成功！");
//			推送日志
//			LogBean log = new LogBean(userMap, push.get("pushId").toString(), push.getClassName(), "",
//				"在推送管理功能下进行了推送,编号："+push.get("pushId"),  
//				"");    //url
//			LogRecordService.saveOperationLog(log, db);
		}else{
//			修改日志
			result.put("method.infoMsg", "修改成功！");
//			LogBean log = new LogBean(userMap, push.get("pushId").toString(), push.getClassName(), "",
//				"在推送管理功能下修改了推送信息,编号："+push.get("pushId"),  
//				"");    //url
//			LogRecordService.saveOperationLog(log, db);
		}
		return result;
	}
}
