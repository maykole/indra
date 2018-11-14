package pe.edu.upc.tp.auditoria.transfer;

import pe.edu.upc.tp.auditoria.bean.ActaReunionBean;
import pe.edu.upc.tp.auditoria.bean.EmpleadoBean;
import pe.edu.upc.tp.auditoria.bean.ProcesoBean;
import pe.edu.upc.tp.auditoria.bean.SolicitudRegistroBean;
import pe.edu.upc.tp.auditoria.constantes.SolicitudEstadosConstantes;
import pe.edu.upc.tp.auditoria.model.SolicitudregistroModel;

import java.util.ArrayList;
import java.util.List;

public class SolicitudRegistroTransfer {

    public List<SolicitudRegistroBean> getSolicitudesregistroBean(List<SolicitudregistroModel> solicitudesregistroModel){
        List<SolicitudRegistroBean> solicitudesregistroBean = new ArrayList<>();
        for (SolicitudregistroModel sm: solicitudesregistroModel){
            solicitudesregistroBean.add(getSolicitudregistroBean(sm));
        }
        return solicitudesregistroBean;
    }

    public SolicitudRegistroBean getSolicitudregistroBean(SolicitudregistroModel solicitudregistroModel){
        SolicitudRegistroBean solicitudregistroBean = new SolicitudRegistroBean();

        solicitudregistroBean.setId(solicitudregistroModel.getId());
        solicitudregistroBean.setAsunto(solicitudregistroModel.getAsunto());
        solicitudregistroBean.setEstadoid(solicitudregistroModel.getEstadoid());
        solicitudregistroBean.setPrioridad(solicitudregistroModel.getPrioridad());
        solicitudregistroBean.setMotivo(solicitudregistroModel.getMotivo());
        solicitudregistroBean.setEstado(SolicitudEstadosConstantes.SOLICITUD_ESTADOS
                .get(String.valueOf(solicitudregistroModel.getEstadoid())));
        solicitudregistroBean.setFecharegistro(solicitudregistroModel.getFecharegistro());
        solicitudregistroBean.setFechaevaluacion(solicitudregistroModel.getFechaevaluacion());

        solicitudregistroBean.setBevaluar(false);
        if(solicitudregistroModel.getEstadoid() == 1){
            solicitudregistroBean.setBevaluar(true);
        }


        solicitudregistroBean.setActareunion(new ActaReunionBean(solicitudregistroModel.getActareunionId()));

        EmpleadoBean solicitante = new EmpleadoBean();
        solicitante.setEmpleadoId(solicitudregistroModel.getSolicitante().getEmpleadoId());
        solicitante.setNombres(solicitudregistroModel.getSolicitante().getNombres() + " "
                + solicitudregistroModel.getSolicitante().getApellidos());
        solicitante.setCargo(solicitudregistroModel.getSolicitante().getCargo());
        solicitudregistroBean.setSolicitante(solicitante);

        if(solicitudregistroModel.getEvaluador() != null){
            EmpleadoBean evaluador = new EmpleadoBean();
            evaluador.setEmpleadoId(solicitudregistroModel.getEvaluador().getEmpleadoId());
            evaluador.setNombres(solicitudregistroModel.getEvaluador().getNombres() + " "+ solicitudregistroModel.getEvaluador().getApellidos());
            evaluador.setCargo(solicitudregistroModel.getEvaluador().getCargo());
            solicitudregistroBean.setEvaluador(evaluador);
        }

        ProcesoBean proceso = new ProcesoBean();
        proceso.setProcesoId(solicitudregistroModel.getProceso().getProcesoId());
        proceso.setNombred(solicitudregistroModel.getProceso().getDescripcion());
        solicitudregistroBean.setProceso(proceso);
        return solicitudregistroBean;
    }
}
