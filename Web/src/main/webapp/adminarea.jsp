<%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
<%--@elvariable id="categoryLines" type="java.util.List"--%>
<%--@elvariable id="records" type="java.util.List"--%>
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
    <title><fmt:message key="adminarea.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
    <div class="row">
        <h1><fmt:message key="adminarea.header"/></h1>
        <div class="col-md-4">
            <h2>Categories</h2>
            <table class="table table-condensed table-hover">
                <tr>
                    <th>Id</th>
                    <th colspan="3">Name</th>
                </tr>
                <c:forEach items="${categoryLines}" var="categoryLine">
                    <tr>
                        <td>${categoryLine.id}</td>
                        <td>${categoryLine.name}</td>
                        <td width="1%">
                            <a href="controller?action=editcategory&id=${categoryLine.id}" title="Edit category">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                        </td>
                        <td width="1%">
                            <a href="controller?action=deletecategory&id=${categoryLine.id}" title="Delete category">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="controller?action=addcategory">Add new category</a>
        </div>
        <div class="col-md-8">
            <h2>Last 5 records (${recordsCount} total)</h2>
            <table class="table table-condensed table-hover">
                <tr>
                    <th>Id</th>
                    <th width="99%" colspan="3">header</th>
                </tr>
                <c:forEach items="${records}" var="record">
                    <tr>
                        <td>${record.id}</td>
                        <td>${record.header}</td>
                        <td width="1%">
                            <a href="controller?action=editrecord&id=${record.id}" title="Edit record">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                        </td>
                        <td width="1%">
                            <a href="controller?action=deleterecord&id=${record.id}" title="Delete record">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@ include file="inc/footer.jsp" %>
</body>
</html>