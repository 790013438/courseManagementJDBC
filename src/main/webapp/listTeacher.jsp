<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" type="text/css" href="static/css/listTeacher.css">
    </head>
    <body>
        <c:catch var="err">
            <jsp:useBean id="teacherBean" class="snippets.bean.Teacher"/>
            <c:set var="teachers" value="${teacherBean.getTeachers()}"/>
        </c:catch>
        <c:choose>
            <c:when  test="${err != null}">
                <c:set var="errMsg" value="${err.message}"/>
            </c:when>
        </c:choose>
        <div class="contact-form" role="form">
            <header>
                <h2>Teachers:</h2>
                <c:if test="${errMsg != null}">
                    <span style="color:red;">
                        <c:out value="${errMsg}"/>
                    </span>
                </c:if>
            </header>
            <table>
                <tr class="flex-container">
                    <th class="label-col">Id</th>
                    <th class="label-col">Name</th>
                    <th class="label-col">Designation</th>
                </tr>
                <c:forEach items="${teachers}"  var="teacher">
                    <tr class="flex-container">
                        <td class="label-col">${teacher.id}</td>
                        <td class="label-col">${teacher.name}</td>
                        <td class="label-col">${teacher.designation}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
