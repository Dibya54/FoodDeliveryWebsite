<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@page import = "com.foodApp.Model.User" %>
<%@ page import="com.foodApp.Model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<style>
    /* General styling */
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to bottom right, #ff6f61, #ffb347);
        margin: 0;
        padding: 0;
        color: #333;
    }

    .header {
        background-color: #ffffff;
        color: #ff4500;
        padding: 20px;
        text-align: center;
        border-bottom: 3px solid #ff4500;
        font-size: 1.5em;
    }

    .welcome {
        font-size: 1.3em;
        font-weight: bold;
    }

    .restaurant-container {
        display: flex;
        flex-wrap: wrap;
        padding: 20px;
        justify-content: center;
        gap: 20px;
    }

    .restaurant-card {
        background-color: white;
        border-radius: 10px;
        width: 280px;
        overflow: hidden;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        text-align: center;
        font-size: 1em;
        color: #333;
    }

    .restaurant-card:hover {
        transform: translateY(-8px);
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.4);
    }

    .restaurant-image {
        width: 100%;
        height: 180px;
        object-fit: cover;
    }

    .restaurant-info {
        padding: 15px;
        background-color: #f7f7f7;
    }

    .restaurant-name {
        font-size: 1.4em;
        font-weight: bold;
        color: #ff4500;
        margin-bottom: 5px;
    }

    .restaurant-rating {
        color: #ffa41b;
        margin-bottom: 5px;
    }

    .restaurant-eta, .restaurant-cuisine, .restaurant-address {
        font-size: 0.9em;
        color: #666;
        margin: 4px 0;
    }

    /* Button style */
    .order-now {
        background-color: #ff4500;
        color: #fff;
        border: none;
        padding: 10px 20px;
        font-size: 0.9em;
        cursor: pointer;
        border-radius: 20px;
        margin-top: 10px;
        transition: background-color 0.3s ease;
    }

    .order-now:hover {
        background-color: #e03e00;
    }
</style>
</head>
<body>
<% 
    User user = (User) session.getAttribute("LoggedInUser"); 
    List<Restaurant> restList = (List<Restaurant>) session.getAttribute("restList");
%>
<div class="header">
    <span class="welcome">Hello, Welcome back <%= user.getUsername() %>!</span>
</div>

<div class="restaurant-container">
    <% 
        if (restList != null) {
            for (Restaurant restaurant : restList) {
    %>
    <div class="restaurant-card">
        <img src="<%= restaurant.getImagepath() %>" alt="Restaurant Image" class="restaurant-image">
        <div class="restaurant-info">
            <div class="restaurant-name"><%= restaurant.getName() %></div>
            <div class="restaurant-rating">Rating: <%= restaurant.getRating() %> ⭐</div>
            <div class="restaurant-eta">ETA: <%= restaurant.getEta() %></div>
            <div class="restaurant-cuisine">Cuisine: <%= restaurant.getCuisineType() %></div>
            <div class="restaurant-address">Address: <%= restaurant.getAddress() %></div>
            <button class="order-now" onclick="location.href='showMenu?restId=<%= restaurant.getRestaurantId() %>'">View Menu</button>

        </div>
    </div>
    <% 
            } 
        } else { 
    %>
    <p style="color: white; font-size: 1.2em; text-align: center;">No restaurants available at the moment.</p>
    <% 
        } 
    %>
</div>
</body>
</html>
