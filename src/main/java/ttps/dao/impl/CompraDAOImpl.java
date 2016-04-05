package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.CompraDAO;
import unlp.comedor.Cartilla;
import unlp.comedor.Compra;
@Repository
@Transactional
public class CompraDAOImpl implements CompraDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Compra createCompra(Compra compra) {
		return entityManager.merge(compra);

	}

	@Override
	public Compra updateCompra(Compra compra) {
		entityManager.merge(compra);
		return compra;
	}

	@Override
	public void deleteCompra(long id) {
		entityManager.remove(entityManager.find(Compra.class, id));

	}

	@Override
	public List<Compra> getAllCompras() {
		Query query = entityManager.createQuery("SELECT e FROM compra e");
		return (List<Compra>)query.getResultList();
	}

	@Override
	public Compra getCompra(long id) {
		return (Compra) entityManager.find(Compra.class, id);
	}

}
