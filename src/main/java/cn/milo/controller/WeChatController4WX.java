package cn.milo.controller;

import cn.milo.wechat.entity.WeixinOauth2Token;
import cn.milo.wechat.service.WeChatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class WeChatController4WX {
	
	Logger log = Logger.getLogger(WeChatController4WX.class);
	
	@Autowired
	private WeChatService weChatService;


	/*
	token验证
	 */
	@RequestMapping(value = "/tokenCheck" , method = RequestMethod.GET)
	public void tokenCheck(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		log.info("--------------------------------------------------------------------------");
		log.info("Wechat Token Check , param : signature = " + signature);
		log.info("Wechat Token Check , param : timestamp = " + timestamp);
		log.info("Wechat Token Check , param : nonce" + nonce);
		log.info("Wechat Token Check , param : echostr" + echostr);
		log.info("--------------------------------------------------------------------------");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			log.info("Wechat Token Check Exception , e = " + e.getMessage());
		}
		if (weChatService.TokenCheck(signature, timestamp, nonce , echostr));
			out.print(echostr);
	}

	/*
	用户授权
	 */
	@RequestMapping(value = "/oauthServlet" , method = RequestMethod.GET)
	public void OAuth2(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		log.info("--------------------------------------------------------------------------");
		log.info("OAuth2.0 , param : code" + code);
		log.info("--------------------------------------------------------------------------");
		WeixinOauth2Token weixinOauth2Token = null;
		try {
			weixinOauth2Token = weChatService.getOauth2AccessToken(code);
		} catch (Exception e) {
			log.info("OAuth2.0 Exception , e = " + e.getMessage());
			return;
		}
		if (weixinOauth2Token == null){
			log.info("OAuth2.0 failed");
			return;
		}
		request.setAttribute("openId",weixinOauth2Token.getOpenid());
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			log.info("OAuth2.0 page redirect Exception , e = " + e.getMessage());
		}
	}


}
