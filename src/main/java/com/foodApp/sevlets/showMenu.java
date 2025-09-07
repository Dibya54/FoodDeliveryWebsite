package com.foodApp.sevlets;

import com.foodApp.DAOImpl.MenuDaoImpl;
import com.foodApp.Model.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/showMenu")
public class showMenu extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int restaurantId = Integer.parseInt(req.getParameter("restId"));

        MenuDaoImpl menuDao = new MenuDaoImpl();
        List<Menu> menuList = menuDao.getMenuByResto(restaurantId);

        HttpSession session = req.getSession();
        session.setAttribute("menuList", menuList);
        session.setAttribute("restaurantId", restaurantId);

        // Redirect to menu.jsp to display the menu items
        resp.sendRedirect("menu.jsp");
    }
}
