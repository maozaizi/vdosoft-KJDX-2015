(function(factory) {
	if (typeof define === "function" && define.amd) {
		define(["jquery", "./jquery.validate"], factory);
	} else {
		factory(jQuery);
	}
}(function($) {
	// 验证的样式
	$("head").append("<style>.error{display: inline;padding-left: 12px;color:red;} .form-must-item{margin-left:12px;font-size: 14px;margin: 0px 5px;color: #FF3C00 !important;font-family: tahoma;vertical-align: middle;}</style>");
	//验证身份证号
	jQuery.validator.addMethod("isIdCardNo", function (value, element) {
		    var flag = true;
		    
		    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
		    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		    var varArray = new Array();
		    var intValue;
		    var lngProduct = 0;
		    var intCheckDigit;
		    var intStrLen = value.length;
		    var idNumber = value;
		    // initialize
		    if ((intStrLen != 15) && (intStrLen != 18)) {
		        flag = false;
		    }
		    // check and set value
		    for (i = 0; i < intStrLen; i++) {
		        varArray[i] = idNumber.charAt(i);
		        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
		            flag = false;
		        } else if (i < 17) {
		            varArray[i] = varArray[i] * factorArr[i];
		        }
		    }
		
		    if (intStrLen == 18) {
		        //check date
		        var date8 = idNumber.substring(6, 14);
		        if (isDate8(date8) == false) {
		            flag = false;
		        }
		        // calculate the sum of the products
		        for (i = 0; i < 17; i++) {
		            lngProduct = lngProduct + varArray[i];
		        }
		        // calculate the check digit
		        intCheckDigit = parityBit[lngProduct % 11];
		        // check last digit
		        if (varArray[17] != intCheckDigit) {
		            flag = false;
		        }
		    }
		    else {        //length is 15
		        //check date
		        var date6 = idNumber.substring(6, 12);
		        if (isDate6(date6) == false) {
		             flag = false;
		        }
		    }
        return this.optional(element) || flag;
    }, "请正确输入您的身份证号码");
	
	
	
	// 验证邮政编码
	jQuery.validator.addMethod("checkPost", function(value, element) {

				var pattern = /^[0-9]{6}$/;

				if (value != '') {
					if (!pattern.exec(value)) {
						return false;
					}
				};

				return true;

			}, "请输入有效的邮政编码");

	// 添加转换小写功能
	jQuery.validator.addMethod("toLowerCase", function(value, element) {
				value = $.trim(String(value));// 去空
				if (value == "") {
					element.value = "";
					return this.optional(element) || true;
				}
				if (value.toLowerCase() != value)
					element.value = value.toLowerCase();
				return this.optional(element) || true;
			}, "");

	// 验证手机

	jQuery.validator.addMethod("checkMobile", function(value, element) {
				var length = value.length;
				var mobile = /^(((1[0-9][0-9]{1})|(15[0-9]{1}))+\d{8})$/;
				return this.optional(element)
						|| (length == 11 && mobile.test(value));
			}, "请正确填写您的手机号码");

	// 验证密码 6-18位由字符数字和特殊符号组成 排除空格..

	jQuery.validator.addMethod("checkPassword", function(value, element) {
				var myreg = /^[^\s]{6,18}$/;
				if (value != '') {
					if (!myreg.test(value)) {
						return false;
					}
				};
				return true;
			}, "请输入有效密码!");

	// 验证邮箱

	jQuery.validator.addMethod("checkEmail", function(value, element) {

		var myreg = /^[_a-zA-Z0-9\-]+(\.[_a-zA-Z0-9\-]*)*@[a-zA-Z0-9\-]+([\.][a-zA-Z0-9\-]+)+$/;

		if (value != '') {
			if (!myreg.test(value)) {
				return false;
			}
		};

		return true;

	}, " 请输入有效的E_mail");

	// 验证固定电话

	jQuery.validator.addMethod("checkTel", function(value, element) {

				var pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;

				if (value != '') {
					if (!pattern.exec(value)) {
						return false;
					}
				};

				return true;

			}, "  请输入有效的固定电话（如：029-88888888）");

	// 验证名称是否重复

	jQuery.validator.addMethod("checkName", function(value, element) {

				var returnMsg = true;

				jQuery.ajax({
							type : "get",
							url : "url",

							async : false,
							cache : false,
							data : {
								toinName : value,
								method : "get"
							},
							dataType : "html",
							scriptCharset : "UTF-8",
							success : function(msg) {

								if (msg == "1") {

									returnMsg = false;

								}

							}
						});

				return returnMsg;

			}, " 此名称已经被占用！请您更换其它名称");

	// select标签必须选择一项
	jQuery.validator.addMethod("selectOne", function(value, element) {
				return value.indexOf("请") > 0;
			}, "必须选择一项");

	// 中文
	jQuery.validator.addMethod("chinese", function(value, element) {
				var chinese = /^[\u4e00-\u9fa5]+$/;
				return this.optional(element) || (chinese.test(value));
			}, "只能输入中文");

	// 输入字母和数字
	jQuery.validator.addMethod("chrnum", function(value, element) {
				var chrnum = /^([a-zA-Z0-9]+)$/;
				return this.optional(element) || (chrnum.test(value));
			}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

	// IP验证
	jQuery.validator.addMethod("ip", function(value, element) {
		var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
		return this.optional(element)
				|| (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256
						&& RegExp.$3 < 256 && RegExp.$4 < 256));

		// QQ号验证
		jQuery.validator.addMethod("qq", function(value, element) {
					var tel = /^[1-9]\d{4,9}$/;
					return this.optional(element) || (tel.test(value));
					// 邮政编码验证
					jQuery.validator.addMethod("zipCode", function(value,
									element) {
								var tel = /^[0-9]{6}$/;
								return this.optional(element)
										|| (tel.test(value));
							}, "邮政编码格式错误");
				}, "qq号码格式错误");
	}, "Ip地址格式错误");
}));


function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}

function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}