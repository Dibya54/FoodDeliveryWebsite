<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.foodApp.Model.CartItem"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style>
.cart-table th, .cart-table td {
	text-align: center;
	vertical-align: middle;
}

.cart-table th {
	background-color: #f8f9fa;
}

.cart-table td {
	background-color: #e9ecef;
}
</style>
</head>
<body>
	<div class="container my-4">
		<h2 class="text-center">Shopping Cart</h2>

		<%  
            // Retrieve the cart from session
            Map<Integer, CartItem> cartMap = (Map<Integer, CartItem>) session.getAttribute("cart");
            double totalPrice = 0.0;

            if (cartMap != null && !cartMap.isEmpty()) {
        %>
		<table class="table table-bordered cart-table">
			<thead>
				<tr>
					<th>Restaurant Name</th>
					<th>Item Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<% 
                for (CartItem c : cartMap.values()) {
                    double itemTotal = c.getPrice() * c.getQuantity();
                    totalPrice += itemTotal;
            %>
				<tr>
					<td><%= c.getRestaurantId() %></td>
					<!-- Replace with restaurant name if available -->
					<td><%= c.getName() %></td>
					<td>Rs <%= c.getPrice() %></td>
					<td><form method="POST" action="UpdateCart"
							style="display: inline;">
							<!-- Decrement Button -->
							<input type="hidden" name="itemId" value="<%=c.getItemId()%>">
							<input type="hidden" name="quantity"
								value="<%=c.getQuantity() - 1%>">
							<button type="submit" class="btn btn-outline-secondary btn-sm"
								<%=c.getQuantity() == 1 ? "disabled" : ""%>>-</button>
						</form> <span><%=c.getQuantity()%></span>

						<form method="POST" action="UpdateCart" style="display: inline;">
							<!-- Increment Button -->
							<input type="hidden" name="itemId" value="<%=c.getItemId()%>">
							<input type="hidden" name="quantity"
								value="<%=c.getQuantity() + 1%>">
							<button type="submit" class="btn btn-outline-secondary btn-sm">+</button>
						</form></td>




					<td>Rs <%= itemTotal %></td>

					<td>$ <%=itemTotal%></td>
					<td><a href="removeCart?itemId=<%=c.getItemId()%>"
						class="btn btn-danger btn-sm">Remove</a></td>





				</tr>
				<% 
                }
            %>
			</tbody>
		</table>
		<% 
            } else { 
        %>
		<p class="text-center">No items in cart</p>
		<% } %>

		<!-- Total Price Section -->
		<div class="cart-total text-center my-4">
			<h4>
				Total: Rs <span id="total-price"><%= totalPrice %></span>
			</h4>
			
			<form action="checkOut.jsp" method="post">
				<button type="submit" class="btn btn-success mt-3">Place
					Order</button>
			</form>

		</div>
	</div>



	<!-- Optional Bootstrap JS for interactive components -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-cu1lQ+kQf7+Y9I9aOecD1mAzYnJZbfq0iyWQm52VmFkh8Uk8I9W0VVqghE2Hj5Jf"
		crossorigin="anonymous"></script>
</body>
</html>
