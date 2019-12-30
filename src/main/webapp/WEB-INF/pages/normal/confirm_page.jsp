<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/17
  Time: 下午9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normal/confirm_page.css" type="text/css">
<body>
<div id="container">
    <div class="subtitle"><label>${title}</label></div>
    <div class="line1">
        <label id="tips">
            确定${opMsg}吗？
        </label>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath.concat(destUrl)}">确定${opMsg}</a>
    </div>
    <div class="bottom">
        <a href="javascript:history.back()">取消${opMsg}</a>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>