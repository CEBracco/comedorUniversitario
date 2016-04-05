package ttps.dao;

import java.util.List;

import unlp.comedor.Menu;

public interface MenuDAO {
	public Menu createMenu(Menu menu);
    public Menu updateMenu(Menu menu);
    public void deleteMenu(long id);
    public List<Menu> getAllMenus();
    public Menu getMenu(long id);
}
