package ttps.dao;

import java.util.List;

import unlp.comedor.Responsable;

public interface ResponsableDAO {
	public Responsable createResponsable(Responsable responsable);
    public Responsable updateResponsable(Responsable responsable);
    public void deleteResponsable(long id);
    public List<Responsable> getAllResponsables();
    public Responsable getResponsable(long id);
    public Responsable getResponsable(Integer documento, String password);
    public Responsable getResponsable(int documento);
    public Responsable toAdministrador(Responsable responsable);
    public Responsable toResponsable(Responsable responsable);
}
