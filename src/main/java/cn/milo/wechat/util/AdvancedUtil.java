package cn.milo.wechat.util;

import cn.milo.util.HttpUtil;
import cn.milo.wechat.entity.WeixinOauth2Token;
import cn.milo.wechat.token.TokenCheckServlet;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * Created by admin on 2017/11/16.
 */
public class AdvancedUtil {

    static Logger log = Logger.getLogger(TokenCheckServlet.class);


    public static WeixinOauth2Token getOauth2AccessToken(String APPID, String APPSECRET,String code) throws Exception{
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        String response = HttpUtil.doGet(url);
        log.info("请求url = " + url);
        log.info("获取AccessToken 返回结果 = " + response);
        WeixinOauth2Token wx = new WeixinOauth2Token();
        JSONObject obj = JSONObject.fromObject(response);
        wx.setAccess_token(obj.optString("access_token"));
        wx.setExpires_in(obj.optInt("expires_in"));
        wx.setOpenid(obj.optString("openid"));
        wx.setRefresh_token(obj.optString("refresh_token"));
        wx.setScope(obj.optString("scope"));
        return wx;
    }

//
//    public static WeixinOauth2Token getSNSUserInfo(String accessToken, String openid) throws Exception{
//        String response = HttpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code");
//        WeixinOauth2Token wx = new WeixinOauth2Token();
//        JSONObject obj = JSONObject.fromObject(response);
//        wx.setAccess_token(obj.optString("access_token"));
//        wx.setExpires_in(obj.optInt("expires_in"));
//        wx.setOpenid(obj.optString("openid"));
//        wx.setRefresh_token(obj.optString("refresh_token"));
//        wx.setScope(obj.optString("scope"));
//        return wx;
//    }
}
