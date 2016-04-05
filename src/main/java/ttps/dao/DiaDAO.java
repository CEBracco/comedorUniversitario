package ttps.dao;

import java.util.List;

import unlp.comedor.Dia;

public interface DiaDAO {
	public Dia createDia(Dia dia);
    public Dia updateDia(Dia dia);
    public void deleteDia(long id);
    public List<Dia> getAllDias();
    public Dia getDia(long id);
}
