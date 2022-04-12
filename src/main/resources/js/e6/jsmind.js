function load_jsmind(){
        var mind = {
            "meta":{
                "name":"demo",
                "author":"impanxh@icloud.com",
                "version":"0.2",
            },
            "format":"node_array",
            "data":[
                {"id":"root", "isroot":true, "topic":"涨停低吸模型"},

                {"id":"sub1", "parentid":"root", "topic":"选股", "background-color":"#0000ff"},
                {"id":"sub11", "parentid":"sub1", "topic":"只关注最近3天3连板和2连板的票"},
                {"id":"sub12", "parentid":"sub1", "topic":"T字版的优先级高于一字板"},
              //  {"id":"sub13", "parentid":"sub1", "topic":"sub13"},

                {"id":"sub2", "parentid":"root", "topic":"买点"},
                {"id":"sub21", "parentid":"sub2", "topic":"博今天"},
                {"id":"sub211", "parentid":"sub21", "topic":"30分的时候 开盘3个点以下水下更佳"},
                {"id":"sub212", "parentid":"sub21", "topic":"必须是有向上趋势的时候才买入,多些时间观察"},
                
                
                {"id":"sub22", "parentid":"sub2", "topic":"博明天","foreground-color":"#33ff33"},
                {"id":"sub221", "parentid":"sub22", "topic":"按博今天 当天无票可买情况下才操作"},
                {"id":"sub222", "parentid":"sub22", "topic":"在快涨停或是刚涨停的时候挂价猛入"},
                {"id":"sub223", "parentid":"sub22", "topic":"在微信炸板通知下买入"},
                
                {"id":"sub24", "parentid":"sub2", "topic":"风险","foreground-color":"red"},
                {"id":"sub241", "parentid":"sub24", "topic":"情绪差 博今天当天亏近13个点"},
                {"id":"sub242", "parentid":"sub24", "topic":" 涨停价博明天 可能当天亏20个点"},
                
                
                {"id":"sub3", "parentid":"root", "topic":"卖点"},
                {"id":"sub31", "parentid":"sub3", "topic":"有收益 情况下 坚持持有 利润最大化"},
                {"id":"sub33", "parentid":"sub3", "topic":"当天买进 不涨停 明天即出"},
                {"id":"sub34", "parentid":"sub3", "topic":"涨停后第二天 如果未达预期或是未涨停 出", "foreground-color":"#d75442"},
                {"id":"sub35", "parentid":"sub3", "topic":"跌超过5个点 及时止损 一身轻松"},
                
                
                {"id":"sub4", "parentid":"root", "topic":"盘中不交易"},
                {"id":"sub41", "parentid":"sub4", "topic":"交易时间控制在开盘5分钟内"},
                {"id":"sub42", "parentid":"sub4", "topic":"正常卖出时间统一到2：10分,止损随时"},
                {"id":"sub43", "parentid":"sub4", "topic":"只在二种情况下 才交易<br>  一 及时止损的情况 尽早割肉<br> 二 早盘无股可买的情况下博明天买股 "},
                   
                {"id":"sub5", "parentid":"root", "topic":"尽可能多时候去空仓 总结思考"},
                
                
                {"id":"sub6", "parentid":"root", "topic":"持多少只股合适"},
                {"id":"sub61", "parentid":"sub6", "topic":"2-3只 2只最佳 多了精力管理不过来"},
                
                
                
                {"id":"sub3", "parentid":"root", "topic":"sub3"},
            ]
        };
        var options = {
            container:'jsmind_container',
            editable:true,
            theme:'primary'
        }
        var jm = jsMind.show(options,mind);
        // jm.set_readonly(true);
        // var mind_data = jm.get_data();
        // alert(mind_data);
        //jm.add_node("sub2","sub23", "new node", {"background-color":"red"});
        jm.set_node_color('sub21', 'green', '#ccc');
        jm.set_node_color('sub24', 'red', '#ccc');
        jm.set_node_color('sub34', 'red', '#ccc');
    }

    load_jsmind();