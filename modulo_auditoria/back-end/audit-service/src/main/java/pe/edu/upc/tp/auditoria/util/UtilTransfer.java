package pe.edu.upc.tp.auditoria.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pe.edu.upc.tp.auditoria.constantes.AnioConstantes;

public class UtilTransfer {

	public static String getFechaActual() {
		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("ES"));
		Date fechaDate = new Date();
		return formateador.format(fechaDate);
	}
	
	public static String getFechaString(Date fechaDate) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("ES"));
		return formateador.format(fechaDate);
	}

	public static String getAñoSiguiente() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy");
		String año = dt.format(new Date());
		//return String.valueOf(Integer.parseInt(año) + 1);
		return AnioConstantes.SIGUIENTE;
	}
	
	public static String getAñoActual() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy");
		//return dt.format(new Date());
		return AnioConstantes.ACTUAL;
	}
	
	public static String objectToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

	public static Object jsonToObject(String json, Object object) {
		if (json != null && !json.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				object = mapper.readValue(json, object.getClass());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return object;
	}

}
