<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表</title>
    <%@include file="common/common.jsp" %>
</head>
<body>
<!-- 页面显示部分-->
<div class="container">
     <div class="panel panel-default">
         <div class="panel-heading text-center">
             <h1>秒杀列表</h1>
         </div>
         <div class="panel-body">
             <table class="table table-hover">
                 <thead>
                 <tr>
                     <th>名称</th>
                     <th>库存</th>
                     <th>开始时间</th>
                     <th>结束时间</th>
                     <th>创建时间</th>
                     <th>详情页</th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:forEach var="recode" items="${list}">
                     <tr>
                         <td>${recode.name}</td>
                         <td>${recode.number}</td>
                         <td>
                            <fmt:formatDate value="${recode.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                         </td>
                         <td>
                             <fmt:formatDate value="${recode.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                         <td>
                             <fmt:formatDate value="${recode.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                         </td>
                         <td>
                             <a class="btn btn-info" href="/seckill/${recode.seckillId}/detail" target="_blank">详情</a>
                         </td>
                     </tr>
                 </c:forEach>
                 </tbody>
             </table>
         </div>
     </div>
</div>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>
