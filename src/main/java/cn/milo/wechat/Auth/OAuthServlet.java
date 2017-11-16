package cn.milo.wechat.Auth;

import cn.milo.wechat.AccessToken.AccessToken;
import cn.milo.wechat.entity.WeixinOauth2Token;
import cn.milo.wechat.token.TokenCheckServlet;
import cn.milo.wechat.util.AdvancedUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/11/16.
 */
public class OAuthServlet extends HttpServlet {

    Logger log = Logger.getLogger(TokenCheckServlet.class);

    private static final long serialVersionUID = -1847238807216447030L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        log.info("111112222授权页面被请求了......");

        try {
            request.setCharacterEncoding("gb2312");
            response.setCharacterEncoding("gb2312");  // 用户同意授权后，能获取到code
            String code = request.getParameter("code");  // 用户同意授权
            log.info("code = " + code);
            // 获取网页授权access_token

            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(AccessToken.appID, AccessToken.appsecret, code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccess_token();
            // 用户标识
            String openId = weixinOauth2Token.getOpenid();
            log.info("openId = " + openId);
    //            // 获取用户信息
    //            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);// 设置要传递的参数
    //            request.setAttribute("snsUserInfo", snsUserInfo);
            request.setAttribute("openId",openId);
            // 跳转到index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //
}
