<%--
  Created by IntelliJ IDEA.
  User: usr0200379
  Date: 15/08/07
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <h1>${title}</h1>
    ${msg}<br />
    <c:out value="${msg}"/><br />
    <fmt:formatNumber value="${num}" pattern="###,###" />
    <fmt:formatNumber value="${num}" pattern="###,###" />
  </body>
</html>
