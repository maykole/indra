package pe.edu.upc.tp.auditoria.dao;

import java.util.List;

import pe.edu.upc.tp.auditoria.model.EmpleadoModel;

public interface EmpleadoDao {

	List<EmpleadoModel> getAuditores();
	List<EmpleadoModel> getAuditoresPracticantes();
	List<EmpleadoModel> getEmpleados();

}
