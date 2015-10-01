package org.gazelle.thy.repositories;

import org.gazelle.thy.models.Employee;
import org.gazelle.thy.repositories.interfaces.EmployeeRepositoryInterface;
import org.npc.dataaccess.repository.BaseRepository;
import org.npc.dataaccess.repository.DatabaseTable;

public class EmployeeRepository extends BaseRepository<Employee> implements EmployeeRepositoryInterface {
	@Override
	public Employee createOne() {
		return new Employee();
	}
	
	public EmployeeRepository() {
		//TODO: Dr. P --> Need to update the DatabaseTable enum to include the Employee table name.
		super(DatabaseTable.PRODUCT);
	}
}