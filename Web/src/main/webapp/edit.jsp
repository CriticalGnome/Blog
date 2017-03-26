<%--@elvariable id="record" type="com.criticalgnome.blog.entities.Record"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <div class="col-md-10 col-md-offset-1">
            <form action="controller" method="post">
                <input type="hidden" name="action" value="saverecord">
                <input type="hidden" name="id" value="${record.id}">
                <input type="hidden" name="author" value="${record.author.id}">
                <div class="form-group">
                    <label for="header">Header</label>
                    <input type="text" id="header" name="header" class="form-control" value="${record.header}">
                </div>
                <div class="form-group">
                    <label for="categoryId">Category</label>
                    <select id="categoryId" name="categoryId" class="form-control">
                        <%--@elvariable id="categories" type="java.util.List"--%>
                        <c:forEach items="${categories}" var="category">
                            <c:if test="${record.category.id == category.id}">
                                <option value="${category.id}" selected>${category.name}</option>
                            </c:if>
                            <c:if test="${record.category.id != category.id}">
                                <option value="${category.id}">${category.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="body">Main text</label>
                    <textarea id="body" name="body" rows="10" class="form-control">${record.body}</textarea>
                </div>
                <button type="submit" id="submit" name="submit" class="btn btn-primary">Save</button>
                <button type="reset" id="reset" name="reset" class="btn btn-warning">Reset</button>
            </form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>