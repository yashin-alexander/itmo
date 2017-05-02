<%@ page contentType="text/html charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table id="answer">
    <tr>
        <th><p>X</p></th>
        <th><p>Y</p></th>
        <th><p>R</p></th>
        <th><p>Is inside</p></th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><p><c:out value="${item.getX()}" /></p></td>
            <td><p><c:out value="${item.getY()}" /></p></td>
            <td><p><c:out value="${item.getR()}" /></p></td>
            <td><p><c:out value="${item.getIsInside()}" /></p></td>
            <td><p><c:out value="q" /></p></td>
            <td><p><c:out value="q" /></p></td>


        </tr
    </c:forEach>

</table>