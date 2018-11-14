package pe.edu.upc.tp.auditoria.dao;

import java.util.List;

import pe.edu.upc.tp.auditoria.model.ProcedimientoModel;

public interface ProcedimientoDao {

	List<ProcedimientoModel> getProcedimientos();
	List<ProcedimientoModel> getProcedimientosByProceso(int idProceso);

}
