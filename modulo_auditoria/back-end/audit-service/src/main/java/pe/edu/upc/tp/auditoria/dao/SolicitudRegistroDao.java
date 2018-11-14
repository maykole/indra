package pe.edu.upc.tp.auditoria.dao;

import pe.edu.upc.tp.auditoria.model.SolicitudregistroModel;

import java.util.List;

public interface SolicitudRegistroDao {

    List<SolicitudregistroModel> getSolicitudesregistro();
    SolicitudregistroModel getSolicitudregistroPendiente(String periodo);
    List<SolicitudregistroModel> getSolicitudregistroByActareunionId(int id);
    List<SolicitudregistroModel> getSolicitudregistroEvaluadas(String periodo);
    int delete(int id);
}
