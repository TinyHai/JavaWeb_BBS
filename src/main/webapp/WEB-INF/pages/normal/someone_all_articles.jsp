<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/25
  Time: 下午10:29
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normal/someone_all_articles.css" type="text/css">
</head>
<body>
<div id="container">
    <div class="subtitle">${pageUser.userName}的帖子</div>
    <div class="article_titles">
        <div class="line1">
            所有帖子:
        </div>
        <c:forEach items="${articles}" var="article" varStatus="status">
            <div class="article_title_line line${status.index % 2 + 1}">
                <label class="article_title">${status.index + 1}.
                    <c:if test="${isSelf eq true}">
                        <a href="${pageContext.request.contextPath}/normal/delete_article?article_id=${article.id}">[删除]</a>
                        <a href="${pageContext.request.contextPath}/normal/modify_article?article_id=${article.id}">[修改]</a>
                    </c:if>
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
        <div class="bottom">
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>
