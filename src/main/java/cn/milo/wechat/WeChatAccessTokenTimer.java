package cn.milo.wechat;

import cn.milo.util.HttpUtil;
import cn.milo.util.PropertyFactory;
import cn.milo.util.StringUtil;
import cn.milo.wechat.AccessToken.AccessToken;
import cn.milo.wechat.token.TokenCheckServlet;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.IOException;


public class WeChatAccessTokenTimer {

    Logger log = Logger.getLogger(WeChatAccessTokenTimer.class);

    public static String accessToken = "";

    private void refreshAccessToken(){

        while(true){

            String APPID = PropertyFactory.getProperty("WECHAT.APPID");
            String SECRET = PropertyFactory.getProperty("WECHAT.SECRET");

            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET+"";

            JSONObject responseJSON = WeChatHTTPUtil.requestWeChatByGet(url , "refreshAccessToken");

            if (responseJSON == null) {
                //如果失败,1分钟后重试
                try {
                    Thread.currentThread().sleep(1000*60);
                } catch (InterruptedException e) {
                    log.info("ERROR : thread sleep Exception , e = " + e.getMessage());
                }
                continue;
            }else{
                //如果成功,根据微信返回的过期时间重新获取
                accessToken = responseJSON.optString("access_token");
                try {
                    Thread.currentThread().sleep(responseJSON.optInt("expires_in"));
                } catch (InterruptedException e) {
                    log.info("ERROR : thread sleep Exception , e = " + e.getMessage());
                }
            }
        }
    }




}
