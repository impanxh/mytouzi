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

    var coluns = [
    
    { "data": "cid", "render": function render(data, type, row, meta) {

            var c2 = data.substring(2, 8);
            var url = 'http://stockpage.10jqka.com.cn/' + c2 + '/#refCountId=stockpage_5c3e9aef_93';
            var c = '<a class="table-link" target="_blank" onclick="signIn(\'' + url + '\');"> ' + c2 + ' </a>';
            return c;
        } }, { "data": "name" }, { "data": "cate", "render": function render(data, type, row, meta) {
             return data;
       } },{ "data": "jxz" },
         { "data": "zdf", "render": function render(data, type, row, meta) {
            data = parseFloat(data);
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "0%" : " <span class='bold green'>" + data + "%</span>  ";
        } },
        { "data": "kpzdf", "render": function render(data, type, row, meta) {
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "-" : " <span class='bold green'>" + data + "%</span>  ";
        } }, 
        { "data": "zkb", "render": function render(data, type, row, meta) {
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "-" : " <span class='bold green'>" + data + "%</span>  ";
        } } 
    
    ];

    var ajaxCallback = function ajaxCallback(json) {
        json.recordsTotal = json.length;
        json.recordsFiltered = json.length;
        return json;
    };
    var ta = new _DTable.DTable("table2d").withiDisplayLength(30).withSort([[2, "desc"]], [1, 0]).preDrawCallback(tooblar, function () {//set tooblar and run fuction
    }).preAjaxReduce(ajaxCallback).preUrl('/gp/ztdx-2dy').withAjax("codegen", function () {
        //filter
        var st = $('#st').val();
        return {
            "filter[year]": $('#type_s1').val(),
            "filter[px]": getParamFromUrl("px"),
            "filter[hpx]": getParamFromUrl("hpx")

        };
    }).withAutoScrollY().withColumns(coluns).withExcelExport(true).withCountTd(false).withEnableSort(true); // is excelport
    ta.dom = '<"#toolbar">t';
    
    ta.withCreatedRow(function (row, data, index) {
        if (data.kpzdf < 0) {
            $('td', row).css('background-color', '#FFFFCC');
        }
    })
    
    
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