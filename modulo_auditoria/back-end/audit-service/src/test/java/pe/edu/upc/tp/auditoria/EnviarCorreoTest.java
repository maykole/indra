package pe.edu.upc.tp.auditoria;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import pe.edu.upc.tp.auditoria.dao.GenericDao;
import pe.edu.upc.tp.auditoria.dao.PlanEspecificoDao;
import pe.edu.upc.tp.auditoria.dao.ProgramaDao;
import pe.edu.upc.tp.auditoria.model.ProgramaModel;
import pe.edu.upc.tp.auditoria.service.impl.PlanEspecificoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EnviarCorreoTest {

	@Autowired
	ProgramaDao programaDao;
	@Autowired
	GenericDao genericDao;
	@Autowired
	PlanEspecificoDao planAuditoriaEspecificaDao;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldSendEmail() {
		System.out.println("init send mail");
		PlanEspecificoServiceImpl plan = new PlanEspecificoServiceImpl();
		List<ProgramaModel> programas = programaDao.getProgramasByPlanAnualAndFechas(1, new Date());
		//plan.enviarCorreo(programas, 1);
		System.out.println("end send mail");
	}

}
