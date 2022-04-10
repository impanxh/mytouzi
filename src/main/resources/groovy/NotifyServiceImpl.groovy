package com.huangxifeng.gupiao.service.impl;

import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

import java.text.SimpleDateFormat

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils
import org.springframework.stereotype.Service

import com.huangxifeng.gupiao.jymodel.RunJianKong
import com.huangxifeng.gupiao.jymodel.RunZhangTingDiXi
import com.huangxifeng.gupiao.service.NotifyService
import com.huangxifeng.gupiao.vo.JianKongVO
import com.huangxifeng.gupiao.vo.QingXuJianKongVO
import com.huangxifeng.gupiao.vo.ZhangTingDiXiVO
import com.huangxifeng.gupiao.util.HtmlUtil
import com.huangxifeng.gupiao.util.TableRequest
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.json.StringEscapeUtils
import groovyx.net.http.*
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huangxifeng.core.config.Config
import com.huangxifeng.core.utils.HttpClientUtil;
/*
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;*/


@Service
public class NotifyServiceImpl implements NotifyService {

	//private static Logger log = LoggerFactory.getLogger("M");



	def totalAlResult = [:]


	public void runUpDownMsg() {

		new Thread(new Runnable() {
					@Override
					public void run() {
						while(1==1){
							try {
								List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
								zxlist.each { t ->
									def code = t.getCid() ;
									def key = code+t.getName() ;
									def zdf = NumberUtils.toFloat(t.getZdf()) ;
									if(totalAlResult.containsKey(key)) {
										def timeView  = totalAlResult[key];
										def hhmm = new SimpleDateFormat("HH:mm").format( System.currentTimeMillis()   )  ;
										//println hhmm;
										if(timeView!=null &&  timeView.containsKey(hhmm)) {
											def number = timeView[hhmm] ;
											if(number > 5000 && zdf < 9.7 && zdf > -9.5) {
												def msg = getCurrTime () + " " +t.getName() + " 拉升 - 分钟交易量:"+ number+" 当前:"+zdf+"%" ;
												//sendMsg(msg,'1000003',  'giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0');
												sendMsg(msg,'1000004',  'PuMOZPQ0ubdUm8o5dFNNlcMJRARRIYMXzMdgjbK4IK8');
											}
										}
									}
								}
							} catch (Exception e) {
								e.printStackTrace()
							}finally {
								Thread.sleep(1000*20);
							}
						}
					}
				}).start();
		println "Start runUpDownMsg thread"
	}

	public Object getStockDetailResult() {
		//runUPDownMsg();
		return  totalAlResult;
	}

	public Object startStockDetail() {
		println "run-method"

		List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
		def i = 0;
		//开启拉升监控
		runUpDownMsg();
		println "startStockDetail~~~~~~~~~~~~~~ - "

		def pageCache = [:]
		while(1==1) {
			zxlist.each { t ->
				def code = t.getCid() ;
				def minView  = [:]
				def hh = new SimpleDateFormat("HH").format( System.currentTimeMillis()  )  ;
				//println hh ;
				//println code;
				if( (code=='sz002349' || 1==1)  && NumberUtils.toInt(hh) < 15) {
					//https://stock.gtimg.cn/data/index.php?appn=detail&action=data&c=sz002349&p=66
					HttpApi f = new HttpApi('https://stock.gtimg.cn');
					def hit  = true;
					(1..90).each{w->
						if(hit) {


							def lastPage = pageCache[code] == null ? w : pageCache[code] + w;
							//lastPage = w;

							//println code+ " "+lastPage;
							def result = f.requestWithFullYParams(null, "/data/index.php",[ "action": "data", "appn":"detail", "c":code,"p":lastPage],[:], Method.GET,groovyx.net.http.ContentType.HTML );
							//Thread.sleep(10);
							def str = String.valueOf(result) ;
							if(str.length()>40) {
								def split1 = str.split("\"");
								//println "-->"+split1[1];
								def oneTime = split1[1].split("\\|");

								oneTime.each { item->
									def timeSplit = item.split("/");
									//println timeSplit
									String time  = timeSplit[1] ;
									def type  = timeSplit[6] ;
									def number  = timeSplit[4] ;
									def min1 =  time.split(":")
									def min = min1[0] +":" + min1[1]

									if(!minView.containsKey(min)) {
										minView[min] = 0;
									}
									if(type == "B") {
										minView[min]  =  minView[min]  + NumberUtils.toInt(number);
									}
									if(type == "S") {
										minView[min]  =  minView[min]  - NumberUtils.toInt(number);
									}
								}
							}else {
								def _lastpage  = lastPage - 5 ; //只取最近5页数据 分析 最近几分钟
								if(_lastpage <= 0) {
									_lastpage= 1;
								}
								pageCache[code] = _lastpage
								//println "run over  "+code + " - "+ t.getName()+ " lastpage:" + lastPage;
								totalAlResult[code+t.getName()] = minView;
								hit = false;
							}//end if

						}

					}
				}

				i++;
			}

			 println " 拉升监控一轮结束  " + pageCache ;
			Thread.sleep(1000*15);

		}
		return totalAlResult;
	}



	public void sendMsg(text,agentid,corpsecret) {
		try {
			println text;

			HttpApi f = new HttpApi('https://qyapi.weixin.qq.com'); //1000003  giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0
			def token = f.requestWithFullYParams(null, "/cgi-bin/gettoken",[   "corpid":"ww44f4eb1850de9bf1", "corpsecret":corpsecret]	,[:], Method.GET ,groovyx.net.http.ContentType.JSON);
			def str =''' {
				   "touser" : "@all",  "msgtype" : "text", "agentid" : "$agentid", "text" : {
				       "content" : "$text"   
					}
				} 
			'''
			def engine = new groovy.text.SimpleTemplateEngine()
			def output =  engine.createTemplate(str).make(['text':text,'agentid':agentid ]).toString()
			def b = f.requestWithBody( "/cgi-bin/message/send",[   "access_token": token.access_token ]	,output );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	def getCurrTime() {
		return new SimpleDateFormat("HH:mm:ss").format( System.currentTimeMillis()   )  ;
	}

	public   List<String> getSubUtil(String soap, String rgex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);

		while (m.find()) {
			int i = 1;
			list.add(m.group(i)+"_"+m.group(i+1));
			i++;
		}
		return list;
	}
	public  String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}


	public def  getHead(LinkedHashMap  map) {
		return map.entrySet().iterator().next();
	}

	public Object queryDataFromCommand(TableRequest req) {
		def params  = req.getFilter();
		def  cmd = params['cmd'];
		//println "---------> " + cmd
		if(  StringUtils.startsWith( cmd, "sync")  ||  StringUtils.startsWith( cmd, "start")     ){
			try {
				return "${cmd}"()
			} finally {
			}
		}
		if( cmd == "gnSortedList") {
			return getSortGnList(req);
		}
		if( cmd == "setGpGnDefault") {
			return setGpGnDefault(params);
		}
		
		return 'default';
	}
	def setGpGnDefault(params) {
		
		if(params .postData!=null && params .postData!= '' ) {
			String dtfileurl = Config.DATA_DIR + File.separator + "data" + File.separator + "gnDefault.txt";
			new File(dtfileurl) << params .postData+"\n";
			
			def split = params .postData .split("_") ;
			List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
			zxlist.each { t ->
				if(t.name == split[0]) {
					t.setHsl(params .postData);
				}
			}
		}
	}
	/*
	 * 从文件加载股票对应gn信息
	 */
	def reloadGnFromFile(params) {
			String dtfileurl = Config.DATA_DIR + File.separator + "data" + File.separator + "gnDefault.txt";
			def file = new File(dtfileurl) ;
			if(file.exists()) {
				List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
				def zxListMap  =[:]
				zxlist.each { t ->
					zxListMap[t.name.trim()] = t;
				}
				def mergeMap = [:]
				new File(dtfileurl) .eachLine(){
					line -> 
					def split = line .split("_") ;
					mergeMap[split[0].trim()] = line ;
				}
				mergeMap.each { code, line -> 
					if(zxListMap.containsKey(code)) {
						zxListMap[code].setHsl(line);
					}
				}
			}
	}

	def getSortGnList(req) {
		def sortField  = req.getOrder()==null ?null: req.getOrder()[0] ;
		def entry  = null;
		if(sortField!=null) {
			entry = getHead(sortField); 
			if(entry!=null && entry.getKey() == '199112'  ){
				return 	  	gn199SortedList .sort{a,b-> return a['199112'] < b['199112']? ( entry.getValue() == 'asc'?-1:1 )   : (a['199112'] == b['199112'] ?0:( entry.getValue() == 'asc'?1:-1 ) )};
			}
			if(entry!=null && entry.getKey() == 'zjjlr'  ){
				return 	 gn199SortedList. sort{a,b-> return a.zjjlr < b.zjjlr? ( entry.getValue() == 'asc'?-1:1 ) : (a.zjjlr == b.zjjlr ?0:( entry.getValue() == 'asc'?1:-1  ))};
			}
		}
		return gn199SortedList
	}
	def gnThreadstarted = false;
	void startGnThread() {
		if(gnThreadstarted) {
			return ;
		}
		gnThreadstarted = true;
		new Thread(new Runnable() {
					@Override
					public void run() {
						while(1==1){
							try {
								syncGn();
							} catch (Exception e) {
								e.printStackTrace()
							}finally {
								Thread.sleep(1000*4);
							}
						}
					}
				}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						try {
							reloadGnFromFile();
						} catch (Exception e) {
							e.printStackTrace()
						}finally {
							Thread.sleep(1000*4);
						}
					}
				}
			}).start();
			
			

	}

	void parseGnSection (body){
		def jsonSlurper = new JsonSlurper()
		//println body;
		def json = getSubUtilSimple(body,"<input type=\"hidden\" id=\"gnSection\" value=(.*?)>");
		if(json!=null) {
			json =json.substring(1,json.length()-1)
			def map = jsonSlurper.parseText(json)
			def list = map.values();
			println  "\n概念板块个数:"+gnMap.size()+" , 有效个数"+list.size();
			list.each {  item->
				//println item;
			}
			gn199SortedList   = list;//.sort{a,b-> return a['199112'] < b['199112']? 1: (a['199112'] == b['199112'] ?0:-1)};
			gnZjjlrSortedList  = list;//.sort{a,b-> return a.zjjlr < b.zjjlr? 1: (a.zjjlr == b.zjjlr ?0:-1)};
		}

	}
	def gn199SortedList = [:]
	def gnZjjlrSortedList = [:]
	def gnMap = [:]
	/**
	 * 获取 同花顺概念板块
	 */
	def syncGn(){
		String body = HttpClientUtil.getInstance().doGet("http://q.10jqka.com.cn/gn");
		List<String> codeList = getSubUtil(body, "<a href=\"http://q.10jqka.com.cn/gn/detail/code/(.*?)/\" target=\"_blank\">(.*?)</a>");
		//println body
		//println codeList
		codeList.each {  t ->
			def split = t.split("_");
			gnMap[split[0]] = split[1];
		}
		//println "概念板块: "+gnMap.size();
		//println "概念板块: "+gnMap.getClass();
		parseGnSection(body);
		return gnMap;

	}

	//----------------------------------------------
	def codeGnGroup=[:]

	def getCodeGnGroupInfo(){
		def r = [:]
		println codeGnGroup;

		codeGnGroup.each{ 	k,v->
			def gpNameSet = [] as HashSet;
			v.each{ code ->
				def gpName =  gnMap[code]   ;
				gpNameSet.add(  gpName );
			}
			r[k] = gpNameSet
		}
		return r;
	}
	/*
	 * group 股票和概念板块结果 得到类似结果  光云科技=301558:阿里巴巴概念, 300186:电子商务, 300900:融资融券, 302035:人工智能, 301524:国产软件
	 */
	def distinctGngp(){
		def newFile =  new FileWriter("/opt/tomcat_gpz/gplist_distinct.txt")
		def map = [:]
		File outputfile = new File("/opt/tomcat_gpz/gplist.txt")
		outputfile.eachLine{ t->
			//天源迪科_301558:阿里巴巴概念

			def split = t.split("_");
			def key = split[0] ;

			if(!map.containsKey(key)){
				map[key]  = [] as HashSet;
			}
			map[key] .add(split[1]);
		}
		map.each{ k,v->
			newFile.write(k+"="+v.toString()+"\n");
		}

	}
	
	def wcResult = null;
	def lastWcUpdate =  -1; 
	def startWc() {
		
		
		if(wcResult != null  ||  (  ( System.currentTimeMillis() -lastWcUpdate) < 45000   )  ) {
			return wcResult;
		}
		/*
		def a1 = URLEncoder. encode("log_info={\"input_type\":\"click\"}&perpage=50&secondary_intent=", "utf-8" ) 
		def a2 = URLEncoder. encode("&block_list=&add_info={\"urp\":{\"scene\":1,\"company\":1,\"business\":1},\"contentType\":\"json\",\"searchInfo\":true}", "utf-8" )
		
		def body =  '<html><body><script src="//s.thsi.cn/js/chameleon/chameleon.min.1649407.js" type="text/javascript"></script>'+
		'<script language="javascript" type="text/javascript">'+
		'window.location.href="http://iwencai.com/unifiedwap/unified-wap/v2/result/get-robot-data?question=短线复盘&source=Ths_iwencai_Xuangu&version=2.0&page=1&'+
		   a1+a2+
		'"</script>'+ 
		'</body></html>'
	
		def str11 =	"http://iwencai.com/unifiedwap/unified-wap/v2/result/get-robot-data?question=短线复盘&source=Ths_iwencai_Xuangu&version=2.0&page=1&"
		//def str12  = URLEncoder. encode("log_info={\"input_type\":\"click\"}&perpage=50&secondary_intent=", "utf-8" )
		 //def str13 = URLEncoder. encode("&block_list=&add_info={\"urp\":{\"scene\":1,\"company\":1,\"business\":1},\"contentType\":\"json\",\"searchInfo\":true}", "utf-8" )
		
		
		def str12  =  "log_info={\"input_type\":\"click\"}&perpage=50&secondary_intent="
		def str13 =  "&block_list=&add_info={\"urp\":{\"scene\":1,\"company\":1,\"business\":1},\"contentType\":\"json\",\"searchInfo\":true}" 
		 def req = str11+str12+str13
		 */
		 
		 HtmlUtil  util = new HtmlUtil();
		 
		def tableHtml =  util.work("http://iwencai.com","http://iwencai.com/unifiedwap/unified-wap/v2/result/get-robot-data?question=%E7%9F%AD%E7%BA%BF%E5%A4%8D%E7%9B%98&source=Ths_iwencai_Xuangu&version=2.0&page=1&log_info%3D%7B%22input_type%22%3A%22click%22%7D%26perpage%3D50%26secondary_intent%3D%26block_list%3D%26add_info%3D%7B%22urp%22%3A%7B%22scene%22%3A1%2C%22company%22%3A1%2C%22business%22%3A1%7D%2C%22contentType%22%3A%22json%22%2C%22searchInfo%22%3Atrue%7D");
		
		def jsonSlurper = new JsonSlurper()
		def map = jsonSlurper.parseText(tableHtml);
		
		def data  = map['data'];
		
		def answer  = data['answer'];
		
		def t0  = answer['txt'];
		def content =  t0[0]['content'];
		def content1 = jsonSlurper.parseText(content);
		//def content =  map.data.answer.txt[0].content
		def components =  content1['components'];//['datas'];
		components.each { 
			item->
			if(item.show_type =='line3') {
				wcResult = item.data.datas;
			}
		}
		if(wcResult.size()>0) {
			lastWcUpdate  = System.currentTimeMillis()
		}
		//def content = jsonSlurper.parseText(content);
		println "fetch-temperature:" + wcResult.size();
		return wcResult  ;
		//return  body  //这里能工作
		
	}
	/**
	 从 http://q.10jqka.com.cn/gn/ 分析 股票对应板块
	 */
	def fetchGnGp(){
		File outputfile = new File("/opt/tomcat_gpz/gplist.txt")
		if(outputfile.exists()) {
			//outputfile.delete();
		}


		HtmlUtil  util = new HtmlUtil();
		def debug =0 ;
		def isAllow = false;
		gnMap.each {  k,v ->

			if(v =="专精特新"){
				isAllow = true;
			}
			//isAllow = true;

			if(debug < 10000 && isAllow) {
				def  url = "https://q.10jqka.com.cn/gn/detail/field/264648/order/desc/page/1/ajax/1/code/"+k ;
				println url;
				String hostUrl = "http://q.10jqka.com.cn/";
				def tableHtml =  util.work(hostUrl,url);
				//取的分页信息   //<span class="page_info">1/2</span>
				String totalPage = getSubUtilSimple(tableHtml,"<span class=\"page_info\">1/(.*?)</span>");
				def trycount = 0 ;
				while(totalPage =="" && trycount < 3 ) {
					url = "https://q.10jqka.com.cn/gn/detail/field/264648/order/desc/page/1/ajax/1/code/"+k ;
					tableHtml =  util.work(hostUrl,url);
					totalPage = getSubUtilSimple(tableHtml,"<span class=\"page_info\">1/(.*?)</span>");
					println v+" again:" + totalPage;
					/*
					 try {
					 Thread.sleep(5000);
					 } catch (InterruptedException e) {
					 }*/
					trycount++;
				}
				println v+" page:" + totalPage;

				if(totalPage==""){
					totalPage = 1;
				}

				def totalPageNumber = Integer.parseInt(totalPage);
				(1..totalPageNumber).each{ page->
					List<String> codeList  = []
					def enabld = 0;
					def lineAppend = new StringBuffer();
					while(codeList.size() ==0 ){
						//取的每页的股票 信息
						tableHtml =  util.work(hostUrl,"https://q.10jqka.com.cn/gn/detail/field/264648/order/desc/page/"+page+"/ajax/1/code/"+k);
						codeList = getSubUtil(tableHtml, "<a href=\"http://stockpage.10jqka.com.cn/(.*?)\" target=\"_blank\">(.*?)</a></td>");


						codeList.each{ oneCode->
							if(!oneCode.contains("/")){
								def split = oneCode.split("_");
								def codeName  = split[1] ;
								enabld++;
								lineAppend.append(codeName+"_"+  k+":"+gnMap[k]+"\n" );
								if(!codeGnGroup.containsKey(codeName)){
									codeGnGroup[codeName]  = [] as HashSet;
								}
								codeGnGroup[codeName] .add(k);
							}
						}/*
						 try {
						 Thread.sleep(5000);
						 } catch (InterruptedException e) {
						 }*/
					}
					println v+" page:" + totalPage+" curr :"+page+ " size : "+ enabld;
					outputfile << lineAppend.toString();
				}
			}


			debug++;
		}

		def r =  getCodeGnGroupInfo();
		println r;
	}

	def isRun = false;
	public void analyDataAndSendMsg( ) {
		//syncGn();
		//distinctGngp();
		//fetchGnGp();
		/*if(1==1){
			return ;
		}*/

		if(isRun) {
			return ;
		}
		isRun = true;

		def cache = [:]
		def downSet = new HashSet();
		def countMap = [:]
		while (true) {
			try {
				List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();

				//2板
				List<JianKongVO> zt2list = RunJianKong.getList("ZT2D_LIST");
				RunJianKong.sort(zt2list, "cate");

				def monitorList = [];

				monitorList.addAll(zxlist)
				monitorList.addAll(zt2list)

				monitorList.each { t ->
					def codeName = t.getName() ;
					def zdf = null;

					if(t.getClass().getName().contains("JianKongVO")){
						zdf = (float) t.getZdf() ;
						codeName = t.getName() +(" 二进三 ")
					}else{
						zdf = NumberUtils.toFloat( t.getZdf() ) ;
					}

					if(!t.getCid().startsWith("sz300")   ) {

						if(t.getZdf() !=null && zdf >= 9.88) {
							//println  codeName + " - "+ zdf
							cache.putIfAbsent(codeName, zdf)
							if(downSet.contains(codeName)) {
								downSet.remove(codeName)
								def msg = getCurrTime () + " " + codeName + " 再涨停  "+zdf ;
								sendMsg(msg,'1000003',  'giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0');
								//sendMsg(msg,'1000004',  'PuMOZPQ0ubdUm8o5dFNNlcMJRARRIYMXzMdgjbK4IK8');
							}
						}
						if(zdf < 9.87 && cache.containsKey(codeName)) {
							countMap.putIfAbsent(codeName,  new java.util.concurrent.atomic.AtomicLong() )
							def fc = countMap[codeName].incrementAndGet();
							//println  codeName + " - "+ zdf+"----> MSG"
							cache.remove(codeName);
							downSet << codeName;
							def msg = getCurrTime () + " " + codeName  + " 炸板 "+ (fc==1?"":"【"+fc+"】")  +"  " + zdf ;
							sendMsg(msg,'1000003',  'giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0');
							//sendMsg(msg,'1000004',  'PuMOZPQ0ubdUm8o5dFNNlcMJRARRIYMXzMdgjbK4IK8');
						}
					}
				}
				println "\n" + cache.toString().replaceAll(",","\n").replaceAll("\\[","").replaceAll("\\]","");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
	}


	public static class HttpApi{
		public HttpApi( ) {
		}


		def http ;
		public HttpApi(def httpUrl) {
			http = 	new HTTPBuilder(httpUrl);
		}


		def queryForObject(_params){
			def list  = getModelList(_params);
			def result =null;

			if(list!=null && list.content.size()>0) {
				if(list.totalElements >1 ){
					println (_params.toString()+ " query result totalElements > 1 ")
				}
				result  = list.content[0];
			}
			return result;
		}

		def getModelList(_params){
			if(_params!=null) {
				if(_params.length  == null) {
					_params.length = 50
				}
			}
			def result = null;
			http.request( GET ,groovyx.net.http.ContentType.JSON) { req ->
				uri.path = '/api/getModelList'
				uri.query = _params
				response.success = { resp ,json->
					result = json;
					// json.totalElements
					//json. json.content
				}
			}
			return result;
		}


		def delByField(dParams){
			/*
			 dParams = [  className:'ImageAiResult',
			 dByField:'pid_s_s',
			 value:rowid
			 ];*/

			def resp2 = http.get( path : '/api/delByField', query : dParams )
			println resp2;
		}
		def saveModel(params){
			if(params!=null) {
				if(params.selectByField  == null) {
					params.selectByField ="id";
				}
			}
			http.request( POST ) {
				uri.path = '/api/saveModel'
				send URLENC,params
				response.success = { resp ,json->
					//println  "saveModel - result> " + json.errorMessage;

				}
			}
		}


		def execute(  String path, Map requestHeaders=[:], Map query=[:], method = Method.POST) {
			try {
				def result = null

				// perform a ${method} request, expecting TEXT response
				http.request(method, ContentType.JSON) {
					uri.path = path
					uri.query = query
					//headers.'Content-Type' = 'application/json'

					// add possible headers
					requestHeaders.each { key, value ->
						headers."${key}" = "${value}"
					}

					// response handler for a success response code
					response.success = { resp, reader ->
						result = reader
					}
				}
				return result
			} catch (groovyx.net.http.HttpResponseException ex) {
				ex.printStackTrace()
				return null
			} catch (java.net.ConnectException ex) {
				ex.printStackTrace()
				return null
			}

		}

		public def requestWithMethodBody(path,     queryParams,bodyParam, method = Method.POST) {
			def r  = {statis:1};
			http.request(  method ,groovyx.net.http.ContentType.JSON) { req ->
				uri.path = path
				uri.query = queryParams
				body =  bodyParam
				response.success = { resp ,json->
					r = json;
				}
				response.failure = { resp ,json->
					println ( "Request failed with status ${resp.status},{}"+json);

				}
				response.error = { resp ,json->
					println( "Request error with status ${resp.status},{}"+json);
				}
			}
			return r;
		}

		public def requestWithBody(path,     queryParams,bodyParam) {
			def r  = {statis:1};
			http.request(  Method.POST ,groovyx.net.http.ContentType.JSON) { req ->
				uri.path = path
				uri.query = queryParams
				body =  bodyParam
				response.success = { resp ,json->
					r = json;
					println( "Request success with status ${resp.status},json:{}"+json);
				}
				response.failure = { resp ,json->
					println ( "Request failed with status ${resp.status},{}"+json);

				}
				response.error = { resp ,json->
					println( "Request error with status ${resp.status},{}"+json);
				}
			}
			return r;
		}


		public def requestWithFullYParams( bodyParam , path ,queryParams, Map requestHeaders=[:], method = Method.POST,  type ) {
			def r  = {statis:1};
			http.request( method ,type) { req ->
				//groovyx.net.http.ContentType.JSON
				uri.path = path
				uri.query = queryParams
				body =  bodyParam // new JsonBuilder( _params ).toPrettyString() ;
				requestHeaders.each { key, value ->
					headers."${key}" = "${value}"
				}
				response.success = { resp ,json->
					r = json;
					//println( "Request success with status ${resp.status},json:{}"+json);
				}
				response.failure = { resp ,json->
					println ( "Request failed with status ${resp.status},{}"+json);

				}
				response.error = { resp ,json->
					println( "Request error with status ${resp.status},{}"+json);
				}
			}
			return r;
		}

		public def requestWithFullYParamsAndGetWithHeader( bodyParam , path ,queryParams, Map requestHeaders=[:], method = Method.POST) {
			def r  = {statis:1};
			def respr=[:]
			http.request( method ,groovyx.net.http.ContentType.HTML) { req ->
				uri.path = path
				uri.query = queryParams
				body =  bodyParam // new JsonBuilder( _params ).toPrettyString() ;
				requestHeaders.each { key, value ->
					headers."${key}" = "${value}"
				}
				response.success = { resp ,json->
					r = json;
					//println resp.status
					respr = resp ;//resp.headers ;
					/* header = resp.headers.each { h ->
					 println h
					 }*/
					//println( "Request success with status ${resp.status},json:{}"+json);
				}
				response.failure = { resp ,json->
					respr = resp ;
					//println ( "Request failed with status ${resp.status},{}"+json);

				}
				response.error = { resp ,json->
					respr = resp ;
					//println( "Request error with status ${resp.status},{}"+json);
				}
			}
			return respr;
		}
	}


}
