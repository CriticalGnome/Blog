<%--@elvariable id="category" type="com.criticalgnome.blog.entities.Category"--%>
<%--@elvariable id="categoryLines" type="java.util.List"--%>
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
    <link rel="icon" type="image/x-icon" href="images/favicon.ico" />
    <title><fmt:message key="category.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
    <div class="row">
        <h1 align="center">
        <c:choose>
            <c:when test="${empty category}"><fmt:message key="category.header.new"/></c:when>
            <c:otherwise><fmt:message key="category.header.edit"/></c:otherwise>
        </c:choose>
        </h1>
        <form action="controller" method="post" class="form-horizontal">
            <input type="hidden" name="action" value="savecategory">
            <input type="hidden" name="id" value="${category.id}">
            <div class="form-group">
                <label for="name" class="col-md-4 control-label"><fmt:message key="category.form.name"/></label>
                <div class="col-md-8">
                    <input type="text" id="name" name="name" class="form-control" value="${category.name}" placeholder="Enter category name">
                </div>
            </div>
            <div class="form-group">
                <label for="parentCategory" class="col-md-4 control-label"><fmt:message key="category.form.parent"/></label>
                <div class="col-md-8">
                    <select id="parentCategory" name="parentCategory" class="form-control">
                        <option value="0"><fmt:message key="category.form.toplevel"/></option>
                        <c:forEach items="${categoryLines}" var="categoryLine">
                            <c:set var="isSelected" value=""/>
                            <c:if test="${categoryLine.id == category.category.id}">
                                <c:set var="isSelected" value=" selected"/>
                            </c:if>
                            <option value="${categoryLine.id}"${isSelected}>${categoryLine.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <button type="submit" class="btn btn-default"><fmt:message key="category.form.submit"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="inc/footer.jsp" %>
</body>
</html>