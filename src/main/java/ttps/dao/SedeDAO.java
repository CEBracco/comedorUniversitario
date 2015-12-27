package ttps.dao;

import java.util.List;

import unlp.comedor.Sede;

public interface SedeDAO {
	public void createSede(Sede sede);
    public Sede updateSede(Sede sede);
    public void deleteSede(long id);
    public List<Sede> getAllSedes();
    public Sede getSede(long id);
}
