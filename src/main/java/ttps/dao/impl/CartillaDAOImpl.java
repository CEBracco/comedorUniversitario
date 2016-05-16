package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.CartillaDAO;
import unlp.comedor.Cartilla;
@Repository
@Transactional
public class CartillaDAOImpl implements CartillaDAO {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cartilla createCartilla(Cartilla carta) {
		return entityManager.merge(carta);

	}

	@Override
	public Cartilla updateCartilla(Cartilla carta) {
		entityManager.merge(carta);
		return carta;
	}

	@Override
	public void deleteCartilla(long id) {
		Query query = entityManager.createQuery("UPDATE Cartilla set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	public List<Cartilla> getAllCartillas() {
		Query query = entityManager.createQuery("SELECT e FROM Cartilla e where e.activo=1");
		return (List<Cartilla>)query.getResultList();
	}

	@Override
	public Cartilla getCartilla(long id) {
		return (Cartilla) entityManager.find(Cartilla.class, id);
	}

	@Override
	public Cartilla getCartillaActual() {
		Query query = entityManager.createQuery("SELECT e "
				+ "FROM Cartilla e "
				+ "where e.activo=1 AND current_date() >= e.inicio AND current_date() <= e.fin");
		return (Cartilla)query.getResultList().get(0);
	}
}
