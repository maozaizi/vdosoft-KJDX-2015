package com.u2a.framework.service.business;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.brick.api.Service;
import com.brick.dao.Page;
import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.brick.exception.BusinessException;
import com.brick.manager.BeanFactory;
import com.u2a.framework.util.FreemarkUtil;
import com.u2a.framework.util.HelperApp;
import com.u2a.framework.util.HelperDB;
import com.u2a.framework.util.DateTimeUtil;
import com.u2a.wsdd.common.Constants;
import com.u2a.wsdd.ht.logrecord.LogRecordService;
import com.u2a.framework.util.PDFUtil;
import com.u2a.wsdd.ht.logrecord.LogBean;

/**
 * 
 *  系统 ：建筑工程教育平台 
 *  类名 : ProjectService类
 *  作者 : duch
 *  时间 : Nov 9, 2014
 *  版本 : 1.0
 */
public class ProjectService1 extends Service {
	/**
	 * 
	 *  描述: 查询项目
	 *  方法: getList方法
	 *  作者: duch
	 *  时间: Nov 15, 2014
	 *  版本: 1.0
	 */
	public IMap<String, Object> getList(IMap<String, Object> in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		String levelid = (String) in.get("projectLevel");
		String typeid = (String) in.get("planType");
		if(!"".equals(levelid)&&levelid!=null){
			IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			tempBean.put("dataItemId", levelid);
			List<IMap> planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
			
			in.put("projectlevelid", levelid);
			in.remove("projectLevel");
			result.put("planTypeList", planTypeList);
		}
		if(!"".equals(typeid)&&typeid!=null){
			in.put("plantypeid",typeid);
			in.remove("planType");
		}
		Page page = db.pageQuery(in, "getprojectList", "com.projectInfo", Integer
				.parseInt((String) in.get("currentPageNO")), Integer
				.parseInt((String) in.get("perCount")));
		
		page.setAction(request);
		
		result.put("page", page);
		return result;
	}
	
	/**
	 * 导出EXCEL
	 * @param in
	 * @return
	 */
	public IMap<String, Object> exportExcelProject(IMap<String, Object> in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		try{
			HttpServletRequest request = (HttpServletRequest) in.get("request");
			HttpServletResponse response = (HttpServletResponse) in.get("response"); 
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			String filename = DateTimeUtil.date2str("yyyy年MM月dd日 HH:mm:ss", new Date())+"项目信息.xls";
			response.setHeader("Content-Disposition", "attachment; filename=\""+ new String(filename.getBytes(),"iso8859-1")+   "\"");
			
			OutputStream os = response.getOutputStream();
			WritableWorkbook book = Workbook.createWorkbook(os);
			
			WritableSheet ws  =  book.createSheet("项目信息" ,  0);
			
			WritableCellFormat format1 = new WritableCellFormat();
			WritableCellFormat format2 = new WritableCellFormat();
			WritableCellFormat format3 = new WritableCellFormat();
			//设置数字格式
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");    //设置数字格式
			jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
			wcfN.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			jxl.write.NumberFormat nf1 = new jxl.write.NumberFormat("0");    //设置数字格式
			jxl.write.WritableCellFormat wcfNint = new jxl.write.WritableCellFormat(nf1);
			wcfNint.setAlignment(jxl.format.Alignment.CENTRE);
			wcfNint.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			// 单元格居中
			format1.setAlignment(jxl.format.Alignment.CENTRE);
			format3.setAlignment(jxl.format.Alignment.LEFT);
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			format1.setWrap(true);
			//设置表头
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),  
					12,   
					WritableFont.BOLD,   
					false,  
					UnderlineStyle.NO_UNDERLINE); 
			format2.setAlignment(jxl.format.Alignment.CENTRE);
			format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format2.setFont(font);
			// 设置字体
			jxl.write.WritableFont wf = new jxl.write.WritableFont(
					WritableFont.COURIER, 30, WritableFont.BOLD, false);
			jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(
					wf);
			jxl.write.WritableFont redWord = new jxl.write.WritableFont(
					WritableFont.COURIER, 10, WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			jxl.write.WritableCellFormat redWordRow = new jxl.write.WritableCellFormat(
					redWord);
			redWordRow.setAlignment(jxl.format.Alignment.CENTRE);
			wcfF.setAlignment(jxl.format.Alignment.CENTRE);
			
			//日期
			jxl.write.DateFormat df = new jxl.write.DateFormat("yyyy-dd-MM"); 
			jxl.write.WritableCellFormat wcfdate = new jxl.write.WritableCellFormat(df); 
			
			//添加标题行
			ws.mergeCells(0, 0, 15, 1);
			ws.addCell(new jxl.write.Label(0, 0, DateTimeUtil.date2str("yyyy年MM月dd日", new Date())+"项目信息", format2));
			ws.addCell(new jxl.write.Label(0, 2, "序号", format1));
			ws.addCell(new jxl.write.Label(1, 2, "项目类别", format1));
			ws.addCell(new jxl.write.Label(2, 2, "项目级别", format1));
			ws.addCell(new jxl.write.Label(3, 2, "立项时间", format1));
			ws.addCell(new jxl.write.Label(4, 2, "项目编号", format1));
			ws.addCell(new jxl.write.Label(5, 2, "项目名称", format1));
			ws.addCell(new jxl.write.Label(6, 2, "计划类别", format1));
			ws.addCell(new jxl.write.Label(7, 2, "所在单位", format1));
			ws.addCell(new jxl.write.Label(8, 2, "负责人", format1));
			ws.addCell(new jxl.write.Label(9, 2, "到款金额（万元）", format1));
			ws.addCell(new jxl.write.Label(10, 2, "管理费", format1));
			ws.addCell(new jxl.write.Label(11, 2, "科研费", format1));
			ws.addCell(new jxl.write.Label(12, 2, "辅助费", format1));
			ws.addCell(new jxl.write.Label(13, 2, "财务总号", format1));
			ws.addCell(new jxl.write.Label(14, 2, "凭证编号", format1));
			ws.addCell(new jxl.write.Label(15, 2, "其他计划类别", format1));
			
			
			String levelid = (String) in.get("projectLevel");
			String typeid = (String) in.get("planType");
			if(!"".equals(levelid)&&levelid!=null){
				IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				tempBean.put("dataItemId", levelid);
				List<IMap> planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
				
				in.put("projectlevelid", levelid);
				in.remove("projectLevel");
				result.put("planTypeList", planTypeList);
			}
			if(!"".equals(typeid)&&typeid!=null){
				in.put("plantypeid",typeid);
				in.remove("planType");
			}
			List peojectList = db.getList(in, "getprojectList", "com.projectInfo");
			//如果项目不为空 则导出所有
			if(!peojectList.isEmpty()){
				for(int i=0;i<peojectList.size();i++){
					IMap map =  (IMap)peojectList.get(i);
					//序号
					ws.addCell(new jxl.write.Number(0, i+3, (Integer)map.get("xuhao"), format1));
					//项目类别
					ws.addCell(new jxl.write.Label(1, i+3, (String)map.get("feeType"), format1));
					//项目级别
					ws.addCell(new jxl.write.Label(2, i+3, (String)map.get("projectlevel"), format1));
					//立项时间
					ws.addCell(new jxl.write.Label(3, i+3, (String)map.get("builddate"), format1));
					//项目编号
					ws.addCell(new jxl.write.Label(4, i+3, (String)map.get("projectcode"), format1));
					//项目名称
					ws.addCell(new jxl.write.Label(5, i+3, (String)map.get("projectname"), format1));
					//计划类别
					ws.addCell(new jxl.write.Label(6, i+3, (String)map.get("plantype"), format1));
					//所在单位
					ws.addCell(new jxl.write.Label(7, i+3, (String)map.get("college"), format1));
					//负责人
					ws.addCell(new jxl.write.Label(8, i+3, (String)map.get("dutyperson"), format1));
					//到款金额（万元）
					ws.addCell(new jxl.write.Number(9, i+3, (Double)map.get("amount"), format1));
					//管理费
					ws.addCell(new jxl.write.Number(10, i+3, (Double)map.get("managefee"), format1));
					//科研费
					ws.addCell(new jxl.write.Number(11, i+3, (Double)map.get("keyanfee"), format1));
					//辅助费
					ws.addCell(new jxl.write.Number(12, i+3, 0,format1));
					//财务总号
					ws.addCell(new jxl.write.Label(13, i+3, (String)map.get("fintotal"), format1));
					//凭证编号
					ws.addCell(new jxl.write.Label(14, i+3, (String)map.get("pzNo"), format1));
					//其他计划类别
					if("1".equals((String)map.get("isother"))){
						ws.addCell(new jxl.write.Label(15, i+3, (String)map.get("othertype"), format1));
					}else{
						ws.addCell(new jxl.write.Label(15, i+3, "", format1));
					}
					
				}
				
//				//报名序号
//				
//				//学员编号
//				
//				//学员姓名
//				
//				String sex = (String)map.get("sex");
//				if("0".equals(sex)){
//					sex ="男";
//				}else{
//					sex = "女";
//				}
//				
//				//身份证号
//				ws.addCell(new jxl.write.Label(4, i+3, (String)map.get("idcardno"), format1));
//				//执业单位
//				ws.addCell(new jxl.write.Label(5, i+3, (String)map.get("companyName"), format1));
//				//资格证书编号
//				ws.addCell(new jxl.write.Label(6, i+3, (String)map.get("zgzsno"), format1));
//				//注册编号
//				ws.addCell(new jxl.write.Label(7, i+3, (String)map.get("zcno"), format1));
//				//专业
//				ws.addCell(new jxl.write.Label(8, i+3, (String)map.get("specialty"), format1));
//				//面授成绩 空
//				ws.addCell(new jxl.write.Label(9, i+3, "", format1));
				
			}
			
			book.write();
			book.close(); 
			os.close();
			response.flushBuffer();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 
	 *  描述: 根据选择的项目级别，查询计划类别
	 *  方法: getPlanTypeList方法
	 *  作者: duch
	 *  时间: Nov 9, 2014
	 *  版本: 1.0
	 */
	public IMap<String, Object> getPlanTypeList(IMap<String, Object> in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		String dataItemId = (String) in.get("dataItemId");
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemId", dataItemId);
		List<IMap> planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");

		result.put("planTypeList", planTypeList);
		return result;
	}
	/**
	 * 
	 *  描述: 增加项目
	 *  方法: addProject方法
	 *  作者: duch
	 *  时间: Nov 9, 2014
	 *  版本: 1.0
	 */
	public IMap<String, Object> addProject(IMap<String, Object> in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		// 从session中获取用户信息
		IMap userMap = (IMap) in.get("userMap");
		String projectId = HelperApp.getAutoIncrementPKID(
				HelperApp.getPackageName(), "com.projectInfo"); 
		
		IMap projectMap = (IMap) in.get("project");
		String istemp = (String) projectMap.get("istemp");
		projectMap.put("id", projectId);
		//拼写财务总号
		String keyan = (String) projectMap.get("feeType");
		String projectLevel = (String)in.get("projectLevel");
		String planType = ((String)in.get("planType")).split("_")[0];
		
		//插入projectLevel
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemId", projectLevel);
		tempBean = db.get(tempBean);
		projectMap.put("projectLevel", tempBean.get("dataItemName"));
		projectMap.put("projectlevelid", projectLevel);
		String projectLevelNo =  (String) tempBean.get("dataItemCode");
		//获取计划类别
		IMap planBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		planBean.put("dataItemId", planType);
		planBean = db.get(planBean);
		projectMap.put("plantype", planBean.get("dataItemName"));
		projectMap.put("plantypeid", planType);
		
		String planTypeNo =  (String) planBean.get("dataItemValue");
		//获取当前年份的自序号 没有则添加
		//获取年份编码
		Calendar c = Calendar.getInstance(); 
		int yearint = c.get(Calendar.YEAR); 
		String year = Integer.toString(yearint).substring(2, 4);
		projectMap.put("inYear",year);
		
		
		//是否为后续项目 根据项目编号查询是否为后续
		IMap selMap = new DataMap();
		String isFollow = "0";
		int followplannum = 0;
		String followfintotal = "";
		String strNO = "";
		selMap.put("projectcode", projectMap.get("projectcode"));
		List<IMap> projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
		if(!projectList.isEmpty()){
			projectMap.put("isfollow","1");
		}else{
			projectMap.put("isfollow","0");
		}
		projectMap.put("istemp",istemp);
		//判断是否临时编码，是则不生成财务总号
		if(!"1".equals(istemp)){
			if(!projectList.isEmpty()){
				projectMap.put("isfollow","1");
				
				projectMap.put("projectsort",projectList.get(0).get("projectsort"));
				projectMap.put("fintotal",projectList.get(0).get("fintotal"));
			}else{
				projectMap.put("isfollow","0");
				//获取当前计划类别下的名字为当前年份的子节点 没有则添加
				IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				cplanBean.put("dataItemId", planType);
				cplanBean.put("dataItemName", Integer.toString(yearint));
				
				cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
				String selfNo = "";
				int plannum = 0 ;
				
				if(cplanBean!=null){
					String updateplanId = (String) cplanBean.get("dataItemId");
					selfNo = (String) cplanBean.get("dataItemValue");
					updateplanId = (String) cplanBean.get("dataItemId");
					plannum = Integer.valueOf(selfNo)+1;
					IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					updateplanBean.put("dataItemId", updateplanId);
					updateplanBean.put("dataItemValue", plannum);
					db.update(updateplanBean);
				}else{
					IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
							.getPackageName(), "com.DataItemBaseInfo"));
					aplanBean.put("parentId", planType);
					aplanBean.put("dataItemCode", "");
					aplanBean.put("dataItemName", Integer.toString(yearint));
					aplanBean.put("dataItemValue", 1);
					aplanBean.put("type", 1); // 类型：0字典项，1字典项明细
					aplanBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
					aplanBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
					aplanBean.put("orderby", Integer.toString(yearint));
					HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);  
					db.insert(aplanBean);
					plannum = 1;
				}
				//将自序号增加1
				strNO =Integer.toString(plannum);
				projectMap.put("projectsort",strNO);
				if(strNO.length()<3){
					for(int i=0;i<4-strNO.length();i++){
						strNO="0"+strNO;
					}
				}
				
				projectMap.put("fintotal",keyan+projectLevelNo+planTypeNo+year+strNO);
				
			}
		}
		
		//获取序号
		//获取现有数据的最大序号，为空则为0
		IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		maxplanBean.put("dataItemName", Integer.toString(yearint));
		
		maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
		int number = 0 ;
		int xuhao = 0;
		if(maxplanBean!=null){
			number = Integer.valueOf((String) maxplanBean.get("xuhao"));
			xuhao = number+1;
			String updateplanId = (String) maxplanBean.get("id");
			IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			updatemaxBean.put("dataItemId", updateplanId);
			updatemaxBean.put("dataItemValue", xuhao);
			db.update(updatemaxBean);
		}else{
			IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			xuhaoplanBean.put("dataItemCode", "projectsort");
			xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
			String parentid = (String) xuhaoplanBean.get("dataItemId");
			
			IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
					.getPackageName(), "com.DataItemBaseInfo"));
			inmaxBean.put("parentId", parentid);
			inmaxBean.put("dataItemCode", "");
			inmaxBean.put("dataItemName", Integer.toString(yearint));
			inmaxBean.put("dataItemValue", 1);
			inmaxBean.put("type", 1); // 类型：0字典项，1字典项明细
			inmaxBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
			inmaxBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
			inmaxBean.put("orderby", Integer.toString(yearint));
			HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);  
			db.insert(inmaxBean);
			xuhao = 1;
		}
		 
		String xuhaoNum = Integer.toString(xuhao);
		if(xuhaoNum.length()<3){
			for(int j=0;j<4-xuhaoNum.length();j++){
				xuhaoNum="0"+xuhaoNum;
			}
		}
		projectMap.put("xuhao",xuhao);
		
		
		Date date =  new Date();
		HelperDB.setDateTime("allotdate", projectMap, date);
		HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), projectMap);
		
		db.insert(projectMap);
		
		result.put("method.infoMsg", "项目添加成功！");
		
		result.put("method.url", in.get("url"));
		return result;
	}
	
	/**
	 * 
	 *  描述: 修改项目
	 *  方法: updateProject方法
	 *  作者: duch
	 *  时间: Nov 15, 2014
	 *  版本: 1.0
	 */
	public IMap<String, Object> updateProject(IMap<String, Object> in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		// 从session中获取用户信息
		IMap userMap = (IMap) in.get("userMap");

		IMap projectMap = (IMap) in.get("project");
		//获取原来的项目信息
		String exprojectid = (String) projectMap.get("id");
		IMap exprojectMap = BeanFactory.getClassBean("com.projectInfo");
		exprojectMap.put("id", exprojectid);
		exprojectMap = db.get(exprojectMap);
		StringBuffer exsb = new StringBuffer();
		int finnum = 0;
		int projectchange = 0;
		//比较项目名称
		String projectName = (String) projectMap.get("projectname");
		String exprojectName = (String) exprojectMap.get("projectname");
		if(!projectName.equals(exprojectName)){
			exsb.append("将项目名称改为"+projectName+",");
		}
		//比较项目编号
		String projectcode = (String) projectMap.get("projectcode");
		String exprojectcode = (String) exprojectMap.get("projectcode");
		if(!projectcode.equals(exprojectcode)){
			exsb.append("将项目编号由"+exprojectcode+"改为"+projectcode+",");
			projectchange++;
		}
		//比较经费类别
		String feeType = (String) projectMap.get("feeType");
		String exfeeType = (String) exprojectMap.get("feeType");
		if(!feeType.equals(exfeeType)){
			exsb.append("将经费类别由"+exfeeType+"改为"+feeType+",");
			finnum++;
		}
		//比较项目级别
		String projectlevelid = (String)in.get("projectLevel");
		String exprojectLevelid = (String) exprojectMap.get("projectlevelid");
		String projectlevel = (String) exprojectMap.get("projectLevel");
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemId", projectlevelid);
		tempBean = db.get(tempBean);
		String projectLevelNo =  (String) tempBean.get("dataItemCode");
		String addplevel = (String)tempBean.get("dataItemName");
		if(!projectlevelid.equals(exprojectLevelid)){
			exsb.append("将项目级别由"+projectlevel+"改为"+addplevel+",");
			finnum++;
		}
		//比较计划类别
		String plantypeid = ((String)in.get("planType")).split("_")[0];
		String explantypeid = (String) exprojectMap.get("plantypeid");
		String plantype = (String) exprojectMap.get("plantype");
		IMap tempplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempplanBean.put("dataItemId", plantypeid);
		tempplanBean = db.get(tempplanBean);
		String planTypeNo =  (String) tempplanBean.get("dataItemValue");
		
		String addplantype = (String) tempplanBean.get("dataItemName");
		if(!plantypeid.equals(explantypeid)){
			if("1".equals(tempplanBean.get("p2"))){
				exsb.append("将计划类别由"+plantype+"改为"+addplantype+"("+(String) projectMap.get("othertype")+"),");
			}else{
				exsb.append("将计划类别由"+plantype+"改为"+addplantype+",");
			}
			finnum++;
		}else{
			//如果为其他的话判断其他计划类别是否改变
			String othertype = (String) projectMap.get("othertype");
			String exothertype = (String) exprojectMap.get("othertype");
			if(!othertype.equals(exothertype)){
				exsb.append("将其他计划类别由"+exothertype+"改为"+othertype+",");
			}
		}
		//比较立项时间
		String builddate = (String) projectMap.get("builddate");
		String exbuilddate = (String) exprojectMap.get("builddate");
		if(!builddate.equals(exbuilddate)){
			exsb.append("将立项时间由"+exbuilddate+"改为"+builddate+",");
		}
		//比较负责人单位
		String college = (String) projectMap.get("college");
		String excollege = (String) exprojectMap.get("college");
		if(!college.equals(excollege)){
			exsb.append("将负责人单位由"+excollege+"改为"+college+",");
		}
		//比较负责人单位
		String dutyperson = (String) projectMap.get("dutyperson");
		String exdutyperson = (String) exprojectMap.get("dutyperson");
		if(!dutyperson.equals(exdutyperson)){
			exsb.append("将负责人由"+exdutyperson+"改为"+dutyperson+",");
		}
		//比较到款金额
		double amount = Double.valueOf((String)projectMap.get("amount")) ;
		double examount = (Double)exprojectMap.get("amount");
		if(amount!=examount){
			exsb.append("将到款金额由"+examount+"改为"+amount+",");
		}
		//比较管理费
		double managefee = Double.valueOf((String) projectMap.get("managefee"));
		double exmanagefee = (Double) exprojectMap.get("managefee");
		if(managefee!=exmanagefee){
			exsb.append("将管理费由"+exmanagefee+"改为"+managefee+",");
		}
		//比较科研费
		double keyanfee = Double.valueOf((String) projectMap.get("keyanfee"));
		double exkeyanfee = (Double) exprojectMap.get("keyanfee");
		if(keyanfee!=exkeyanfee){
			exsb.append("将科研费由"+exkeyanfee+"改为"+keyanfee+",");
		}
		//比较辅助费
		double fuzhufee = Double.valueOf((String) projectMap.get("keyanfee"));
		double exfuzhufee = (Double) exprojectMap.get("keyanfee");
		if(fuzhufee!=exfuzhufee){
			exsb.append("将辅助费由"+exfuzhufee+"改为"+fuzhufee+",");
		}
		//比较凭证编号
		String pzNo = (String) projectMap.get("pzNo");
		String expzNo = (String) exprojectMap.get("pzNo");
		if(!pzNo.equals(expzNo)){
			exsb.append("将凭证编号由"+expzNo+"改为"+pzNo+",");
		}
		
		
		
		String isfollow = (String) exprojectMap.get("isfollow");
		String fprojectsort = "";
		String ffintotal = "";
		if(projectchange>0){
			//如果项目编号被修改，则先判断是否为后续项目，如果是后续项目，则使用以前的财务总号，否则，重新生成
			//是否为后续项目 根据项目编号查询是否为后续
			IMap selMap = new DataMap();
			selMap.put("projectcode", projectcode);
			List<IMap> projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
			if(!projectList.isEmpty()){
				isfollow = "1";
				projectMap.put("isfollow","1");
				
				fprojectsort = (String) projectList.get(0).get("projectsort");
				ffintotal = (String) projectList.get(0).get("fintotal");
			}else{
				isfollow = "0";
			}
		}
		//如果修改的项目中包含经费类别、项目级别、计划类别 则财务总号重新生成并记录
		if(finnum>0){
			//作废原有记录
			IMap delMap = BeanFactory.getClassBean("com.projectInfo");
			delMap.put("id", exprojectid);
			delMap.put("isValid", "0");
			HelperDB.setModifyInfo(HelperApp.getUserName(userMap), delMap);
			db.update(delMap);
			//生成新的记录
			IMap addMap = BeanFactory.getClassBean("com.projectInfo");
			addMap = projectMap;
			addMap.put("id", HelperApp.getAutoIncrementPKID(
					HelperApp.getPackageName(), "com.projectInfo"));
			
			String fintotal = feeType+projectLevelNo+planTypeNo;
			//获取年份 和类别编号
			//获取年份编码
			Calendar c = Calendar.getInstance(); 
			int yearint = c.get(Calendar.YEAR); 
			String year = Integer.toString(yearint).substring(2, 4);
			addMap.put("inYear",year);
			//如果为后续项目 则使用以前的财务总号 否则重新生成
			if("0".equals(isfollow)){
				//编号
				IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				cplanBean.put("dataItemId", plantypeid);
				cplanBean.put("dataItemName", Integer.toString(yearint));
				
				cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
				String selfNo = "";
				int plannum = 0 ;
				
				if(cplanBean!=null){
					String updateplanId = (String) cplanBean.get("dataItemId");
					selfNo = (String) cplanBean.get("dataItemValue");
					updateplanId = (String) cplanBean.get("dataItemId");
					plannum = Integer.valueOf(selfNo)+1;
					IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					updateplanBean.put("dataItemId", updateplanId);
					updateplanBean.put("dataItemValue", plannum);
					db.update(updateplanBean);
				}else{
					IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
							.getPackageName(), "com.DataItemBaseInfo"));
					aplanBean.put("parentId", plantypeid);
					aplanBean.put("dataItemCode", "");
					aplanBean.put("dataItemName", Integer.toString(yearint));
					aplanBean.put("dataItemValue", 1);
					aplanBean.put("type", 1); // 类型：0字典项，1字典项明细
					aplanBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
					aplanBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
					aplanBean.put("orderby",Integer.toString(yearint));
					HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);  
					db.insert(aplanBean);
					plannum = 1;
				}
				//将自序号增加1
				String strNO =Integer.toString(plannum);
				if(strNO.length()<3){
					for(int i=0;i<4-strNO.length();i++){
						strNO="0"+strNO;
					}
				}
				addMap.put("projectsort",strNO);
				fintotal = fintotal+year+strNO;
				addMap.put("fintotal",fintotal);
			}else{
				addMap.put("projectsort",fprojectsort);
				addMap.put("fintotal",ffintotal);
			}
			//获取现有数据的最大序号，为空则为0
			IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			maxplanBean.put("dataItemName", Integer.toString(yearint));
			
			maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
			int number = 0 ;
			int xuhao = 0;
			if(maxplanBean!=null){
				number = Integer.valueOf((String) maxplanBean.get("xuhao"));
				xuhao = number+1;
				String updateplanId = (String) maxplanBean.get("id");
				IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				updatemaxBean.put("dataItemId", updateplanId);
				updatemaxBean.put("dataItemValue", xuhao);
				db.update(updatemaxBean);
			}else{
				IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				xuhaoplanBean.put("dataItemCode", "projectsort");
				xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
				String parentid = (String) xuhaoplanBean.get("dataItemId");
				
				IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
						.getPackageName(), "com.DataItemBaseInfo"));
				inmaxBean.put("parentId", parentid);
				inmaxBean.put("dataItemCode", "");
				inmaxBean.put("dataItemName", Integer.toString(yearint));
				inmaxBean.put("dataItemValue", 1);
				inmaxBean.put("type", 1); // 类型：0字典项，1字典项明细
				inmaxBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
				inmaxBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
				inmaxBean.put("orderby", Integer.toString(yearint));
				HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);  
				db.insert(inmaxBean);
				xuhao = 1;
			}
			 
			String xuhaoNum = Integer.toString(xuhao);
			if(xuhaoNum.length()<3){
				for(int j=0;j<4-xuhaoNum.length();j++){
					xuhaoNum="0"+xuhaoNum;
				}
			}
			addMap.put("xuhao",xuhao);
			
			addMap.put("allotdate", exprojectMap.get("allotdate"));
			addMap.put("projectLevel", addplevel);
			addMap.put("projectlevelid", projectlevelid);
			addMap.put("plantype", addplantype);
			addMap.put("plantypeid", plantypeid);
			addMap.put("isfollow", isfollow);
			
			HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), addMap);
			
			db.insert(addMap);
			
			LogBean log = new LogBean(userMap,"",exsb.toString());
			LogRecordService.saveOperationLog(log, db);
			
		}else{
			//如果为后续项目 则使用以前的财务总号 否则重新生成
			if("0".equals(isfollow)){
				String fintotal = feeType+projectLevelNo+planTypeNo;
				//获取年份 和类别编号
				//获取年份编码
				Calendar c = Calendar.getInstance(); 
				int yearint = c.get(Calendar.YEAR); 
				String year = Integer.toString(yearint).substring(2, 4);
				projectMap.put("inYear",year);
				//编号
				IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				cplanBean.put("dataItemId", plantypeid);
				cplanBean.put("dataItemName", Integer.toString(yearint));
				
				cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
				String selfNo = "";
				int plannum = 0 ;
				
				if(cplanBean!=null){
					String updateplanId = (String) cplanBean.get("dataItemId");
					selfNo = (String) cplanBean.get("dataItemValue");
					updateplanId = (String) cplanBean.get("dataItemId");
					plannum = Integer.valueOf(selfNo)+1;
					IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					updateplanBean.put("dataItemId", updateplanId);
					updateplanBean.put("dataItemValue", plannum);
					db.update(updateplanBean);
				}else{
					IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
							.getPackageName(), "com.DataItemBaseInfo"));
					aplanBean.put("parentId", plantypeid);
					aplanBean.put("dataItemCode", "");
					aplanBean.put("dataItemName", Integer.toString(yearint));
					aplanBean.put("dataItemValue", 1);
					aplanBean.put("type", 1); // 类型：0字典项，1字典项明细
					aplanBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
					aplanBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
					aplanBean.put("orderby",Integer.toString(yearint));
					HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);  
					db.insert(aplanBean);
					plannum = 1;
				}
				//将自序号增加1
				String strNO =Integer.toString(plannum);
				if(strNO.length()<3){
					for(int i=0;i<4-strNO.length();i++){
						strNO="0"+strNO;
					}
				}
				projectMap.put("projectsort",strNO);
				fintotal = fintotal+year+strNO;
				projectMap.put("fintotal",fintotal);
			}else{
				IMap selMap = new DataMap();
				selMap.put("projectcode", projectcode);
				List<IMap> projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
				if(!projectList.isEmpty()){
					fprojectsort = (String) projectList.get(0).get("projectsort");
					ffintotal = (String) projectList.get(0).get("fintotal");
				}
				projectMap.put("projectsort",fprojectsort);
				projectMap.put("fintotal",ffintotal);
			}
			HelperDB.setModifyInfo(HelperApp.getUserName(userMap), projectMap);
			db.update(projectMap);
			
		}
		result.put("method.infoMsg", "项目修改成功！");
		result.put("method.url", in.get("url"));
		return result;
	}
	
	/**
	 * 
	 *  描述: 导入项目
	 *  方法: saveProjects方法
	 *  作者: duch
	 *  时间: Nov 11, 2014
	 *  版本: 1.0
	 */
	public IMap saveProjects(IMap in){
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		String fileUrl = (String)in.get("fileUrl");
		String message = "";
		String messageDetails="";
		int err=0;//错误数据
		int success=0;//导入数据
		int importNum=0;//需要导入条数
		List successMapList = new ArrayList();//数据正确待保存scoreRecord
		InputStream input = null;
		//导入文件路径
		fileUrl=request.getSession().getServletContext().getRealPath("/")+ fileUrl;
		
		try {
			input = new BufferedInputStream(new FileInputStream(fileUrl));
			HSSFWorkbook wb = new HSSFWorkbook(input);
			HSSFSheet sheet = wb.getSheetAt(0);
			if(sheet == null){
				messageDetails +="Excel图表不存在，请重新导入";
			}else{
				int total = sheet.getLastRowNum();
				message +="共有数据"+total+"行。<br />";
				importNum = total;
				HSSFRow c = sheet.getRow(0);
				//判断excel表头符合要求
				boolean flag = true;
				if(!"经费类别".equals(c.getCell((short)0).getStringCellValue().trim())){
					messageDetails +="Excel图表中第1列的应为经费类别！<br/>";
					flag = false;
				}
				if(!"项目级别".equals(c.getCell((short)1).getStringCellValue().trim())){
					messageDetails +="Excel图表中第2列的应为项目级别！<br/>";
					flag = false;
				}
				if(!"计划类别".equals(c.getCell((short)2).getStringCellValue().trim())){
					messageDetails +="Excel图表中第3列的应为学员姓名！<br/>";
					flag = false;
				}
				if(!"项目编号".equals(c.getCell((short)3).getStringCellValue().trim())){
					messageDetails +="Excel图表中第4列的应为项目编号！<br/>";
					flag = false;
				}
				if(!"项目名称".equals(c.getCell((short)4).getStringCellValue().trim())){
					messageDetails +="Excel图表中第5列的应为身份证号！<br/>";
					flag = false;
				}
				if(!"立项时间".equals(c.getCell((short)5).getStringCellValue().trim())){
					messageDetails +="Excel图表中第6列的应为立项时间！<br/>";
					flag = false;
				}
				if(!"所在单位".equals(c.getCell((short)6).getStringCellValue().trim())){
					messageDetails +="Excel图表中第7列的应为所在单位！<br/>";
					flag = false;
				}
				if(!"负责人".equals(c.getCell((short)7).getStringCellValue().trim())){
					messageDetails +="Excel图表中第8列的应为负责人！<br/>";
					flag = false;
				}
				if(!"到款金额（万元）".equals(c.getCell((short)8).getStringCellValue().trim())){
					messageDetails +="Excel图表中第9列的应为到款金额（万元）！<br/>";
					flag = false;
				}
				if(!"凭证编号".equals(c.getCell((short)9).getStringCellValue().trim())){
					messageDetails +="Excel图表中第10列的应为凭证编号！<br/>";
					flag = false;
				}
				IMap userMap = (IMap) in.get("userMap");
				//获取年份编码
				Calendar cal = Calendar.getInstance(); 
				int yearint = cal.get(Calendar.YEAR); 
				String year = Integer.toString(yearint).substring(2, 4);
				//循环读取excel数据
				for(int j=1;j<sheet.getLastRowNum()+1&&flag;j++){
					//项目信息
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					String projectId = HelperApp.getAutoIncrementPKID(HelperApp
							.getPackageName(), "com.projectInfo");
					projectInfoMap.put("id", projectId);
					
					StringBuffer fintotal = new StringBuffer();
					HSSFRow hssfRow = sheet.getRow(j);
					if (hssfRow == null && j != sheet.getLastRowNum()) {
						messageDetails +="Excel图表中第"+(j+1)+"行内容为空！<br/>";
					}
					String isfollow = "";
					//cell3 项目编码
					HSSFCell cell3 = hssfRow.getCell((short)3);
					if(cell3!=null){
						String projectcode = cell3.getStringCellValue();
						projectInfoMap.put("projectcode", projectcode);
						String ffintotal = "";
						//是否为后续项目 根据项目编号查询是否为后续
						IMap selMap = new DataMap();
						selMap.put("projectcode", projectcode);
						List<IMap> projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
						if(!projectList.isEmpty()){
							projectInfoMap.put("isfollow","1");
							projectInfoMap.put("projectsort",projectList.get(0).get("projectsort"));
							projectInfoMap.put("fintotal",projectList.get(0).get("fintotal"));
							isfollow = "1";
							//fprojectsort = (String) projectList.get(0).get("projectsort");
							//ffintotal = (String) projectList.get(0).get("fintotal");
						}else{
							projectInfoMap.put("isfollow","0");
							isfollow = "0";
						}
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(4)+"列项目编码不能为空！<br/>";
						err++;
						continue;
					}
					
					//cell0 经费类别
					HSSFCell cell0 = hssfRow.getCell((short)0);
					if(cell0!=null){
						String feeType = cell0.getStringCellValue();
						projectInfoMap.put("feeType", feeType);
						fintotal.append(feeType);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(1)+"列经费类别不能为空！<br/>";
						err++;
						continue;
					}
					//cell1 项目级别
					HSSFCell cell1 = hssfRow.getCell((short)1);
					if(cell0!=null){
						String projectlevel = cell1.getStringCellValue();
						String projectlevelNo = projectlevel.substring(0,2);
						IMap planlevelBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planlevelBean.put("dataItemName", projectlevel);
						
						List<IMap> planBeanList = db.getList(planlevelBean,"getDataItemByName","com.DataItemBaseInfo");
						if(planBeanList.isEmpty()){
							messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(2)+"列项目级别不存在！<br/>";
							err++;
							continue;
						}else{
							projectInfoMap.put("projectlevel", projectlevel);
							projectInfoMap.put("projectlevelid", (String) planBeanList.get(0).get("dataItemId"));
						}
						fintotal.append(projectlevelNo);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(2)+"列项目级别不能为空！<br/>";
						err++;
						continue;
					}
					//cell2 计划类别
					HSSFCell cell2 = hssfRow.getCell((short)2);
					String strNO ="";
					if(cell2!=null){
						String plantype = cell2.getStringCellValue();
						String plantypeNos = plantype.substring(0,2);
						String plantypeid = "";
						//获取自序号
						IMap planBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planBean.put("dataItemName", plantype);
						
						List<IMap> planBeanList = db.getList(planBean,"getDataItemByName","com.DataItemBaseInfo");
						String isother = "0";
						if(planBeanList.isEmpty()){
							messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(3)+"列计划类别不存在！<br/>";
							err++;
							continue;
						}else{
							planBean = planBeanList.get(0);
							plantypeid = (String) planBean.get("dataItemId");
							if("1".equals((String)planBean.get("p2"))){
								isother = "1";
								projectInfoMap.put("othertype", plantype);
							}
							//判断是否为后续项目 是则不重新生成财务总号。
							if("0".equals(isfollow)){
								//获取当前计划类别下的名字为当前年份的子节点 没有则添加
								IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
								cplanBean.put("dataItemId", planBean.get("dataItemId"));
								cplanBean.put("dataItemName", Integer.toString(yearint));
								
								cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
								String selfNo = "";
								int plannum = 0 ;
								
								if(cplanBean!=null){
									String updateplanId = (String) cplanBean.get("dataItemId");
									selfNo = (String) cplanBean.get("dataItemValue");
									updateplanId = (String) cplanBean.get("dataItemId");
									plannum = Integer.valueOf(selfNo)+1;
									IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
									updateplanBean.put("dataItemId", updateplanId);
									updateplanBean.put("dataItemValue", plannum);
									db.update(updateplanBean);
								}else{
									IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
									aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
											.getPackageName(), "com.DataItemBaseInfo"));
									aplanBean.put("parentId", planBean.get("dataItemId"));
									aplanBean.put("dataItemCode", "");
									aplanBean.put("dataItemName", Integer.toString(yearint));
									aplanBean.put("dataItemValue", 1);
									aplanBean.put("type", 1); // 类型：0字典项，1字典项明细
									aplanBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
									aplanBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
									aplanBean.put("orderby",Integer.toString(yearint));
									HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);  
									db.insert(aplanBean);
									plannum = 1;
								}
								//将自序号增加1
								strNO =Integer.toString(plannum);
								if(strNO.length()<3){
									for(int i=0;i<4-strNO.length();i++){
										strNO="0"+strNO;
									}
								}
								projectInfoMap.put("projectsort",strNO);
								fintotal.append(plantypeNos);
							}
							
							projectInfoMap.put("plantype", plantype);
							projectInfoMap.put("plantypeid", plantypeid);
							projectInfoMap.put("isother", isother);
						}
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(3)+"列计划类别不能为空！<br/>";
						err++;
						continue;
					}
					//cell4 项目名称
					HSSFCell cell4 = hssfRow.getCell((short)4);
					if(cell4!=null){
						String projectname = cell4.getStringCellValue();
						projectInfoMap.put("projectname", projectname);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(5)+"列项目名称不能为空！<br/>";
						err++;
						continue;
					}
					//cell5 立项时间
					HSSFCell cell5 = hssfRow.getCell((short)5);
					if(cell5!=null){
						String builddate = cell5.getStringCellValue();
						projectInfoMap.put("builddate", builddate);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(6)+"列立项时间不能为空！<br/>";
						err++;
						continue;
					}
					//cell6 所在单位
					HSSFCell cell6 = hssfRow.getCell((short)6);
					if(cell6!=null){
						String college = cell6.getStringCellValue();
						projectInfoMap.put("college", college);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(7)+"列所在单位不能为空！<br/>";
						err++;
						continue;
					}
					//cell7 负责人
					HSSFCell cell7 = hssfRow.getCell((short)7);
					if(cell7!=null){
						String dutyperson = cell7.getStringCellValue();
						projectInfoMap.put("dutyperson", dutyperson);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(8)+"列负责人不能为空！<br/>";
						err++;
						continue;
					}
					//cell8 付款金额
					HSSFCell cell8 = hssfRow.getCell((short)8);
					if(cell8!=null){
						String amount = cell8.getStringCellValue();
						projectInfoMap.put("amount", Double.valueOf(amount));
						//将付款金额分配到三个项目中
						projectInfoMap.put("managefee",Double.valueOf(amount)*10000*4/1000000);
						projectInfoMap.put("keyanfee",Double.valueOf(amount)*10000*96/1000000);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(9)+"列付款金额不能为空！<br/>";
						err++;
						continue;
					}
					//cell9 凭证编号
					HSSFCell cell9 = hssfRow.getCell((short)9);
					if(cell9!=null){
						String pzNo = cell9.getStringCellValue();
						projectInfoMap.put("pzNo", pzNo);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(10)+"列凭证编号不能为空！<br/>";
						err++;
						continue;
					}
					//获取序号
					//获取现有数据的最大序号，为空则为0
					IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					maxplanBean.put("dataItemName", Integer.toString(yearint));
					maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
					int number = 0 ;
					int xuhao = 0;
					if(maxplanBean!=null){
						number = Integer.valueOf((String) maxplanBean.get("xuhao"));
						xuhao = number+1;
						String updateplanId = (String) maxplanBean.get("id");
						IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						updatemaxBean.put("dataItemId", updateplanId);
						updatemaxBean.put("dataItemValue", xuhao);
						db.update(updatemaxBean);
					}else{
						IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						xuhaoplanBean.put("dataItemCode", "projectsort");
						xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
						String parentid = (String) xuhaoplanBean.get("dataItemId");
						
						IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
								.getPackageName(), "com.DataItemBaseInfo"));
						inmaxBean.put("parentId", parentid);
						inmaxBean.put("dataItemCode", "");
						inmaxBean.put("dataItemName", Integer.toString(yearint));
						inmaxBean.put("dataItemValue", 1);
						inmaxBean.put("type", 1); // 类型：0字典项，1字典项明细
						inmaxBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
						inmaxBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
						inmaxBean.put("orderby", Integer.toString(yearint));
						HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);  
						db.insert(inmaxBean);
						xuhao = 1;
					}
					 
					String xuhaoNum = Integer.toString(xuhao);
					if(xuhaoNum.length()<3){
						for(int k=0;k<4-xuhaoNum.length();k++){
							xuhaoNum="0"+xuhaoNum;
						}
					}
					projectInfoMap.put("xuhao",xuhao);
					//获取年份编码
					projectInfoMap.put("inYear",year);
					
					fintotal.append(year);
					fintotal.append(strNO);
					
					if("0".equals(isfollow)){
						projectInfoMap.put("fintotal",fintotal.toString());
					}
					successMapList.add(projectInfoMap);
					success+=1;
				}
			}
		}catch(Exception e){
			throw new BusinessException("导入失败!");
		}finally{
			//关闭input流
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int flag =0; //标记是否导入
			int add = 0;//导入成功条数
			int update = 0;//导入失败条数
			if(importNum==success){//数据全部正确
				//全部导入
				IMap userMap = (IMap) in.get("userMap");
				for (int i = 0; i < successMapList.size(); i++) {
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					projectInfoMap = (IMap) successMapList.get(i);
					
					Date date =  new Date();
					HelperDB.setDateTime("allotdate", projectInfoMap, date);
					
					
					HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), projectInfoMap);
					
					db.insert(projectInfoMap);
				}
				flag =1;
			}else{//删除已上传的EXCEL文件
				java.io.File file = new java.io.File(fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}
			if(messageDetails==""){
				messageDetails +="暂无。";
			}
			message += "需导入："+importNum+"条，正确数据："+(success) +"条。<br/>";
			message += "错误数据：<font color='red'>"+err+"</font>条。<br/>";
			message += "错误信息：<br/>"+messageDetails;
			result.put("message", message);
			result.put("flag",flag);
		}
		return result;
	}
	/**
	 * 
	 *  描述: 跳转到修改页面
	 *  方法: viewProject方法
	 *  作者: duch
	 *  时间: Nov 12, 2014
	 *  版本: 1.0
	 */
	public IMap toupdateProject(IMap in) {
		IMap result = new DataMap();
		String id = (String) in.get("pid");
		IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
		projectInfoMap.put("id", id);
		
		projectInfoMap = db.get(projectInfoMap);
		
		String levelid = (String) projectInfoMap.get("projectlevelid");
		String typeid = (String) projectInfoMap.get("plantypeid");
		if(!"".equals(levelid)&&levelid!=null){
			IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			tempBean.put("dataItemId", levelid);
			List<IMap> planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
			
			result.put("planTypeList", planTypeList);
		}
		if(!"".equals(typeid)&&typeid!=null){
			IMap typeBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			typeBean.put("dataItemId", typeid);
			typeBean = db.get(typeBean);
			
			String p2 = (String) typeBean.get("p2");
			result.put("p2", p2);
		}
		
		result.put("project", projectInfoMap);
		return result;
	}
	
	
	/**
	 * 
	 *  描述: 查看项目详情
	 *  方法: viewProject方法
	 *  作者: duch
	 *  时间: Nov 12, 2014
	 *  版本: 1.0
	 */
	public IMap viewProject(IMap in) {
		IMap result = new DataMap();
		String id = (String) in.get("id");
		IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
		projectInfoMap.put("id", id);
		
		projectInfoMap = db.get(projectInfoMap);
		
		result.put("project", projectInfoMap);
		return result;
	}
	/**
	 * 
	 *  描述: 删除项目
	 *  方法: deleteProject方法
	 *  作者: duch
	 *  时间: Nov 13, 2014
	 *  版本: 1.0
	 */
	public IMap deleteProject(IMap in) {
		IMap result = new DataMap();
		IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
		IMap userMap = (IMap) in.get("userMap");
		projectInfoMap.put("id", in.get("pid"));
		projectInfoMap.put("isValid", "0");
		
		HelperDB.setModifyInfo(HelperApp.getUserName(userMap), projectInfoMap);
		db.update(projectInfoMap);
		
		result.put("method.infoMsg", "项目删除成功！");
		
		result.put("method.url", in.get("url"));
		return result;
	}
	
	/**
	 * 
	 *  描述: 后续项目查看项目历史详情
	 *  方法: viewHistory方法
	 *  作者: duch
	 *  时间: Nov 12, 2014
	 *  版本: 1.0
	 */
	public IMap viewHistory(IMap in) {
		IMap result = new DataMap();
		List<IMap> list = db.getList(in, "getprojectList", "com.projectInfo");
		
		result.put("projectList", list);
		return result;
	}
	/**
	 * 
	 *  描述: 生成科研项目建卡分析表
	 *  方法: exportProject方法
	 *  作者: duch
	 *  时间: Nov 12, 2014
	 *  版本: 1.0
	 */
	public IMap exportProject(IMap in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		HttpServletResponse response = (HttpServletResponse) in.get("response");
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		String path = request.getSession().getServletContext().getRealPath(
		"/");
		String[] projectid = (String[]) in.get("projectid");
		//String ids = "";
		//获取页面上获取的项目编号
		//for(int j=0;j<projectid.length;j++){
		//	ids = ids + projectid[j]+",";
		//}
//		ids = ids.substring(0,ids.length()-1);
		//获取项目的详细信息
		IMap map = new DataMap();
		map.put("ids",projectid);
		List<IMap> projectlist = db.getList(map, "getprojectListasc", "");
		
		IMap freemap = new DataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
		//freemap.put("sqsj", sd.format(new Date()));
		//freemap.put("year", sdf.format(new Date()));
		List<IMap> projectlists = new ArrayList<IMap>();
		for(int i=0;i<projectlist.size();i++){
			IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
			projectInfoMap = projectlist.get(i);
			projectInfoMap.put("allotdate", sdf.format(projectInfoMap.get("allotdate")));
			projectlists.add(projectInfoMap);
		}
		freemap.put("projectlist",projectlists);
		freemap.put("issign", in.get("issign"));
		//将map存入到模板中并生成PDF
		String body = FreemarkUtil.transform(path+"temp","zxky_jkfpb.ftl",freemap);
		
		PDFUtil.getPdf("建卡分配表",path+"temp",body, request, response);
		
		
		return result;
	}

	/**
	 * 
	 *  描述: 查看修改历史
	 *  方法: toViewHistory方法
	 *  作者: duch
	 *  时间: Nov 15, 2014
	 *  版本: 1.0
	 */
	public IMap<String, Object> toViewHistory(IMap<String, Object> in) {
		IMap<String, Object> result = new DataMap<String, Object>();// 输出map
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		
		Page page = db.pageQuery(in, "getLogList", "com.LogRecord", Integer
				.parseInt((String) in.get("currentPageNO")), Integer
				.parseInt((String) in.get("perCount")));
		
		page.setAction(request);
		
		result.put("page", page);
		return result;
	}
	
	/**
	 * 
	 *  描述: 保存历史数据
	 *  方法: saveHistoryProjects方法
	 *  作者: duch
	 *  时间: Nov 15, 2014
	 *  版本: 1.0
	 */
	public IMap saveHistoryProjects(IMap in){
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest) in.get("request");
		String fileUrl = (String)in.get("fileUrl");
		String message = "";
		String messageDetails="";
		int err=0;//错误数据
		int success=0;//导入数据
		int importNum=0;//需要导入条数
		List successMapList = new ArrayList();//数据正确待保存scoreRecord
		InputStream input = null;
		//导入文件路径
		fileUrl=request.getSession().getServletContext().getRealPath("/")+ fileUrl;
		
		try {
			input = new BufferedInputStream(new FileInputStream(fileUrl));
			HSSFWorkbook wb = new HSSFWorkbook(input);
			HSSFSheet sheet = wb.getSheetAt(0);
			if(sheet == null){
				messageDetails +="Excel图表不存在，请重新导入";
			}else{
				int total = sheet.getLastRowNum();
				message +="共有数据"+total+"行。<br />";
				importNum = total;
				HSSFRow c = sheet.getRow(0);
				//判断excel表头符合要求
				boolean flag = true;
				if(!"经费类别".equals(c.getCell((short)0).getStringCellValue().trim())){
					messageDetails +="Excel图表中第1列的应为经费类别！<br/>";
					flag = false;
				}
				if(!"项目级别".equals(c.getCell((short)1).getStringCellValue().trim())){
					messageDetails +="Excel图表中第2列的应为项目级别！<br/>";
					flag = false;
				}
				if(!"计划类别".equals(c.getCell((short)2).getStringCellValue().trim())){
					messageDetails +="Excel图表中第3列的应为学员姓名！<br/>";
					flag = false;
				}
				if(!"项目编号".equals(c.getCell((short)3).getStringCellValue().trim())){
					messageDetails +="Excel图表中第4列的应为项目编号！<br/>";
					flag = false;
				}
				if(!"项目名称".equals(c.getCell((short)4).getStringCellValue().trim())){
					messageDetails +="Excel图表中第5列的应为身份证号！<br/>";
					flag = false;
				}
				if(!"立项时间".equals(c.getCell((short)5).getStringCellValue().trim())){
					messageDetails +="Excel图表中第6列的应为立项时间！<br/>";
					flag = false;
				}
				if(!"所在单位".equals(c.getCell((short)6).getStringCellValue().trim())){
					messageDetails +="Excel图表中第7列的应为所在单位！<br/>";
					flag = false;
				}
				if(!"负责人".equals(c.getCell((short)7).getStringCellValue().trim())){
					messageDetails +="Excel图表中第8列的应为负责人！<br/>";
					flag = false;
				}
				if(!"到款金额（万元）".equals(c.getCell((short)8).getStringCellValue().trim())){
					messageDetails +="Excel图表中第9列的应为到款金额（万元）！<br/>";
					flag = false;
				}
				if(!"凭证编号".equals(c.getCell((short)9).getStringCellValue().trim())){
					messageDetails +="Excel图表中第10列的应为凭证编号！<br/>";
					flag = false;
				}
				if(!"开单时间".equals(c.getCell((short)10).getStringCellValue().trim())){
					messageDetails +="Excel图表中第11列的应为开单时间！<br/>";
					flag = false;
				}
				if(!"其他计划类别".equals(c.getCell((short)11).getStringCellValue().trim())){
					messageDetails +="Excel图表中第12列的应为其他计划类别！<br/>";
					flag = false;
				}
				if(!"制表时间".equals(c.getCell((short)12).getStringCellValue().trim())){
					messageDetails +="Excel图表中第13列的应为制表时间！<br/>";
					flag = false;
				}
				IMap userMap = (IMap) in.get("userMap");
				//循环读取excel数据
				for(int j=1;j<sheet.getLastRowNum()+1&&flag;j++){
					//项目信息
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					String projectId = HelperApp.getAutoIncrementPKID(HelperApp
							.getPackageName(), "com.projectInfo");
					projectInfoMap.put("id", projectId);
					
					StringBuffer fintotal = new StringBuffer();
					HSSFRow hssfRow = sheet.getRow(j);
					if (hssfRow == null && j != sheet.getLastRowNum()) {
						messageDetails +="Excel图表中第"+(j+1)+"行内容为空！<br/>";
					}
					//cell0 经费类别
					HSSFCell cell0 = hssfRow.getCell((short)0);
					if(cell0!=null){
						String feeType = cell0.getStringCellValue();
						projectInfoMap.put("feeType", feeType);
						fintotal.append(feeType);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(1)+"列经费类别不能为空！<br/>";
						err++;
						continue;
					}
					//cell10 开单时间
					HSSFCell cell10 = hssfRow.getCell((short)10);
					String inyear ="";
					String year ="";
					if(cell10!=null){
						inyear = cell10.getStringCellValue();
						year = inyear.substring(2, 4);
						projectInfoMap.put("inYear", year);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(11)+"列开单时间不能为空！<br/>";
						err++;
						continue;
					}
					//cell1 项目级别
					HSSFCell cell1 = hssfRow.getCell((short)1);
					String levelid = "";
					if(cell0!=null){
						String projectlevel = cell1.getStringCellValue();
						String projectlevelNo = projectlevel.substring(0,2);
						IMap planlevelBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planlevelBean.put("dataItemName", projectlevel);
						
						List<IMap> planBeanList = db.getList(planlevelBean,"getDataItemByName","com.DataItemBaseInfo");
						System.out.println("==========================================================");
						System.out.println("========="+planBeanList.size()+"======================================================");
						System.out.println("==========================================================");
						if(planBeanList.isEmpty()){
							messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(2)+"列项目级别不存在！<br/>";
							err++;
							continue;
						}else{
							projectInfoMap.put("projectlevel", projectlevel);
							projectInfoMap.put("projectlevelid", (String) planBeanList.get(0).get("dataItemId"));
							levelid = (String) planBeanList.get(0).get("dataItemId");
						}
						fintotal.append(projectlevelNo);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(2)+"列项目级别不能为空！<br/>";
						err++;
						continue;
					}
					//cell2 计划类别
					HSSFCell cell2 = hssfRow.getCell((short)2);
					String strNO ="";
					if(cell2!=null){
						String plantype = cell2.getStringCellValue();
						String plantypeNos = plantype.substring(0,2);
						//获取自序号
						IMap planBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planBean.put("dataItemName", plantype);
						planBean.put("parentid", levelid);
						
						List<IMap> planBeanList = db.getList(planBean,"getDataItemByName","com.DataItemBaseInfo");
						String isother = "0";
						if(planBeanList.isEmpty()){
							messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(3)+"列计划类别不存在！<br/>";
							err++;
							continue;
						}else{
							planBean = planBeanList.get(0);
							if("1".equals((String)planBean.get("p2"))){
								isother = "1";
							}
							//获取当前计划类别下的名字为当前年份的子节点 没有则添加
							IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
							cplanBean.put("dataItemId", planBean.get("dataItemId"));
							cplanBean.put("dataItemName", inyear);
							
							cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
							String selfNo = "";
							int plannum = 0 ;
							
							if(cplanBean!=null){
								String updateplanId = (String) cplanBean.get("dataItemId");
								selfNo = (String) cplanBean.get("dataItemValue");
								updateplanId = (String) cplanBean.get("dataItemId");
								plannum = Integer.valueOf(selfNo)+1;
								IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
								updateplanBean.put("dataItemId", updateplanId);
								updateplanBean.put("dataItemValue", plannum);
								db.update(updateplanBean);
							}else{
								IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
								aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
										.getPackageName(), "com.DataItemBaseInfo"));
								aplanBean.put("parentId", planBean.get("dataItemId"));
								aplanBean.put("dataItemCode", "");
								aplanBean.put("dataItemName", inyear);
								aplanBean.put("dataItemValue", 1);
								aplanBean.put("type", 1); // 类型：0字典项，1字典项明细
								aplanBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
								aplanBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
								aplanBean.put("orderby", inyear);
								HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);  
								db.insert(aplanBean);
								plannum = 1;
							}
							//将自序号增加1
							strNO =Integer.toString(plannum);
							if(strNO.length()<3){
								for(int i=0;i<4-strNO.length();i++){
									strNO="0"+strNO;
								}
							}
							projectInfoMap.put("projectsort",strNO);
							fintotal.append(plantypeNos);
							projectInfoMap.put("plantype", plantype);
							projectInfoMap.put("plantypeid", planBean.get("dataItemId"));
							projectInfoMap.put("isother", isother);
						}
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(3)+"列计划类别不能为空！<br/>";
						err++;
						continue;
					}
					//cell3 项目编码
					HSSFCell cell3 = hssfRow.getCell((short)3);
					if(cell3!=null){
						String projectcode = cell3.getStringCellValue();
						projectInfoMap.put("projectcode", projectcode);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(4)+"列项目编码不能为空！<br/>";
						err++;
						continue;
					}
					//cell4 项目名称
					HSSFCell cell4 = hssfRow.getCell((short)4);
					if(cell4!=null){
						String projectname = cell4.getStringCellValue();
						projectInfoMap.put("projectname", projectname);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(5)+"列项目名称不能为空！<br/>";
						err++;
						continue;
					}
					//cell5 立项时间
					HSSFCell cell5 = hssfRow.getCell((short)5);
					if(cell5!=null){
						String builddate = cell5.getStringCellValue();
						projectInfoMap.put("builddate", builddate);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(6)+"列立项时间不能为空！<br/>";
						err++;
						continue;
					}
					//cell6 所在单位
					HSSFCell cell6 = hssfRow.getCell((short)6);
					if(cell6!=null){
						String college = cell6.getStringCellValue();
						projectInfoMap.put("college", college);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(7)+"列所在单位不能为空！<br/>";
						err++;
						continue;
					}
					//cell7 负责人
					HSSFCell cell7 = hssfRow.getCell((short)7);
					if(cell7!=null){
						String dutyperson = cell7.getStringCellValue();
						projectInfoMap.put("dutyperson", dutyperson);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(8)+"列负责人不能为空！<br/>";
						err++;
						continue;
					}
					//cell8 付款金额
					HSSFCell cell8 = hssfRow.getCell((short)8);
					if(cell8!=null){
						String amount = cell8.getStringCellValue();
						projectInfoMap.put("amount", Double.valueOf(amount));
						//将付款金额分配到三个项目中
						projectInfoMap.put("managefee",Double.valueOf(amount)*10000*4/1000000);
						projectInfoMap.put("keyanfee",Double.valueOf(amount)*10000*96/1000000);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(9)+"列付款金额不能为空！<br/>";
						err++;
						continue;
					}
					//cell9 凭证编号
					HSSFCell cell9 = hssfRow.getCell((short)9);
					if(cell9!=null){
						String pzNo = cell9.getStringCellValue();
						projectInfoMap.put("pzNo", pzNo);
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(10)+"列凭证编号不能为空！<br/>";
						err++;
						continue;
					}
					//cell9 凭证编号
					HSSFCell cell11 = hssfRow.getCell((short)11);
					if(cell11!=null){
						String othertype = cell11.getStringCellValue();
						projectInfoMap.put("othertype", othertype);
					}
					//获取现有数据的最大序号，为空则为0
					IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					maxplanBean.put("dataItemName", inyear);
					maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
					int number = 0 ;
					int xuhao = 0;
					if(maxplanBean!=null){
						number = Integer.valueOf((String) maxplanBean.get("xuhao"));
						xuhao = number+1;
						String updateplanId = (String) maxplanBean.get("id");
						IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						updatemaxBean.put("dataItemId", updateplanId);
						updatemaxBean.put("dataItemValue", xuhao);
						db.update(updatemaxBean);
					}else{
						IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						xuhaoplanBean.put("dataItemCode", "projectsort");
						xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
						String parentid = (String) xuhaoplanBean.get("dataItemId");
						
						IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp
								.getPackageName(), "com.DataItemBaseInfo"));
						inmaxBean.put("parentId", parentid);
						inmaxBean.put("dataItemCode", "");
						inmaxBean.put("dataItemName", inyear);
						inmaxBean.put("dataItemValue", 1);
						inmaxBean.put("type", 1); // 类型：0字典项，1字典项明细
						inmaxBean.put("isValid", Constants.ISVALID);// 是否有效：0表示无效，1表示有效
						inmaxBean.put("isSys", "0"); // 是否系统 1系统，0用户添加
						inmaxBean.put("orderby", inyear);
						HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);  
						db.insert(inmaxBean);
						xuhao = 1;
					}
					 
					//cell11 制表时间
					HSSFCell cell12 = hssfRow.getCell((short)12);
					if(cell12!=null){
						String allotdateStr = cell12.getStringCellValue();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						projectInfoMap.put("allotdate", sdf.format(DateTimeUtil.str2Date("yyyy/MM/dd hh:mm:ss", allotdateStr)));
					}else{
						messageDetails +="Excel图表中第<font color='red'>"+(j+1)+"</font>行第"+(13)+"列制表时间不能为空！<br/>";
						err++;
						continue;
					}
					
					
					String xuhaoNum = Integer.toString(xuhao);
					if(xuhaoNum.length()<3){
						for(int k=0;k<4-xuhaoNum.length();k++){
							xuhaoNum="0"+xuhaoNum;
						}
					}
					projectInfoMap.put("xuhao",xuhao);
					
					fintotal.append(year);
					fintotal.append(strNO);
					
					projectInfoMap.put("fintotal",fintotal.toString());
					
					successMapList.add(projectInfoMap);
					success+=1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException("导入失败!");
		}finally{
			//关闭input流
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int flag =0; //标记是否导入
			int add = 0;//导入成功条数
			int update = 0;//导入失败条数
			if(importNum==success){//数据全部正确
				//全部导入
				IMap userMap = (IMap) in.get("userMap");
				for (int i = 0; i < successMapList.size(); i++) {
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					projectInfoMap = (IMap) successMapList.get(i);
					
					//是否为后续项目 根据项目编号查询是否为后续
					IMap selMap = new DataMap();
					selMap.put("projectcode", projectInfoMap.get("projectcode"));
					List<IMap> projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
					if(!projectList.isEmpty()){
						projectInfoMap.put("isfollow","1");
					}else{
						projectInfoMap.put("isfollow","0");
					}
					HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), projectInfoMap);
					
					db.insert(projectInfoMap);
				}
				flag =1;
			}else{//删除已上传的EXCEL文件
				java.io.File file = new java.io.File(fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}
			if(messageDetails==""){
				messageDetails +="暂无。";
			}
			message += "需导入："+importNum+"条，正确数据："+(success) +"条。<br/>";
			message += "错误数据：<font color='red'>"+err+"</font>条。<br/>";
			message += "错误信息：<br/>"+messageDetails;
			result.put("message", message);
			result.put("flag",flag);
		}
		return result;
	}
	
}
