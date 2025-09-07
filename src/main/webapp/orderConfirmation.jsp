<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.HashMap" %>
   <%@ page import="com.foodApp.Model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 20px;
}

.container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

h1 {
    text-align: center;
    color: #4CAF50;
}

.details {
    margin: 20px 0;
}

.details th, .details td {
    padding: 10px;
    text-align: left;
}
</style>
</head>
<body>
    <div class="container">
        <h1>Order Successfully Placed!</h1>
        <p>Your order has been placed successfully. Below are your order details:</p>

        <table class="details">
            <tr>
                <th>Order ID:</th>
                <td>${orderId}</td>
            </tr>
            <tr>
                <th>Total Amount:</th>
                <td>${totalAmount}</td>
            </tr>
            <tr>
                <th>Payment Mode:</th>
                <td>${modeOfPayment}</td>
            </tr>
        </table>

        <h3>Items Ordered:</h3>
        <table border="1" cellpadding="10" cellspacing="0" width="100%">
            <tr>
                <th>Menu ID</th>
                <th>Quantity</th>
                <th>Total Amount</th>
            </tr>
            <%
                // Fetch the cart session attribute
                HashMap<Integer, CartItem> cart = 
                    (HashMap<Integer, CartItem>) session.getAttribute("cart");

                if (cart != null && !cart.isEmpty()) {
                    for (CartItem item : cart.values()) {
            %>
            <tr>
                <td><%= item.getItemId() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getPrice() * item.getQuantity() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3" style="text-align: center;">No items in the cart.</td>
            </tr>
            <%
                }
            %>
        </table>

        <p style="text-align: center;">Thank you for ordering with us!</p>
        
        <!-- Form to redirect to home.jsp -->
        <form action="home.jsp" method="get">
            <button type="submit">Home</button>
        </form>
        
         <!-- Form for OrderHistory button -->
        <form action="OrderHistory" method="post">
            <button type="submit">Order History</button>
        </form>
    </div>
</body>
</html>
