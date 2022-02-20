/**
 * 验证相关
 *
 * @param
 */
function percentage(number1, number2) {
    if (number2 == 0) {
        return "";
    }
    return (Math.round(number1 / number2 * 10000) / 100.00 + "%"); // 小数点后两位百分比
}

function getParamFromUrl(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

function trimNaN(a, d) {
    return (typeof(a) == "" || isNaN(a)) ? d : parseInt(a);
}

function isNull(v) {
    return !isNotNull(v);
}

function isNotNull(v) {
    return typeof(v) != "undefined";
}

function trimToDefault(v, d) {
    return typeof(v) != "undefined" ? v : d;
}

/**
 * 获取行内 节点的值 如果不存在返回 d
 *
 * @param row
 *            行对象
 * @param mapGroup
 *            group节点 比如i_Map dt_Map
 * @param field
 *            字段名字
 * @param d
 *            默认值
 * @returns
 */
function getRowFiled(row, mapGroup, field, d) {
    return isNotNull(row[mapGroup]) ? row[mapGroup][field] : d;
}

//查单个数据
function queryForObject(params, toClassName) {
    if (isNotNull(params)) {
        params.length = 1;
    }
    var t;
    var result = queryByExample(params, toClassName);
    if (isNotNull(result) && result.totalElements > 0) {
        if (result.totalElements > 1) { console.warn(" query result totalElements > 1 "); }
        t = result.content[0];
    }
    return t;
}
//查model
function queryByExample(params, toClassName) {
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddr() + "/common/getModelList?filter[className]=" + toClassName,
        "method": "POST",
        "data": params
    }
    var r;
    $.ajax(settings).done(function(response) {
        r = response;
    });
    return r;
}


function saveDataModel(params, toClassName) {
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddr() + "/common/saveModel?className=" + toClassName,
        "method": "POST",
        "data": params
    }
    $.ajax(settings).done(function(response) {
        console.log(response);
    });
}


//----------------

/**
 * 按序号查费用
 */
function queryFy(list) {
    var r = {};
    $.ajax({
        url: 'http://' + changeAddr() + '/common/getModelList?filter[className]=ReportRole&length=10000',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: { 'filter[seriaNum_s_s_L]': list },
        success: function(data) {
            $.each(data.content, function(i, item) {
                r[item.s_Map.seriaNum_s] = {};
                if (isNotNull(item.d_Map)) {
                    r[item.s_Map.seriaNum_s].kcf_d = item.d_Map.zhkcf_d;
                    r[item.s_Map.seriaNum_s].zzf_d = item.d_Map.zzf_d;
                    r[item.s_Map.seriaNum_s].hj_d = item.d_Map.hj_d;
                }
                if (isNotNull(item.s_Map)) {
                    r[item.s_Map.seriaNum_s].hy_s = item.s_Map.hy_s;
                }
                if (isNotNull(item.dt_Map)) {
                    r[item.s_Map.seriaNum_s].qdrq_dt = item.dt_Map.qdrq_dt;
                    r[item.s_Map.seriaNum_s].yjsksj_dt = item.dt_Map.yjsksj_dt;
                }


            });
        }
    });
    return r;
}



//-------


function isNotEmptyString(str) {
    if (str != null && str != undefined && str != "") {
        return true;
    }
    return false;
}

function isPasswd(s) {
    var patrn = /^(.){6,20}$/;
    if (!patrn.test(s)) return false
    return true
}

function checkUserName(str) {
    var re = /^[A-Za-z0-9\._'\u4e00-\u9fa5]+$/ig;
    if (!re.test(str)) return false
    return true
}

function checkID(value, element) {
    var tmpValue = $.trim(value);
    var pattern = /^[a-zA-Z][a-zA-Z0-9_]{4,11}$/;
    if (tmpValue != '') {
        if (pattern.test(tmpValue)) {
            return true;
        }
    }
    return false;
}

function checkPassword(value, element) {
    var tmpValue = $.trim(value);
    var pattern = /^[a-zA-Z0-9_]{8,16}$/;
    if (tmpValue != '') {
        if (pattern.test(tmpValue)) {
            return true;
        }
    }
    return false;
}

function checkChinese(value, element) {
    var tmpValue = $.trim(value);
    var pattern = /^[u4E00-u9FA5]+$/;
    if (tmpValue != '') {
        if (!pattern.test(tmpValue)) {
            return true;
        }
    }
    return false;
}

var tableId = "tbl";
var oddRowColor = "#f1f1f1";
var evenRowColor = "#fff";
var hoverRowColor = "#C6D7F8";
var highlightRowColor = "#FFD460";

function senfe(o, a, b, c, d) {
    var t = document.getElementById(o).getElementsByTagName("tr");
    for (var i = 0; i < t.length; i++) {
        t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
        t[i].onclick = function() {
            var td = this.getElementsByTagName("td")[0];
            var ck = null;
            if (td != null) {
                ck = td.firstChild;
            }
            if (this.x != "1") {
                this.x = "1";
                this.style.backgroundColor = d;
                if (ck != null) {
                    ck.checked = true;
                }
            } else {
                this.x = "0";
                this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
                if (ck != null) {
                    ck.checked = false;
                }
            }
        }
        t[i].onmouseover = function() {
            if (this.x != "1") this.style.backgroundColor = c;
        }
        t[i].onmouseout = function() {
            if (this.x != "1") this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
        }
    }
}

function checkAll(tblId, selector) {
    var len = $(selector).length;
    if (len > 0) {
        var t = document.getElementById(tblId).getElementsByTagName("tr");
        if ($('#checkAll').attr("checked") == "checked") {
            $(selector).attr("checked", "checked");

            for (var i = 1; i < t.length; i++) {
                t[i].x = "1";
                t[i].style.backgroundColor = highlightRowColor;
            }
        } else {
            $(selector).attr("checked", false);
            for (var i = 1; i < t.length; i++) {
                t[i].x = "0";
                t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? evenRowColor : oddRowColor;
            }
        }
    }
}

//检查标题，只允许中文、英文、数字、下划线
function chkTitle(str, minlen, maxlen) {
    var re = /^[A-Za-z0-9_\u4e00-\u9fa5]+$/ig;
    if (re.test(str)) {
        var ret = true;
        if (typeof minlen == 'number') {
            if (str.length < minlen) ret = false;
        }
        if (typeof maxlen == 'number') {
            if (str.length > maxlen) ret = false;
        }
        return ret;
    } else {
        return false;
    }
}

//检查面板标题，只允许中文、英文、数字、下划线[]
function chkPanelTitle(str, minlen, maxlen) {
    var re = /^[A-Za-z0-9_\[\]\u4e00-\u9fa5]+$/ig;
    if (re.test(str)) {
        var ret = true;
        if (typeof minlen == 'number') {
            if (str.length < minlen) ret = false;
        }
        if (typeof maxlen == 'number') {
            if (str.length > maxlen) ret = false;
        }
        return ret;
    } else {
        return false;
    }
}

//验证密码
function checkPassword2(value) {
    var pattern = /^[A-Za-z0-9\W]+$/ig;
    var ret = true;
    if (pattern.test(value)) {
        if (value.length < 6) ret = false;
        if (value.length > 12) ret = false;
    } else {
        ret = false;
    }
    return ret;
}

//检查是否7位以内0以上整数
function chkInt(str) {
    var re = /^[0-9]{1,7}$/ig;
    return re.test(str);
}

//检查是否为数字（包括整数1-7位以内、小数）
function chkNumber(str) {
    var re = /^[0-9]{1,7}((\.[0-9]{1,2}){0,1})?$/ig;
    return re.test(str);
}

//检查是否是7位以内正整数
function chkPositiveInt(str) {
    var re = /^[1-9]{1}[0-9]{0,6}$/ig;
    return re.test(str);
}

//检查是否是电话号码
function chkPhone(str) {
    var re = /^(13[0-9]{9})|(15[389][0-9]{8})|(17[0-9]{9})|(18[0-9]{9})$/;
    return re.test(str);
}
//检查是否是邮件地址
function isEmail(str) {
    var re = /^[0-9a-z][_.0-9a-z-]{0,31}@([0-9a-z][0-9a-z-]{0,30}[0-9a-z]\.){1,4}[a-z]{2,4}$/;
    return re.test(str);
}
/**
 * type 转显示内容
 * @param typeEn
 * @returns {String}
 */
function getTypeString(typeEn) {
    var t = "视频";
    if (typeEn == 'video')
        t = ("视频");
    if (typeEn == 'image')
        t = ("图片");
    if (typeEn == 'info')
        t = ("文本");
    if (typeEn == 'body')
        t = ("车身");
    if (typeEn == 'other')
        t = ("其他");
    return t;
}
/**
 * 金额格式化
 * @param num
 * @returns {String}
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
        num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

function setQueryDate(curryear, beginInputName, endInputName) {
    var y = $('#' + curryear).val();
    var firstDay = new Date(y, 0, 1);
    var lastDay = new Date(y, 12, 0);
    var b = $.format.date(firstDay, "yyyy-MM-dd");
    var e = $.format.date(lastDay, "yyyy-MM-dd");
    console.log("onloadyear:" + y + " " + beginInputName + " = " + b + " " + endInputName + " = " + e);
    $('#' + beginInputName).val(b);
    $('#' + endInputName).val(e);
}

function setQueryMonth(curryear, beginInputName, endInputName) {
    var y = $('#' + curryear).val();
    var firstDay = new Date(y, 0, 1);
    var lastDay = new Date(y, 12, 0);
    var b = $.format.date(firstDay, "yyyy-MM");
    var e = $.format.date(lastDay, "yyyy-MM");
    console.log("onloadyear:" + y + " " + beginInputName + " = " + b + " " + endInputName + " = " + e);
    $('#' + beginInputName).val(b);
    $('#' + endInputName).val(e);
}
var LEVELJSON = { 0: '特级', 1: 'A++', 2: 'A+', 3: 'A', 4: '无', 4: '待定', 6: 'B+', 7: 'B' };

function getValueFromJsonString(jsonString, k) {
    if (isNotNull(jsonString)) {
        var jsonObj = $.parseJSON(jsonString);
        return (jsonObj[k]);
    }
    return '';
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
}

function getRequest() {
    var url = window.location.search; //获取url中"?"符后的字串   
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            //就是这句的问题
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
            //之前用了unescape()
            //才会出现乱码  
        }
    }
    return theRequest;
}

function goPage(cId, uniqId_s, from, url) {
    //60.205.183.191:9098
    window.open('/-/' + url + '?_=&id=' + cId + '&seriaNum_s=' + uniqId_s + '&_from=' + from + '&_url=' + url);
}

function showEchart() {
    $('#_Echart').slideToggle();
    if ($('#_show').text() == "显示统计图") {
        $('#_show').text("隐藏统计图")
    } else {
        $('#_show').text("显示统计图")
    }
}



function checkNewDayRole(contractCode) {
    var r = false;
    var list = ["BB21", "BB22", "BB23", "BB24", "BB25", "BB26", "BB27"];
    $.each(list, function(key, v) {
        if (contractCode.indexOf(v) != -1) {
            r = true;
        }
    });
    return r;
}



function with2021MonthRole(date) {

    var _role = { "31": 1, "62": 2, "92": 3, "123": 4, "153": 5, "184": 6, "215": 7, "245": 8, "276": 9, "306": 10, "337": 11, "365": 12 }
    var d = "";
    var month = _role[date];
    if (isNull(month)) {
        var dateNum = parseInt(date);
        var b = 0;
        var c = 0;
        var t = 0;
        $.each(_role, function(key, v) {
            var level = parseInt(key);
            if (dateNum < level) {
                if (b == 0) {
                    b = 1;
                }
            } else {
                c = level;
                b = 1;
                t = v;
            }
        });
        var mo = (dateNum - c) / 30;
        if (mo >= 1) {
            d = (t + 1).toFixed(2)
            if (dateNum > 365) {
                d = (t + mo).toFixed(2)
            }
        } else {
            d = (t + mo).toFixed(2)
        }
    } else {
        d = month
    }
    return d;


}

function showTableField(api) {
    var ff = api.context[0].aoColumns;
    console.log("------------------------")
    $.each(ff, function(i, item) {
        var type = "varchar,256,";
        if (isNotNull(item.mData)) {

            try {
                if (item.mData.indexOf("_i") != -1) {
                    type = "int,8,";
                }
                if (item.mData.indexOf("_d") != -1) {
                    type = "double,0,";
                }
                if (item.mData.indexOf("_dt") != -1) {
                    type = "datetime,0,";
                }
                if (item.mData.indexOf(".") != -1) {
                    item.mData = item.mData.split(".")[1];
                }
                console.log(item.mData + "," + type + item.sTitle)
            } catch (e) {}


        }
    });
    console.log("------------------------\n\n")
}


function getLineConlevel(data) {
    var r = '';
    if (data == 1) {
        r = "严控";
    } else if (data == 2) {
        r = "一般管控";
    } else if (data == 3) {
        r = "严1";
    }
    return r;
}