package es.regueiro.collectionManager.dao;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<E, N extends Serializable> {

	E findById(N id);

	void saveOrUpdate(E e);

	void delete(E e);

	List<E> findByCriteria(Criterion criterion);

	E findOneByCriteria(Criterion criterion);
	
	List<E>  listAll();
}
