var gnCache = {};

(function (global, factory) {
    if (typeof define === "function" && define.amd) {
        define(['DTable'], factory);
    } else if (typeof exports !== "undefined") {
        factory(require('DTable'));
    } else {
        var mod = {
            exports: {}
        };
        factory(global.DTable);
        global.gphyTable = mod.exports;
    }
})(this, function (_DTable) {
    'use strict';

    //set query tooblar
    var tooblar = '<div> \n</div>';
    //How to control data entry to have date format
    //class="validate[required,custom[date]]" doesn't work 
    //datepicker still doesn't work

    //set result result;


    var role = '';
    role = {};
    //

    var coluns = [{ "data": "cid", "render": function render(data, type, row, meta) {
            var url = 'http://q.10jqka.com.cn/gn/detail/code/' + data + '/';
            var c = '<a class="table-link" target="_blank" onclick="signIn(\'' + url + '\');"> ' + row.platename + ' </a>';
            return c;
        } }, { "data": "199112", "render": function render(data, type, row, meta) {
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "0%" : " <span class='bold green'>" + data + "%</span>  ";
        } }, { "data": "zjjlr", "render": function render(data, type, row, meta) {
            var a = data > 0 ? " <span class='bold red'>" + data+ "</span> " : data == 0 ? "0" : " <span class='bold green'>" + data + "</span>  ";
            return a  
        } }];

    var ajaxCallback = function ajaxCallback(json) {
        json.recordsTotal = json.length;
        json.recordsFiltered = json.length;
        var p1 ={}
        for(i = 0; i < json.length; i++){
    		  var pair = json[i] 
             p1[ pair['cid'] ] = pair; 
            
  	 	} 
  	 	gnCache = p1;
  	 	//console.log(gnCache)
        return json;
    };
    var ta = new _DTable.DTable("tablehy").withiDisplayLength(30).withSort([[1, "desc"]], [ 0]).preDrawCallback(tooblar, function () {//set tooblar and run fuction
    }).preAjaxReduce(ajaxCallback).preUrl('/10jqka/queryDataFromCommand?filter[cmd]=gnSortedList').withAjax("codegen", function () {
        //filter
        var st = $('#st').val();
        return {
            "filter[year]": $('#type_s1').val(),
            "filter[px]": getParamFromUrl("px"),
            "filter[hpx]": getParamFromUrl("hpx")

        };
    }).withAutoScrollY().withColumns(coluns).withExcelExport(true).withCountTd(false).withEnableSort(true); // is excelport
    ta.dom = '<"#toolbar">t';
    ta.withInitComplete(function () {
        //set initcomplete
        $('#kpi_s,#type_s1').change(function () {
            ta.fndraw();
        });
    }).draw();

    setInterval(function () {
        ta.fndraw();
    }, "3000");
});