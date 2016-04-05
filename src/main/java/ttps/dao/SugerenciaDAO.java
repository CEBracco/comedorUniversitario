package ttps.dao;

import java.util.List;

import unlp.comedor.Sugerencia;

public interface SugerenciaDAO {
	public Sugerencia createSugerencia(Sugerencia sugerencia);
    public Sugerencia updateSugerencia(Sugerencia sugerencia);
    public void deleteSugerencia(long id);
    public List<Sugerencia> getAllSugerencias();
    public Sugerencia getSugerencia(long id);
}
