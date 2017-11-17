package cn.milo.wechat;

import cn.milo.util.HttpUtil;
import cn.milo.util.PropertyFactory;
import cn.milo.wechat.AccessToken.AccessToken;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/11/17.
 */

public class WeChatService {

    private static String APPID = PropertyFactory.getProperty("WECHAT.APPID");
    private static String APPSECRET = PropertyFactory.getProperty("WECHAT.SECRET");

    static Logger log = Logger.getLogger(WeChatService.class);

    /*
    获取用户token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String code) throws Exception{

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        JSONObject responseJSON = WeChatHTTPUtil.requestWeChatByGet(url , "Get User AccessToken");
        if (responseJSON == null) {
            return null;
        }else{
            WeixinOauth2Token wx = new WeixinOauth2Token();
            wx.setAccess_token(responseJSON.optString("access_token"));
            wx.setExpires_in(responseJSON.optInt("expires_in"));
            wx.setOpenid(responseJSON.optString("openid"));
            wx.setRefresh_token(responseJSON.optString("refresh_token"));
            wx.setScope(responseJSON.optString("scope"));
            return wx;
        }
    }

    /*
    创建菜单
     */
    protected void createMenu(JSONObject requestJSON) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ AccessToken.AccessToken;
        WeChatHTTPUtil.requestWeChatByPost(url ,  requestJSON , "Create Menu");
    }

    /*
    发送模板消息
     */
    protected void sendTemplateMessage(JSONObject requestJSON) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ AccessToken.AccessToken;
        WeChatHTTPUtil.requestWeChatByPost(url ,  requestJSON , "Send TemplateMessage");
    }



}
