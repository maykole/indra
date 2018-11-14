package pe.edu.upc.tp.auditoria.dao.impl;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pe.edu.upc.tp.auditoria.dao.GenericDao;
import pe.edu.upc.tp.auditoria.model.FechasprocesoplananualModel;

@Repository
public class GenericDaoImpl implements GenericDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Object findByNamedQuery(String name) {
		TypedQuery<Object> query = entityManager.createNamedQuery(name, Object.class);
		return query.getResultList();
	}

	@Override
	public Object findById(int id, String objeto) {
		Query q = entityManager.createQuery("SELECT x FROM " + objeto + " x where x.id = " + id);
		return (Object) q.getSingleResult();
	}

	@Override
	public int ultimo(String id, String objeto) throws Exception {
		Query q = entityManager.createQuery("SELECT max(x." + id + ") FROM " + objeto + " x");
		Integer max = (Integer) q.getSingleResult();
		int result = 0;
		if (max != null) {
			result = max.intValue();
		}
		return result;
	}

	@Override
	public void insert(Object object, Collection<?> collection) throws Exception {
		try {
			entityManager.persist(object);
			for (Iterator<?> it = collection.iterator(); it.hasNext();) {
				Object o = it.next();
				entityManager.persist(o);
				entityManager.flush();
				entityManager.clear();
			}
		} catch (Exception e) {
			throw new Exception("ERROR insert(Object object, Collection collection) : " + e);
		}
	}

	@Override
	public void insert(Collection<?> collection) throws Exception {
		try {
			for (Iterator<?> it = collection.iterator(); it.hasNext();) {
				Object o = it.next();
				entityManager.persist(o);
				entityManager.flush();
				entityManager.clear();
			}
		} catch (Exception e) {
			throw new Exception("ERROR insert(Collection collection) : " + e);
		}
	}

	@Override
	public void insert(Object object) throws Exception {
		try {
			entityManager.persist(object);
		} catch (Exception e) {
			throw new Exception("ERROR insert(Object object) : " + e);
		}
	}

	@Override
	public void edit(Collection<?> collection) throws Exception {
		try {
			for (Iterator<?> it = collection.iterator(); it.hasNext();) {
				Object o = it.next();
				entityManager.merge(o);
				entityManager.flush();
				entityManager.clear();
			}
		} catch (Exception e) {
			throw new Exception("ERROR insert(Collection collection) : " + e);
		}
	}

	@Override
	public void edit(Object object) throws Exception {
		try {
			entityManager.merge(object);
		} catch (Exception e) {
			throw new Exception("ERROR edit(Object object) : " + e);
		}
	}

	@Override
	public FechasprocesoplananualModel getFechasprocesoplananual() {
		Query q = entityManager.createQuery("SELECT f FROM FechasprocesoplananualModel f");
		return (FechasprocesoplananualModel) q.getSingleResult();
	}

	@Override
	public int remove(Object object) throws Exception {
		try {
			entityManager.remove(object);
		} catch (Exception e) {
			throw new Exception("ERROR remove(Object object) : " + e);
		}
		return 1;
	}

}
