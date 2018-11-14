package pe.edu.upc.tp.auditoria.service;

import pe.edu.upc.tp.auditoria.bean.SolicitudRegistroInit;
import pe.edu.upc.tp.auditoria.bean.SolicitudRegistroBean;

import java.util.List;

public interface SolicitudRegistroService {

	SolicitudRegistroInit init();
	SolicitudRegistroBean create(SolicitudRegistroBean solicitudregistroRQ);
	SolicitudRegistroBean findById(int id);
	SolicitudRegistroBean evaluar(int id, SolicitudRegistroBean solicitudregistro);
	List<SolicitudRegistroBean> getSolicitudesregistros();
	int delete(int id);

}
