<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
     <%@ page import="com.foodApp.Model.OrderHistory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order History</title>
<style>
    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid black;
    }
    th, td {
        padding: 10px;
        text-align: center;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>
    <h1 style="text-align: center;">Order History</h1>
    <%
        // Retrieve the OrderHistoryList from the session
        List<OrderHistory> orderHistoryList = (List<OrderHistory>) session.getAttribute("OrderHistoryList");

        if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>OrderHistory ID</th>
                    <th>Action</th>
                   
                </tr>
            </thead>
            <tbody>
                <%
                    // Iterate through the orderHistoryList and display the data
                    for (OrderHistory orderHistory : orderHistoryList) {
                %>
                <tr>
                    <td><%= orderHistory.getOrderId() %></td>
                    <td><%= orderHistory.getUserId() %></td>
                    <td><%= orderHistory.getOrderHistoryId() %></td>
                    <td>
                        <form action="ViewOrderDetailsController" method="post" style="margin: 0;">
                            <input type="hidden" name="orderId" value="<%= orderHistory.getOrderId() %>">
                            <button type="submit" class="view-button">View</button>
                        </form>
                    </td>
                </tr>
               
                <% 
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p style="text-align: center;">You have no order history.</p>
    <%
        }
    %>
</body>
</html>
