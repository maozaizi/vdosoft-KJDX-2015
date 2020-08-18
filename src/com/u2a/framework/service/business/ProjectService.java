package com.u2a.framework.service.business;

import com.brick.api.Service;
import com.brick.dao.IBaseDAO;
import com.brick.dao.Page;
import com.brick.data.DataMap;
import com.brick.data.IMap;
import com.brick.exception.BusinessException;
import com.brick.manager.BeanFactory;
import com.u2a.framework.util.*;
import com.u2a.wsdd.ht.logrecord.LogBean;
import com.u2a.wsdd.ht.logrecord.LogRecordService;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;

import org.apache.poi.hssf.usermodel.*;

public class ProjectService extends Service
{

	public ProjectService()
	{
	}

	public IMap getList(IMap in)
	{
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest)in.get("request");
		String levelid = (String)in.get("projectLevel");
		String typeid = (String)in.get("planType");
		if (!"".equals(levelid) && levelid != null)
		{
			IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			tempBean.put("dataItemId", levelid);
			List planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
			in.put("projectlevelid", levelid);
			in.remove("projectLevel");
			result.put("planTypeList", planTypeList);
		}
		if (!"".equals(typeid) && typeid != null)
		{
			in.put("plantypeid", typeid);
			in.remove("planType");
		}
		Page page = db.pageQuery(in, "getprojectList", "com.projectInfo", Integer.parseInt((String)in.get("currentPageNO")), Integer.parseInt((String)in.get("perCount")));
		page.setAction(request);
		result.put("page", page);
		return result;
	}

	public IMap exportExcelProject(IMap in)
	{
		IMap result = new DataMap();
		try
		{
			HttpServletRequest request = (HttpServletRequest)in.get("request");
			HttpServletResponse response = (HttpServletResponse)in.get("response");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			String filename = (new StringBuilder(String.valueOf(DateTimeUtil.date2str("yyyy年MM月dd日 HH:mm:ss", new Date())))).append("项目信息.xls").toString();
			response.setHeader("Content-Disposition", (new StringBuilder("attachment; filename=\"")).append(new String(filename.getBytes(), "iso8859-1")).append("\"").toString());
			OutputStream os = response.getOutputStream();
			WritableWorkbook book = Workbook.createWorkbook(os);
			WritableSheet ws = book.createSheet("项目信息", 0);
			WritableCellFormat format1 = new WritableCellFormat();
			WritableCellFormat format2 = new WritableCellFormat();
			WritableCellFormat format3 = new WritableCellFormat();
			NumberFormat nf = new NumberFormat("0.00");
			WritableCellFormat wcfN = new WritableCellFormat(nf);
			wcfN.setBorder(Border.ALL, BorderLineStyle.THIN);
			NumberFormat nf1 = new NumberFormat("0");
			WritableCellFormat wcfNint = new WritableCellFormat(nf1);
			wcfNint.setAlignment(Alignment.CENTRE);
			wcfNint.setBorder(Border.ALL, BorderLineStyle.THIN);
			format1.setAlignment(Alignment.CENTRE);
			format3.setAlignment(Alignment.LEFT);
			format1.setVerticalAlignment(VerticalAlignment.CENTRE);
			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			format1.setWrap(true);
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
			format2.setAlignment(Alignment.CENTRE);
			format2.setVerticalAlignment(VerticalAlignment.CENTRE);
			format2.setFont(font);
			WritableFont wf = new WritableFont(WritableFont.COURIER, 30, WritableFont.BOLD, false);
			WritableCellFormat wcfF = new WritableCellFormat(wf);
			WritableFont redWord = new WritableFont(WritableFont.COURIER, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
			WritableCellFormat redWordRow = new WritableCellFormat(redWord);
			redWordRow.setAlignment(Alignment.CENTRE);
			wcfF.setAlignment(Alignment.CENTRE);
			DateFormat df = new DateFormat("yyyy-dd-MM");
			WritableCellFormat wcfdate = new WritableCellFormat(df);
			ws.mergeCells(0, 0, 15, 1);
			ws.addCell(new Label(0, 0, (new StringBuilder(String.valueOf(DateTimeUtil.date2str("yyyy年MM月dd日", new Date())))).append("项目信息").toString(), format2));
			ws.addCell(new Label(0, 2, "序号", format1));
			ws.addCell(new Label(1, 2, "项目类别", format1));
			ws.addCell(new Label(2, 2, "项目级别", format1));
			ws.addCell(new Label(3, 2, "立项时间", format1));
			ws.addCell(new Label(4, 2, "项目编号", format1));
			ws.addCell(new Label(5, 2, "项目名称", format1));
			ws.addCell(new Label(6, 2, "计划类别", format1));
			ws.addCell(new Label(7, 2, "所在单位", format1));
			ws.addCell(new Label(8, 2, "负责人", format1));
			ws.addCell(new Label(9, 2, "到款金额（万元）", format1));
			ws.addCell(new Label(10, 2, "管理费", format1));
			ws.addCell(new Label(11, 2, "科研费", format1));
			ws.addCell(new Label(12, 2, "辅助费", format1));
			ws.addCell(new Label(13, 2, "财务总号", format1));
			ws.addCell(new Label(14, 2, "凭证编号", format1));
			ws.addCell(new Label(15, 2, "其他计划类别", format1));
			String levelid = (String)in.get("projectLevel");
			String typeid = (String)in.get("planType");
			if (!"".equals(levelid) && levelid != null)
			{
				IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				tempBean.put("dataItemId", levelid);
				List planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
				in.put("projectlevelid", levelid);
				in.remove("projectLevel");
				result.put("planTypeList", planTypeList);
			}
			if (!"".equals(typeid) && typeid != null)
			{
				in.put("plantypeid", typeid);
				in.remove("planType");
			}
			List peojectList = db.getList(in, "getprojectList", "com.projectInfo");
			if (!peojectList.isEmpty())
			{
				for (int i = 0; i < peojectList.size(); i++)
				{
					IMap map = (IMap)peojectList.get(i);
					ws.addCell(new Number(0, i + 3, ((Integer)map.get("xuhao")).intValue(), format1));
					ws.addCell(new Label(1, i + 3, (String)map.get("feeType"), format1));
					ws.addCell(new Label(2, i + 3, (String)map.get("projectlevel"), format1));
					ws.addCell(new Label(3, i + 3, (String)map.get("builddate"), format1));
					ws.addCell(new Label(4, i + 3, (String)map.get("projectcode"), format1));
					ws.addCell(new Label(5, i + 3, (String)map.get("projectname"), format1));
					ws.addCell(new Label(6, i + 3, (String)map.get("plantype"), format1));
					ws.addCell(new Label(7, i + 3, (String)map.get("college"), format1));
					ws.addCell(new Label(8, i + 3, (String)map.get("dutyperson"), format1));
					ws.addCell(new Number(9, i + 3, ((Double)map.get("amount")).doubleValue(), format1));
					ws.addCell(new Number(10, i + 3, ((Double)map.get("managefee")).doubleValue(), format1));
					ws.addCell(new Number(11, i + 3, ((Double)map.get("keyanfee")).doubleValue(), format1));
					ws.addCell(new Number(12, i + 3, 0.0D, format1));
					ws.addCell(new Label(13, i + 3, (String)map.get("fintotal"), format1));
					ws.addCell(new Label(14, i + 3, (String)map.get("pzNo"), format1));
					if ("1".equals((String)map.get("isother")))
						ws.addCell(new Label(15, i + 3, (String)map.get("othertype"), format1));
					else
						ws.addCell(new Label(15, i + 3, "", format1));
				}

			}
			book.write();
			book.close();
			os.close();
			response.flushBuffer();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (WriteException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public IMap getPlanTypeList(IMap in)
	{
		IMap result = new DataMap();
		String dataItemId = (String)in.get("dataItemId");
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemId", dataItemId);
		List planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
		result.put("planTypeList", planTypeList);
		return result;
	}

	public IMap addProject(IMap in)
	{
		IMap result = new DataMap();
		IMap userMap = (IMap)in.get("userMap");
		String projectId = HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.projectInfo");
		IMap projectMap = (IMap)in.get("project");
		String istemp = (String)projectMap.get("istemp");
		projectMap.put("id", projectId);
		String keyan = (String)projectMap.get("feeType");
		String projectLevel = (String)in.get("projectLevel");
		String planType = ((String)in.get("planType")).split("_")[0];
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemId", projectLevel);
		tempBean = db.get(tempBean);
		projectMap.put("projectLevel", tempBean.get("dataItemName"));
		projectMap.put("projectlevelid", projectLevel);
		String projectLevelNo = (String)tempBean.get("dataItemCode");
		IMap planBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		planBean.put("dataItemId", planType);
		planBean = db.get(planBean);
		projectMap.put("plantype", planBean.get("dataItemName"));
		projectMap.put("plantypeid", planType);
		String planTypeNo = (String)planBean.get("dataItemValue");
		Calendar c = Calendar.getInstance();
		int yearint = c.get(1);
		String year = Integer.toString(yearint).substring(2, 4);
		projectMap.put("inYear", year);
		IMap selMap = new DataMap();
		String isFollow = "0";
		int followplannum = 0;
		String followfintotal = "";
		String strNO = "";
		selMap.put("projectcode", projectMap.get("projectcode"));
		List projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
		if (!projectList.isEmpty())
			projectMap.put("isfollow", "1");
		else
			projectMap.put("isfollow", "0");
		projectMap.put("istemp", istemp);
		if (!"1".equals(istemp))
			if (!projectList.isEmpty())
			{
				projectMap.put("isfollow", "1");
				projectMap.put("projectsort", ((IMap)projectList.get(0)).get("projectsort"));
				projectMap.put("fintotal", ((IMap)projectList.get(0)).get("fintotal"));
			} else
			{
				projectMap.put("isfollow", "0");
				IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				cplanBean.put("dataItemId", planType);
				cplanBean.put("dataItemName", Integer.toString(yearint));
				cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
				String selfNo = "";
				int plannum = 0;
				if (cplanBean != null)
				{
					String updateplanId = (String)cplanBean.get("dataItemId");
					selfNo = (String)cplanBean.get("dataItemValue");
					updateplanId = (String)cplanBean.get("dataItemId");
					plannum = Integer.valueOf(selfNo).intValue() + 1;
					IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					updateplanBean.put("dataItemId", updateplanId);
					updateplanBean.put("dataItemValue", Integer.valueOf(plannum));
					db.update(updateplanBean);
				} else
				{
					IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
					aplanBean.put("parentId", planType);
					aplanBean.put("dataItemCode", "");
					aplanBean.put("dataItemName", Integer.toString(yearint));
					aplanBean.put("dataItemValue", Integer.valueOf(1));
					aplanBean.put("type", Integer.valueOf(1));
					aplanBean.put("isValid", "1");
					aplanBean.put("isSys", "0");
					aplanBean.put("orderby", Integer.toString(yearint));
					HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);
					db.insert(aplanBean);
					plannum = 1;
				}
				strNO = Integer.toString(plannum);
				projectMap.put("projectsort", strNO);
				if (strNO.length() < 3)
				{
					for (int i = 0; i < 4 - strNO.length(); i++)
						strNO = (new StringBuilder("0")).append(strNO).toString();

				}
				projectMap.put("fintotal", (new StringBuilder(String.valueOf(keyan))).append(projectLevelNo).append(planTypeNo).append(year).append(strNO).toString());
			}
		IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		maxplanBean.put("dataItemName", Integer.toString(yearint));
		maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
		int number = 0;
		int xuhao = 0;
		if (maxplanBean != null)
		{
			number = Integer.valueOf((String)maxplanBean.get("xuhao")).intValue();
			xuhao = number + 1;
			String updateplanId = (String)maxplanBean.get("id");
			IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			updatemaxBean.put("dataItemId", updateplanId);
			updatemaxBean.put("dataItemValue", Integer.valueOf(xuhao));
			db.update(updatemaxBean);
		} else
		{
			IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			xuhaoplanBean.put("dataItemCode", "projectsort");
			xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
			String parentid = (String)xuhaoplanBean.get("dataItemId");
			IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
			inmaxBean.put("parentId", parentid);
			inmaxBean.put("dataItemCode", "");
			inmaxBean.put("dataItemName", Integer.toString(yearint));
			inmaxBean.put("dataItemValue", Integer.valueOf(1));
			inmaxBean.put("type", Integer.valueOf(1));
			inmaxBean.put("isValid", "1");
			inmaxBean.put("isSys", "0");
			inmaxBean.put("orderby", Integer.toString(yearint));
			HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);
			db.insert(inmaxBean);
			xuhao = 1;
		}
		String xuhaoNum = Integer.toString(xuhao);
		if (xuhaoNum.length() < 3)
		{
			for (int j = 0; j < 4 - xuhaoNum.length(); j++)
				xuhaoNum = (new StringBuilder("0")).append(xuhaoNum).toString();

		}
		projectMap.put("xuhao", Integer.valueOf(xuhao));
		Date date = new Date();
		HelperDB.setDateTime("allotdate", projectMap, date);
		HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), projectMap);
		db.insert(projectMap);
		result.put("method.infoMsg", "项目添加成功！");
		result.put("method.url", in.get("url"));
		return result;
	}

	public IMap updateProject(IMap in)
	{
		IMap result = new DataMap();
		IMap userMap = (IMap)in.get("userMap");
		IMap projectMap = (IMap)in.get("project");
		String exprojectid = (String)projectMap.get("id");
		IMap exprojectMap = BeanFactory.getClassBean("com.projectInfo");
		exprojectMap.put("id", exprojectid);
		exprojectMap = db.get(exprojectMap);
		StringBuffer exsb = new StringBuffer();
		int finnum = 0;
		int projectchange = 0;
		String projectName = (String)projectMap.get("projectname");
		String exprojectName = (String)exprojectMap.get("projectname");
		if (!projectName.equals(exprojectName))
			exsb.append((new StringBuilder("将项目名称改为")).append(projectName).append(",").toString());
		String projectcode = (String)projectMap.get("projectcode");
		String exprojectcode = (String)exprojectMap.get("projectcode");
		if (!projectcode.equals(exprojectcode))
		{
			exsb.append((new StringBuilder("将项目编号由")).append(exprojectcode).append("改为").append(projectcode).append(",").toString());
			projectchange++;
		}
		String feeType = (String)projectMap.get("feeType");
		String exfeeType = (String)exprojectMap.get("feeType");
		if (!feeType.equals(exfeeType))
		{
			exsb.append((new StringBuilder("将经费类别由")).append(exfeeType).append("改为").append(feeType).append(",").toString());
			finnum++;
		}
		String projectlevelid = (String)in.get("projectLevel");
		String exprojectLevelid = (String)exprojectMap.get("projectlevelid");
		String projectlevel = (String)exprojectMap.get("projectLevel");
		IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempBean.put("dataItemId", projectlevelid);
		tempBean = db.get(tempBean);
		String projectLevelNo = (String)tempBean.get("dataItemCode");
		String addplevel = (String)tempBean.get("dataItemName");
		if (!projectlevelid.equals(exprojectLevelid))
		{
			exsb.append((new StringBuilder("将项目级别由")).append(projectlevel).append("改为").append(addplevel).append(",").toString());
			finnum++;
		}
		String plantypeid = ((String)in.get("planType")).split("_")[0];
		String explantypeid = (String)exprojectMap.get("plantypeid");
		String plantype = (String)exprojectMap.get("plantype");
		IMap tempplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
		tempplanBean.put("dataItemId", plantypeid);
		tempplanBean = db.get(tempplanBean);
		String planTypeNo = (String)tempplanBean.get("dataItemValue");
		String addplantype = (String)tempplanBean.get("dataItemName");
		if (!plantypeid.equals(explantypeid))
		{
			if ("1".equals(tempplanBean.get("p2")))
				exsb.append((new StringBuilder("将计划类别由")).append(plantype).append("改为").append(addplantype).append("(").append((String)projectMap.get("othertype")).append("),").toString());
			else
				exsb.append((new StringBuilder("将计划类别由")).append(plantype).append("改为").append(addplantype).append(",").toString());
			finnum++;
		} else
		{
			String othertype = (String)projectMap.get("othertype");
			String exothertype = (String)exprojectMap.get("othertype");
			if (!othertype.equals(exothertype))
				exsb.append((new StringBuilder("将其他计划类别由")).append(exothertype).append("改为").append(othertype).append(",").toString());
		}
		String builddate = (String)projectMap.get("builddate");
		String exbuilddate = (String)exprojectMap.get("builddate");
		if (!builddate.equals(exbuilddate))
			exsb.append((new StringBuilder("将立项时间由")).append(exbuilddate).append("改为").append(builddate).append(",").toString());
		String college = (String)projectMap.get("college");
		String excollege = (String)exprojectMap.get("college");
		if (!college.equals(excollege))
			exsb.append((new StringBuilder("将负责人单位由")).append(excollege).append("改为").append(college).append(",").toString());
		String dutyperson = (String)projectMap.get("dutyperson");
		String exdutyperson = (String)exprojectMap.get("dutyperson");
		if (!dutyperson.equals(exdutyperson))
			exsb.append((new StringBuilder("将负责人由")).append(exdutyperson).append("改为").append(dutyperson).append(",").toString());
		double amount = Double.valueOf((String)projectMap.get("amount")).doubleValue();
		double examount = ((Double)exprojectMap.get("amount")).doubleValue();
		if (amount != examount)
			exsb.append((new StringBuilder("将到款金额由")).append(examount).append("改为").append(amount).append(",").toString());
		double managefee = Double.valueOf((String)projectMap.get("managefee")).doubleValue();
		double exmanagefee = ((Double)exprojectMap.get("managefee")).doubleValue();
		if (managefee != exmanagefee)
			exsb.append((new StringBuilder("将管理费由")).append(exmanagefee).append("改为").append(managefee).append(",").toString());
		double keyanfee = Double.valueOf((String)projectMap.get("keyanfee")).doubleValue();
		double exkeyanfee = ((Double)exprojectMap.get("keyanfee")).doubleValue();
		if (keyanfee != exkeyanfee)
			exsb.append((new StringBuilder("将科研费由")).append(exkeyanfee).append("改为").append(keyanfee).append(",").toString());
		double fuzhufee = Double.valueOf((String)projectMap.get("keyanfee")).doubleValue();
		double exfuzhufee = ((Double)exprojectMap.get("keyanfee")).doubleValue();
		if (fuzhufee != exfuzhufee)
			exsb.append((new StringBuilder("将辅助费由")).append(exfuzhufee).append("改为").append(fuzhufee).append(",").toString());
		String pzNo = (String)projectMap.get("pzNo");
		String expzNo = (String)exprojectMap.get("pzNo");
		if (!pzNo.equals(expzNo))
			exsb.append((new StringBuilder("将凭证编号由")).append(expzNo).append("改为").append(pzNo).append(",").toString());
		String isfollow = (String)exprojectMap.get("isfollow");
		String fprojectsort = "";
		String ffintotal = "";
		if (projectchange > 0)
		{
			IMap selMap = new DataMap();
			selMap.put("projectcode", projectcode);
			List projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
			if (!projectList.isEmpty())
			{
				isfollow = "1";
				projectMap.put("isfollow", "1");
				fprojectsort = (String)((IMap)projectList.get(0)).get("projectsort");
				ffintotal = (String)((IMap)projectList.get(0)).get("fintotal");
			} else
			{
				isfollow = "0";
			}
		}
		if (finnum > 0)
		{
			IMap delMap = BeanFactory.getClassBean("com.projectInfo");
			delMap.put("id", exprojectid);
			delMap.put("isValid", "0");
			HelperDB.setModifyInfo(HelperApp.getUserName(userMap), delMap);
			db.update(delMap);
			IMap addMap = BeanFactory.getClassBean("com.projectInfo");
			addMap = projectMap;
			addMap.put("id", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.projectInfo"));
			String fintotal = (new StringBuilder(String.valueOf(feeType))).append(projectLevelNo).append(planTypeNo).toString();
			Calendar c = Calendar.getInstance();
			int yearint = c.get(1);
			String year = Integer.toString(yearint).substring(2, 4);
			addMap.put("inYear", year);
			if ("0".equals(isfollow))
			{
				IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				cplanBean.put("dataItemId", plantypeid);
				cplanBean.put("dataItemName", Integer.toString(yearint));
				cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
				String selfNo = "";
				int plannum = 0;
				if (cplanBean != null)
				{
					String updateplanId = (String)cplanBean.get("dataItemId");
					selfNo = (String)cplanBean.get("dataItemValue");
					updateplanId = (String)cplanBean.get("dataItemId");
					plannum = Integer.valueOf(selfNo).intValue() + 1;
					IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					updateplanBean.put("dataItemId", updateplanId);
					updateplanBean.put("dataItemValue", Integer.valueOf(plannum));
					db.update(updateplanBean);
				} else
				{
					IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
					aplanBean.put("parentId", plantypeid);
					aplanBean.put("dataItemCode", "");
					aplanBean.put("dataItemName", Integer.toString(yearint));
					aplanBean.put("dataItemValue", Integer.valueOf(1));
					aplanBean.put("type", Integer.valueOf(1));
					aplanBean.put("isValid", "1");
					aplanBean.put("isSys", "0");
					aplanBean.put("orderby", Integer.toString(yearint));
					HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);
					db.insert(aplanBean);
					plannum = 1;
				}
				String strNO = Integer.toString(plannum);
				if (strNO.length() < 3)
				{
					for (int i = 0; i < 4 - strNO.length(); i++)
						strNO = (new StringBuilder("0")).append(strNO).toString();

				}
				addMap.put("projectsort", strNO);
				fintotal = (new StringBuilder(String.valueOf(fintotal))).append(year).append(strNO).toString();
				addMap.put("fintotal", fintotal);
			} else
			{
				addMap.put("projectsort", fprojectsort);
				addMap.put("fintotal", ffintotal);
			}
			IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			maxplanBean.put("dataItemName", Integer.toString(yearint));
			maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
			int number = 0;
			int xuhao = 0;
			if (maxplanBean != null)
			{
				number = Integer.valueOf((String)maxplanBean.get("xuhao")).intValue();
				xuhao = number + 1;
				String updateplanId = (String)maxplanBean.get("id");
				IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				updatemaxBean.put("dataItemId", updateplanId);
				updatemaxBean.put("dataItemValue", Integer.valueOf(xuhao));
				db.update(updatemaxBean);
			} else
			{
				IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				xuhaoplanBean.put("dataItemCode", "projectsort");
				xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
				String parentid = (String)xuhaoplanBean.get("dataItemId");
				IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
				inmaxBean.put("parentId", parentid);
				inmaxBean.put("dataItemCode", "");
				inmaxBean.put("dataItemName", Integer.toString(yearint));
				inmaxBean.put("dataItemValue", Integer.valueOf(1));
				inmaxBean.put("type", Integer.valueOf(1));
				inmaxBean.put("isValid", "1");
				inmaxBean.put("isSys", "0");
				inmaxBean.put("orderby", Integer.toString(yearint));
				HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);
				db.insert(inmaxBean);
				xuhao = 1;
			}
			String xuhaoNum = Integer.toString(xuhao);
			if (xuhaoNum.length() < 3)
			{
				for (int j = 0; j < 4 - xuhaoNum.length(); j++)
					xuhaoNum = (new StringBuilder("0")).append(xuhaoNum).toString();

			}
			addMap.put("xuhao", Integer.valueOf(xuhao));
			addMap.put("allotdate", exprojectMap.get("allotdate"));
			addMap.put("projectLevel", addplevel);
			addMap.put("projectlevelid", projectlevelid);
			addMap.put("plantype", addplantype);
			addMap.put("plantypeid", plantypeid);
			addMap.put("isfollow", isfollow);
			HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), addMap);
			db.insert(addMap);
			LogBean log = new LogBean(userMap, "", exsb.toString());
			LogRecordService.saveOperationLog(log, db);
		} else
		{
			if ("0".equals(isfollow))
			{
				String fintotal = (new StringBuilder(String.valueOf(feeType))).append(projectLevelNo).append(planTypeNo).toString();
				Calendar c = Calendar.getInstance();
				int yearint = c.get(1);
				String year = Integer.toString(yearint).substring(2, 4);
				projectMap.put("inYear", year);
				IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
				cplanBean.put("dataItemId", plantypeid);
				cplanBean.put("dataItemName", Integer.toString(yearint));
				cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
				String selfNo = "";
				int plannum = 0;
				if (cplanBean != null)
				{
					String updateplanId = (String)cplanBean.get("dataItemId");
					selfNo = (String)cplanBean.get("dataItemValue");
					updateplanId = (String)cplanBean.get("dataItemId");
					plannum = Integer.valueOf(selfNo).intValue() + 1;
					IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					updateplanBean.put("dataItemId", updateplanId);
					updateplanBean.put("dataItemValue", Integer.valueOf(plannum));
					db.update(updateplanBean);
				} else
				{
					IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
					aplanBean.put("parentId", plantypeid);
					aplanBean.put("dataItemCode", "");
					aplanBean.put("dataItemName", Integer.toString(yearint));
					aplanBean.put("dataItemValue", Integer.valueOf(1));
					aplanBean.put("type", Integer.valueOf(1));
					aplanBean.put("isValid", "1");
					aplanBean.put("isSys", "0");
					aplanBean.put("orderby", Integer.toString(yearint));
					HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);
					db.insert(aplanBean);
					plannum = 1;
				}
				String strNO = Integer.toString(plannum);
				if (strNO.length() < 3)
				{
					for (int i = 0; i < 4 - strNO.length(); i++)
						strNO = (new StringBuilder("0")).append(strNO).toString();

				}
				projectMap.put("projectsort", strNO);
				fintotal = (new StringBuilder(String.valueOf(fintotal))).append(year).append(strNO).toString();
				projectMap.put("fintotal", fintotal);
			} else
			{
				IMap selMap = new DataMap();
				selMap.put("projectcode", projectcode);
				List projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
				if (!projectList.isEmpty())
				{
					fprojectsort = (String)((IMap)projectList.get(0)).get("projectsort");
					ffintotal = (String)((IMap)projectList.get(0)).get("fintotal");
				}
				projectMap.put("projectsort", fprojectsort);
				projectMap.put("fintotal", ffintotal);
			}
			HelperDB.setModifyInfo(HelperApp.getUserName(userMap), projectMap);
			db.update(projectMap);
		}
		result.put("method.infoMsg", "项目修改成功！");
		result.put("method.url", in.get("url"));
		return result;
	}

	public IMap saveProjects(IMap in)
	{
		IMap result;
		String fileUrl;
		String message;
		String messageDetails;
		int err;
		int success;
		int importNum;
		List successMapList;
		InputStream input;
		result = new DataMap();
		HttpServletRequest request = (HttpServletRequest)in.get("request");
		fileUrl = (String)in.get("fileUrl");
		message = "";
		messageDetails = "";
		err = 0;
		success = 0;
		importNum = 0;
		successMapList = new ArrayList();
		input = null;
		fileUrl = (new StringBuilder(String.valueOf(request.getSession().getServletContext().getRealPath("/")))).append(fileUrl).toString();
		try
		{
			input = new BufferedInputStream(new FileInputStream(fileUrl));
			HSSFWorkbook wb = new HSSFWorkbook(input);
			HSSFSheet sheet = wb.getSheetAt(0);
			if (sheet == null)
			{
				messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表不存在，请重新导入").toString();
			} else
			{
				int total = sheet.getLastRowNum();
				message = (new StringBuilder(String.valueOf(message))).append("共有数据").append(total).append("行。<br />").toString();
				importNum = total;
				HSSFRow c = sheet.getRow(0);
				boolean flag = true;
				if (!"经费类别".equals(c.getCell((short)0).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第1列的应为经费类别！<br/>").toString();
					flag = false;
				}
				if (!"项目级别".equals(c.getCell((short)1).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第2列的应为项目级别！<br/>").toString();
					flag = false;
				}
				if (!"计划类别".equals(c.getCell((short)2).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第3列的应为学员姓名！<br/>").toString();
					flag = false;
				}
				if (!"项目编号".equals(c.getCell((short)3).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第4列的应为项目编号！<br/>").toString();
					flag = false;
				}
				if (!"项目名称".equals(c.getCell((short)4).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第5列的应为身份证号！<br/>").toString();
					flag = false;
				}
				if (!"立项时间".equals(c.getCell((short)5).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第6列的应为立项时间！<br/>").toString();
					flag = false;
				}
				if (!"所在单位".equals(c.getCell((short)6).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第7列的应为所在单位！<br/>").toString();
					flag = false;
				}
				if (!"负责人".equals(c.getCell((short)7).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第8列的应为负责人！<br/>").toString();
					flag = false;
				}
				if (!"到款金额（万元）".equals(c.getCell((short)8).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第9列的应为到款金额（万元）！<br/>").toString();
					flag = false;
				}
				if (!"凭证编号".equals(c.getCell((short)9).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第10列的应为凭证编号！<br/>").toString();
					flag = false;
				}
				IMap userMap = (IMap)in.get("userMap");
				Calendar cal = Calendar.getInstance();
				int yearint = cal.get(1);
				String year = Integer.toString(yearint).substring(2, 4);
				for (int j = 1; j < sheet.getLastRowNum() + 1 && flag; j++)
				{
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					String projectId = HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.projectInfo");
					projectInfoMap.put("id", projectId);
					StringBuffer fintotal = new StringBuffer();
					HSSFRow hssfRow = sheet.getRow(j);
					if (hssfRow == null && j != sheet.getLastRowNum())
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第").append(j + 1).append("行内容为空！<br/>").toString();
					String isfollow = "";
					HSSFCell cell3 = hssfRow.getCell((short)3);
					if (cell3 != null)
					{
						String projectcode = cell3.getStringCellValue();
						projectInfoMap.put("projectcode", projectcode);
						String ffintotal = "";
						IMap selMap = new DataMap();
						selMap.put("projectcode", projectcode);
						List projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
						if (!projectList.isEmpty())
						{
							projectInfoMap.put("isfollow", "1");
							projectInfoMap.put("projectsort", ((IMap)projectList.get(0)).get("projectsort"));
							projectInfoMap.put("fintotal", ((IMap)projectList.get(0)).get("fintotal"));
							isfollow = "1";
						} else
						{
							projectInfoMap.put("isfollow", "0");
							isfollow = "0";
						}
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(4).append("列项目编码不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell0 = hssfRow.getCell((short)0);
					if (cell0 != null)
					{
						String feeType = cell0.getStringCellValue();
						projectInfoMap.put("feeType", feeType);
						fintotal.append(feeType);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(1).append("列经费类别不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell1 = hssfRow.getCell((short)1);
					if (cell0 != null)
					{
						String projectlevel = cell1.getStringCellValue();
						String projectlevelNo = projectlevel.substring(0, 2);
						IMap planlevelBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planlevelBean.put("dataItemName", projectlevel);
						List planBeanList = db.getList(planlevelBean, "getDataItemByName", "com.DataItemBaseInfo");
						if (planBeanList.isEmpty())
						{
							messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(2).append("列项目级别不存在！<br/>").toString();
							err++;
							continue;
						}
						projectInfoMap.put("projectlevel", projectlevel);
						projectInfoMap.put("projectlevelid", (String)((IMap)planBeanList.get(0)).get("dataItemId"));
						fintotal.append(projectlevelNo);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(2).append("列项目级别不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell2 = hssfRow.getCell((short)2);
					String strNO = "";
					if (cell2 != null)
					{
						String plantype = cell2.getStringCellValue();
						String plantypeNos = plantype.substring(0, 2);
						String plantypeid = "";
						IMap planBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planBean.put("dataItemName", plantype);
						List planBeanList = db.getList(planBean, "getDataItemByName", "com.DataItemBaseInfo");
						String isother = "0";
						if (planBeanList.isEmpty())
						{
							messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(3).append("列计划类别不存在！<br/>").toString();
							err++;
							continue;
						}
						planBean = (IMap)planBeanList.get(0);
						plantypeid = (String)planBean.get("dataItemId");
						if ("1".equals((String)planBean.get("p2")))
						{
							isother = "1";
							projectInfoMap.put("othertype", plantype);
						}
						if ("0".equals(isfollow))
						{
							IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
							cplanBean.put("dataItemId", planBean.get("dataItemId"));
							cplanBean.put("dataItemName", Integer.toString(yearint));
							cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
							String selfNo = "";
							int plannum = 0;
							if (cplanBean != null)
							{
								String updateplanId = (String)cplanBean.get("dataItemId");
								selfNo = (String)cplanBean.get("dataItemValue");
								updateplanId = (String)cplanBean.get("dataItemId");
								plannum = Integer.valueOf(selfNo).intValue() + 1;
								IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
								updateplanBean.put("dataItemId", updateplanId);
								updateplanBean.put("dataItemValue", Integer.valueOf(plannum));
								db.update(updateplanBean);
							} else
							{
								IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
								aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
								aplanBean.put("parentId", planBean.get("dataItemId"));
								aplanBean.put("dataItemCode", "");
								aplanBean.put("dataItemName", Integer.toString(yearint));
								aplanBean.put("dataItemValue", Integer.valueOf(1));
								aplanBean.put("type", Integer.valueOf(1));
								aplanBean.put("isValid", "1");
								aplanBean.put("isSys", "0");
								aplanBean.put("orderby", Integer.toString(yearint));
								HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);
								db.insert(aplanBean);
								plannum = 1;
							}
							strNO = Integer.toString(plannum);
							if (strNO.length() < 3)
							{
								for (int i = 0; i < 4 - strNO.length(); i++)
									strNO = (new StringBuilder("0")).append(strNO).toString();

							}
							projectInfoMap.put("projectsort", strNO);
							fintotal.append(plantypeNos);
						}
						projectInfoMap.put("plantype", plantype);
						projectInfoMap.put("plantypeid", plantypeid);
						projectInfoMap.put("isother", isother);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(3).append("列计划类别不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell4 = hssfRow.getCell((short)4);
					if (cell4 != null)
					{
						String projectname = cell4.getStringCellValue();
						projectInfoMap.put("projectname", projectname);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(5).append("列项目名称不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell5 = hssfRow.getCell((short)5);
					if (cell5 != null)
					{
						String builddate = cell5.getStringCellValue();
						projectInfoMap.put("builddate", builddate);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(6).append("列立项时间不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell6 = hssfRow.getCell((short)6);
					if (cell6 != null)
					{
						String college = cell6.getStringCellValue();
						projectInfoMap.put("college", college);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(7).append("列所在单位不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell7 = hssfRow.getCell((short)7);
					if (cell7 != null)
					{
						String dutyperson = cell7.getStringCellValue();
						projectInfoMap.put("dutyperson", dutyperson);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(8).append("列负责人不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell8 = hssfRow.getCell((short)8);
					if (cell8 != null)
					{
						String amount = cell8.getStringCellValue();
						projectInfoMap.put("amount", Double.valueOf((Double.valueOf(amount).doubleValue() * 1000000D * 100D) / 100000000D));
						//projectInfoMap.put("managefee", Double.valueOf((Double.valueOf(amount).doubleValue() * 10000D * 4D) / 1000000D));
						//projectInfoMap.put("keyanfee", Double.valueOf((Double.valueOf(amount).doubleValue() * 10000D * 96D) / 1000000D));
						HSSFCell cell11 = hssfRow.getCell((short)11);
						if(cell11!=null) {
							String managefee = cell11.getStringCellValue();
							//projectInfoMap.put("managefee", Double.valueOf((Double.valueOf(amount).doubleValue() * 10000D * 4D) / 1000000D));
							projectInfoMap.put("managefee", Double.valueOf((Double.valueOf(managefee).doubleValue() * 10000D * 100D) / 1000000D));
						}
						HSSFCell cell12 = hssfRow.getCell((short)12);
						if(cell12!=null) {
							String keyanfee = cell12.getStringCellValue();
							//projectInfoMap.put("keyanfee", Double.valueOf((Double.valueOf(amount).doubleValue() * 10000D * 4D) / 1000000D));
							projectInfoMap.put("keyanfee", Double.valueOf((Double.valueOf(keyanfee).doubleValue() * 10000D * 100D) / 1000000D));
						}
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(9).append("列付款金额不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell9 = hssfRow.getCell((short)9);
					if (cell9 != null)
					{
						String pzNo = cell9.getStringCellValue();
						projectInfoMap.put("pzNo", pzNo);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(10).append("列凭证编号不能为空！<br/>").toString();
						err++;
						continue;
					}
					IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					maxplanBean.put("dataItemName", Integer.toString(yearint));
					maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
					int number = 0;
					int xuhao = 0;
					if (maxplanBean != null)
					{
						number = Integer.valueOf((String)maxplanBean.get("xuhao")).intValue();
						xuhao = number + 1;
						String updateplanId = (String)maxplanBean.get("id");
						IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						updatemaxBean.put("dataItemId", updateplanId);
						updatemaxBean.put("dataItemValue", Integer.valueOf(xuhao));
						db.update(updatemaxBean);
					} else
					{
						IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						xuhaoplanBean.put("dataItemCode", "projectsort");
						xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
						String parentid = (String)xuhaoplanBean.get("dataItemId");
						IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
						inmaxBean.put("parentId", parentid);
						inmaxBean.put("dataItemCode", "");
						inmaxBean.put("dataItemName", Integer.toString(yearint));
						inmaxBean.put("dataItemValue", Integer.valueOf(1));
						inmaxBean.put("type", Integer.valueOf(1));
						inmaxBean.put("isValid", "1");
						inmaxBean.put("isSys", "0");
						inmaxBean.put("orderby", Integer.toString(yearint));
						HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);
						db.insert(inmaxBean);
						xuhao = 1;
					}
					String xuhaoNum = Integer.toString(xuhao);
					if (xuhaoNum.length() < 3)
					{
						for (int k = 0; k < 4 - xuhaoNum.length(); k++)
							xuhaoNum = (new StringBuilder("0")).append(xuhaoNum).toString();

					}
					projectInfoMap.put("xuhao", Integer.valueOf(xuhao));
					projectInfoMap.put("inYear", year);
					fintotal.append(year);
					fintotal.append(strNO);
					if ("0".equals(isfollow))
						projectInfoMap.put("fintotal", fintotal.toString());
					successMapList.add(projectInfoMap);
					success++;
				}

			}
		}
		catch (Exception e)
		{
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

	public IMap toupdateProject(IMap in)
	{
		IMap result = new DataMap();
		String id = (String)in.get("pid");
		IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
		projectInfoMap.put("id", id);
		projectInfoMap = db.get(projectInfoMap);
		String levelid = (String)projectInfoMap.get("projectlevelid");
		String typeid = (String)projectInfoMap.get("plantypeid");
		if (!"".equals(levelid) && levelid != null)
		{
			IMap tempBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			tempBean.put("dataItemId", levelid);
			List planTypeList = db.getList(tempBean, "dataItemBaseInfoList", "com.DataItemBaseInfo");
			result.put("planTypeList", planTypeList);
		}
		if (!"".equals(typeid) && typeid != null)
		{
			IMap typeBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
			typeBean.put("dataItemId", typeid);
			typeBean = db.get(typeBean);
			String p2 = (String)typeBean.get("p2");
			result.put("p2", p2);
		}
		result.put("project", projectInfoMap);
		return result;
	}

	public IMap viewProject(IMap in)
	{
		IMap result = new DataMap();
		String id = (String)in.get("id");
		IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
		projectInfoMap.put("id", id);
		projectInfoMap = db.get(projectInfoMap);
		result.put("project", projectInfoMap);
		return result;
	}

	public IMap deleteProject(IMap in)
	{
		IMap result = new DataMap();
		IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
		IMap userMap = (IMap)in.get("userMap");
		projectInfoMap.put("id", in.get("pid"));
		projectInfoMap.put("isValid", "0");
		HelperDB.setModifyInfo(HelperApp.getUserName(userMap), projectInfoMap);
		db.update(projectInfoMap);
		result.put("method.infoMsg", "项目删除成功！");
		result.put("method.url", in.get("url"));
		return result;
	}

	public IMap viewHistory(IMap in)
	{
		IMap result = new DataMap();
		List list = db.getList(in, "getprojectList", "com.projectInfo");
		result.put("projectList", list);
		return result;
	}

	public IMap exportProject(IMap in)
	{
		IMap result = new DataMap();
		HttpServletResponse response = (HttpServletResponse)in.get("response");
		HttpServletRequest request = (HttpServletRequest)in.get("request");
		String path = request.getSession().getServletContext().getRealPath("/");
		String projectid[] = (String[])in.get("projectid");
		IMap map = new DataMap();
		map.put("ids", projectid);
		List projectlist = db.getList(map, "getprojectListasc", "");
		IMap freemap = new DataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
		List projectlists = new ArrayList();
		for (int i = 0; i < projectlist.size(); i++)
		{
			IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
			projectInfoMap = (IMap)projectlist.get(i);
			projectInfoMap.put("allotdate", sdf.format(projectInfoMap.get("allotdate")));
			projectlists.add(projectInfoMap);
		}

		freemap.put("projectlist", projectlists);
		freemap.put("issign", in.get("issign"));
		String body = FreemarkUtil.transform((new StringBuilder(String.valueOf(path))).append("temp").toString(), "zxky_jkfpb.ftl", freemap);
		PDFUtil.getPdf("建卡分配表", (new StringBuilder(String.valueOf(path))).append("temp").toString(), body, request, response);
		return result;
	}

	public IMap toViewHistory(IMap in)
	{
		IMap result = new DataMap();
		HttpServletRequest request = (HttpServletRequest)in.get("request");
		Page page = db.pageQuery(in, "getLogList", "com.LogRecord", Integer.parseInt((String)in.get("currentPageNO")), Integer.parseInt((String)in.get("perCount")));
		page.setAction(request);
		result.put("page", page);
		return result;
	}

	public IMap saveHistoryProjects(IMap in)
	{
		IMap result;
		String fileUrl;
		String message;
		String messageDetails;
		int err;
		int success;
		int importNum;
		List successMapList;
		InputStream input;
		result = new DataMap();
		HttpServletRequest request = (HttpServletRequest)in.get("request");
		fileUrl = (String)in.get("fileUrl");
		message = "";
		messageDetails = "";
		err = 0;
		success = 0;
		importNum = 0;
		successMapList = new ArrayList();
		input = null;
		fileUrl = (new StringBuilder(String.valueOf(request.getSession().getServletContext().getRealPath("/")))).append(fileUrl).toString();
		try
		{
			input = new BufferedInputStream(new FileInputStream(fileUrl));
			HSSFWorkbook wb = new HSSFWorkbook(input);
			HSSFSheet sheet = wb.getSheetAt(0);
			if (sheet == null)
			{
				messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表不存在，请重新导入").toString();
			} else
			{
				int total = sheet.getLastRowNum();
				message = (new StringBuilder(String.valueOf(message))).append("共有数据").append(total).append("行。<br />").toString();
				importNum = total;
				HSSFRow c = sheet.getRow(0);
				boolean flag = true;
				if (!"经费类别".equals(c.getCell((short)0).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第1列的应为经费类别！<br/>").toString();
					flag = false;
				}
				if (!"项目级别".equals(c.getCell((short)1).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第2列的应为项目级别！<br/>").toString();
					flag = false;
				}
				if (!"计划类别".equals(c.getCell((short)2).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第3列的应为学员姓名！<br/>").toString();
					flag = false;
				}
				if (!"项目编号".equals(c.getCell((short)3).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第4列的应为项目编号！<br/>").toString();
					flag = false;
				}
				if (!"项目名称".equals(c.getCell((short)4).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第5列的应为身份证号！<br/>").toString();
					flag = false;
				}
				if (!"立项时间".equals(c.getCell((short)5).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第6列的应为立项时间！<br/>").toString();
					flag = false;
				}
				if (!"所在单位".equals(c.getCell((short)6).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第7列的应为所在单位！<br/>").toString();
					flag = false;
				}
				if (!"负责人".equals(c.getCell((short)7).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第8列的应为负责人！<br/>").toString();
					flag = false;
				}
				if (!"到款金额（万元）".equals(c.getCell((short)8).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第9列的应为到款金额（万元）！<br/>").toString();
					flag = false;
				}
				if (!"凭证编号".equals(c.getCell((short)9).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第10列的应为凭证编号！<br/>").toString();
					flag = false;
				}
				if (!"开单时间".equals(c.getCell((short)10).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第11列的应为开单时间！<br/>").toString();
					flag = false;
				}
				if (!"其他计划类别".equals(c.getCell((short)11).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第12列的应为其他计划类别！<br/>").toString();
					flag = false;
				}
				if (!"制表时间".equals(c.getCell((short)12).getStringCellValue().trim()))
				{
					messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第13列的应为制表时间！<br/>").toString();
					flag = false;
				}
				IMap userMap = (IMap)in.get("userMap");
				for (int j = 1; j < sheet.getLastRowNum() + 1 && flag; j++)
				{
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					String projectId = HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.projectInfo");
					projectInfoMap.put("id", projectId);
					StringBuffer fintotal = new StringBuffer();
					HSSFRow hssfRow = sheet.getRow(j);
					if (hssfRow == null && j != sheet.getLastRowNum())
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第").append(j + 1).append("行内容为空！<br/>").toString();
					HSSFCell cell0 = hssfRow.getCell((short)0);
					if (cell0 != null)
					{
						String feeType = cell0.getStringCellValue();
						projectInfoMap.put("feeType", feeType);
						fintotal.append(feeType);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(1).append("列经费类别不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell10 = hssfRow.getCell((short)10);
					String inyear = "";
					String year = "";
					if (cell10 != null)
					{
						inyear = cell10.getStringCellValue();
						year = inyear.substring(2, 4);
						projectInfoMap.put("inYear", year);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(11).append("列开单时间不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell1 = hssfRow.getCell((short)1);
					String levelid = "";
					if (cell0 != null)
					{
						String projectlevel = cell1.getStringCellValue();
						String projectlevelNo = projectlevel.substring(0, 2);
						IMap planlevelBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planlevelBean.put("dataItemName", projectlevel);
						List planBeanList = db.getList(planlevelBean, "getDataItemByName", "com.DataItemBaseInfo");
						System.out.println("==========================================================");
						System.out.println((new StringBuilder("=========")).append(planBeanList.size()).append("======================================================").toString());
						System.out.println("==========================================================");
						if (planBeanList.isEmpty())
						{
							messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(2).append("列项目级别不存在！<br/>").toString();
							err++;
							continue;
						}
						projectInfoMap.put("projectlevel", projectlevel);
						projectInfoMap.put("projectlevelid", (String)((IMap)planBeanList.get(0)).get("dataItemId"));
						levelid = (String)((IMap)planBeanList.get(0)).get("dataItemId");
						fintotal.append(projectlevelNo);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(2).append("列项目级别不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell2 = hssfRow.getCell((short)2);
					String strNO = "";
					if (cell2 != null)
					{
						String plantype = cell2.getStringCellValue();
						String plantypeNos = plantype.substring(0, 2);
						IMap planBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						planBean.put("dataItemName", plantype);
						planBean.put("parentid", levelid);
						List planBeanList = db.getList(planBean, "getDataItemByName", "com.DataItemBaseInfo");
						String isother = "0";
						if (planBeanList.isEmpty())
						{
							messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(3).append("列计划类别不存在！<br/>").toString();
							err++;
							continue;
						}
						planBean = (IMap)planBeanList.get(0);
						if ("1".equals((String)planBean.get("p2")))
							isother = "1";
						IMap cplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						cplanBean.put("dataItemId", planBean.get("dataItemId"));
						cplanBean.put("dataItemName", inyear);
						cplanBean = db.get(cplanBean, "getDatasByYear", "com.DataItemBaseInfo");
						String selfNo = "";
						int plannum = 0;
						if (cplanBean != null)
						{
							String updateplanId = (String)cplanBean.get("dataItemId");
							selfNo = (String)cplanBean.get("dataItemValue");
							updateplanId = (String)cplanBean.get("dataItemId");
							plannum = Integer.valueOf(selfNo).intValue() + 1;
							IMap updateplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
							updateplanBean.put("dataItemId", updateplanId);
							updateplanBean.put("dataItemValue", Integer.valueOf(plannum));
							db.update(updateplanBean);
						} else
						{
							IMap aplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
							aplanBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
							aplanBean.put("parentId", planBean.get("dataItemId"));
							aplanBean.put("dataItemCode", "");
							aplanBean.put("dataItemName", inyear);
							aplanBean.put("dataItemValue", Integer.valueOf(1));
							aplanBean.put("type", Integer.valueOf(1));
							aplanBean.put("isValid", "1");
							aplanBean.put("isSys", "0");
							aplanBean.put("orderby", inyear);
							HelperDB.setCreateInfo(HelperApp.getUserName(userMap), aplanBean);
							db.insert(aplanBean);
							plannum = 1;
						}
						strNO = Integer.toString(plannum);
						if (strNO.length() < 3)
						{
							for (int i = 0; i < 4 - strNO.length(); i++)
								strNO = (new StringBuilder("0")).append(strNO).toString();

						}
						projectInfoMap.put("projectsort", strNO);
						fintotal.append(plantypeNos);
						projectInfoMap.put("plantype", plantype);
						projectInfoMap.put("plantypeid", planBean.get("dataItemId"));
						projectInfoMap.put("isother", isother);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(3).append("列计划类别不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell3 = hssfRow.getCell((short)3);
					if (cell3 != null)
					{
						String projectcode = cell3.getStringCellValue();
						projectInfoMap.put("projectcode", projectcode);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(4).append("列项目编码不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell4 = hssfRow.getCell((short)4);
					if (cell4 != null)
					{
						String projectname = cell4.getStringCellValue();
						projectInfoMap.put("projectname", projectname);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(5).append("列项目名称不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell5 = hssfRow.getCell((short)5);
					if (cell5 != null)
					{
						String builddate = cell5.getStringCellValue();
						projectInfoMap.put("builddate", builddate);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(6).append("列立项时间不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell6 = hssfRow.getCell((short)6);
					if (cell6 != null)
					{
						String college = cell6.getStringCellValue();
						projectInfoMap.put("college", college);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(7).append("列所在单位不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell7 = hssfRow.getCell((short)7);
					if (cell7 != null)
					{
						String dutyperson = cell7.getStringCellValue();
						projectInfoMap.put("dutyperson", dutyperson);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(8).append("列负责人不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell8 = hssfRow.getCell((short)8);
					if (cell8 != null)
					{
						String amount = cell8.getStringCellValue();
						projectInfoMap.put("amount", Double.valueOf((Double.valueOf(amount).doubleValue() * 1000000D * 100D) / 100000000D));
						HSSFCell cell12 = hssfRow.getCell((short)12);
						if(cell12!=null) {
							String managefee = cell12.getStringCellValue();
							//projectInfoMap.put("managefee", Double.valueOf((Double.valueOf(amount).doubleValue() * 10000D * 4D) / 1000000D));
							projectInfoMap.put("managefee", Double.valueOf((Double.valueOf(managefee).doubleValue() * 10000D * 100D) / 1000000D));
						}
						HSSFCell cell13 = hssfRow.getCell((short)13);
						if(cell13!=null) {
							String keyanfee = cell13.getStringCellValue();
							//projectInfoMap.put("keyanfee", Double.valueOf((Double.valueOf(amount).doubleValue() * 10000D * 4D) / 1000000D));
							projectInfoMap.put("keyanfee", Double.valueOf((Double.valueOf(keyanfee).doubleValue() * 10000D * 100D) / 1000000D));
						}
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(9).append("列付款金额不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell9 = hssfRow.getCell((short)9);
					if (cell9 != null)
					{
						String pzNo = cell9.getStringCellValue();
						projectInfoMap.put("pzNo", pzNo);
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(10).append("列凭证编号不能为空！<br/>").toString();
						err++;
						continue;
					}
					HSSFCell cell11 = hssfRow.getCell((short)11);
					if (cell11 != null)
					{
						String othertype = cell11.getStringCellValue();
						projectInfoMap.put("othertype", othertype);
					}
					IMap maxplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
					maxplanBean.put("dataItemName", inyear);
					maxplanBean = db.get(maxplanBean, "getMaxProjectNum", null);
					int number = 0;
					int xuhao = 0;
					if (maxplanBean != null)
					{
						number = Integer.valueOf((String)maxplanBean.get("xuhao")).intValue();
						xuhao = number + 1;
						String updateplanId = (String)maxplanBean.get("id");
						IMap updatemaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						updatemaxBean.put("dataItemId", updateplanId);
						updatemaxBean.put("dataItemValue", Integer.valueOf(xuhao));
						db.update(updatemaxBean);
					} else
					{
						IMap xuhaoplanBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						xuhaoplanBean.put("dataItemCode", "projectsort");
						xuhaoplanBean = db.get(xuhaoplanBean, "getDataDictionary", "com.DataItemBaseInfo");
						String parentid = (String)xuhaoplanBean.get("dataItemId");
						IMap inmaxBean = BeanFactory.getClassBean("com.DataItemBaseInfo");
						inmaxBean.put("dataItemId", HelperApp.getAutoIncrementPKID(HelperApp.getPackageName(), "com.DataItemBaseInfo"));
						inmaxBean.put("parentId", parentid);
						inmaxBean.put("dataItemCode", "");
						inmaxBean.put("dataItemName", inyear);
						inmaxBean.put("dataItemValue", Integer.valueOf(1));
						inmaxBean.put("type", Integer.valueOf(1));
						inmaxBean.put("isValid", "1");
						inmaxBean.put("isSys", "0");
						inmaxBean.put("orderby", inyear);
						HelperDB.setCreateInfo(HelperApp.getUserName(userMap), inmaxBean);
						db.insert(inmaxBean);
						xuhao = 1;
					}
					HSSFCell cell12 = hssfRow.getCell((short)12);
					if (cell12 != null)
					{
						String allotdateStr = cell12.getStringCellValue();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
						projectInfoMap.put("allotdate", sdf.format(DateTimeUtil.str2Date("yyyy/MM/dd hh:mm:ss", allotdateStr)));
					} else
					{
						messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("Excel图表中第<font color='red'>").append(j + 1).append("</font>行第").append(13).append("列制表时间不能为空！<br/>").toString();
						err++;
						continue;
					}
					String xuhaoNum = Integer.toString(xuhao);
					if (xuhaoNum.length() < 3)
					{
						for (int k = 0; k < 4 - xuhaoNum.length(); k++)
							xuhaoNum = (new StringBuilder("0")).append(xuhaoNum).toString();

					}
					projectInfoMap.put("xuhao", Integer.valueOf(xuhao));
					fintotal.append(year);
					fintotal.append(strNO);
					projectInfoMap.put("fintotal", fintotal.toString());
					successMapList.add(projectInfoMap);
					success++;
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new BusinessException("导入失败!");
		}finally{
			try
			{
				input.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			int flag = 0;
			int add = 0;
			int update = 0;
			if (importNum == success)
			{
				IMap userMap = (IMap)in.get("userMap");
				for (int i = 0; i < successMapList.size(); i++)
				{
					IMap projectInfoMap = BeanFactory.getClassBean("com.projectInfo");
					projectInfoMap = (IMap)successMapList.get(i);
					IMap selMap = new DataMap();
					selMap.put("projectcode", projectInfoMap.get("projectcode"));
					List projectList = db.getList(selMap, "getprojectList", "com.projectInfo");
					if (!projectList.isEmpty())
						projectInfoMap.put("isfollow", "1");
					else
						projectInfoMap.put("isfollow", "0");
					HelperDB.setCreate4isValid(HelperApp.getUserName(userMap), projectInfoMap);
					db.insert(projectInfoMap);
				}

				flag = 1;
			} else
			{
				File file = new File(fileUrl);
				if (file.exists())
					file.delete();
			}
			if (messageDetails == "")
				messageDetails = (new StringBuilder(String.valueOf(messageDetails))).append("暂无。").toString();
			message = (new StringBuilder(String.valueOf(message))).append("需导入：").append(importNum).append("条，正确数据：").append(success).append("条。<br/>").toString();
			message = (new StringBuilder(String.valueOf(message))).append("错误数据：<font color='red'>").append(err).append("</font>条。<br/>").toString();
			message = (new StringBuilder(String.valueOf(message))).append("错误信息：<br/>").append(messageDetails).toString();
			result.put("message", message);
			result.put("flag", Integer.valueOf(flag));
		}
		return result;
	}
}