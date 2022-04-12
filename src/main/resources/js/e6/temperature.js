//  https://www.wetuancloud.com/zh-cn/g2/3.x/demo/other/line-realtime.html#

	function getWithFunc(url,func) {
	    $.ajax({
	        url: url,
	        type: 'GET',
	        dataType: 'json',
	        success: function(data) {
	            func(data);
	        }
	    })
	}


  var data = [];
  var chart = new G2.Chart({
    container: 'mountNode',
    forceFit: true,
    height: 300
  });
  chart.source(data, {
    time: {
      alias: '日期',
      tickCount: 30,
    //  nice: false
    },
    temperature: {
      alias: '平均温度(°C)',
      min: 0,
      max: 100
    },
    type: {
      type: 'cat'
    }
  });
//  chart.tooltip(false)
/*
chart.tooltip({
  shared: true,
  position: "top"
});*/

//chart.interaction('active-region'); // 使用 active-region 交互行为
  chart.line().position('time*temperature').color('type', ['rgb(134, 94, 209)', 'rgb(86, 214, 167)','rgb(255, 204, 77)', 'rgb(242, 85, 158)','rgb(102, 153, 255)']).shape('line').size(2);
   // chart.interaction("active-region");
  chart.render();

  //chart.interaction('active-region'); // 使用 active-region 交互行为
//  chart.annotation();
  
  
   var func = function (rows){
    	 //console.log(rows)
    	   for(i = 0; i < rows.length; i++){
    	        var pair = rows[i]
    	        var time  = (""+pair['时间区间']).replace("2022",'') ; 
    	        data.push({
    	  	      time: time,
    	  	      temperature: parseInt (pair['情绪温度走势']),
    	  	      type: '情绪温度走势'
    	  	    });
    		    data.push({
    		      time: time,
    		      temperature: 10,
    		      type: '冰点'
    		    });
    		     data.push({
    		      time: time,
    		      temperature: 20,
    		      type: '冷点'
    		    });
    		    data.push({
    		      time: time,
    		      temperature: 80,
    		      type: '过热'
    		    });
    		     data.push({
    		      time: time,
    		      temperature: 90,
    		      type: '沸点'
    		    });
    		   
    		  //  console.log(data);
    		  //  console.log(pair['时间区间']);
    		  
    		    
    	   }
    	    chart.changeData(data);
    	   
    }
        
  getWithFunc('/10jqka/queryDataFromCommand?filter[cmd]=startWc',func);
  setInterval(function() {
   // var now = new Date();
   // var time = now.getTime();
   data = [];
    var temperature1 = ~~(Math.random() * 100) + 22;
    var temperature2 = ~~(Math.random() * 100) + 1;
    if (data.length >= 200) {
      data.shift();
      data.shift();
    }
  
    getWithFunc('/10jqka/queryDataFromCommand?filter[cmd]=startWc',func);
 }, 5000);