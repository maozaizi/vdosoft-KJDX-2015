﻿package com.u2a.framework.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.brick.data.DataMap;
import com.brick.data.IMap;

/**      
 * 类名称：Util   
 * 类描述：系统公共类   
 * 创建人：庞海超 
 * 创建时间：2013年3月18日 11:10:03
 */

@SuppressWarnings("unchecked")
public class Util {
	/**
	 * 判断字符是否为空
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.length() > 0) {
			return false;
		}
		return true;
	}
	/**
	 * 生成空json
	 * 
	 * @param name
	 * @return
	 */
	public static String cerateEmptyJson(String name) {
		IMap m = new DataMap();
		JSONObject j = JSONObject.fromObject(m);
		return j.toString();
	}

	/**
	 * json 转imap
	 * 
	 * @param json
	 * @return
	 */
	public static IMap json2IMap(Map json) {
		IMap m = new DataMap();
		if (json != null) {
			m.putAll(json);
		}
		return m;
	}

	/**
	 * json 转imap
	 * 
	 * @param json
	 * @return
	 */
	public static IMap json2IMap(String json) {
		IMap m = new DataMap();
		if (Util.checkNotNull(json)) {
			JSONObject o = JSONObject.fromObject(json);
			m.putAll(o);
		}
		return m;
	}

	public static List json2List(String json) {
		List list = new ArrayList();
		if (Util.checkNotNull(json)) {
			JSONArray o = JSONArray.fromObject(json);
			list.addAll(o);
		}
		return list;
	}

	/** 
	 * @Description 判断为空
	 * @param @param value
	 * @return boolean   
	 * @author panghc
	 * @date 2013年3月15日 10:41:56
	 */

	public static boolean checkNull(Object value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;

	}

	/** 
	 * @Description 判断不为空
	 * @param @param value
	 * @return boolean   
	 * @author panghc
	 * @date 2013年3月15日 10:44:19
	 */

	public static boolean checkNotNull(Object value) {
		if (value == null || "".equals(value)) {
			return false;
		}
		return true;
	}

	/** 
	 * @Description 判断不为空
	 * @param @param value
	 * @return boolean   
	 * @author panghc
	 * @date 2013年3月15日 10:44:19
	 */
	public static boolean checkNotNull(Object[] values) {
		if (checkNull(values))
			return false;
		if (values.length <= 0)
			return false;
		for (Object value : values) {
			if (!checkNotNull(value)) {
				return false;
			}
		}
		return true;
	}

	/** 
	 * @Description 判断不为空
	 * @param @param value
	 * @return boolean   
	 * @author panghc
	 * @date 2013年3月15日 10:44:19
	 */
	public static boolean checkNotNull(String[] values) {
		if (values == null || values.length <= 0)
			return false;
		for (String value : values) {
			if (!checkNotNull(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串中是否仅包含字母，数字，下划线
	 */
	public static boolean isWord(String inputString) {
		if (inputString != null) {
			return inputString.matches("^\\w+$");
		} else {
			return false;
		}
	}

	/**
	 * 是否合法的用户名<br>
	 * 4-16位,字母,数字,下划线,且不能以下划线开头,结尾
	 */
	public static boolean isUserName(String inputString) {
		if (inputString != null) {
			return inputString.matches("^[A-Za-z0-9]\\w{2,14}[A-Za-z0-9]$");
		} else {
			return false;
		}
	}

	/**
	 * 检测email地址是否合法
	 */
	public static boolean isMail(String smail) {
		Pattern pattern = Pattern.compile(
				"[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",
				Pattern.CASE_INSENSITIVE);
		//		java.util.regex.Pattern p = Pattern
		//				.compile("\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");
		return pattern.matcher(smail).matches();
	}

	/**
	 * 检测是否为合法的电话号码
	 */
	public static boolean isPhoneNumber(String num) {
		return Pattern.matches("(\\(\\d{3}\\)|\\d{3,4}-)?\\d{7,8}$", num);
	}

	/**
	 * 检测是否为合法的手机号码
	 */
	public static boolean isMobileNumber(String num) {
		return Pattern.matches("0?(13|15|18)\\d{9}", num);
	}

	/**
	 * 检测是否为合法的邮编
	 */
	public static boolean isZipCodeNumber(String num) {
		return Pattern.matches("\\d{6}", num);
	}

	/**
	 * 判断字符是不是汉字
	 */
	public static boolean isCharacter(String str) {
		boolean temp = false;
		temp = str.matches("[\\u4e00-\\u9fa5]");
		return temp;
	}

	/**
	 * 删除字符串中非字母，数字，下划线的字符
	 */
	public static String removeCharIsNotWord(String inputString) {
		if (inputString == null) {
			return "";
		}
		return inputString.replaceAll("[^\\w]+", "");
	}

	/**
	 * 删除字符串中非数字，字母，下划线，汉字的字符
	 */
	public static String doBBSUsernameParttern(String inputString) {
		if (inputString == null) {
			return "";
		}
		return inputString.replaceAll("[^\\u4e00-\\u9fa5\\w]+", "");
	}

	/**
	 * 将字符串分割为数组
	 */
	public static Serializable[] splite(String str, String f) {

		if (checkNotNull(str)) {
			StringTokenizer a = new StringTokenizer(str, f);
			int i = 0;
			Serializable[] strs = new Serializable[a.countTokens()];
			if (a.countTokens() > 0) {
				try {
					while (a.hasMoreTokens()) {
						strs[i] = a.nextToken();
						i++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
//					System.out.println("数组越界");
				}

			}
			return strs;
		} else {
			Serializable[] o = {};
			return o;
		}
	}

	/**
	 * 将输入的数组转换为字符串
	 */
	public static String spliteToString(Serializable[] array, String f) {
		String strs = "";
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (i < array.length - 1) {
					strs += array[i] + f;
				} else {
					strs += array[i];
				}
			}
		}
		return strs;
	}

	/**
	 * 取随机数 长度为n
	 */
	public static String random(int n) {
		Random randomNumber = new Random();
		String s = "";
		for (int i = 0; i < n; i++) {
			int a = (int) (Math.random() * 10) + 1;
			s += a;
		}
		return s;
	}

	/**
	 * 取随字母 长度为n
	 */
	public static String randomChar(int n) {

		String str = "";

		char[] cha = new char[52];
		int i = 0;
		for (; i < 26; i++) {
			cha[i] = (char) (65 + i);
		}
		for (; i < 52; i++) {
			cha[i] = (char) (71 + i);
		}
		//cha数组中，此时元素为A_Za-z
		Random random = new Random();
		for (i = 0; i < n; i++) {
			int rand = random.nextInt(52);
			str = str + cha[rand];
		}
		return str;
	}

	/**
	 * 取随字母+数字 长度为n
	 */
	public static String randomString(int n) {

		String str = "";

		char[] cha = new char[62];
		int i = 0;
		for (; i < 26; i++) {
			cha[i] = (char) (65 + i);
		}
		for (; i < 52; i++) {
			cha[i] = (char) (71 + i);
		}
		for (; i < 62; i++) {
			cha[i] = (char) (-4 + i);
		}
		//cha数组中，此时元素为A_Za-z0-9
		Random random = new Random();
		for (i = 0; i < n; i++) {
			int rand = random.nextInt(62);
			str = str + cha[rand];
		}
		return str;
	}

	/**
	 * 产生文件名:yyyyMMddHHmmss,并在后面加n位随机数
	 */
	public static String createFileName(int n) {
		String date = DateTimeUtil.getLocalDateStr("yyyyMMddHHmmss");
		// 取4位随机数
		String b = String.valueOf(Math.random());
		String c = b.substring(2, n + 2);
		return date + c;
	}

	/**
	 * unicode转码
	 */
	public static String unicodeEncoding(String str) {
		StringBuffer unicodeBytes = new StringBuffer();
		for (int byteIndex = 0; byteIndex < str.length(); byteIndex++) {
			String hexB = Integer.toHexString(str.charAt(byteIndex));
			unicodeBytes.append("\\u");
			if (hexB.length() <= 2) {
				unicodeBytes.append("00");
			}
			unicodeBytes.append(hexB);
		}

		return unicodeBytes.toString();
	}

	/**
	 * 1,2,3,4,5,6,7  换成一，二,三，四，五，六，日
	 */
	public static String toWeekDay(String day) {
		String type = "";

		if ("1".equals(day)) {
			type = "一";
			return type;
		}
		if ("2".equals(day)) {
			type = "二";
			return type;
		}
		if ("3".equals(day)) {
			type = "三";
			return type;
		}
		if ("4".equals(day)) {
			type = "四";
			return type;
		}
		if ("5".equals(day)) {
			type = "五";
			return type;
		}
		if ("6".equals(day)) {
			type = "六";
			return type;
		}
		if ("7".equals(day)) {
			type = "日";
			return type;
		}
		if ("0".equals(day)) {
			type = "日";
			return type;
		}
		return type;
	}

	/**
	 * 0,1,2,3,4,5,6,7,8,9,10换成零,一，二,三，四，五，六，七，八，九，十
	 */
	public static String toChinese(String day) {
		String type = "";

		if ("0".equals(day)) {
			type = "零";
			return type;
		}
		if ("1".equals(day)) {
			type = "一";
			return type;
		}
		if ("2".equals(day)) {
			type = "二";
			return type;
		}
		if ("3".equals(day)) {
			type = "三";
			return type;
		}
		if ("4".equals(day)) {
			type = "四";
			return type;
		}
		if ("5".equals(day)) {
			type = "五";
			return type;
		}
		if ("6".equals(day)) {
			type = "六";
			return type;
		}
		if ("7".equals(day)) {
			type = "七";
			return type;
		}
		if ("8".equals(day)) {
			type = "八";
			return type;
		}
		if ("9".equals(day)) {
			type = "九";
			return type;
		}
		if ("10".equals(day)) {
			type = "十";
			return type;
		}
		return type;
	}

	/**
	 * 按绝对长度截取字符串，并在后面加pointLeng个点，汉字占两位 
	 * 形势：abcdef... 
	 * 如果结束处刚好是半个汉字，则少取一位
	 */

	public static String subText(String str, int leng, int pointLeng) {
		StringBuffer res = new StringBuffer("");
		if (str != null) {
			int length = str.getBytes().length;//字符串的绝对长度
			if (length > leng) {
				int position = pointLeng;//已取字符的位置长度(字符一个,汉字两个,不按绝对长度算)
				int i = 0;
				for (i = 0; i < str.length(); i++) {
					String t = str.substring(i, i + 1);

					int bytelength = t.getBytes().length;

					if (bytelength > 1) {
						position = position + 2;
					} else {
						position = position + 1;
					}
					if (position == leng) {
						res.append(t);
						break;
					}
					if (position > leng) {
						break;
					}
					res.append(t);
				}//for
				if (i < str.length()) {
					//要不要加点，加几个点
					if (pointLeng > 0) {
						for (i = 0; i <= pointLeng; i++)
							res.append(".");
					}
				}
			} else {
				res.append(str);
			}//length > leng
		} //str null
		return res.toString();
	}

	/**
	 * 把文本中的html字符转换成实体：< > ' " $ % m²
	 */
	public static String HTMLEncode(String text) {
		String a = "";
		if (text == null || text == "") {
			return "";
		}
		a = text.replaceAll("<", "&lt;");
		a = a.replaceAll(">", "&gt;");
		a = a.replaceAll("\'", "&#39;");
		a = a.replaceAll("\"", "&quot;");
		a = a.replaceAll("\\$", "&#36;");
		a = a.replaceAll("%", "&#37;");
		a = a.replaceAll("\n", "<br/>");
		a = a.replaceAll(" ", "&nbsp;");
		a = a.replaceAll("m²", "m&sup2;");
		return a;
	}

	/**
	 * 把实体换回html字符，用于文本域中的显示：< > ' " $ %
	 */
	public static String HTMLTags(String text) {
		String a = "";
		if (text == null || text == "") {
			return "";
		}
		a = text.replaceAll("&lt;", "<");
		a = a.replaceAll("&gt;", ">");
		a = a.replaceAll("&#39;", "\'");
		a = a.replaceAll("&quot;", "\"");
		a = a.replaceAll("&#36;", "\\$");
		a = a.replaceAll("&#37;", "%");
		return a;
	}

	/**
	 * 把文本中的html字符删除：< > ' " $ %
	 */
	public static String RemoveHTMLTags(String text) {
		String a = "";
		if (text == null || text == "") {
			return "";
		}
		a = text.replaceAll("<", "");
		a = a.replaceAll(">", "");
		a = a.replaceAll("\'", "");
		a = a.replaceAll("\"", "");
		a = a.replaceAll("\\$", "");
		a = a.replaceAll("%", "");
		a = a.replaceAll("\n", "");
		return a;
	}

	/**
	 * 删除文本中的html标签
	 */
	public static String RemoveHTMLTags2(String inputString) {
		String input = inputString; // 含html标签的字符串
		if (input == null || input.trim().equals("")) {
			return "";
		}
		String htmlStr = inputString; //含html标签的字符串 
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
			String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); //过滤script标签 
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); //过滤style标签 
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); //过滤html标签 
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		textStr = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		textStr = textStr.replaceAll("[(/>)<]", "");
		textStr = textStr.replaceAll("</?[^>]+>", "").trim();
		textStr = textStr.replaceAll("<", "");
		textStr = textStr.replaceAll(">", "");
		textStr = textStr.replaceAll("\'", "");
		textStr = textStr.replaceAll("\"", "");
		textStr = textStr.replaceAll("\\$", "");
		textStr = textStr.replaceAll("%", "");
		textStr = textStr.replaceAll("\n", "");
		textStr = textStr.replaceAll("\r", "");
		textStr = textStr.replaceAll("<br>", "");
		textStr = textStr.replaceAll("<br/>", "");
		return textStr;
	}

	/**
	 * 把文本中的实体字符删除：
	 */
	public static String RemoveHTMLEncode(String text) {
		if (text == null || text == "") {
			return "";
		}
		int a = 0;
		int b = 0;
		while (true) {
			a = text.indexOf("&", a);
			b = text.indexOf(";", a);
			if (a != -1 && b != -1) {
				if (b - a >= 3 && b - a <= 7) {
					String tem = text.substring(a, b + 1);
					text = text.replaceFirst(tem, "");
				} else {
					a = a + 1;
				}
			} else {
				break;
			}
		}

		return text;
	}

	/**
	 * 替换bean对象里面的属性的String属性字段的字符串中的：< > ' " $ % 成实体
	 * 
	 * @param instance
	 * @param attributes 不过滤的instance的attributes属性
	 * @return
	 */
	public static Object beanReplaceFiter(Object instance, String[] attributes) {
		String text = "";

		String[] outFiter = {};// 预设，不做过滤的get方法名称结尾，或全称。
		if (attributes != null && attributes.length != 0) {
			outFiter = attributes;
		}
		// Field[] fields=instance.getClass().getDeclaredFields();//取得实例对象的属性
		Method[] method = instance.getClass().getMethods();

		for (int m = 0; m < method.length; m++) {
			Method meth = method[m];
			if (meth.getName().startsWith("get")
					&& meth.getReturnType().getSimpleName().equals("String")) {
				boolean ifcontain = false;
				for (int i = 0; i < outFiter.length; i++) {
					if (meth.getName().toLowerCase().endsWith(
							outFiter[i].toLowerCase())) {
						ifcontain = true;
						break;
					}
				}
				if (!ifcontain) {
					// 取出字符串,过滤
					try {
						text = (String) meth.invoke(instance, new Object[] {});
						if (text != null && text.length() > 0) {// 如果text是有值的
							text = HTMLEncode(text);
							// 再set回去
							Method setMethod = instance.getClass().getMethod(
									"set" + meth.getName().substring(3),
									new Class[] { String.class });
							setMethod.invoke(instance, new Object[] { text });
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

		}
		return instance;
	}

	/**
	 * 删除bean对象里面的属性的String属性字段的字符串中的：< > ' " $ %,同时删除实体
	 * 
	 * @param instance
	 * @param attributes 不删除的instance的attributes属性
	 * @return
	 */
	public static Object beanRemoveFiter(Object instance, String[] attributes) {
		String text = "";

		String[] outFiter = {};// 预设，不做过滤的get方法名称结尾，或全称。
		if (attributes != null && attributes.length != 0) {
			outFiter = attributes;
		}
		// Field[] fields=instance.getClass().getDeclaredFields();//取得实例对象的属性
		Method[] method = instance.getClass().getMethods();

		for (int m = 0; m < method.length; m++) {
			Method meth = method[m];
			if (meth.getName().startsWith("get")
					&& meth.getReturnType().getSimpleName().equals("String")) {
				boolean ifcontain = false;
				for (int i = 0; i < outFiter.length; i++) {
					if (meth.getName().toLowerCase().endsWith(
							outFiter[i].toLowerCase())) {
						ifcontain = true;
						break;
					}
				}
				if (!ifcontain) {
					// 取出字符串,过滤
					try {
						text = (String) meth.invoke(instance, new Object[] {});
						if (text != null && text.length() > 0) {// 如果text是有值的
							text = RemoveHTMLTags(text);
							text = RemoveHTMLEncode(text);
							// 再set回去
							Method setMethod = instance.getClass().getMethod(
									"set" + meth.getName().substring(3),
									new Class[] { String.class });
							setMethod.invoke(instance, new Object[] { text });
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

		}
		return instance;
	}

	/**
	 * 转换字符集为gbk
	 */
	public static String toGBK(String str) {
		String st = "";
		try {
			st = new String(str.getBytes("ISO-8859-1"), "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return st;
	}

	/**
	 * 转换字符集为utf-8
	 */
	public static String toUTF(String str) {
		String st = "";
		try {
			st = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return st;
	}

	/**
	 * 字符串过滤
	 */
	public static String charFiter(String str) {
		String[] fchar = { "日本", "日本人", "日本女人" };
		String instead = "**";
		if (str != null) {
			for (int i = 0; i < fchar.length; i++) {
				str = str.replaceAll(fchar[i], instead);
			}
		} else {
			return "";

		}
		return str;
	}

	/**
	 *  在页面上将时间类型转换为string类型
	 */
	public static String toStringDate(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 在页面上将时间类型按本地时间转换为string类型
	 */
	public static String toLocalStringDate(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(new SimpleTimeZone(28800000, "Asia/Shanghai"));
			return sdf.format(date);
		} else {
			return "";
		}
	}

	// 日期转化为大小写
	public static String dataToUpper(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return numToUpper(year) + "年" + monthToUppder(month) + "月"
				+ dayToUppder(day) + "日";
	}

	// 将数字转化为大写
	public static String numToUpper(int num) {
		// String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String u[] = { "0", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	// 月转化为大写
	public static String monthToUppder(int month) {
		if (month < 10) {
			return numToUpper(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + numToUpper(month - 10);
		}
	}

	// 日转化为大写
	public static String dayToUppder(int day) {
		if (day < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十"
						+ numToUpper(Integer.parseInt(str[1] + ""));
			}
		}
	}

	//判断String 是否包含在数组中
	public static boolean stringIsHave(String[] strs, String s) {
		/*此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 * */
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].indexOf(s) != -1) {//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
				return true;//查找到了就返回真，不在继续查询
			}
		}
		return false;//没找到返回false
	}

	
	/** 
	 * @Description 获取访问者Ip地址
	 * @param @param request
	 * @return String   
	 * @author 庞海超
	 * @date 2013年3月18日 14:02:37
	 */ 
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		//ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	

}
