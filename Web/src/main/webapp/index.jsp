<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${empty records}">
    <c:redirect url="controller?action=mainpage"/>
</c:if>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="row">
    <div class="container">
        <div class="col-md-12">
            <c:forEach items="${records}" var="record">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><b>${record.header}</b></h3>
                    </div>
                    <div class="panel-body">
                        <h6><b>Category: ${record.category.name}</b></h6>
                        ${record.body}
                        <br><br><hr>
                        <c:forEach items="${record.tags}" var="tag">
                            <span class="badge">${tag.name}</span>
                        </c:forEach>
                    </div>
                    <div class="panel-footer">
                        <small>
                            Author: ${record.author.nickName}
                        </small>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
