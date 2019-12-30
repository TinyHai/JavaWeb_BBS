<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/18
  Time: 下午1:55
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css">
</head>
<body>
<div id="container">
    <div class="subtitle">
        <label>
            欢迎你:${" "}
            <c:if test="${sessionScope.isLogin}">
                ${sessionScope.user.userName}
                <a href="${pageContext.request.contextPath}/normal/user_page">个人中心</a>
                <a href="${pageContext.request.contextPath}/normal/new_article">发布新帖</a>
                <c:if test="${sessionScope.isAdmin}">
                    <a href="${pageContext.request.contextPath}/admin/manage">管理论坛</a>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.isLogin eq null}">
                游客
            </c:if>
        </label>
        <span class="right_span">
            <c:if test="${sessionScope.isLogin == null}">
                <span>
                    <a href="${pageContext.request.contextPath}/login/signin">登录</a>
                    <a href="${pageContext.request.contextPath}/login/signup">注册</a>
                </span>
            </c:if>
            <c:if test="${sessionScope.isLogin == true}">
                <span>
                    <a href="${pageContext.request.contextPath}/login/signout">安全退出</a>
                </span>
            </c:if>
        </span>
    </div>
    <div class="article_titles">
        <div class="line1">
            最新帖子:
        </div>
        <c:forEach items="${articles}" var="article" varStatus="status">
            <div class="article_title_line line${status.index % 2 + 1}">
                <label class="article_title">${status.index + 1}.
                    <a href="${pageContext.request.contextPath}/public/article_details?article_id=${article.id}">
                            ${article.title}
                    </a>
                </label>
                <br>
                <label><a href="${pageContext.request.contextPath}/public/comment?article_id=${article.id}">${article.commentCount}</a>条回复</label>
                <span class="right_span">
                    <fml:formatDate value="${article.publishDate}" type="date" dateStyle="long" />
                </span>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>
