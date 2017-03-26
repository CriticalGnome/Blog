<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="inc/uselocale.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <title><fmt:message key="adminarea.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
    <div class="row">
        <h1><fmt:message key="adminarea.header"/></h1>
    </div>
</div>
<%@ include file="inc/footer.jsp" %>
</body>
</html>