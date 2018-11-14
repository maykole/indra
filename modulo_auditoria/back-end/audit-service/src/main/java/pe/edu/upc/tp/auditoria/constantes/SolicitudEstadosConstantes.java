package pe.edu.upc.tp.auditoria.constantes;

import java.util.HashMap;
import java.util.Map;

public class SolicitudEstadosConstantes {
	
	public static final int SOLICITUD_PENDIENTE = 1;
	public static final int SOLICITUD_APROBADA = 2;
	public static final int SOLICITUD_RECHAZADA = 3;
	
	public static final Map<String, String> SOLICITUD_ESTADOS;
	static {
		SOLICITUD_ESTADOS = new HashMap<String, String>();
		SOLICITUD_ESTADOS.put("1", "Pendiente");
		SOLICITUD_ESTADOS.put("2", "Aprobado");
		SOLICITUD_ESTADOS.put("3", "Rechazado");
	}
	
}
