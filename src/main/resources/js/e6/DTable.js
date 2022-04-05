(function (global, factory) {
    if (typeof define === "function" && define.amd) {
        define(['exports'], factory);
    } else if (typeof exports !== "undefined") {
        factory(exports);
    } else {
        var mod = {
            exports: {}
        };
        factory(mod.exports);
        global.DTable = mod.exports;
    }
})(this, function (exports) {
    'use strict';

    Object.defineProperty(exports, "__esModule", {
        value: true
    });

    function _classCallCheck(instance, Constructor) {
        if (!(instance instanceof Constructor)) {
            throw new TypeError("Cannot call a class as a function");
        }
    }

    var _createClass = function () {
        function defineProperties(target, props) {
            for (var i = 0; i < props.length; i++) {
                var descriptor = props[i];
                descriptor.enumerable = descriptor.enumerable || false;
                descriptor.configurable = true;
                if ("value" in descriptor) descriptor.writable = true;
                Object.defineProperty(target, descriptor.key, descriptor);
            }
        }

        return function (Constructor, protoProps, staticProps) {
            if (protoProps) defineProperties(Constructor.prototype, protoProps);
            if (staticProps) defineProperties(Constructor, staticProps);
            return Constructor;
        };
    }();

    var DTable = function () {
        function DTable(tableId) {
            _classCallCheck(this, DTable);

            this.tableId = tableId;
            this.tableOj = {};
            this.tableOj.ordering = true;
            this.tableOj.iDisplayLength = 20;
            this.tableOj.aLengthMenu = [[20, 40, 100, 2000, 50000], [20, 40, 100, 2000, 50000]];
            this.datatable = ''; //DataTable;
            this._subDataTableObj;
            this.dom = '<"#toolbar"><"top"il>Bfrt<"bottom"p>';
            this.ajaxUrl = null;
            this.className = '';
            this._fnDrawCallback;
            this._createdRow;
            this.tableOj.autoScrollY = true;
            this.jumptoPageTag = true;
            this.ajaxCallback = function (json) {
                console.log("ajax-over!");
            };
            DTable.addCountField = false;
            DTable.xuhaoIndex = 0;
        }

        _createClass(DTable, [{
            key: 'withNoPageTool',
            value: function withNoPageTool() {
                this.dom = '<"#toolbar">t';
                return this;
            }
        }, {
            key: 'withExcelExport',
            value: function withExcelExport(isExport) {
                if (!isExport) {
                    this.dom = '<"#toolbar"><"top"il>rt<"bottom"p><"clear">';
                }
                return this;
            }
        }, {
            key: 'withiDisplayLength',
            value: function withiDisplayLength(length) {
                this.tableOj.iDisplayLength = length;
                return this;
            }
        }, {
            key: 'preDrawCallback',
            value: function preDrawCallback(toolbar, func) {
                var _pt = this.tableId;
                var subFun = function subFun() {
                    if ($.trim($('#' + _pt + '_wrapper').find('#toolbar').html()) == '') {
                        $('#' + _pt + '_wrapper').find('#toolbar').html(toolbar);
                        func();
                    }
                };

                this.tableOj.preDrawCallback = subFun;
                return this;
            }
        }, {
            key: 'withEnableSort',
            value: function withEnableSort(b) {
                this.tableOj.ordering = b;
                return this;
            }
        }, {
            key: 'withPageMenu',
            value: function withPageMenu(pageMenu) {
                this.tableOj.aLengthMenu = pageMenu;
            }
        }, {
            key: 'withSort',
            value: function withSort(defaultSort, notSortField) {
                this.tableOj.aaSorting = defaultSort;
                this.tableOj.columnDefs = [{ "orderable": false, "targets": notSortField }];
                return this;
            }
        }, {
            key: '_htypeHold',
            value: function _htypeHold() {
                var data = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : "";
                var row = arguments[1];
                var value = arguments[2];


                var eumnList = value.enum;

                if (isNotNull(value.enumReduce)) {
                    eumnList = value.enumReduce(row);
                }
                var reloadTable = '';
                if (isNotNull(value.reloadData)) {
                    //select 时候刷新table
                    reloadTable = '$(\'#' + this.tableId + '\' ).DataTable().draw();';
                }
                var htype = value.htype;
                var changeCallBack = '';
                if (isNotNull(value.changeCallBack)) {
                    //select 时候刷新table
                    changeCallBack = value.changeCallBack + '(\'' + row.id + '\', $(this))';
                    if ('datepicker' === htype) {
                        changeCallBack = value.changeCallBack + '(\'' + row.id + '\', this.value)';
                    }
                }

                if (isNotNull(value.suffix)) {
                    //用于上一条件字段是否存在 如果不存在 就不能操作显示 default 空白
                    var suffixField = row[value.suffix];
                    if (!isNotNull(suffixField)) {
                        if ('select' == htype) {
                            return eumnList[data];
                        } else {
                            return data;
                        }
                    }
                }

                if (isNotNull(value.suffixEq)) {
                    //用于判断是否等于某个值  直接返回 不能编辑
                    var _suffixField = row[value.suffixEq];
                    if (isNotNull(_suffixField)) {
                        if (value.eqv == _suffixField) {
                            if (isNotNull(value.showField)) {
                                return row[value.showField];
                            }
                            if ('select' == htype) {
                                return eumnList[data];
                            } else {
                                return data;
                            }
                        }
                    }
                }

                if (isNotNull(value.suffixNeq)) {
                    //用于判断是否不等于某个值  直接返回 不能编辑
                    var _suffixField2 = row[value.suffixNeq];
                    if (isNotNull(_suffixField2)) {
                        if (value.eqv != _suffixField2) {
                            if ('select' == htype) {
                                return eumnList[data];
                            } else {
                                return data;
                            }
                        }
                    }
                }
                if (isNotNull(value.suffixNot)) {
                    //用于上一条件字段是否存在 如果不存在 就不能操作显示 default 空白
                    var _suffixField3 = row[value.suffixNot];
                    if (isNotNull(_suffixField3)) {
                        if ('select' == htype) {
                            return eumnList[data];
                        } else {
                            return data;
                        }
                    }
                }

                var _width = isNotNull(value.width) ? "width:" + value.width : 'datepicker' == htype || "select" == htype ? "width:120px" : "width:250px";

                var fname = value.data;

                var cl = this.className;
                if (!isNotNull(htype)) {
                    return eumnList[data];
                } else if ('select' == htype) {
                    var optionStr = '';
                    $.each(eumnList, function (name, value) {
                        var _d = name == data ? 'selected="selected" ' : '';
                        optionStr += '<option value="' + name + '" ' + _d + '>' + value + '</option>';
                    });
                    //-----


                    var c = '<select   id="sel_' + value.data + '_' + row.id + '"  style="' + _width + ';height:35px;"   \n            rowindex="' + row.rowindex + '"\n            onchange="updateFieldByid(\'' + row.id + '\', \'' + fname + '\', this.value, \'' + cl + '\');' + changeCallBack + ';' + reloadTable + ';"\n            value="">\n\n                ' + optionStr + ' </select>';
                    return c;
                } else if ('input' == htype) {
                    return '<input id="' + row.id + '"\n                onchange="updateFieldByid(\'' + row.id + '\', \'' + fname + '\', this.value, \'' + cl + '\');' + changeCallBack + ';' + reloadTable + ';"\n                style="' + _width + ';height:35px;" type="text" value="' + data + '" /> ';
                } else if ('datepicker' === htype) {
                    if (data != '' && isNotNull(data)) {
                        data = $.format.date(data, isNotNull(value.format) ? value.format : "yyyy-MM-dd");
                    }

                    return '<input id="date_' + value.data + '_' + row.id + '" class="ui-input datepicker "\n                onchange="updateFieldByid(\'' + row.id + '\', \'' + fname + '\', this.value, \'' + cl + '\');' + changeCallBack + ';' + reloadTable + ';"\n                style="' + _width + ';height:35px;" type="text" value="' + data + '" /> ';
                }
            }
        }, {
            key: 'withColumns',
            value: function withColumns(columnsList) {
                var le = this;
                columnsList.forEach(function (value) {
                    value.defaultContent = "";
                    if (isNull(value.render)) {
                        try {
                            if (value.data.indexOf("_dt") != -1) {
                                value.render = function (data) {
                                    return data == null ? "" : $.format.date(data, isNotNull(value.format) ? value.format : "yyyy-MM-dd");
                                };
                            }
                        } catch (err) {
                            console.log(err);
                        }
                        //preparse
                        if (isNotNull(value.roles)) {
                            if (isNotNull(value.roles[value.data])) {
                                value.htype = value.roles[value.data].htype;
                                value.suffix = value.roles[value.data].suffix;
                                value.suffixNot = value.roles[value.data].suffixNot;
                                value.suffixEq = value.roles[value.data].suffixEq;
                                value.showField = value.roles[value.data].showField;
                                value.eqv = value.roles[value.data].eqv;
                                value.suffixNeq = value.roles[value.data].suffixNeq;
                                value.width = value.roles[value.data].width;
                                //value.eqv = value.roles[value.data].neqv;
                            }
                        }

                        //------
                        if (isNotNull(value.enum)) {
                            value.render = function (data, type, row) {
                                return le._htypeHold(data, row, value);
                            };
                        }
                        if (isNotNull(value.htype) && (value.htype == 'input' || 'datepicker')) {
                            value.render = function (data, type, row) {
                                return le._htypeHold(data, row, value);
                            };
                        }
                    }
                });
                this.tableOj.columns = columnsList;
                return this;
            }
        }, {
            key: 'preAjaxReduce',
            value: function preAjaxReduce(ajaxCallback) {
                this.ajaxCallback = ajaxCallback;
                return this;
            }
        }, {
            key: 'preUrl',
            value: function preUrl(url) {
                this.ajaxUrl = url;
                return this;
            }
        }, {
            key: 'withJumptoPage',
            value: function withJumptoPage(jumptoPageTag) {
                this.jumptoPageTag = jumptoPageTag;
                return this;
            }
        }, {
            key: 'withCountTd',
            value: function withCountTd(b) {
                DTable.addCountField = b;
                return this;
            }
        }, {
            key: 'withCountColumnIndex',
            value: function withCountColumnIndex(b) {
                DTable.xuhaoIndex = b;
                return this;
            }
        }, {
            key: 'withAutoScrollY',
            value: function withAutoScrollY() {
                this.tableOj.autoScrollY = true;
                return this;
            }
        }, {
            key: 'withScrollY',
            value: function withScrollY(height) {
                this.tableOj.autoScrollY = false;
                this.tableOj.scrollY = height;
                return this;
            }
        }, {
            key: 'withAjax',
            value: function withAjax(className, paramfun, ajaxType) {
                this.className = className;
                var _callBack = this.ajaxCallback;
                var _url = 'http://' + changeAddr() + '/api/getModelList?filter[className]=' + className;
                if (this.ajaxUrl != null) {
                    _url = this.ajaxUrl;
                }
                var _ajaxType = 'post';
                if (isNotNull(ajaxType)) {
                    _ajaxType = ajaxType;
                }
                this.tableOj.ajax = {
                    type: _ajaxType,
                    url: _url,
                    data: function data(d) {
                        return $.extend({}, d, paramfun());
                    },
                    "dataSrc": function dataSrc(json) {
                        json.recordsTotal = json.totalElements;
                        json.recordsFiltered = json.totalElements;
                        if (isNotNull(_callBack)) {
                            return _callBack(json);
                        }
                        return json.content;
                    }
                };
                return this;
            }
        }, {
            key: 'draw',
            value: function draw() {
                var _this = this;

                this.tableOj.dom = this.dom;
                this.tableOj.searching = false;

                if (isNotNull(this._createdRow)) {
                    this.tableOj.createdRow = this._createdRow;
                }

                this.tableOj.serverSide = true;
                this.tableOj.scrollX = true;
                if (this.tableOj.autoScrollY == false && isNull(this.tableOj.scrollY)) {
                    this.tableOj.scrollY = 550;
                }

                //this.tableOj.bAutoWidth = false;


                this.tableOj.buttons = [{
                    extend: 'excelHtml5',
                    text: '导出excel',
                    className: 'green'
                }];
                this.tableOj.language = {
                    "url": "/js/jquery.dataTables.lang.cn.json"
                }, this.tableOj.drawCallback = this._drawCallback;

                this.tableOj.footerCallback = function (row, data, start, end, display) {
                   // console.log("--call fnDrawCallback");
                    if (isNotNull(_this._fnDrawCallback)) {
                        _this._fnDrawCallback();
                    }
                };

                this.datatable = $('#' + this.tableId).DataTable(this.tableOj);

                var subDataTableobj = $('#' + this.tableId).dataTable();
                this._subDataTableObj = subDataTableobj;

                subDataTableobj.fnNameOrdering();

                $('#' + this.tableId + ' tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('selected')) {
                        $(this).removeClass('selected');
                    } else {
                        $(this).addClass('selected');
                    }
                });
            }
        }, {
            key: 'withInitComplete',
            value: function withInitComplete(func) {
                this.tableOj.initComplete = func;
                return this;
            }
        }, {
            key: 'withfnDrawCallback',
            value: function withfnDrawCallback(func) {
                this._fnDrawCallback = func;
                return this;
            }
        }, {
            key: 'withCreatedRow',
            value: function withCreatedRow(func) {
                this._createdRow = func;
                return this;
            }
        }, {
            key: 'fndraw',
            value: function fndraw() {
                this.datatable.draw();
            }
        }, {
            key: 'jumptoPage',
            value: function jumptoPage() {
                var pagenum = parseInt(document.getElementById('pagenumber').value);
                $('#' + this.tableId).dataTable().fnPageChange(pagenum - 1);
            }
        }, {
            key: '_drawCallback',
            value: function _drawCallback() {
                //counter_columns( $('#table').dataTable(),0);
                bindLayerMouseOver();
                // counter_columns(table,0);
                //设定序号列
                var api = this.api(); // this._subDataTableObj.api();
                var startIndex = api.context[0]._iDisplayStart; //获取到本页开始的条数
                if (DTable.addCountField) {
                    api.column(DTable.xuhaoIndex).nodes().each(function (cell, i) {
                        cell.innerHTML = startIndex + i + 1;
                    });
                }

                //页码跳转----------->>
                // if(this.withJumptoPage){
                var currPage = api.page.info().page + 1;
                if (!isNotNull($("#pagenumber").html())) {
                    $("#table_paginate").append('<span>到第</span><input id="pagenumber" onkeyup="value=value.replace(/[^\\d]/g,\'\')" style="width:45px" value = "' + currPage + '"onchange="jumptoPage()"/><span>页</span>');
                }
                //}
                //页码跳转-----------<<

                $('.table-action').click(function () {
                    $.post($(this).attr("url"), function () {
                        //this.datatable.draw();
                        api.draw();
                        //$('#' + table).DataTable().draw();//
                    });
                });

                try {
                    $('.datepicker').datepicker().on('click', function (ev) {
                        $(this).css("z-index", "999999999");
                    }).data('datepicker');
                } catch (err) {
                   // console.log(err);
                }
                try {
               // showTableField(api);
 
            } catch (err) {
                console.log(err);
            }
            }
        }]);

        return DTable;
    }();

    exports.DTable = DTable;
});