package com.u2a.wsdd.common;
/**
 * 
 *  
 * 类名称：Constants   
 * 类描述：  常量类 定义一些系统使用的常量 
 * 创建人：duch
 * 创建时间：Feb 22, 2012 10:15:19 AM
 */
public class Constants {
	/**
	 * 数据做逻辑删除时的标识字段 有效
	 */
	public final static String ISVALID = "1";
	/**
	 * 无效
	 */
	public final static String ISNOTVALID = "0";
	/**
	 * 分页时默认的每一页行数
	 */
	public final static int PAGESIZE = 10;
	/**
	 * 申请提交时候暂时保存字段 0 暂时保存
	 */
	public final static String ISSAVE = "0";
	/**
	 * 1 提交最终保存
	 */
	public final static String ISSUBMIT = "1";
	
	/**
	 * 日志操作类型
	 */
	public final static String opt_add = "添加";
	public final static String opt_update = "修改";
	public final static String opt_del = "作废";
	public final static String opt_audit = "审核";
	public final static String opt_back = "退回";
	public final static String opt_commit = "提交";
	public final static String opt_jump= "跳转";
	public final static String opt_preparepass= "预审通过";
	public final static String opt_changeOrg= "转移";
	 
	
}
