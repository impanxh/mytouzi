/**
 * 公共数据js接口
 */

function getParamsFromStoreage(key, sortKey, resultJson) {
    var _value = localStorage.getItem(key);
    var sort = localStorage.getItem(sortKey);
    try {
        if (_value != null) {
            var json = JSON.parse(_value);
            $.each(json, function(key, val) {
                resultJson[key] = val
            });
        }
        if (sort != null) {
            var sortObj = JSON.parse(sort);
            resultJson['order[0][' + sortObj.property + ']'] = sortObj.direction
        }

    } catch (error) {

    }
    return resultJson;

}

function spliturlFromStoreage(key, resultJson) {
    var _value = localStorage.getItem(key);
    var aThat = _value.split("&");
    for (var i = 0, iLen = aThat.length; i < iLen; i++) {
        var _v = aThat[i];
        var pair = _v.split("=");
        resultJson[pair[0]] = pair[1];
    }
    return resultJson
}



//query one data
var _companyname = {
    "ZY": "自营中心",
    "跨中心_ZY": "跨中心_自营",
    "CBS": "CBS中心",
    "BR": "博瑞中心",
    "QC": "七彩中心",
    "白马": "白马中心",
    "智马": "智马"
}

//  eg:  var obj = queryForObjectV3({"filter[seriaNum_s_s]":seriaNum_s},'OfflineSheet_cancelbase');
function queryForObjectV3(params, toClassName) {
    if (isNotNull(params)) {
        params.length = 1;
    }
    var t;
    var result = queryByExampleV3(params, toClassName);
    if (isNotNull(result) && result.totalElements > 0) {
        if (result.totalElements > 1) { console.warn(" query result totalElements > 1 "); }
        t = result.content[0];
    }
    return t;
}

function queryById(id, toClassName) {
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddr() + "/api/queryById?className=" + toClassName,
        "method": "GET",
        "data": { "id": id }
    }
    var r;
    $.ajax(settings).done(function(response) {
        r = response;
    }).fail(function(data, status, xhr) {
        layer.msg("网络有问题,可能服务器正在重启")
    });
    return r;
}
//var dataList = queryByExampleV3({"filter[seriaNum_s_s]":seriaNum_s},'OfflineSheet_cancelbase');
//query more data
function queryByTask(params, t) {
    if (isNotNull(params)) {
        if (isNull(params.length)) {
            params.length = 50;
        }
    }
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddrWB() + "/task/" + t + "/findByCommon",
        "method": "POST",
        "data": params
    }
    var r;
    $.ajax(settings).done(function(response) {
        r = response;
    }).fail(function(data, status, xhr) {
        layer.msg("网络有问题,可能服务器正在重启")
    });
    return r;
}

function queryByExampleV3(params, toClassName) {

    if (isNotNull(params)) {
        if (isNull(params.length)) {
            params.length = 50;
        }
    }


    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddr() + "/api/getModelList?filter[className]=" + toClassName,
        "method": "POST",
        "data": params
    }
    var r;
    $.ajax(settings).done(function(response) {
        r = response;
    }).fail(function(data, status, xhr) {
        layer.msg("网络有问题,可能服务器正在重启")
    });
    return r;
}
//delete data
function deleteDataV3(id, toClassName) {
    var r;
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddr() + "/api/delById?className=" + toClassName,
        "method": "GET",
        "data": { "id": id }
    }
    $.ajax(settings).done(function(response) {
        r = (response);
    });
    return r;
}
/**
 *  var params = {"selectByField":"id","id":rowId};
        params["lineid_s"] = selectObj.val();
        params["linename_s"] = selectObj.find("option:selected").text();
 */

//save data
//eg saveDataModelV3({"selectByField":"id","lineJson_s":"bb","roleJson_s":"sfsf","checkUniq":'Y'},"ReportVersion");
//eg:  	saveDataModelV3({"baseJson_s":"aaa","lineJson_s":"bb","roleJson_s":"sfsf","checkUniq":'N'},"ReportVersion");
function saveDataModelV3(params, toClassName) {
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://" + changeAddr() + "/api/saveModel?className=" + toClassName,
        "method": "POST",
        "data": params
    }
    $.ajax(settings).done(function(response) {
        console.log(response);
        if (isNotNull(response) && isNotNull(response.id)) {
            params.id = response.id
        }
    });
}

function saveDataModelV3WithUrl(params, url) {
    var settings = {
        "async": false,
        "crossDomain": true,
        "url": url,
        "method": "POST",
        "data": params
    }
    var _response = ''
    $.ajax(settings).done(function(response) {

        _response = response

    });
    console.log(_response);
    return _response
}


function g(rowid, url) {
    window.open("/-/" + url + "?_=&id=" + rowid);
}



function saveModelBatchV3WithCallBack(params, async, func) {
    var data = JSON.stringify(params);
    $.ajax({
        url: "http://" + changeAddr() + '/api/saveModelBatch',
        type: 'POST',
        async: async,
        dataType: 'json',
        contentType: "application/json; charset=utf-8", //contextType must add so server can know requestType
        "data": data,
        success: function(data) {
            console.log(data);

            try { func(); } catch (err) {}
        }
    });
}



function saveModelBatchV3Async(params, async) {
    var data = JSON.stringify(params);
    $.ajax({
        url: "http://" + changeAddr() + '/api/saveModelBatch',
        type: 'POST',
        async: async,
        dataType: 'json',
        contentType: "application/json; charset=utf-8", //contextType must add so server can know requestType
        "data": data,
        success: function(data) {
            console.log(data);
        }
    });
}


function saveModelBatchV3WithFunc(params, func) {
    var data = JSON.stringify(params);
    $.ajax({
        url: "http://" + changeAddr() + '/api/saveModelBatch',
        type: 'POST',
        dataType: 'json',
        contentType: "application/json; charset=utf-8", //contextType must add so server can know requestType
        "data": data,
        success: function(data) {
            console.log(data);
            func();
        }
    });
}

function saveModelBatchV3(params) {
    var data = JSON.stringify(params);
    $.ajax({
        url: "http://" + changeAddr() + '/api/saveModelBatch',
        type: 'POST',
        dataType: 'json',
        contentType: "application/json; charset=utf-8", //contextType must add so server can know requestType
        "data": data,
        success: function(data) {
            console.log(data);
        }
    });
}

function pushMessageToApp(id, field, value, className) {
    if (field == 'thiryPersonId_s' && className == 'PublishSheet_car') {
        var obj = { "checkUniq": 'N' };
        var p = queryForObjectV3({ "filter[id]": value }, 'R1_thirdPartyPerson');
        var b = queryForObjectV3({ "filter[id]": id }, 'PublishSheet_car');
        if (isNotNull(p)) {
            obj.pushMsg_s = "您有新的车牌号为[" + b.licensePlate_s + "]车需要上刊";
            obj.user_s = p.username_s;
            obj.push_Tag = "Y";
            obj.from = "SK";
            saveDataModelV3(obj, "push_log");
        }

    }


}

function updateFieldByidEndLayer(id, field, elementId, className) {

    //pushMessageToApp(id, field, value, className);

    var obj = { "selectByField": "id", "id": id, "checkUniq": 'Y' };
    obj[field] = $('#' + elementId).val();
    saveDataModelV3(obj, className);
    layer.alert("修改成功!", { icon: 6 })

}

function updateFieldByid(id, field, value, className) {

    pushMessageToApp(id, field, value, className);

    var obj = { "selectByField": "id", "id": id, "checkUniq": 'Y' };
    obj[field] = value;
    saveDataModelV3(obj, className);

}

function ajaxFormWithTable(formId, table) {
    ajaxForm(formId, table);
}


function ajaxForm(formId, table, confirm) {
    $('#' + formId).ajaxForm(function(data) {
        if (data.status == 200) {
            if (isNull(confirm)) {
                try { document.getElementById('subutton').setAttribute('disabled', true); } catch (err) {}

            }
            layer.msg("保存成功");
            $("#subutton").css("background-color", "#85A2AD");

            if (isNotNull(table)) {
                $('#' + table).DataTable().draw();
            }

            if (isNotNull(confirm)) {
                //询问框
                layer.confirm('保存成功,返回列表页面？', {
                    btn: ['返回列表页', '继续增加'] //按钮
                }, function() {
                    var _from = getParamFromUrl("f");
                    if (_from != '' && _from != null) {
                        window.location.href = "/-/" + _from;
                    }

                    refer = document.referrer;
                    window.location.href = refer;
                }, function() {
                    document.getElementById(formId).reset();
                    $("#subutton").css("background-color", "#00a8e8");
                });
            }


            var uptime = window.setTimeout(function() {
                $("#cc").trigger("click");
                clearTimeout(uptime);
            }, 2000)
        } else {
            layer.msg("保存失败");
        }
    }).submit();
}

function delRow(id, toClassName, tableId) {
    layer.confirm('确定删除？', {
        icon: 3
    }, function(index) {
        layer.close(index);
        if (true) {
            var r = deleteDataV3(id, toClassName);
            $('#' + tableId).DataTable().draw();
            return r;
        }
    });
}

function compareDate(s1, s2) {
    return ((new Date(s1.replace(/-/g, "\/"))) >= (new Date(s2.replace(/-/g, "\/"))));
}


function genCodeNumber(keyword, tag) {
    var code = "";
    if (keyword != "") {
        $.ajax({
            url: 'http://' + changeAddr() + '/con/getCode?tag=' + tag + '&company=' + keyword,
            type: "GET",
            data: {},
            async: false,
            dataType: "json",
            success: function(data) {
                if (data != undefined) {
                    //console.log(data);
                    if (tag == 1) //保存序号
                        code = data.i_Map.val_i;
                    else
                        code = data.i_Map.val_i + 1;
                }
            }
        });
    }
    return code;
}

function getUniqId() {
    var t = 0;
    $.ajax({
        url: 'http://' + changeAddr() + '/report/getSeriaNum',
        type: 'GET',
        async: false,
        dataType: 'json',
        success: function(data) {
            t = (data);

        }
    })
    return t;
}


function jsoup(url) {
    var t;
    $.ajax({
        url: url,
        type: 'GET',
        async: false,
        crossDomain: true,
        dataType: 'jsonp',
        //dataType: 'json', 
        success: function(data) {
            t = (data);
        }
    })
    return t;
}

function getHttp(url) {
    return get(url);
}

function get(url) {
    var t;
    $.ajax({
        url: url,
        type: 'GET',
        async: false,
        crossDomain: true,
        //dataType: 'jsonp',
        dataType: 'json',
        success: function(data) {
            t = (data);
        }
    })
    return t;
}


function getWithThread(url) {
    var t;
    $.ajax({
        url: url,
        type: 'GET',
        crossDomain: true,
        dataType: 'json',
        success: function(data) {
            t = (data);
        }
    })
    return t;
}

function post(url, params) {
    var t;
    $.ajax({
        url: url,
        type: 'POST',
        async: false,
        crossDomain: true,
        data: params,
        dataType: 'json',
        success: function(data) {
            t = (data);
        }
    })
    return t;
}


function _afterChange(row, value) {
    console.log("_afterChange:" + row)
    console.log(value);
}

var skBmCompanyJson = {
    "白马": "BM",
    "CBS": "CBS",
    "七彩": "QC",
    "博瑞": "BR",
    "博瑞中心": "BR",
    "自营中心": "ZY",
    "智马": "ZM",
    "七彩中心": "QC",
    "CBC中心": "CBS",
    "白马中心": "BM",
    "智马中心": "ZN",
    "自营中心(原运通)": "ZY",
    "自营中心(BRT)": "ZY",
};

function shortName(str) {
    var l = str.length;
    if (l < 20) {
        return str;
    } else {
        var d = str.substring(l - 4, l);
        return str.substring(0, 10) + "...-" + d;
    }
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


//批量修改字段
function changeMsg(obj, table, toClassName, element) {
    var sum = 0;
    var fieldName = obj;
    var elVal = element.val();

    $("input[name='checkone']:checkbox:checked").each(function() {
        sum++;
        var thisId = $(this).val();
        var json = { "selectByField": "id", "id": thisId, "checkUniq": 'Y' };
        json[fieldName] = elVal;
        saveDataModelV3(json, toClassName);
    });
    if (sum == 0) {
        layer.msg("请勾选需要操作的车辆");
        return;
    }
    $('#' + table).DataTable().draw();
}

function getProbilmDataRule() {
    var filterSerinum = get("/user/sessions-filter");
    var filterid = '';
    $.each(filterSerinum.functionMap, function(key, item) {
        if (key.indexOf("hpw_") != -1) {
            if (filterid == '') {
                filterid = key.replace("hpw_", "");
            } else {
                filterid = filterid + "," + key.replace("hpw_", "");
            }
        }
    });
    return filterid;
}

function setOpationSelectByName() {


    var _list = '',
        _list = ($('#licensePlates').val()).split(/[\s\n]/);

    var json = {};
    $.each(_list, function(k, v) {
        if (v != '') {
            $("input[name='checkone'][linename='" + v + "']").prop("checked", "checked");
        }

    });

    var i = $("input[name='checkone']:checkbox:checked").length;

    layer.alert("共选中 [" + i + "]个车");

}

function setOpationSelectByCarName() {

    var _list = '',
        _list = ($('#licensePlates_car').val()).split(/[\s\n]/);
    console.log(_list);
    var json = {};
    $.each(_list, function(k, v) {
        if (v != '') {
            $("input[name='checkone'][licensePlate_s='" + v + "']").prop("checked", "checked");
        }

    });

    var i = $("input[name='checkone']:checkbox:checked").length;

    layer.alert("共选中 [" + i + "]个车");

}


$(function() {
    //选中区间内序号
    $(document).on('click', '.checkIndex', function() {
        var startIndex = parseInt($('#startIndex').val()) - 1;
        var endIndex = parseInt($('#endIndex').val()) - 1;
        // if (endIndex - startIndex >= 40) return alert('一次最多选择40个')
        $('input[name="checkone"]').each(function() {
            $(this).removeAttr("checked");
        });
        for (startIndex; startIndex <= endIndex; startIndex++) {
            $('input[name="checkone"]').eq(startIndex).prop("checked", 'true');
        }
    });
    //全选
    $("#checkall").on('click', function() {
        $("input[name='checkone']").prop("checked", this.checked);
    });
})


function queryUserCompany() {
    var obj = get('/user/sessions-filter');
    var r = '';
    $.each(obj.functionMap, function(key, vale) {
        if (key.indexOf('thirdPartyCp') != -1) {
            r = key.split("_")[2];
        }
    });
    return r;
}


function getBelongCompany(filterstr) {
    var obj = get('/user/sessions-filter');
    var r = '';
    $.each(obj.functionMap, function(key, vale) {
        if (key.indexOf(filterstr) != -1) {
            r = key.split("_")[2];
        }
    });
    return r;
}

function querybusAndOrderSearch4XKX() {
    var obj = get('/user/sessions-filter');
    var f = [];
    $.each(obj.functionMap, function(key, value) {
        if (key.indexOf('busAndOrderSearch_') != -1) {
            if (value.indexOf('七彩') != -1) {
                f.push('七彩中心');
            } else if (value.indexOf('白马') != -1) {
                f.push('白马中心');
            } else if (value.indexOf('CBS') != -1) {
                f.push('CBS中心');
            } else if (value.indexOf('博瑞') != -1) {
                f.push('博瑞中心');
            } else {
                f.push(value);
            }
        }
    });
    return f.join(",");
}

function querybusAndOrderSearch() {
    var obj = get('/user/sessions-filter');
    var f = [];
    $.each(obj.functionMap, function(key, value) {
        if (key.indexOf('busAndOrderSearch_') != -1) {
            f.push(value == '七彩' ? '七彩中心' : value);
        }
    });
    return f.join(",");
}


function getCheckUserList(selectid) {

    var dataList = queryByExampleV3({ "filter[enable_s_s]": "Y" }, 'R1_thirdCheckPerson');
    var json = {}
    if (isNotNull(selectid)) {
        $("#" + selectid).empty();
        $("#" + selectid).append('<option value="">请选择</option>');
    }

    $.each(dataList.content, function(i, item) {
        if (isNotNull(selectid)) {
            $("#" + selectid).append('<option value=' + item.id + '>' + item.realyName_s + '</option>');
        }
        json[item.id] = item.realyName_s;
    });
    return json;
}

function getSeraNumsFromUrl() {
    var id = getParamFromUrl('seriaNum_s');
    var replaceTASKId = getParamFromUrl('replaceTASKId');
    if (replaceTASKId == "Y") {
        var tid = getParamFromUrl('TID');
        if (isNotNull(tid)) {
            id = tid;
        }
    }
    return id;
}



function closeRow(id, table, className) {
    layer.confirm('确定关闭该条记录吗？', {
        icon: 3
    }, function(index) {
        layer.close(index);
        if (true) {
            saveDataModelV3({
                "selectByField": "id",
                "id": id,
                "markAsDel_i": "1",
                "checkUniq": 'Y'
            }, className);
            layer.msg("关闭成功");
            try { $('#' + table).DataTable().draw(); } catch (err) {}


        }
    });
}


function getLastMonthDay(year, month) {
    var firstdate = year + '-' + month + '-01';
    var day = new Date(year, month, 0);
    var lastdate = year + '-' + month + '-' + day.getDate();
    return lastdate;
}

function _trimDateDefault(v, append, d) {
    return typeof(v) != "undefined" && v != "" ? v + append : d;
}

function getMonthQuery(monthstr) {
    var ds = monthstr;
    var begin = '';
    var end = '';
    if (ds != '') {
        var p = ds.split("-");
        if (p.length == 1) {
            return _trimDateDefault(ds + "-01-01", 'T00:00:00Z', '2001-02-16T00:00:00Z') + "_TO_" + _trimDateDefault(ds + "-12-31", 'T23:59:59Z', '2030-02-16T00:00:00Z');
        }

        end = (getLastMonthDay(p[0], p[1]));
        begin = (monthstr + "-01");
    }
    return _trimDateDefault(begin, 'T00:00:00Z', '2001-02-16T00:00:00Z') + "_TO_" + _trimDateDefault(end, 'T23:59:59Z', '2030-02-16T00:00:00Z');

}

function toPage(cId, uniqId_s, from, url) { //60.205.183.191:9098
    window.open("/-/" + url + "?_=&id=" + cId + "&seriaNum_s=" + uniqId_s + "&_from=" + from);
}

function _trimDateToDefault(v, append, d) {
    return typeof(v) != "undefined" && v != "" ? v + append : d;
}


function openNewWin(h) {
    window.open(h);
}

function getLastDay(year, month) {
    var new_date = new Date(year, month, 1); //取当年当月中的第一天
    var f = (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)); //获取当月最后一天日期
    return $.format.date(f, 'yyyy-MM-dd');
}

function _dateQuery(a, b) {
    a = $('#' + a).val(), b = $('#' + b).val();
    if (a == '' && b == '') {
        return '';
    }
    if (a == '' || !isNotNull(a)) {
        a = '2000-09-15T00:00:00Z';
    } else {
        a = a + "T00:00:00Z";
    }
    if (b == '' || !isNotNull(b)) {
        b = '2050-09-15T00:00:00Z';
    } else {
        b = b + 'T23:59:59Z';
    }
    return a + "_TO_" + b
}

function dateDBQueryTool(a, b) {
    a = $('#' + a).val(), b = $('#' + b).val();
    if (a == '' && b == '') {
        return '';
    }
    if (a == '' || !isNotNull(a)) {
        a = '2000-09-15';
    }
    if (b == '' || !isNotNull(b)) {
        b = '2050-09-15';
    }
    return a + "_TO_" + b
}

function getShortCompanyName(compName_s) {

    var fc = "";
    if (compName_s.indexOf("冠一") != -1) {
        fc = "冠一"
    } else if (compName_s.indexOf("弘永") != -1) {
        fc = "弘永"
    } else if (compName_s.indexOf("旭森") != -1) {
        fc = "旭森"
    } else if (compName_s.indexOf("港龙") != -1) {
        fc = "港龙"
    } else if (compName_s.indexOf("索凡") != -1) {
        fc = "索凡"
    } else if (compName_s.indexOf("逸雅") != -1) {
        fc = "逸雅"
    }
    return fc;
}

//导入xlsx
function fixdata(data) { //文件流转BinaryString
    var o = "",
        l = 0,
        w = 10240;
    for (; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
    o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
    return o;
}
//导出xlsx
function base64(content) {
    return window.btoa(unescape(encodeURIComponent(content)));
}

function tableToExcel_html(tableID, fileName) {
    var excelContent = $("#" + tableID).html();
    var excelContent2 = $("#Ttable").html();
    // var fileName = $("#uniqid_s").text();
    console.log(excelContent);
    var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
    excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
    excelFile += "<body><table width='10%'  border='1'>";
    excelFile += excelContent;
    excelFile += "</table>\r";
    excelFile += "<table width='100%'  border='1'>";
    excelFile += excelContent2;
    excelFile += "</table>"
    excelFile += "</body></html>";

    var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
    var a = document.createElement("a");
    a.download = fileName + ".xls";
    a.href = link;
    a.click();
}

function trim(a, d) {
    return typeof(a) == "undefined" ? d : a;
}


function suffiexLineName(lineName) {
    if (lineName.indexOf("(原") != -1) {
        lineName = lineName.split("(")[0]
    }
    if (lineName.indexOf("（原") != -1) {
        lineName = lineName.split("（")[0]
    }
    if (lineName == "300内快") return "300快内"
    if (lineName == "300外快") return "300快外"
    if (lineName == "400内快") return "400快内"
    if (lineName == "400外快") return "400快外"

    return lineName;
}