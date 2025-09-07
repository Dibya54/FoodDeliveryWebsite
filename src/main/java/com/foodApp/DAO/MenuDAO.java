package com.foodApp.DAO;

import java.util.List;

import com.foodApp.Model.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	List<Menu>getAll();
	List<Menu>getMenuByResto(int restauratId);
}
