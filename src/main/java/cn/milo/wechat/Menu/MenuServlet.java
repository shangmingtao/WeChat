package cn.milo.wechat.Menu;

import cn.milo.util.HttpUtil;
import cn.milo.wechat.AccessToken.AccessToken;
import cn.milo.wechat.token.TokenCheckServlet;
import cn.milo.wechat.util.CheckUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by admin on 2017/11/16.
 */
public class MenuServlet extends HttpServlet {

    Logger log = Logger.getLogger(TokenCheckServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestSTR = HttpUtil.getJsonFromRequest(request);
        try {
            log.info(HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ AccessToken.AccessToken,requestSTR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
