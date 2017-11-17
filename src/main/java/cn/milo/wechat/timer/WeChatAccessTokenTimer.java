package cn.milo.wechat.timer;

import cn.milo.util.PropertyFactory;
import cn.milo.wechat.service.WeChatAccessTokenThreadInit;
import cn.milo.wechat.service.WeChatService;
import cn.milo.wechat.util.WeChatHTTPUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


public class WeChatAccessTokenTimer implements Runnable{

    Logger log = Logger.getLogger(WeChatAccessTokenTimer.class);

    @Override
    public void run(){

        while(true){

            String APPID = PropertyFactory.getProperty("WECHAT.APPID");
            String SECRET = PropertyFactory.getProperty("WECHAT.SECRET");

            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;

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
                WeChatAccessTokenThreadInit.accessToken = responseJSON.optString("access_token");
                try {
                    Thread.currentThread().sleep(responseJSON.optInt("expires_in")*1000);
                } catch (InterruptedException e) {
                    log.info("ERROR : thread sleep Exception , e = " + e.getMessage());
                }
            }
        }
    }



}
