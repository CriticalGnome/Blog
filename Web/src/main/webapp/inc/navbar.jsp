<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Blog</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="about.jsp">About</a></li>
                <li><a href="#">Link</a></li>
            </ul>
            <c:if test="${empty user}">
                <form action="login.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default">Sign in</button>
                </form>
            </c:if>
            <c:if test="${not empty user}">
                <form action="controller" class="navbar-form navbar-right">
                    Welcome, ${user.nickName}
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn btn-default">Sign out</button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
