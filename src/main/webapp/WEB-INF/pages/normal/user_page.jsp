<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/25
  Time: 下午7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normal/user_page.css" type="text/css">
</head>
<body>
<div id="container">
    <div class="subtitle"><b>${pageUser.userName}</b>的个人中心</div>
    <div class="info">
        <label><b>${who}的昵称:</b>&nbsp${pageUser.userName}</label>
        <br>
        <label><b>${who}的个性签名:</b>&nbsp;${pageUser.signature}</label>
        <br>
        <label><b>${who}的权限:</b>&nbsp;${authority}</label>
        <br>
        <label><b>${who}的</b>
            <a href="${pageContext.request.contextPath}/normal/someone_all_articles?user_name=${pageUser.userName}">
                帖子(${articleCount})
            </a>.
            <a href="${pageContext.request.contextPath}/normal/someone_all_comments?user_name=${pageUser.userName}">
                回复(${commentCount})
            </a>
        </label>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/">返回首页</a>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>

