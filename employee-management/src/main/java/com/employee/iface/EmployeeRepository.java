package com.employee.iface;

public interface EmployeeRepository<T, P> extends CRUDRepository<T, P> {
	
	/**
	 * Update email by primary Key returns rows updated
	 * @param primaryKey
	 * @param updatedEmail
	 * @return
	 */
	public int updateEmailByPrimaryKey(P primaryKey, String updatedEmail);

	/**
	 * Update Phone Number by primary key returns rows updated
	 * @param primaryKey
	 * @param updatedPhoneNumber
	 * @return
	 */
	public int updatePhoneNumberByPrimaryKey(P primaryKey, Long updatedPhoneNumber);

	/**
	 * Delete by Employee's First Name return rows deleted
	 * @param employeeName
	 * @return
	 */
	public int deleteByFirstName(String employeeName);
}
