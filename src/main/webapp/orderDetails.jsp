<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodApp.Model.OrderItem" %>
<html>
<head>
    <title>Order Details</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>

<h2>Order Details</h2>

<%-- Retrieve the orderItemsList from the request attribute --%>
<%
    List<OrderItem> orderItemsList = (List<OrderItem>) request.getAttribute("orderItemsList");
    if (orderItemsList != null && !orderItemsList.isEmpty()) {
%>

    <table>
        <thead>
            <tr>
                <th>Order Item ID</th>
                <th>Restaurant Name</th>
                <th>Dish Name</th>
                <th>Quantity</th>
                <th>Total Amount</th>
            </tr>
        </thead>
        <tbody>
            <%-- Loop through each OrderItem in the list and display its details --%>
            <% 
                for (OrderItem item : orderItemsList) {
            %>
                <tr>
                    <td><%= item.getOrderItemId() %></td>
                    <td><%= item.getRestaurantName() %></td> <!-- Display restaurant name -->
                    <td><%= item.getDishName() %></td> <!-- Display dish name -->
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getTotalAmount() %></td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>

<% 
    } else {
%>
    <p>No order details available.</p>
<% 
    }
%>

</body>
</html>
