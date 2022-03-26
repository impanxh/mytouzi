package com.huangxifeng.gupiao.service.impl;

import org.apache.commons.lang3.math.NumberUtils
import org.springframework.stereotype.Service

import com.huangxifeng.gupiao.jymodel.RunZhangTingDiXi
import com.huangxifeng.gupiao.service.NotifyService
import com.huangxifeng.gupiao.vo.ZhangTingDiXiVO

import java.text.SimpleDateFormat
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

import groovyx.net.http.*

@Service
public class NotifyServiceImpl implements NotifyService {
	public void sendMsg2(text) {
		try {
			println text; 
			HttpApi f = new HttpApi('https://qyapi.weixin.qq.com'); //1000003  giTnB3iCtxSTj2ZNd4809flDH_JC5DbPW3I6JMOx2R0
			def token = f.requestWithFullYParams(null, "/cgi-bin/gettoken",[   "corpid":"ww44f4eb1850de9bf1", "corpsecret":"PuMOZPQ0ubdUm8o5dFNNlcMJRARRIYMXzMdgjbK4IK8"]	,[:], Method.GET );
			def str =''' {
				   "touser" : "@all",  "msgtype" : "text", "agentid" : 1000004, "text" : {
				       "content" : "$text"  
					}
				}
			'''
			def engine = new groovy.text.SimpleTemplateEngine()
			def output =  engine.createTemplate(str).make(['text':text ]).toString()
			def b = f.requestWithBody( "/cgi-bin/message/send",[   "access_token": token.access_token ]	,output );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void analyDataAndSendMsg( ) {
		def cache = [:]
		while (true) {
			try {
				List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
				zxlist.each { t ->
					def codeName = t.getName() ;
					def zdf = NumberUtils.toFloat(t.getZdf()) ;
					if(t.getZdf()!=null && zdf >= 9.88) {
						//println  codeName + " - "+ zdf
						cache.putIfAbsent(codeName, zdf)
					}
					sendMsg2(new SimpleDateFormat("MM-dd HH:mm:ss").format(   System.currentTimeMillis()  )+ " "+t.getName() + " 炸版 - "+zdf);
					if(zdf < 9.87 && cache.containsKey(codeName)) {
						println  codeName + " - "+ zdf+"----> MSG"
						cache.remove(codeName);
						sendMsg2(new SimpleDateFormat("MM-dd HH:mm:ss").format(   System.currentTimeMillis()  )+ " "+t.getName() + " 炸版 - "+zdf);
					}
				}
				//println zxlist;
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

		public def requestWithFullYParams( bodyParam , path ,queryParams, Map requestHeaders=[:], method = Method.POST) {
			def r  = {statis:1};
			http.request( method ,groovyx.net.http.ContentType.JSON) { req ->
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
