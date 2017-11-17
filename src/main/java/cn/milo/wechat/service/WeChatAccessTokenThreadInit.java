package cn.milo.wechat.service;

import cn.milo.wechat.timer.WeChatAccessTokenTimer;

/******************************************************
 ****** @ClassName   : WeChatAccessTokenThreadInit.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2017 11 17 15:35     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class WeChatAccessTokenThreadInit {

    public static String accessToken = "";

    public void init(){
        WeChatAccessTokenTimer weChatAccessTokenTimer = new WeChatAccessTokenTimer();
        Thread thread = new Thread(weChatAccessTokenTimer);
        thread.start();
    }
}
