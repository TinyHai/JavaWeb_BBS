<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/17
  Time: 下午9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
</head>
<body>
    <div id="container">
        <div class="subtitle"><label>${title}</label></div>
        <c:if test="${error}">
            <div class="error">
                <label>账号或密码错误！</label>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login/signin_handler" method="post">
            <div class="line1">
                <label>
                    用户名：<br>
                    <input type="text" placeholder="请输入用户名" name="userName" value="${userName}" size="36" required>
                </label>
            </div>
            <div class="line2">
                <label>
                    密码：<br>
                    <input type="password" placeholder="请输入密码" name="password" size="36" required>
                </label>
            </div>
            <div class="line1">
                <label>
                    <input class="bt" type="submit" value="登录">
                    <a class="forget_pwd_a" href="#">忘记密码?</a>
                </label>
            </div>
        </form>
        <div class="bottom">
            <a href="${pageContext.request.contextPath}/login/signup">立即注册</a>
        </div>
        <div class="bottom">
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>
