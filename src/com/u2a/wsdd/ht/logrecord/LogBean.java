package com.u2a.wsdd.ht.logrecord;


import com.brick.data.IMap;
import com.brick.exception.BusinessException;
import com.brick.util.Util;

public class LogBean {
	/**
	 * 
	 * 创建一个新的实例 LogBean.   
	 *   
	 * @param user userMap (必填)
	 * @param objId  操作表主键(必填)
	 * @param objName 操作表名称(必填)
	 * @param reason  作废时的原因
	 * @param remark  记录操作情况(格式如示例：在订单审核功能下删除商品，商品编号：XXXX，商品名称：XXXX，订单编号：XXXX，客户名称：XXXX)(必填)
	 */
	public LogBean(IMap user,String reason,String remark){
		if (user!=null){
			this.userId=(String) user.get("userId");
			this.userName=(String) user.get("name");
		}
		if (Util.checkNull(remark)){
			throw new BusinessException("补充填写日志记录为空!");
		}
		this.reason=reason;
		this.remark = remark;
	}
	/*8
	 * 保存附件的日志
	 */
//	public LogBean(IMap user,String objId,String objName,String url,String reason,String remark){
//		if (user!=null){
//			this.userId=(String) user.get("userId");
//			this.userName=(String) user.get("name");
//		}
//		if (!Util.checkNull(url)){
//			this.url = url;
//		}
//		if (Util.checkNull(objId)){
//			throw new BusinessException("日志数据项目/操作表主键为空！");
//		}
//		if (Util.checkNull(remark)){
//			throw new BusinessException("补充填写日志记录为空!");
//		}
//		this.objId = objId;
//		this.objName = objName;
//		this.reason=reason;
//		this.remark = remark;
//	}
	/**
	 * @Fields userId : 用户ID
	 */
		
	private String userId;
	
	/**
	 * @Fields userName : 用户名
	 */
		
	private String userName;
	
	/**
	 * @Fields dataId : 文档ID
	 */
		
	private String url;
	
	
	/**
	 * @Fields objId : 操作表主键
	 */
		
	private String objId;
	/**
	 * @Fields 操作表名称
	 */
	private String objName ;
	/**
	 * @Fields reason : 作废原因
	 */	
	private String reason;
	/**
	 * @Fields reason : 其他非业务操作时的日志补充记录
	 */	
	private String remark;
	
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	
	public String getReason() {
		return reason;
	}
	public String getObjId() {
		return objId;
	}
	public String getObjName() {
		return objName;
	}
	public String getRemark() {
		return remark;
	}
	public String getUrl() {
		return url;
	}
}
