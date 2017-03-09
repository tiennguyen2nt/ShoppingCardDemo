<%-- 
    Document   : cart
    Created on : Mar 8, 2017, 1:11:48 PM
    Author     : TienNN5
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.language}" />
<fmt:setBundle basename="com.shoppingCart.language.shopping" />
<jsp:useBean id="prod" class="com.shoppingCart.model.ProductCart" scope="session"/>

<center>
    <table border="1" bgcolor="lightgreen" cellspacing="0"  cellpadding="0">
        <tr>
            <td><b><fmt:message key="productId" /></b></td>
            <td><b><fmt:message key="productName" /></b></td>
            <td><b><fmt:message key="productType" /></b></td>
            <td><b><fmt:message key="price" /></b></td>
            <td><b><fmt:message key="quantity" /></b></td>
        </tr>
        
        <c:forEach var="item" items="${prod.cartItems}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.type}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>
                        <form action="ShoppingServlet" name="deleteForm" method="post">
                            <input type="submit" value=<fmt:message key="Delete"/>>
                            <input type="hidden" name="delItem" value="${item.id}">
                            <input type="hidden" name="action" value=<fmt:message key="Delete"/>>
                        </form>
                    </td>
                </tr>
            </c:forEach>
    </table>
        <form name="CheckOutForm" method="post" action="Shopping">
            <input type="hidden" name="action" value="checkout" />
            <input type="submit" value="<fmt:message key="checkout" />" name="checkout" />
        </form>
</center>