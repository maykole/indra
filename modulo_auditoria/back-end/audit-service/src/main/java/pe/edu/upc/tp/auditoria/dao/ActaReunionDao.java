package pe.edu.upc.tp.auditoria.dao;

import pe.edu.upc.tp.auditoria.model.ActareunionModel;

import java.util.List;

public interface ActaReunionDao {

    ActareunionModel getActareunionByPeriodo(String anio);
    List<ActareunionModel> getActasreunion();
    int delete(int id);

}
