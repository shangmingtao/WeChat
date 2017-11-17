package cn.milo.wechat.util;

import cn.milo.util.HttpUtil;
import cn.milo.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/******************************************************
 ****** @ClassName   : WeChatHTTPUtil.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2017 11 17 11:41     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class WeChatHTTPUtil {

   static Logger log = Logger.getLogger(WeChatHTTPUtil.class);


    public static JSONObject requestWeChatByGet(String url , String logDescription){

        String responseSTR = null;
        JSONObject responseJSON = null;

        try {
            log.info("[WeChat] ====> Request WeChat (HTTP.GET) , Operation = " + logDescription + " , url = " + url);
            responseSTR = HttpUtil.doGet(url);
            log.info("[WeChat] <==== Response WeChat (HTTP.GET) , Operation = " + logDescription + " , Response =  = " + responseSTR);
        } catch (Exception e) {
            log.info("[WeChat] ERROR : Request WeChat (HTTP.GET) Exception , Operation = " + logDescription + " , e = " + e.getMessage());
            return null;
        }
        if(responseSTR != null){
            log.info("[WeChat] ERROR : Request WeChat (HTTP.GET) , Operation = " + logDescription + " , reponse null");
            return null;
        }
        responseJSON = JSONObject.fromObject(responseSTR);
        if (!StringUtil.isEmpty(responseJSON.optString("errcode"))){
            log.info("[WeChat] ERROR : Request WeChat (HTTP.GET) , Operation = " + logDescription + " , have errcode , errorce = " + responseJSON.optString("errcode"));
            return null;
        }
        return responseJSON;
    }

    public static JSONObject requestWeChatByPost(String url , JSONObject requestJSON , String logDescription){

        String responseSTR = null;
        JSONObject responseJSON = null;

        try {
            log.info("[WeChat] ====> Request WeChat (HTTP.POST) , Operation = " + logDescription + " , url = " + url + " , requestBody = " + requestJSON.toString());
            responseSTR = HttpUtil.doPost(url,requestJSON.toString());
            log.info("[WeChat] <==== Response WeChat (HTTP.POST) , Operation = " + logDescription + " , Response =  = " + responseSTR);
        } catch (Exception e) {
            log.info("[WeChat] ERROR : Request WeChat (HTTP.POST) Exception , Operation = " + logDescription + " , e = " + e.getMessage());
            return null;
        }
        if(responseSTR != null){
            log.info("[WeChat] ERROR : Request WeChat (HTTP.POST) , Operation = " + logDescription + " , reponse null");
            return null;
        }
        responseJSON = JSONObject.fromObject(responseSTR);
        if (!StringUtil.isEmpty(responseJSON.optString("errcode"))){
            log.info("[WeChat] ERROR : Request WeChat (HTTP.POST) , Operation = " + logDescription + " , have errcode , errorce = " + responseJSON.optString("errcode"));
            return null;
        }
        return responseJSON;
    }
}
