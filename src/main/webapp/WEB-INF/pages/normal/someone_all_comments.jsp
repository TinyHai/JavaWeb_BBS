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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normal/someone_all_comments.css" type="text/css">
</head>
<body>
<div id="container">
    <div class="subtitle">${pageUser.userName}的评论</div>
    <div class="comment_lines">
        <div class="line1">
            所有评论:
        </div>
        <c:forEach items="${comments}" end="${comments.size()}" var="comment" varStatus="status">
            <div class="comment_line line${status.index % 2 + 1}">
                <span>
                    ${status.end + 1 - status.count}.
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
        <div class="bottom">
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>

