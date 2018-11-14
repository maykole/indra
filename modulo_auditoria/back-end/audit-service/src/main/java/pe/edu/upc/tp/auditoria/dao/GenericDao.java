package pe.edu.upc.tp.auditoria.dao;

import java.util.Collection;

import pe.edu.upc.tp.auditoria.model.FechasprocesoplananualModel;

public interface GenericDao {

	int ultimo(String id, String objeto) throws Exception;
	void insert(Object object, Collection<?> collection) throws Exception;
	void insert(Collection<?> collection) throws Exception;
	void insert(Object object) throws Exception;
	void edit(Collection<?> collection) throws Exception;
	void edit(Object object) throws Exception;
	FechasprocesoplananualModel getFechasprocesoplananual();
	Object findByNamedQuery(String name);
	Object findById(int id, String objeto);
	int remove(Object object) throws Exception;

}
