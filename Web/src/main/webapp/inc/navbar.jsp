<%--@elvariable id="alert" type="com.criticalgnome.blog.utils.Alert"--%>
<%--@elvariable id="user" type="com.criticalgnome.blog.entities.User"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only"><fmt:message key="navbar.toggle"/></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp"><fmt:message key="navbar.name"/></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="about.jsp"><fmt:message key="navbar.about"/></a></li>
                <c:if test="${not empty user}">
                    <li><a href="write.jsp"><fmt:message key="navbar.newrecord"/></a></li>
                </c:if>
                <c:if test="${not empty user and user.role.id == 1}">
                    <li><a href="adminarea.jsp"><fmt:message key="navbar.adminpanel"/></a></li>
                </c:if>
            </ul>
            <c:if test="${empty user}">
                <form action="login.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="navbar.signin"/></button>
                </form>
            </c:if>
            <c:if test="${not empty user}">
                <form action="controller" class="navbar-form navbar-right">
                    <fmt:message key="navbar.welcome"/>, ${user.nickName}
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn btn-default"><fmt:message key="navbar.signout"/></button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
<c:if test="${not empty alert}">
    <div class="row">
        <div class="container">
            <div class="col-md-4 col-md-offset-4">
                <div class="alert ${alert.alertClass} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    ${alert.alertMessage}
                </div>
            </div>
        </div>
    </div>
    <c:remove var="alert" scope="session" />
</c:if>