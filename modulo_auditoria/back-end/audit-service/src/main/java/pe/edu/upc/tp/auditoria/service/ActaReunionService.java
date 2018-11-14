package pe.edu.upc.tp.auditoria.service;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.ActaReunionInit;

import java.util.List;

public interface ActaReunionService {

    ActaReunionInit init();
    ActaReunionBean create(ActaReunionBean actaReunionRQ);
    ActaReunionBean findById(int id);
    List<ActaReunionBean> getActasreunion();
    int delete(int id);

}
