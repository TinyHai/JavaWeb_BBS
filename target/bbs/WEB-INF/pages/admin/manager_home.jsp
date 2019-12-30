<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/26
  Time: 下午3:17
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/manager_home.css" type="text/css">
</head>
<body>
<div id="container">
    <div class="subtitle">
        <label>
            管理员::${sessionScope.user.userName}&nbsp;你好&nbsp;
            <a href="${pageContext.request.contextPath}/admin/manage?type=0">管理帖子</a>
            <a href="${pageContext.request.contextPath}/admin/manage?type=1">管理评论</a>
        </label>
    </div>
    <div class="article_titles">
        <c:if test="${requestScope.type ne 1}">
            <div class="line1">
                所有帖子:
            </div>
            <c:forEach items="${articles}" var="article" varStatus="status">
                <div class="line${status.index % 2 + 1}">
                    <label class="article_title">${status.index + 1}.
                        <a href="${pageContext.request.contextPath}/normal/delete_article?article_id=${article.id}">[删除]</a>
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
        </c:if>
        <c:if test="${requestScope.type eq 1}">
            <div class="line1">
                所有回复:
            </div>
            <c:forEach items="${comments}" end="${comments.size()}" var="comment" varStatus="status">
                <div class="line${status.index % 2 + 1}">
                <span>
                    ${status.count}.
                    <a href="${pageContext.request.contextPath}/admin/delete_comment?comment_id=${comment.id}">[删除]</a>
                    <a class="comment_line_a" href="${pageContext.request.contextPath}/normal/user_page?user_name=${comment.commentByWho}">
                            ${comment.commentByWho}
                    </a>
                    : ${comment.content}
                    <br>
                    <fml:formatDate value="${comment.publishDate}" pattern="YY-dd hh:mm"/>)&nbsp;
                    <a class="comment_line_a" href="${pageContext.request.contextPath}/public/article_details?article_id=${comment.articleId}">
                        查看
                    </a>
                </span>
                </div>
            </c:forEach>
        </c:if>
        <div class="bottom">
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>