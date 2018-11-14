package pe.edu.upc.tp.auditoria.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import pe.edu.upc.tp.auditoria.constantes.AccesoConstantes;
import pe.edu.upc.tp.auditoria.util.UtilTransfer;

@Component
public class CORSFilter implements Filter{
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String clientOrigin = ((HttpServletRequest) request).getHeader("origin");
		//System.out.println("Client Origin : " + UtilTransfer.objectToJson(request));
		System.out.println("Client Origin : " + UtilTransfer.objectToJson(clientOrigin));
		
		boolean isAllowed = true;
		if (isAllowed) {
			//if (clientOrigin.equals(AccesoConstantes.IP_FRONTEND)) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setHeader("Access-Control-Allow-Origin", clientOrigin);
			httpResponse.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
			httpResponse.setHeader("Access-Control-Max-Age", "3600");
			httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type");
		} else {
			System.out.println("Client Origin " + clientOrigin + " is blocked");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
