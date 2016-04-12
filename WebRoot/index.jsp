<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
   <%@ include file="/public/head.jspf" %>
  </head>
  
  <body>
    This is my JSP page. <br>
    
     <a href="send_main_aindex.action">直接到后台UI版</a><br>
     <a href="send_main_index.action">直接到后台Div版</a><br>
     <a href="${shop}/category_update.action?category.id=2&category.type=儿童休闲&category.hot=false&category.aid=3">访问update SSH环境</a>
     <a href="${shop}/category_update.action?id=2&type=儿童休闲&hot=false&aid=3">ModelDriven测试</a>
     <a href="category_save.action">访问save，测试Spring与Struts环境</a>
    <a href="account_query.action?login=admin&pass=admin">account Action测试</a>
	 <a href="${pageContext.request.contextPath}/category_query.action">查询所有类别</a><br>
	<c:forEach items="${requestScope.categoryList}" var="category">
		${category.id}|${category.type}|${category.hot}|${category.aid}<br>
	</c:forEach>
	<hr/>
	<c:forEach items="${sessionScope.categoryList}" var="category">
		${category.id}|${category.type}|${category.hot}|${category.aid}<br>
	</c:forEach>
	
    <hr/>
	<c:forEach items="${applicationScope.categoryList}" var="category">
		${category.id}|${category.type}|${category.hot}|${category.aid}<br>
	</c:forEach>
     
    <hr/>
      <form action="category_save.action" method="post">
     	 类别名称:<input type="text" name="category.type" /><br />
     	热点:<input type="radio" name="category.hot" value="true" />
     	非热点:<input type="radio" name="category.hot" value="false" />
     	<input type="submit" value="添加" />
     </form>
     <form action="category_update.action" method="post">
     	 类别名称:<input type="text" name="category.type" /><br />
     	热点:<input type="radio" name="category.hot" value="true" />
     	非热点:<input type="radio" name="category.hot" value="false" />
     	编号:<input type="text" size="2" name="category.id" />
     	外编号:<input type="text" size="2" name="category.aid" />
     	<input type="submit" value="更新" />
     </form>
  </body>
</html>
