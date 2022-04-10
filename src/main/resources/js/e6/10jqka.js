      function msgGn(cid,name){
   var data = gpGnAlertString [cid];
   //console.log(data);
   	layer.open({
   		 offset: 'rb',
   		 title: name+ ' &nbsp;&nbsp;所属概念板块',
          area: '390px',
		  type: 1, 
		  content: data+'<br><br>',
		});
   }
   function g2(code){
        window.open("http://q.10jqka.com.cn/gn/detail/code/"+code);
   }
    function setGpGnDefault(gnid,name){
	   saveDataModelV3('/10jqka/queryDataFromCommand?filter[cmd]=setGpGnDefault',{"filter[postData]":name+"_"+gnid});
   }
   function postData(params){
   }
   
   function saveDataModelV3(postUrl, params) {
	    var settings = {
	        "async": false,
	        "crossDomain": true,
	        "url": postUrl,
	        "method": "POST",
	        "data": params
	    }
	    $.ajax(settings).done(function(response) {
	        console.log(response);
	        layer.msg("设置成功") ;
	    });
	}
	var lastPercentAge = 100;
   
    setInterval(function () {
        var vo = get("/10jqka/indexflash");
        
        $('#p0').html(vo.dppj_data);
        
        $('#p3').html(vo.zdt_data.last_zdt.ztzs);
        $('#p4').html(vo.zdt_data.last_zdt.dtzs);
        
        $('#p1').html(vo.zdfb_data.znum);
        $('#p2').html(vo.zdfb_data.dnum); 
        var p01 = vo.dppj_data *10 ; 
        if(p01!=lastPercentAge){
	        $('#circlechart1').attr("data-percentage",p01);
	        $('#circlechart1').html( p01);
	        $('.circlechart').circlechart();  
        }
        console.log(p01)
        lastPercentAge = p01;
    
    
    
    }, "3000");
33       

/*
			        el:'my_html',//canvas元素id
			        deg:vo.dppj_data*10,//绘制角度
			        timer:10,//绘制时间
			        lineWidth:20,//线宽
			       // lineBgColor:'#e2e2e2',//底圆颜色
					//lineColor:'#e4393c',//动态圆颜色
			      //  textColor:'#000',//文本颜色
			         //rgb(89, 184, 129)
			        lineBgColor:'#e2e2e2',//底圆颜色
			        lineColor:'#d75442',//动态圆颜色
			        textColor:'#d75442',//文本颜色
			        fontSize:50,//字体大小
			        circleRadius:70//圆半径
	   		 */