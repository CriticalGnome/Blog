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
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="login">
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label"><fmt:message key="login.form.email"/></label>
                    <div class="col-sm-10">
                        <input type="email" id="email" name="email" class="form-control" placeholder="enter email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label"><fmt:message key="login.form.password"/></label>
                    <div class="col-sm-10">
                        <input type="password" id="password" name="password" class="form-control" placeholder="enter password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><fmt:message key="login.form.submit"/></button>
                        <a href="register.jsp"><button type="button" class="btn btn-default"><fmt:message key="login.form.register"/></button></a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="inc/footer.jsp" %>
</body>
</html>