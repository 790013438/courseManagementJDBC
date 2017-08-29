<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Course</title>
        <link rel="stylesheet" type="text/css" href="static/css/addCourse.css">
    </head>
    <body>
        <c:set var="errMsg" value="${null}"/>
        <c:set var="displayForm" value="${true}"/>
        <jsp:useBean id="teacherBean" class="snippets.bean.Teacher"/>
        <c:catch var="teacherBeanErr">
            <c:set var="teachers" value="${teacherBean.getTeachers()}"/>
        </c:catch>
        <c:if test="${teacherBeanErr} != null">
            <c:set var="errMsg" value="${teacherBeanErr.message}"/>
        </c:if>
        <c:if test="${\"POST\".equalsIgnoreCase(pageContext.request.method) && pageContext.request.getParameter(\"submit\") != null}">
            <jsp:useBean id="courseBean" class="snippets.bean.Course">
                <c:catch var="beanStorageException">
                    <jsp:setProperty name="courseBean" property="*"/>
                    <jsp:setProperty name="courseBean" property="name" value="<%= new String(request.getParameter(\"name\").getBytes(\"ISO8859_1\"), \"utf-8\") %>"/>
                </c:catch>
            </jsp:useBean>
            <c:choose>
                <c:when test="${!courseBean.isValidCourse() || beanStorageException != null}">
                    <c:set var="errMsg" value="Invalid course details. Please try again"/>
                </c:when>
                <c:otherwise>
                    <c:catch var="addCourseException">
                        ${courseBean.addCourse()}
                    </c:catch>
                    <c:choose>
                        <c:when test="${addCourseException != null}">
                            <c:set var="errMsg" value="${addCourseException.message}"/>
                        </c:when>
                        <c:otherwise>
                            <c:redirect url="listCourse.jsp"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:if>
        <h2>Add Course:</h2>
        <c:if test="${errMsg != null}">
            <span style="color:red;">
                <c:out value="${errMsg}"/>
            </span>
        </c:if>
        <div class="main-container flex-container">
            <div class="article-container flex-container" style="width:100%">
                <form method="post" class="main-content">
                    <div><p style="width:12%;display:inline-block;">Name: </p><input type="text" name="name"/></div>
                    <div><p style="width:12%;display:inline-block;">Credits: </p><input type="text" name="credits"/></div>
                    <div><p style="width:12%;display:inline-block;">Teacher: </p><select name="teacherId">
                            <c:forEach items="${teachers}" var="teacher">
                                <option value="${teacher.id}">${teacher.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" name="submit">Add</button>
                </form>
            </div>
        </div>
    </body>
</html>
