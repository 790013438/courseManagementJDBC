<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Teacher</title>
        <link rel="stylesheet" type="text/css" href="static/css/addTeacher.css">
    </head>
    <body>
        <c:set var="errMsg" value="${null}"/>
        <c:if test="${\"POST\".equalsIgnoreCase(pageContext.request.method) && pageContext.request.getParameter(\"submit\") != null}">
            <jsp:useBean id="teacherBean" class="snippets.bean.Teacher">
                <c:catch var="beanStorageException">
                    <jsp:setProperty name="teacherBean" property="*"/>
                </c:catch>
            </jsp:useBean>
            <c:choose>
                <c:when test="${!teacherBean.isValidTeacher() || beanStorageException != null}">
                    <c:set var="errMsg" value="Invalid teacher details. Please try again"/>
                </c:when>
                <c:otherwise>
                    <c:catch var="addTeacherException">
                        ${teacherBean.addTeacher()}
                    </c:catch>
                    <c:choose>
                        <c:when test="${addTeacherException != null}">
                            <c:set var="errMsg" value="${addTeacherException.message}"/>
                        </c:when>
                        <c:otherwise>
                            <c:redirect url="listTeacher.jsp"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:if>
        <div class="main-container">
            <div class="contact-form" role="form">
                <header>
                    <h2>Add Teacher</h2>
                    <c:if test="${errMsg != null}">
                        <span style="color:red;">
                            <c:out value="${errMsg}"/>
                        </span>
                    </c:if>
                </header>
                <form method="post">
                    <div class="flex-container">
                        <label class="label-col">Name: <input type="text" name="name" class="field"></label>
                    </div>
                    <div class="flex-container">
                        <label class="label-col">designation: <input type="text" name="designation" class="field"/></label>
                    </div>
                    <button type="submit" name="submit">Add</button>
                </form>
            </div>
        </div>
    </body>
</html>
