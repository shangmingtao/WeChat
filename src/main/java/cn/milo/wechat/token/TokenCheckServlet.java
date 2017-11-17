package cn.milo.wechat.token;

import cn.milo.wechat.CheckUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mac on 2017/11/15.
 */
public class TokenCheckServlet extends HttpServlet {

    Logger log = Logger.getLogger(TokenCheckServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("signature = " + signature);
        log.info("timestamp = " + timestamp);
        log.info("nonce" + nonce);
        log.info("echostr" + echostr);
        PrintWriter out = response.getWriter();
        if(CheckUtil.check(signature, timestamp, nonce)){
            out.print(echostr);
        }
    }
}
