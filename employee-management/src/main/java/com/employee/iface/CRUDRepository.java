package com.employee.iface;

import java.util.*;

public interface CRUDRepository<T,P> {

	/**
	 * Inserts employee details to database
	 * @param obj
	 * @return
	 */
	public boolean save(T obj);
	
	/**
	 * Function shows all entries in database
	 * @return
	 */
	public Collection<T> findAll();
	
	/**
	 * Deletes an entry from database based on primary key
	 * @param obj
	 * @return
	 */
	public int deleteByPrimaryKey(P obj);
}
