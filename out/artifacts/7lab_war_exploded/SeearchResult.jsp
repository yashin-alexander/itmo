<%@ page contentType="text/html charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<table id="answer">--%>
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Is inside</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item.getX()}" /></td>
            <td><c:out value="${item.getY()}" /></td>
            <td><c:out value="${item.getR()}" /></td>
            <td><c:out value="${item.getIsInside()}" /></td>
        </tr
    </c:forEach>
</table>