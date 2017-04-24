<%--@elvariable id="user" type="com.criticalgnome.blog.entities.User"--%>
<%--@elvariable id="categories" type="java.util.List"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="old-index.jsp"/>
</c:if>
<%@ include file="inc/uselocale.jsp" %>
<c:if test="${empty categories}">
    <c:redirect url="controller?action=writerecord"/>
</c:if>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="images/favicon.ico" />
    <title><fmt:message key="write.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <form action="controller" method="post">
                <input type="hidden" name="action" value="saverecord">
                <input type="hidden" name="mode" value="create">
                <input type="hidden" name="id" value="0">
                <input type="hidden" name="author" value="${user.id}">
                <div class="form-group">
                    <label for="header"><fmt:message key="edit.form.header"/></label>
                    <input type="text" id="header" name="header" class="form-control" value="">
                </div>
                <div class="form-group">
                    <label for="categoryId"><fmt:message key="edit.form.category"/></label>
                    <select id="categoryId" name="categoryId" class="form-control">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="body"><fmt:message key="edit.form.maintext"/></label>
                    <textarea id="body" name="body" rows="10" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label for="tags"><fmt:message key="edit.form.tags"/></label>
                    <input type="text" id="tags" name="tags" class="form-control" value="">
                </div>
                <button type="submit" id="submit" name="submit" class="btn btn-primary"><fmt:message key="edit.form.save"/></button>
            </form>
        </div>
    </div>
</div>
<%@ include file="inc/footer.jsp" %>
</body>
</html>