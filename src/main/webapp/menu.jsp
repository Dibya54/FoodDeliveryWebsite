<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodApp.Model.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }
        .menu-card {
            background-color: white;
            border-radius: 10px;
            width: 280px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            text-align: center;
            padding: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .menu-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.4);
        }
        .menu-image {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 8px;
        }
        .menu-name {
            font-size: 1.3em;
            font-weight: bold;
            margin: 10px 0;
            color: #ff4500;
        }
        .menu-description {
            font-size: 0.9em;
            color: #666;
        }
        .menu-price {
            font-size: 1em;
            color: #333;
            font-weight: bold;
            margin: 8px 0;
        }
    </style>
</head>
<body>
<%
    List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
%>
<div class="menu-container">
    <% if (menuList != null && !menuList.isEmpty()) { %>
        <% for (Menu menu : menuList) { %>
            <div class="menu-card">
                <img src="<%= menu.getImagePath() %>" alt="Menu Image" class="menu-image">
                <div class="menu-name"><%= menu.getName() %></div>
                <div class="menu-description"><%= menu.getDescription() %></div>
                <div class="menu-price">Price: ₹<%= menu.getPrice() %></div>
                <div>Rating: <%= menu.getRating() %> ⭐</div>
                <!-- Form to add item to the cart -->
                <form action="AddToCartController" method="post">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>" />
                    <input type="number" name="quantity" value="1" min="1" class="form-control mb-2" />
                    <input type="submit" value="Add to Cart">
                </form>
            </div>
        <% } %>
    <% } else { %>
        <p>No menu items available for this restaurant.</p>
    <% } %>
</div>
</body>
</html>
