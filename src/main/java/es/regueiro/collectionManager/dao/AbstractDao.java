package es.regueiro.collectionManager.dao;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * An abstract DAO for accessing the database
 * 
 * @param <E>
 *            the element type that the implementation will support
 * @param <N>
 *            the type of the element id
 */
public interface AbstractDao<E, N extends Serializable> {

	/**
	 * Get a single element by id.
	 * 
	 * @param id
	 *            the element id
	 * @return the element if found
	 */
	E getById(N id);

	/**
	 * Save or update an element.
	 * 
	 * @param e
	 *            the element to save
	 */
	void saveOrUpdate(E e);

	/**
	 * Delete an element.
	 * 
	 * @param e
	 *            the element
	 */
	void delete(E e);

	/**
	 * Find elements by criteria.
	 * 
	 * @param criterion
	 *            the criteria for the search
	 * @return the list of found elements
	 */
	List<E> findByCriteria(Criterion criterion);

	/**
	 * Get an element by criteria.
	 * 
	 * @param criterion
	 *            the criteria for the search
	 * @return the element if found
	 */
	E getByCriteria(Criterion criterion);

	/**
	 * List all elements.
	 * 
	 * @return the list of elements
	 */
	List<E> listAll();
}
