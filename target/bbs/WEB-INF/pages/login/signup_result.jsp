<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/21
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login_signup_result.css" type="text/css">
<body>
<div id="container">
    <div class="subtitle"><label>${title}</label></div>
    <div class="line1">
        <label id="signup_result">
            恭喜你， 注册成功
        </label>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/login/signin">立即登录</a>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/">返回首页</a>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>

