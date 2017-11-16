package cn.milo.wechat.AccessToken;

import cn.milo.util.HttpUtil;
import cn.milo.wechat.token.TokenCheckServlet;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Timer;

/**
 * Created by admin on 2017/11/16.
 */
public class AccessToken extends HttpServlet {

    public static String AccessToken = null;
    public static String appID = "wx5a0ee712e5bc6d1f";
    public static String appsecret = "d10663ea3a226d4825f9c6994337ff20";


    Logger log = Logger.getLogger(TokenCheckServlet.class);

    public void init() throws ServletException
    {
        try {
            AccessToken = JSONObject.fromObject(HttpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret+"")).optString("access_token");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
