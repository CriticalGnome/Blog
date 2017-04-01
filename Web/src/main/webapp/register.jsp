<%--@elvariable id="lastNameMessage" type="java.lang.String"--%>
<%--@elvariable id="lastNameValue" type="java.lang.String"--%>
<%--@elvariable id="lastNameClass" type="java.lang.String"--%>
<%--@elvariable id="firstNameMessage" type="java.lang.String"--%>
<%--@elvariable id="firstNameValue" type="java.lang.String"--%>
<%--@elvariable id="firstNameClass" type="java.lang.String"--%>
<%--@elvariable id="nickNameMessage" type="java.lang.String"--%>
<%--@elvariable id="nickNameValue" type="java.lang.String"--%>
<%--@elvariable id="nickNameClass" type="java.lang.String"--%>
<%--@elvariable id="passwordMessage" type="java.lang.String"--%>
<%--@elvariable id="passwordValue" type="java.lang.String"--%>
<%--@elvariable id="passwordClass" type="java.lang.String"--%>
<%--@elvariable id="emailMessage" type="java.lang.String"--%>
<%--@elvariable id="emailValue" type="java.lang.String"--%>
<%--@elvariable id="emailClass" type="java.lang.String"--%>
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
    <title><fmt:message key="register.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="register">
                <div class="form-group ${emailClass}">
                    <label for="email" class="col-sm-3 control-label"><fmt:message key="register.form.email"/></label>
                    <div class="col-sm-9">
                        <input type="email" id="email" name="email" class="form-control" placeholder="enter email" value="${emailValue}">
                        <c:if test="${not empty emailMessage}">
                            <span class="help-block">${emailMessage}</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group ${passwordClass}">
                    <label for="password" class="col-sm-3 control-label"><fmt:message key="register.form.password"/></label>
                    <div class="col-sm-9">
                        <input type="password" id="password" name="password" class="form-control" placeholder="enter password" value="${passwordValue}">
                        <c:if test="${not empty passwordMessage}">
                            <span class="help-block">${passwordMessage}</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group ${nickNameClass}">
                    <label for="nickName" class="col-sm-3 control-label"><fmt:message key="register.form.nickname"/></label>
                    <div class="col-sm-9">
                        <input type="text" id="nickName" name="nickName" class="form-control" placeholder="enter nickname" value="${nickNameValue}">
                        <c:if test="${not empty nickNameMessage}">
                            <span class="help-block">${nickNameMessage}</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group ${firstNameClass}">
                    <label for="firstName" class="col-sm-3 control-label"><fmt:message key="register.form.firstname"/></label>
                    <div class="col-sm-9">
                        <input type="text" id="firstName" name="firstName" class="form-control" placeholder="enter first name" value="${firstNameValue}">
                        <c:if test="${not empty firstNameMessage}">
                            <span class="help-block">${firstNameMessage}</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group ${lastNameClass}">
                    <label for="lastName" class="col-sm-3 control-label"><fmt:message key="register.form.lastname"/></label>
                    <div class="col-sm-9">
                        <input type="text" id="lastName" name="lastName" class="form-control" placeholder="enter last name" value="${lastNameValue}">
                        <c:if test="${not empty lastNameMessage}">
                            <span class="help-block">${lastNameMessage}</span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary"><fmt:message key="register.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="inc/footer.jsp" %>
</body>
</html>