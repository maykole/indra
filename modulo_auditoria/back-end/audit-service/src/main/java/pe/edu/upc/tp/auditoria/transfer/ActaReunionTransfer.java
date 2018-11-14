package pe.edu.upc.tp.auditoria.transfer;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.EmpleadoBean;
import pe.edu.upc.tp.auditoria.bean.ProcesoBean;
import pe.edu.upc.tp.auditoria.model.ActareunionAsistenteModel;
import pe.edu.upc.tp.auditoria.model.ActareunionModel;
import pe.edu.upc.tp.auditoria.model.ActareunionProcesoModel;

import java.util.ArrayList;
import java.util.List;

public class ActaReunionTransfer {

    public List<ActaReunionBean> getActasreunionBean(List<ActareunionModel> actasreunionModel){
        List<ActaReunionBean> actasreunionBean = new ArrayList<>();
        for (ActareunionModel am: actasreunionModel) {
            actasreunionBean.add(getActareunionBean(am));
        }
        return actasreunionBean;
    }

    public ActaReunionBean getActareunionBean(ActareunionModel actareunionModel){
        ActaReunionBean actaReunionBean = new ActaReunionBean();
        actaReunionBean.setId(actareunionModel.getId());
        actaReunionBean.setPeriodo(actareunionModel.getPeriodo());
        actaReunionBean.setTitulo(actareunionModel.getTitulo());
        actaReunionBean.setDescripcion(actareunionModel.getDescripcion());
        actaReunionBean.setLugar(actareunionModel.getLugar());
        actaReunionBean.setFecha(actareunionModel.getFecha());
        actaReunionBean.setAcuerdos(actareunionModel.getAcuerdos());
        actaReunionBean.setConclusiones(actareunionModel.getConclusiones());
        List<ProcesoBean> procesos = new ArrayList<>();
        for(ActareunionProcesoModel ap: actareunionModel.getActareunionProcesos()){
            ProcesoBean proceso = new ProcesoBean();
            proceso.setProcesoId(ap.getProceso().getProcesoId());
            proceso.setNombred(ap.getProceso().getDescripcion());
            procesos.add(proceso);
        }
        actaReunionBean.setProcesos(procesos );

        List<EmpleadoBean> asistentes = new ArrayList<>();
        for(ActareunionAsistenteModel aa: actareunionModel.getActareunionAsistentes()){
            EmpleadoBean empleadoBean = new EmpleadoBean();
            empleadoBean.setEmpleadoId(aa.getEmpleado().getEmpleadoId());
            empleadoBean.setNombres(aa.getEmpleado().getNombres()+" "+aa.getEmpleado().getApellidos());
            empleadoBean.setCargo(aa.getCargo());
            asistentes.add(empleadoBean);
        }
        actaReunionBean.setAsistentes(asistentes);
        return actaReunionBean;
    }

}
