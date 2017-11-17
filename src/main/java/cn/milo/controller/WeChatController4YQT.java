package cn.milo.controller;

import cn.milo.util.HttpUtil;
import cn.milo.wechat.service.WeChatService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/******************************************************
 ****** @ClassName   : WeChatController4YQT.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2017 11 17 14:40     
 ****** @version     : v1.0.x                      
 *******************************************************/
@Controller
public class WeChatController4YQT {

    Logger log = Logger.getLogger(WeChatController4YQT.class);

    @Autowired
    private WeChatService weChatService;

    /*
    创建menu
     */
    @RequestMapping(value = "/createMenu")
    public void menuCreate(HttpServletRequest request, HttpServletResponse response){
        JSONObject requestJSON = JSONObject.fromObject(HttpUtil.getJsonFromRequest(request));
        try {
            weChatService.createMenu(requestJSON);
        } catch (Exception e) {
            log.info("Create Menu Exception , e = " + e.getMessage());
        }
    }

    /*
    创建menu
     */
    @RequestMapping(value = "/Sendtemplate")
    public void sendTemplate(HttpServletRequest request, HttpServletResponse response){
        JSONObject requestJSON = JSONObject.fromObject(HttpUtil.getJsonFromRequest(request));
        try {
            weChatService.sendTemplateMessage(requestJSON);
        } catch (Exception e) {
            log.info("Send Template Exception , e = " + e.getMessage());
        }
    }
}
