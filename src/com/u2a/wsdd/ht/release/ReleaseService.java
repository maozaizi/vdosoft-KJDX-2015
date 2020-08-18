package com.u2a.wsdd.ht.release;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * 发布版本管理
 * 崔佳华
 */
public class ReleaseService extends Service{
	
	/**
	 * 版本信息列表
	 * @param in
	 * @return
	 */
	public IMap getReleaseList(IMap in) {
		// 设置结果集
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		//分页参数
		Integer currentPageNO = (String) in.get("currentPageNO")==null?0:Integer.parseInt((String) in.get("currentPageNO"));
		Integer perCount =(String) in.get("perCount")==null?0:Integer.parseInt((String) in.get("perCount"));
		//查询分页结果
		Page page = db.pageQuery(in,"getReleaseList","com.Release",currentPageNO,perCount);
		//回写
		page.setAction(request);
		result.put("page", page);
		return result;
	}
	/**
	 * 发布新版本
	 * @param in
	 * @return
	 */
	public IMap addRelease(IMap in) {
		IMap result = new DataMap();
		IMap userMap = (IMap) in.get("userMap");
		IMap release = BeanFactory.getClassBean("com.Release");
		release.put("isValid",1);
		release=db.get(release);
		release.put("isValid", 0);
		HelperDB.setCreateInfo(HelperApp.getUserName(userMap).toString(), release);
		db.update(release);
		
		
		BigDecimal code=new BigDecimal((String)release.get("releaseCode")) ;
	 	BigDecimal code1=new BigDecimal("1") ;
		String releaseCode=code.add(code1).toString();
		String releaseName=(String)release.get("releaseName");
		releaseName=releaseName.replaceAll(String.valueOf(code),releaseCode);
		release.put("releaseCode", releaseCode);
		release.put("releaseName", releaseName);
		release.put("releaseId",  HelperApp.getAutoIncrementPKID(HelperApp
				.getPackageName(), "com.Release"));
		HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), release);
		db.insert(release);
//		日志
//		LogBean log = new LogBean(userMap, release.get("releaseId").toString(), release.getClassName(), "",
//				"在版本管理功能下新增了版本信息,版本："+release.get("releaseName"),  
//				"");    //url
//		LogRecordService.saveOperationLog(log, db);
		String url = in.get("url").toString();
		result.put("method.url", url);
		result.put("method.infoMsg", "发布成功");
		return result;
	}
	/**
	 * 修改新版本的发布路径
	 * @param in
	 * @return
	 */
	public IMap updateRelease(IMap in) {
		IMap result = new DataMap();
		IMap userMap = (IMap) in.get("userMap");
		IMap release = (IMap)in.get("release");
		HelperDB.setModifyInfo(HelperApp.getUserName(userMap), release);
		db.update(release);
////		日志
//		LogBean log = new LogBean(userMap, release.get("releaseId").toString(), release.getClassName(), "",
//				ConstantsWsdd.CZFS0060+"在版本管理功能下修改了版本信息,版本："+release.get("releaseName"),  
//				"");    //url
//		LogRecordService.saveOperationLog(log, db);
		String url = in.get("url").toString();
		result.put("method.url", url);
		result.put("method.infoMsg", "修改成功");
		return result;
	}
	
	
	/**
	 * 初始化数据
	 */
	public IMap initializationAddRelease (IMap in) {
		IMap result = new DataMap();
		IMap userMap = (IMap) in.get("userMap");
		IMap release = (IMap) in.get("Release");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//ID
		String releaseId = HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.Release");
		//版本号
		String releaseCode = "1.0";
		//版本名称
		String releaseName = "Android V1.0";
		//版本路径
		String address = release.get("address")+"uploadfiles/app/javamall.apk";
		//发布人
		String publisher= userMap.get("name").toString();
		//发布时间
		String releaseTime= sdf.format(new Date());
		//是否有效
		String isValid= "1";
		//创建人
		String createUser= userMap.get("name").toString();
		//创建时间
		String createDate= sdf.format(new Date());
		release.put("releaseId", releaseId);
		release.put("releaseCode", releaseCode);
		release.put("releaseName", releaseName);
		release.put("address", address);
		release.put("publisher", publisher);
		release.put("releaseTime", releaseTime);
		release.put("isValid", isValid);
		release.put("createUser", createUser+"/"+createUser);
		release.put("createDate", createDate);
		release.put("modifyUser", createUser+"/"+createUser);
		release.put("modifyDate", createDate);
		db.insert(release);
//		LogBean log = new LogBean(userMap, release.get("releaseId").toString(), release.getClassName(), "",
//				"在版本管理功能下初始化了版本信息,版本："+release.get("releaseName"),  
//				"");    //url
//		LogRecordService.saveOperationLog(log, db);
		
		String url = in.get("url").toString();
		System.out.println(url);
		result.put("method.url", url);
		result.put("method.infoMsg", "初始化成功");
		return result;
	}
}
