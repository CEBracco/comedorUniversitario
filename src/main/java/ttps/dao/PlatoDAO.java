package ttps.dao;

import java.util.List;

import unlp.comedor.Plato;

public interface PlatoDAO {
	public Plato createPlato(Plato plato);
    public Plato updatePlato(Plato plato);
    public void deletePlato(long id);
    public List<Plato> getAllPlatos();
    public Plato getPlato(long id);

}
