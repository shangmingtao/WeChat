<%@ page language="java" pageEncoding="gb2312"%>
<%--
<%@ page import="org.liufeng.course.pojo.SNSUserInfo;"%>
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
    <title>OAuth2.0��ҳ��Ȩ</title>
    <meta name="viewport" content="width=device-width,user-scalable=0">
    <style type="text/css">
        *{margin:0; padding:0}
        table{border:1px dashed #B9B9DD;font-size:12pt}
        td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
    </style>
</head>
<body>
<%
/*    // ��ȡ��OAuthServlet�д���Ĳ���
    SNSUserInfo user = (SNSUserInfo)request.getAttribute("snsUserInfo");
    if(null != user) {*/
    String  openId = (String)request.getAttribute("openId");
    if(null != openId ){
%>
<form method="post" action="<%=basePath%>a">
    <input type="hidden" name="openid" value="<%=openId%>"><input>
<table width="100%" cellspacing="0" cellpadding="0">
    <tr><td width="20%">����</td><td width="80%">ֵ</td></tr>
    <tr><td>OpenID</td><td><%=openId%></td></tr>
</table>
    <input type="submit">
</form>
<%
    }
    else
        out.print("�û���ͬ����Ȩ,δ��ȡ���û���Ϣ��");
%>
</body>
</html>
