<%--@elvariable id="pageNumber" type="java.lang.Integer"--%>
<%--@elvariable id="recordsPerPage" type="java.lang.Integer"--%>
<%--@elvariable id="recordsCount" type="java.lang.Integer"--%>
<%--@elvariable id="records" type="java.util.List"--%>
<%--@elvariable id="user" type="com.criticalgnome.blog.entities.User"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="inc/uselocale.jsp" %>
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
    <link rel="icon" type="image/x-icon" href="images/favicon.ico" />
    <title><fmt:message key="index.title"/></title>
</head>
<body>
<%@ include file="inc/navbar.jsp" %>
<div class="container">
    <div class="col-md-12">
        <c:forEach items="${records}" var="record">
            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><b>${record.header}</b></h3><p><small>${record.createdAt}</small></p>
                        <c:if test="${(not empty user and user.role.name != 'Administrator' and user.role.name != 'Editor') or (not empty user and user.id == record.author.id)}">
                            <a href="controller?action=editrecord&id=${record.id}">
                                <button type="button" class="btn btn-success btn-xs">
                                    <fmt:message key="index.record.edit"/>
                                </button>
                            </a>
                            <a href="controller?action=deleterecord&id=${record.id}">
                                <button type="button" class="btn btn-danger btn-xs">
                                    <fmt:message key="index.record.delete"/>
                                </button>
                            </a>
                        </c:if>
                    </div>
                    <div class="panel-body">
                        <h6><b><fmt:message key="index.record.category"/>: <a href="controller?action=showcategory&id=${record.category.id}" style="color: #1b569c; text-decoration: none;">${record.category.name}</a></b></h6>
                            ${record.body}
                        <br>
                        <hr>
                        <c:forEach items="${record.tags}" var="tag">
                            <span class="badge"><a href="controller?action=showtag&id=${tag.id}" style="color: white; text-decoration: none;">${tag.name}</a></span>
                        </c:forEach>
                    </div>
                    <div class="panel-footer">
                        <small>
                            <fmt:message key="index.record.author"/>: ${record.author.nickName}
                        </small>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
<div class="row">
    <div class="container" align="center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${pageNumber > 1}">
                        <li>
                            <a href="controller?action=mainpage&page=${pageNumber - 1}" aria-label="Newer">
                                <span aria-hidden="true">&larr; </span><fmt:message key="index.newer"/>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled">
                            <a href="#" aria-label="Newer">
                                <span aria-hidden="true">&larr; </span><fmt:message key="index.newer"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:if test="${pageNumber - 3 > 0}">
                    <li><a href="controller?action=mainpage&page=1">1</a></li>
                </c:if>

                <c:if test="${pageNumber - 2 > 0}">
                    <li><a href="controller?action=mainpage&page=${pageNumber - 2}">${pageNumber - 2}</a></li>
                </c:if>

                <c:if test="${pageNumber - 1 > 0}">
                    <li><a href="controller?action=mainpage&page=${pageNumber - 1}">${pageNumber - 1}</a></li>
                </c:if>

                <li class="active"><a href="#">${pageNumber}</a></li>

                <c:if test="${pageNumber * recordsPerPage < recordsCount}">
                    <li><a href="controller?action=mainpage&page=${pageNumber + 1}">${pageNumber + 1}</a></li>
                </c:if>

                <c:if test="${(pageNumber + 1) * recordsPerPage < recordsCount}">
                    <li><a href="controller?action=mainpage&page=${pageNumber + 2}">${pageNumber + 2}</a></li>
                </c:if>

                <c:if test="${(pageNumber + 2) * recordsPerPage < recordsCount}">
                    <fmt:formatNumber var="lastPage" value="${recordsCount / recordsPerPage}" maxFractionDigits="0" />
                    <li><a href="controller?action=mainpage&page=${lastPage}">${lastPage}</a></li>
                </c:if>

                <c:choose>
                    <c:when test="${recordsCount > pageNumber * recordsPerPage}">
                        <li>
                            <a href="controller?action=mainpage&page=${pageNumber + 1}" aria-label="Next">
                                <fmt:message key="index.older"/><span aria-hidden="true"> &rarr;</span>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled">
                            <a href="#" aria-label="Next">
                                <fmt:message key="index.older"/><span aria-hidden="true"> &rarr;</span>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <form action="controller" method="post" class="form-inline">
            <input type="hidden" name="action" value="changepagination">
            <div class="form-group">
                <label for="recordsPerPage"><fmt:message key="index.records.per.page"/>: </label>
                <select id="recordsPerPage" name="recordsPerPage" class="form-control" onchange="this.form.submit()">
                    <c:choose>
                        <c:when test="${recordsPerPage == 1}"><option value="1" selected>1</option></c:when>
                        <c:otherwise><option value="1">1</option></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${recordsPerPage == 3}"><option value="3" selected>3</option></c:when>
                        <c:otherwise><option value="3">3</option></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${recordsPerPage == 5}"><option value="5" selected>5</option></c:when>
                        <c:otherwise><option value="5">5</option></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${recordsPerPage == 10}"><option value="10" selected>10</option></c:when>
                        <c:otherwise><option value="10">10</option></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${recordsPerPage == 25}"><option value="25" selected>25</option></c:when>
                        <c:otherwise><option value="25">25</option></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${recordsPerPage == 50}"><option value="50" selected>50</option></c:when>
                        <c:otherwise><option value="50">50</option></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${recordsPerPage == 100}"><option value="100" selected>100</option></c:when>
                        <c:otherwise><option value="100">100</option></c:otherwise>
                    </c:choose>
                </select>
            </div>
        </form>
    </div>
</div>
<%@ include file="inc/footer.jsp" %>
</body>
</html>
