package ttps.dao;

import java.util.List;

import unlp.comedor.Administrador;

public interface AdministradorDAO {

	public Administrador createAdministrador(Administrador admin);
    public Administrador updateAdministrador(Administrador admin);
    public void deleteAdministrador(long id);
    public List<Administrador> getAllAdministradores();
    public Administrador getAdministrador(long id);
    public Administrador getAdministrador(Integer documento, String password);
    public Administrador getAdministrador(int documento);
	
}
