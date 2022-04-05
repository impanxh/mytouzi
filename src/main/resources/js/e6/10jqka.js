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

   
   
    setInterval(function () {
        var vo = get("/10jqka/indexflash");
        
        $('#p0').html(vo.dppj_data);
        
        $('#p3').html(vo.zdt_data.last_zdt.ztzs);
        $('#p4').html(vo.zdt_data.last_zdt.dtzs);
        
        $('#p1').html(vo.zdfb_data.znum);
        $('#p2').html(vo.zdfb_data.dnum);
    }, "3000");
33       