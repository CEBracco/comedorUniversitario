package ttps.dao;

import java.util.List;

import unlp.comedor.Comensal;

public interface ComensalDAO {

	public Comensal createComensal(Comensal comensal);
    public Comensal updateComensal(Comensal comensal);
    public void deleteComensal(long id);
    public List<Comensal> getAllComensales();
    public Comensal getComensal(long id);
    public Comensal getComensal(Integer documento, String password);
	
}
