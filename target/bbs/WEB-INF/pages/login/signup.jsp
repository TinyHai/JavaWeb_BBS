<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/17
  Time: 下午9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
    <script type="text/javascript">
        var onSubmit = function () {
            var passwords = document.getElementsByClassName("password");
            var password1 = passwords[0].value;
            var password2 = passwords[1].value;
            if (password1 !== password2) {
                alert("两次密码不相同， 请重新输入！");
                passwords[0].value = "";
                passwords[1].value = "";
                return false;
            }
            return true;
        };
    </script>
</head>
<body>
    <div id="container">
        <div class="subtitle"><label>${title}</label></div>
        <c:if test="${error}">
            <div class="error">
                <label>${errorMsg}</label>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login/signup_handler" method="post" onsubmit="return onSubmit()">
            <div class="line1">
                <label>
                    用户名：<br>
                    <input type="text" placeholder="请输入用户名" name="userName" size="36" required>
                    <input type="hidden" name="formToken" value="${sessionScope.formToken}">
                </label>
            </div>
            <div class="line2">
                <label>
                    密码：<br>
                    <input type="password" placeholder="请输入密码" class="password" name="password" size="36" minlength="8" required>
                </label>
            </div>
            <div class="line1">
                <label>
                    确认密码：<br>
                    <input type="password" placeholder="请再次输入密码" class="password" minlength="8" size="36" required>
                </label>
            </div>
            <div class="line2">
                <label>
                    电子邮箱：<br>
                    <input type="email" placeholder="请输入电子邮箱" name="email" size="36" required>
                </label>
            </div>
            <div class="line1">
                <label>
                    <input class="bt" type="submit" value="注册">
                </label>
            </div>
        </form>
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
