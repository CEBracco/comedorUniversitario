package ttps.dao;

import java.util.List;

import unlp.comedor.Compra;

public interface CompraDAO {
	public Compra createCompra(Compra compra);
    public Compra updateCompra(Compra compra);
    public void deleteCompra(long id);
    public List<Compra> getAllCompras();
    public Compra getCompra(long id);
}
