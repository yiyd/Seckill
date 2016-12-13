<%@include file="common/tag.jsp"%>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>秒杀页列表</title>
  <%@include file="common/head.jsp"%>
</head>
<body>
  <div class="container">
      <div class="panel panel-default">
          <div class="panel-heading text-center">
              <h2>秒杀列表</h2>
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
                    <c:forEach var="sk" items="${list}">
                      <tr>
                        <td>${sk.name}</td>
                        <td>${sk.number}</td>
                        <td>
                          <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                          <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                          <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                          <a class="btn btn-info" href="/seckill/${sk.seckillId}/detail" target="_blank">link</a>
                        </td>
                      </tr>
                    </c:forEach>
                </tbody>
              </table>
          </div>
      </div>
  </div>


</body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>