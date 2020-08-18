// 列表页字段排序功能
//排序
$(document).ready(function () {
});
function hiden(divObj) {
	$("#" + divObj).hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
}
function show(divObj) {
	$("#" + divObj).show();//显示,参数说明同上  
}  
	
//鼠标移上tr变色
$(document).ready(function () {
	$(".table_list tr").addClass("hover");
	$(".table_list tr").mouseover(function () {  
         //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).removeClass("out");
		$(this).removeClass("hover");
		$(this).addClass("over");
	});
	$(".table_list tr").mouseout(function () {
                //给这行添加class值为over，并且当鼠标一出该行时执行函数
		$(this).removeClass("over");
		$(this).removeClass("hover");
		$(this).addClass("out");
	});  //移除该行的class
});
		//year:开始年份，toYear:结束年份(为空当前年)，name：select的name，selectValue：默认选中项。
function authYear(year, toYear, name) {
	if (name == null || year == null) {
		return;
	}
	var currentDate = new Date();
	var currentYear = currentDate.getYear();
	if (toYear == null) {
		toYear = currentYear;
	}
	var $selectObj = $("select[name='" + name + "']");
	for (var i = toYear; i >= year; i--) {
		if ($selectObj.val() == i) {
			var $op = $selectObj.find("option[value='" + i + "']");
			$op.remove();
			$selectObj.append("<option value='" + i + "' selected>" + i + "</option>");
		} else {
			$selectObj.append("<option value='" + i + "'>" + i + "</option>");
		}
	}
}
//弹出页面
var theWindow;
function openPage(url) {
	if (theWindow != null) {
		try {
			theWindow.location = url;
			theWindow.focus();
			return;
		}
		catch (e) {
		}
	}
	theWindow = window.open(url, "_blank", "scrollbars=yes,width=600,height=500");
	if (theWindow.opener == null) {
		theWindow.opener = window;
	}
	if (window.focus) {
		theWindow.focus();
	}
}
//输入浮点数验证
function numValidate(o) {
	var reg = new RegExp(/^[\d.]+$/);
	try {
		if (!reg.test(o.value)) {
			throw e;
		}
		o.value = parseFloat(o.value);
		if (isNaN(o.value)) {
			throw e;
		}
		if (o.value.indexOf(".") == -1) {
			o.value += ".00";
		}
	}
	catch (e) {
		o.value = "0.00";
	}
	return o.value;
}


		// 添加行  
function addRow4Node(obj) {
	var pf = obj.parentNode;
	while (typeof (pf) != "undefined" && pf.nodeName != "TR") {
		pf = pf.parentNode;
	}
	var field = pf;
	var pf = pf.parentNode;
	while (typeof (pf) != "undefined" && pf.nodeName != "TABLE") {
		pf = pf.parentNode;
	}
	var signFrame = pf;
	var index = field.rowIndex+1;
			//添加行
	var newTR = signFrame.insertRow(index);
	var c = field.cells.length;
	for (var i = 0; i < c; i++) {
		var str = field.cells[i].innerHTML;
		var td = newTR.insertCell(i);
		td.className = field.cells[i].className;
		td.width = field.cells[i].width;	
		td.innerHTML = str;
	}
}
		// 删除行
function removeRow4Node(obj) {
	var pf = obj.parentNode;
	while (typeof (pf) != "undefined" && pf.nodeName != "TR") {
		pf = pf.parentNode;
	} 
	var field = pf;
	var pf = pf.parentNode;
	while (typeof (pf) != "undefined" && pf.nodeName != "TABLE") {
		pf = pf.parentNode;
	}
	var signFrame = pf;
	var index = field.rowIndex;
	if (signFrame.rows.length > 1) {
			//删除指定Index的行
		signFrame.deleteRow(index);
	}
}
		// 添加行  
	 	function addRow(tableid,trid){
	 	    var field=document.getElementById(trid);
			var signFrame = document.getElementById(tableid);
			//添加行
			var newTR = signFrame.insertRow(0);
			var c=field.cells.length;
			for (var i=0;i<c;i++){
			  var str=field.cells[i].innerHTML;
			  var td=newTR.insertCell(i);	
			  td.className = field.cells[i].className;
			  td.width = field.cells[i].width;	
			  td.innerHTML =str; 
			}
		}
		// 删除行
		function removeRow(tableid){
			var signFrame = document.getElementById(tableid);
			if (signFrame.rows.length>1){
			//删除指定Index的行
			   signFrame.deleteRow(0);
			}
			
		}
