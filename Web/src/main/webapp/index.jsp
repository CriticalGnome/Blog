<%--@elvariable id="rightPage" type="java.lang.String"--%>
<%--@elvariable id="rightPageClass" type="java.lang.String"--%>
<%--@elvariable id="leftPage" type="java.lang.String"--%>
<%--@elvariable id="leftPageClass" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="records" type="java.util.List"--%>
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
                        <%--@elvariable id="user" type="com.criticalgnome.blog.entities.User"--%>
                        <c:if test="${(not empty user and user.role.id < 3) or (not empty user and user.id == record.author.id)}">
                            <a href="controller?action=editrecord&id=${record.id}">Edit</a>
                        </c:if>
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
<div class="row">
    <div class="container">
        <nav aria-label="...">
            <ul class="pager">
                <li class="previous${leftPageClass}"><a href="${leftPage}"><span aria-hidden="true">&larr;</span> Left</a></li>
                <li class="next${rightPageClass}"><a href="${rightPage}">Right <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
