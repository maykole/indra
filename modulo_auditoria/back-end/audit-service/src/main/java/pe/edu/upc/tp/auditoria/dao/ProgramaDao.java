package pe.edu.upc.tp.auditoria.dao;

import java.util.Date;
import java.util.List;

import pe.edu.upc.tp.auditoria.model.ProgramaModel;

public interface ProgramaDao {
	
	List<ProgramaModel> getProgramasByPlanAnualAndFechas(int idPlanAnual, Date currentDate);

}
