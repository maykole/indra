package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.EmpleadoDao;
import pe.edu.upc.tp.auditoria.model.EmpleadoModel;

@Repository
public class EmpleadoDaoImpl implements EmpleadoDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpleadoModel> getAuditores() {
		Query q = entityManager.createQuery("SELECT p FROM EmpleadoModel p WHERE p.cargo = 'Auditor' and p.nivel <> 'Practicante'");
		return (List<EmpleadoModel>)q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmpleadoModel> getAuditoresPracticantes() {
		Query q = entityManager.createQuery("SELECT p FROM EmpleadoModel p WHERE p.cargo = 'Auditor' and p.nivel = 'Practicante'");
		return (List<EmpleadoModel>)q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpleadoModel> getEmpleados() {
		 Query q = entityManager.createQuery("SELECT p FROM EmpleadoModel p");
		 return (List<EmpleadoModel>)q.getResultList();}

}
