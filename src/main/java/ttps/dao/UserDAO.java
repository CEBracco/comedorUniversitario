package ttps.dao;

import java.util.List;

import unlp.comedor.Usuario;

public interface UserDAO {
	
		public void createUser(Usuario user);
	    public Usuario updateUser(Usuario user);
	    public void deleteUser(long id);
	    public List<Usuario> getAllUsers();
	    public Usuario getUser(long id);
	    public Usuario getUser(Integer documento, String password);
	
}
