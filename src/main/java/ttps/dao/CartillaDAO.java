package ttps.dao;

import java.util.List;

import unlp.comedor.Cartilla;

public interface CartillaDAO {
	public Cartilla createCartilla(Cartilla carta);
    public Cartilla updateCartilla(Cartilla carta);
    public void deleteCartilla(long id);
    public List<Cartilla> getAllCartillas();
    public Cartilla getCartilla(long id);
    public Cartilla getCartillaActual();
}
