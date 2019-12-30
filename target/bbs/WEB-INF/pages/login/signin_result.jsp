<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/20
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login_signin_result.css" type="text/css">
<body>
    <div id="container">
        <div class="subtitle"><label>${title}</label></div>
        <div class="line1">
            <label id="login_result">
                <c:if test="${fromLoginPage}">
                    恭喜你，登录成功
                </c:if>
                <c:if test="${fromLoginPage eq false}">
                    ${user.userName}, 你已经登录。
                </c:if>
            </label>
        </div>

        <c:if test="${fromLoginPage eq false}">
            <div class="bottom">
                <a href="${pageContext.request.contextPath}/login/signout">注销登录</a>
            </div>
        </c:if>
        <div class="bottom">
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>
