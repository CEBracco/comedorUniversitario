package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.MenuDAO;
import unlp.comedor.Dia;
import unlp.comedor.Menu;
@Repository
@Transactional
public class MenuDAOImpl implements MenuDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Menu createMenu(Menu menu) {
		return entityManager.merge(menu);

	}

	@Override
	public Menu updateMenu(Menu menu) {
		entityManager.merge(menu);
		return menu;
	}

	@Override
	public void deleteMenu(long id) {
		Query query = entityManager.createQuery("UPDATE Menu set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	public List<Menu> getAllMenus() {
		Query query = entityManager.createQuery("SELECT e FROM Menu e where e.activo=1");
		return (List<Menu>)query.getResultList();
	}

	@Override
	public Menu getMenu(long id) {
		return (Menu) entityManager.find(Menu.class, id);
	}

}
