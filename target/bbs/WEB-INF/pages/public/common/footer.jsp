<%--
  Created by IntelliJ IDEA.
  User: tiny
  Date: 2019/12/22
  Time: 下午7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/footer.css" type="text/css">
<div class="footer">
    <div class="divide_line"></div>
    <div class="time_container">
        <label id="time">
            <script>
                document.getElementById('time').innerHTML = new Date().toLocaleString()
                    + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
                setInterval(
                    "document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",
                    1000);
            </script>
        </label>
    </div>
</div>
