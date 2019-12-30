<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/26
  Time: 下午3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normal/new_article.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/base.css" type="text/css">
    <script type="text/javascript">
        var check = function () {
            if (editor.txt.text().length < 15) {
                document.getElementById("tips").innerText = " 帖子内容不能小于15个字符";
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div class="subtitle"><label>${title}</label></div>
    <div class="form_container">
        <form action="${pageContext.request.contextPath}/normal/modify_article_handler" onsubmit="return check()" method="post">
            <div class="article_title">
                <input type="hidden" name="article_id" value="${article.id}">
                <label style="display: flex; flex-direction: row; flex-wrap: nowrap">
                    帖子标题:
                    <input style="flex-grow: 1" type="text" value="${article.title}" name="article_title" minlength="5" maxlength="50" width="100%" required>
                </label>
                <div id="content_editor" style="margin: 10px 0"></div>
                <label hidden>
                    <input type="hidden" name="formToken" value="${sessionScope.formToken}">
                    <textarea id="content" name="article_content"></textarea>
                </label>
                <script type="text/javascript" src="${pageContext.request.contextPath}/wangEdit/wangEditor.min.js"></script>
                <script type="text/javascript">
                    var content = document.getElementById("content");
                    var E = window.wangEditor;
                    var editor = new E('#content_editor');
                    editor.customConfig.onchange = function (html) {
                        content.value = html;
                    };
                    editor.customConfig.uploadImgServer = "/upload";
                    editor.customConfig.uploadFileName = "upload_img";
                    editor.customConfig.debug = location.href.indexOf('debug=1') > 0;
                    editor.create();
                    content.value = "${article.content}";
                    editor.txt.html("${article.content}");
                </script>
                <label>
                    <input class="bt" type="submit" value="修改并发布">
                    <label id="tips"></label>
                </label>
            </div>
        </form>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/">返回首页</a>
    </div>
    <jsp:include page="/public/common/footer"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sethight.js"></script>
</body>
</html>

