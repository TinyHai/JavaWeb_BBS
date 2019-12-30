<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/24
  Time: 下午5:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/div_line.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/comment.css" type="text/css">
</head>
<body>
<div id="container">
    <div class="subtitle">${title}</div>
    <div class="comment">
        <div class="comment_header">
            <span>评论: </span>
            <c:if test="${sessionScope.isLogin ne true}">
                <span style="padding: 0 10px">你还没有登录， 不能评论。</span>
                <span class="right_span" style="text-align: right; float: right">
                    <span>
                    <a href="${pageContext.request.contextPath}/login/signin">立即登录</a>
                    </span>
                    <span>
                        <a href="${pageContext.request.contextPath}/login/signup">立即注册</a>
                    </span>
                </span>
            </c:if>
            <c:if test="${sessionScope.isLogin eq true}">
                <div>
                    <b>回复${floor}</b><br>
                    <form action="${pageContext.request.contextPath}/public/comment_handler" method="post">
                        <label>
                            <textarea required id="reply" minlength="15" class="comment_textarea" name="content" placeholder="我也来说两句" rows="2"></textarea>
                            <br>
                            <input type="hidden" name="comment_to_floor" value="${commentToFloor}">
                            <input type="hidden" name="article_id" value="${articleId}">
                            <input type="submit" value="立即回复">
                        </label>
                    </form>
                </div>
            </c:if>
        </div>
        <c:if test="${comments.size() eq 0}">
            <div class="comment_line">目前还没有人评论，赶快抢一个一楼吧。</div>
        </c:if>
        <c:forEach items="${comments}" end="${comments.size()}" var="comment">
            <div class="comment_line">
                <span>
                    ${comment.floor}楼
                    <a class="comment_line_a" href="${pageContext.request.contextPath}/public/comment?article_id=${comment.articleId}&comment_to_floor=${comment.floor}">[回]</a>
                    <a class="comment_line_a" href="#">${comment.commentByWho}</a>
                    <c:if test="${comment.commentToFloor ne 0}">回复${comment.commentToFloor}楼</c:if>
                    : ${comment.content}
                    &nbsp(<fml:formatDate value="${comment.publishDate}" type="time"/>)
                </span>
            </div>
        </c:forEach>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/">返回首页</a>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>
