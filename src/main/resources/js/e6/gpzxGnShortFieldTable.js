var gpGnAlertString = {};
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
        global.gpzxTable = mod.exports;
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

            var c2 = data.substring(2, 8);
            var url = 'http://stockpage.10jqka.com.cn/' + c2 + '/#refCountId=stockpage_5c3e9aef_93';
            var c = '<a class="table-link" target="_blank" onclick="signIn(\'' + url + '\');"> ' + c2 + ' </a>';
            return c;
        } }, { "data": "name" },
        
           { "data": "zdf", "render": function render(data, type, row, meta) {
            data = parseFloat(data);
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "0%" : " <span class='bold green'>" + data + "%</span>  ";
        } },
        
        
        { "data": "kpzdb", "render": function render(data, type, row, meta) {
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "-" : " <span class='bold green'>" + data + "%</span>  ";
        } }, { "data": "zkb", "render": function render(data, type, row, meta) {
            return data > 0 ? " <span class='bold red'>" + data + "%</span> " : data == 0 ? "-" : " <span class='bold green'>" + data + "%</span>  ";
        } },
        
         { "data": "cate", "render": function render(data, type, row, meta) {
            var info = data;

            var f = row.hyzd;
            if (f > 0) {
                f = "( <span class='red'>" + f + "%</span>  <span></span> <span></span>   )";
            }
            if (f < 0) {
                f = "( <span class='green'>" + f + "%</span>  <span></span> <span></span>   )";
            }
            if (f == 0) {
                f = "0%";
            }
            var url = 'http://q.10jqka.com.cn/thshy/detail/code/' + row['hyid'] + '/';
            var c = '&nbsp;<a class="table-link" target="_blank" onclick="signIn(\'' + url + '\');"> ' + info + ' ' + f + '</a>';
            return c;
        } }, { "data": "bsstr" }, 
        
        
      
       //  { "data": "liang15d" },
       //   { "data": "ztzd", "render": function render(data, type, row, meta) {
        //    return data == "昨涨" ? " <span class='bold red'>" + data + "</span> " : data;
       // } },
        
         { "data": "score" }, { "data": "czstr" },
          { "data": "cje", "render": function render(data, type, row, meta) {
         		if( data =="-" || data ==""){
         			return "";
         			
         		}else{
	         		var rows  = data.split(",");
	         		var r = [];
	         		var shorN = '';
	         		var output = '';
         		     for(i = 0; i < rows.length; i++){
         		      var pair = rows[i].split(":");
         		      var name = pair[1];
         		      var code = pair[0]
         		      	
         		      	var pcode = $.trim(code) ; 
         		      	var json  = gnCache[pcode];
         		      	if(isNotNull(json)){
         		      		 r.push(json); 
         		      	}
         		      	 r.sort(function(a,b){
					            if(a['199112']>b['199112']) return -1 ;
					            if(a['199112']<b['199112']) return 1 ;
					            return 0 ;
					     }) ;
         		   } 
         		   
         		     if(r.length > 0 ){
					     	shorN = r[0].platename;   
					     	  for(var w = 0; w < r.length; w++){
					     	  	var obj = r[w];
					     	  	var _cid = $.trim(obj.cid) ; 
					     	  	var  setDefault = '&nbsp;&nbsp;&nbsp;'+ '<a class="table-link" target="_blank" onclick="setGpGnDefault(\'' + _cid + "','" + row.name     + '\');">设置默认显示</a>';
					     	  	
					     	   output += '&nbsp;&nbsp; '
					     	  	 + '&nbsp;<a class="table-link" target="_blank" onclick="g2(\'' +_cid  + '\')";>'
					     	    + obj.platename + "&nbsp;&nbsp;["+obj['199112']+"]" 
					     	    + "&nbsp;&nbsp;"+obj['zjjlr']
					     	    +  setDefault  
					     	    
					     	    + '</a><br>';
					     	  }    
					  }
					 if(shorN != '' && shorN.length > 4){
         		      		shorN = shorN.substring(0, 4);
         		     }
         		      if(row.hsl!=''){
         		        var pair = row.hsl.split("_");
         		      	var json  = gnCache[pair[1]];
         		      	if(isNotNull(json)){
         		      	  shorN = json.platename
         		      	} 
         		     }
         		     gpGnAlertString[row.cid] =  output;
         		   //console.log(output);
         		   
         		   return  '<a class="table-link" target="_blank" onclick="msgGn(\'' + row.cid + "','" + row.name     + '\');"> ' + shorN + '</a>';
         		}
         		
           	 
        } } 
         ];

    var ajaxCallback = function ajaxCallback(json) {
        json.recordsTotal = json.length;
        json.recordsFiltered = json.length;
        return json;
    };
    var ta = new _DTable.DTable("table").withiDisplayLength(30).withSort([[2, "desc"]], [1, 0]).preDrawCallback(tooblar, function () {//set tooblar and run fuction
    }).preAjaxReduce(ajaxCallback).preUrl('/gp/ztdx-zx').withAjax("codegen", function () {
        //filter
        
         // console.log(gnCache)
        var st = $('#st').val();
        return {
            "filter[year]": $('#type_s1').val(),

            "filter[px]": getParamFromUrl("px"),
            "filter[hpx]": getParamFromUrl("hpx")

        };
    }).withAutoScrollY().withColumns(coluns).withExcelExport(true).withCountTd(false).withEnableSort(false); // is excelport
    ta.dom = '<"#toolbar">Bfrt';
   	ta.withCreatedRow(function (row, data, index) {
        if (data.kpzdb < 0) {
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

        //1
        var vo = get("/gp/ztdx-qx");
        $('#zx_gkb').html(vo.zxjkvo.zx_gkb + "%");
        $('#zx_jzf').html(vo.zxjkvo.zx_jzf + "%");
        $('#h31').addClass(vo.zxjkvo.zx_css);
        $('#zxjkvo_zx_gknum').html(vo.zxjkvo.zx_gknum + "/" + vo.zxjkvo.zx_allnum);
        $('#zx_jzf').addClass(vo.zxjkvo.zx_jzf > 3 ? "red" : "green");

        //2
        $('#h32').addClass(vo.zxjkvo.zx_sszdb > vo.zxjkvo.zx_gkb ? "red" : "green");

        $('#b1').html("[" + (vo.zxjkvo.zx_sszdb - vo.zxjkvo.zx_gkb).toFixed(2) + "%]");
        $('#b2').html(vo.zxjkvo.zx_sszdb + "%");

        $('#b3').html("[" + (vo.zxjkvo.zx_ssjzf - vo.zxjkvo.zx_jzf).toFixed(2) + "%]");
        $('#b4').html(vo.zxjkvo.zx_ssjzf + "%");
        $('#b4').addClass(vo.zxjkvo.zx_ssjzf > vo.zxjkvo.zx_jzf ? "red" : "green");

        $('#b5').html(vo.zxjkvo.zx_sszkb + "%");
        $('#b5').addClass(vo.zxjkvo.zx_sszkb > 1 ? "red" : "green");

        //2


        $('#z40').addClass(vo.ztjkvo.jk_css);
        $('#z41').html(vo.ztjkvo.jk_gknum + "/" + vo.ztjkvo.jk_allnum);
        $('#z42').html(vo.ztjkvo.jk_gkb + "%");
        $('#z43').html(vo.ztjkvo.jk_jzf + "%");

        $('#z43').addClass(vo.ztjkvo.jk_jzf > 2.5 ? "red" : "green");

        //2.2
        $('#z32').addClass(vo.ztjkvo.jk_sszdb > vo.ztjkvo.jk_gkb ? "red" : "green");

        $('#z1').html("[" + (vo.ztjkvo.jk_sszdb - vo.ztjkvo.jk_gkb).toFixed(2) + "%]");
        $('#z2').html(vo.ztjkvo.jk_sszdb + "%");
        $('#z2').addClass(vo.ztjkvo.jk_sszdb >= 65 ? "red" : "green");

        $('#z3').html("[" + (vo.ztjkvo.jk_ssjzf - vo.ztjkvo.jk_jzf).toFixed(2) + "%]");
        $('#z4').html(vo.ztjkvo.jk_ssjzf + "%");
        $('#z4').addClass(vo.ztjkvo.jk_ssjzf > vo.ztjkvo.jk_jzf ? "red" : "green");

        $('#z5').html(vo.ztjkvo.jk_sszkb + "%");
        $('#z5').addClass(vo.ztjkvo.jk_sszkb > 1 ? "red" : "green");

        //3

        //3
        // console.log(vo.dtjkvo.jk_css);
        $('#t40').addClass(vo.dtjkvo.jk_css);
        $('#t41').html(vo.dtjkvo.jk_gknum + "/" + vo.dtjkvo.jk_allnum);
        $('#t42').html(vo.dtjkvo.jk_gkb + "%");
        $('#t43').html(vo.dtjkvo.jk_jzf + "%");

        $('#t43').addClass(vo.dtjkvo.jk_jzf > 2.5 ? "red" : "green");

        //3.2
        $('#t0').addClass(vo.dtjkvo.jk_sszdb > vo.dtjkvo.jk_gkb ? "red" : "green");

        $('#t1').html("[" + (vo.dtjkvo.jk_sszdb - vo.dtjkvo.jk_gkb).toFixed(2) + "%]");
        $('#t2').html(vo.dtjkvo.jk_sszdb + "%");
        $('#t2').addClass(vo.dtjkvo.jk_sszdb >= 60 ? "red" : "green");

        $('#t3').html("[" + (vo.dtjkvo.jk_ssjzf - vo.dtjkvo.jk_jzf).toFixed(2) + "%]");
        $('#t4').html(vo.dtjkvo.jk_ssjzf + "%");
        $('#t4').addClass(vo.dtjkvo.jk_ssjzf > vo.dtjkvo.jk_jzf ? "red" : "green");

        $('#t5').html(vo.dtjkvo.jk_sszkb + "%");
        $('#t5').addClass(vo.dtjkvo.jk_sszkb > 1 ? "red" : "green");

        //44
        $('#g40').addClass(vo.gbjkvo.jk_css);
        $('#g41').html(vo.gbjkvo.jk_gknum + "/" + vo.gbjkvo.jk_allnum);
        $('#g42').html(vo.gbjkvo.jk_gkb + "%");
        $('#g43').html(vo.gbjkvo.jk_jzf + "%");

        $('#g43').addClass(vo.gbjkvo.jk_jzf > 2.5 ? "red" : "green");

        //3.2
        $('#g0').addClass(vo.gbjkvo.jk_sszdb >= vo.gbjkvo.jk_gkb ? "red" : "green");

        $('#g1').html("[" + (vo.gbjkvo.jk_sszdb - vo.gbjkvo.jk_gkb).toFixed(2) + "%]");
        $('#g2').html(vo.gbjkvo.jk_sszdb + "%");
        $('#g2').addClass(vo.gbjkvo.jk_sszdb >= 65 ? "red" : "green");

        $('#g3').html("[" + (vo.gbjkvo.jk_ssjzf - vo.gbjkvo.jk_jzf).toFixed(2) + "%]");

        $('#g3').addClass(vo.gbjkvo.jk_ssjzf > vo.gbjkvo.jk_jzf ? "red" : "green");

        $('#g4').html(vo.gbjkvo.jk_ssjzf + "%");
        $('#g4').addClass(vo.gbjkvo.jk_ssjzf > vo.gbjkvo.jk_jzf ? "red" : "green");

        $('#g5').html(vo.gbjkvo.jk_sszkb + "%");
        $('#g5').addClass(vo.gbjkvo.jk_sszkb > 1 ? "red" : "green");

        //55


        $('#r40').addClass(vo.hygpjkvo.jk_css);
        $('#r41').html(vo.hygpjkvo.jk_gknum + "/" + vo.hygpjkvo.jk_allnum);
        $('#r42').html(vo.hygpjkvo.jk_gkb + "%");
        $('#r43').html(vo.hygpjkvo.jk_jzf + "%");

        $('#r43').addClass(vo.hygpjkvo.jk_jzf > 2.5 ? "red" : "green");

        //5.2
        $('#r0').addClass(vo.hygpjkvo.jk_sszdb > vo.hygpjkvo.jk_gkb ? "red" : "green");

        $('#r1').html("[" + (vo.hygpjkvo.jk_sszdb - vo.hygpjkvo.jk_gkb).toFixed(2) + "%]");
        $('#r2').html(vo.hygpjkvo.jk_sszdb + "%");
        $('#r2').addClass(vo.hygpjkvo.jk_sszdb >= 60 ? "red" : "green");

        $('#r3').html("[" + (vo.hygpjkvo.jk_ssjzf - vo.hygpjkvo.jk_jzf).toFixed(2) + "%]");
        $('#r4').html(vo.hygpjkvo.jk_ssjzf + "%");
        $('#r4').addClass(vo.hygpjkvo.jk_ssjzf > vo.hygpjkvo.jk_jzf ? "red" : "green");

        $('#r5').html(vo.hygpjkvo.jk_sszkb + "%");
        $('#r5').addClass(vo.hygpjkvo.jk_sszkb > 1 ? "red" : "green");
    }, "3000");
});