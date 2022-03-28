package com.huangxifeng.gupiao.service.impl;

import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

import java.text.SimpleDateFormat

import org.apache.commons.lang3.math.NumberUtils
import org.springframework.stereotype.Service

import com.huangxifeng.gupiao.jymodel.RunJianKong
import com.huangxifeng.gupiao.jymodel.RunZhangTingDiXi
import com.huangxifeng.gupiao.service.NotifyService
import com.huangxifeng.gupiao.vo.JianKongVO
import com.huangxifeng.gupiao.vo.QingXuJianKongVO
import com.huangxifeng.gupiao.vo.ZhangTingDiXiVO

import groovyx.net.http.*

@Service
public class NotifyServiceImpl implements NotifyService {



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
										println hhmm;
										if(timeView!=null &&  timeView.containsKey(hhmm)) {
											def number = timeView[hhmm] ;
											if(number > 5000 && zdf < 9.7) {
												def msg = getCurrTime () + " " +t.getName() + " 拉升 - 分钟交易量:"+ number+" 当前:"+zdf+"%" ;
												sendMsg(msg,'1000003',  'giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0');
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

							println code+ " "+lastPage;
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
								println "run over  "+code + " - "+ t.getName()+ " page:" + lastPage;
								totalAlResult[code+t.getName()] = minView;
								hit = false;
							}//end if

						}

					}
				}

				i++;
			}
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

	public void analyDataAndSendMsg( ) {
		def cache = [:]
		def downSet = new HashSet();
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
						codeName = t.getName() +("-2进3")
					}else{
						zdf = NumberUtils.toFloat( t.getZdf() ) ;
					}
					//def zdf = NumberUtils.toFloat(t.getZdf()) ;
					if(t.getZdf() !=null && zdf >= 9.88) {
						//println  codeName + " - "+ zdf
						cache.putIfAbsent(codeName, zdf)
						if(downSet.contains(codeName)) {
							downSet.remove(codeName)
							def msg = getCurrTime () + " " + codeName + " 再涨停 - "+zdf ;
							sendMsg(msg,'1000003',  'giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0');
							sendMsg(msg,'1000004',  'PuMOZPQ0ubdUm8o5dFNNlcMJRARRIYMXzMdgjbK4IK8');
						}
					} 
					if(zdf < 9.87 && cache.containsKey(codeName)) {
						println  codeName + " - "+ zdf+"----> MSG"
						cache.remove(codeName);
						downSet << codeName;
						def msg = getCurrTime () + " " + codeName + " 炸板 - "+zdf ;
						sendMsg(msg,'1000003',  'giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0');
						sendMsg(msg,'1000004',  'PuMOZPQ0ubdUm8o5dFNNlcMJRARRIYMXzMdgjbK4IK8');
					}
				}
				println cache;
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
