package ttps.dao;

import java.util.List;

import unlp.comedor.Recarga;

public interface RecargaDAO {
	public Recarga createRecarga(Recarga recarga);
    public Recarga updateRecarga(Recarga recarga);
    public void deleteRecarga(long id);
    public List<Recarga> getAllRecargas();
    public Recarga getRecarga(long id);
}
