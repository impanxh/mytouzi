    setInterval(function () {
        var vo = get("/10jqka/indexflash");
        
        $('#p0').html(vo.dppj_data);
        
        $('#p3').html(vo.zdt_data.last_zdt.ztzs);
        $('#p4').html(vo.zdt_data.last_zdt.dtzs);
        
        $('#p1').html(vo.zdfb_data.znum);
        $('#p2').html(vo.zdfb_data.dnum);
    }, "3000");
33       