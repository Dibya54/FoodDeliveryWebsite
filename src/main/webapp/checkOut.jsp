<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Check-out</title>
</head>
<body>

	<div class="container">
		<h2>Checkout</h2>
		<form action="CheckoutServlet" method="post">
			<div class="form-group">
				<label for="plot">Plot/Flat No:</label> <input type="text" id="plot"
					name="plot" placeholder="Enter plot/flat number">
			</div>
			<div class="form-group">
				<label for="street">Street Name:</label> <input type="text"
					id="street" name="street" placeholder="Enter street name">
			</div>
			<div class="form-group">
				<label for="pincode">Pincode:</label> <input type="text"
					id="pincode" name="pincode" placeholder="Enter pincode">
			</div>
			<div class="form-group">
				<label for="city">City:</label> <input type="text" id="city"
					name="city" placeholder="Enter city" required>
			</div>

			<div class="form-group">
				<label for="payment">Mode of Payment:</label> <select id="payment"
					name="payment">
					<option value="Cash">Cash on Delivery</option>
					<option value="CreditCard">Credit Card</option>
					<option value="DebitCard">Debit Card</option>
					<option value="Upi">Upi</option>
				</select>
			</div>
			<button type="submit" class="btn" style="width: 100%;">Confirm</button>
		</form>
	</div>

</body>
</html>