package org.gazelle.thy.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.gazelle.thy.enums.EmployeeClassification;
import org.gazelle.thy.models.fieldnames.EmployeeFieldNames;
import org.gazelle.thy.repositories.EmployeeRepository;
import org.npc.dataaccess.model.BaseModel;

public class Employee extends BaseModel<Employee> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		// unsure of recordID
		//TODO: Dr. P --> The record ID is taken care of in BaseModel.java. Check out
		//  BaseModel#load() - line 68.

		this.firstName = rs.getString(EmployeeFieldNames.FIRST_NAME);
		this.lastName = rs.getString(EmployeeFieldNames.LAST_NAME);
		this.employeeId = rs.getString(EmployeeFieldNames.EMPLOYEE_ID);  //TODO: Dr. P --> See my comment on the field declaration.
		this.active = rs.getBoolean(EmployeeFieldNames.ACTIVE);
		this.classification = EmployeeClassification.map(rs.getInt(EmployeeFieldNames.CLASSIFICATION));

		// unsure of manager
		//TODO: Dr. P --> Just load it like any other field in the ResultSet object. The
		//  only difference being that it can be NULL, per your specification.
		Object managerId = rs.getObject(EmployeeFieldNames.MANAGER);
		this.managerId = ((managerId != null) ? ((UUID) managerId) : (new UUID(0, 0)));

		this.password = rs.getString(EmployeeFieldNames.PASSWORD);
		this.timeStamp = rs.getTimestamp(EmployeeFieldNames.TIME_STAMP).toLocalDateTime();
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		// record.put(EmployeeFieldNames.record_id, this.recordID);
		//TODO: Dr. P --> The record ID is added to the "record" in BaseModel#insertNewRecord() - line 116.

		record.put(EmployeeFieldNames.FIRST_NAME, this.firstName);
		record.put(EmployeeFieldNames.LAST_NAME, this.lastName);
		record.put(EmployeeFieldNames.EMPLOYEE_ID, this.employeeId);  //TODO: Dr. P --> See my comment on the field declaration.
		record.put(EmployeeFieldNames.ACTIVE, this.active);
		record.put(EmployeeFieldNames.CLASSIFICATION, this.classification.getValue());

		if (this.managerId != null) {
			record.put(EmployeeFieldNames.MANAGER, this.managerId);
		}

		record.put(EmployeeFieldNames.PASSWORD, this.password);  //TODO: Dr. P --> You had an extra closing parenthesis.
		record.put(EmployeeFieldNames.TIME_STAMP, Timestamp.valueOf(this.timeStamp));
				
		return record;
	}

	// Employee column: time_stamp (timestamp)
	private LocalDateTime timeStamp;
	public LocalDateTime getTimeStamp() {
		return this.timeStamp;  //TODO: Dr. P --> You left out the semicolon.
	}

	// Employee column: password (varchar(50))
	private String password;
	public String getPassword() {
		return this.password;
	}
	public Employee setPassword(String password) {
		if(!StringUtils.equals(this.password, password)) {  //TODO: Dr. P --> You misspelled the instance variable.
			this.password = password;  //TODO: Dr. P --> You needed to actually make an assignment to "this.password".
			this.propertyChanged(EmployeeFieldNames.PASSWORD);
		}

		return this;
	}

	// Not sure how to do Foreign Key
	// TODO: Dr. P --> For our simple program, it is unnecessary to do anything
	//  out of the ordinary for foreign keys. The foreign key constraint is just for
	//  the database. In other words, we will not do anything in our Employee "model
	//  object" to represent the database constraint. We will just hold the data.
	private UUID managerId;
	public UUID getManagerId() {
		return this.managerId;
	}
	public Employee setManagerId(UUID managerId) {
		if (!this.managerId.equals(managerId)) {
			this.managerId = managerId;
			this.propertyChanged(EmployeeFieldNames.MANAGER);
		}
		
		return this;
	}

	// Employee column: classification (varchar(25))
	//TODO: Dr. P --> More often than not fields similar to employee classification,
	//  transaction status, product type, etc... are stored in the database as integers.
	//  This makes it easier to work with them in code.
	private EmployeeClassification classification;
	public EmployeeClassification getClassification() {
		return this.classification;
	}
	public Employee setClassification(EmployeeClassification classification) {
		if(this.classification != classification) {
			this.classification = classification;  //TODO: Dr. P --> You needed to actually make an assignment to "this.classification".
			this.propertyChanged(EmployeeFieldNames.CLASSIFICATION);
		}

		return this;
	}

	// Employee column: active (boolean)
	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public Employee setActive(boolean active) {
		if(this.active != active){
			this.active = active;
			this.propertyChanged(EmployeeFieldNames.ACTIVE);
		}

		return this;
	}

	//TODO: Dr. P --> I was expecting the teams to get with me about questions for the fields.
	//  Employee ID should actually be defined as a alphanumeric string of length 15. Think of your
	//  UA student ID, but alphanumeric. (So string datatype.) You will need to update your
	//  CREATE TABLE SQL script.
	// Employee column: employee_id (int)
	private String employeeId;
	public String getEmployeeId() {
		return this.employeeId;
	}
	public Employee setEmployeeId(String employeeId){
		if (!StringUtils.equals(this.employeeId, employeeId)) {
			this.employeeId = employeeId;
			this.propertyChanged(EmployeeFieldNames.EMPLOYEE_ID);
		}

		return this;
	}

	// Employee column: last_name (varchar(50))
	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public Employee setLastName(String lastName) {
		if(!StringUtils.equals(this.lastName, lastName)) {
			this.lastName = lastName;  //TODO: Dr. P --> You needed to actually make an assignment to "this.lastName".
			this.propertyChanged(EmployeeFieldNames.LAST_NAME);
		}

		return this;
	}

	// Employee column: first_name (varchar(50))
	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public Employee setFirstName(String firstName) {
		if(!StringUtils.equals(this.firstName, firstName)) {
			this.firstName = firstName;  //TODO: Dr. P --> You needed to actually make an assignment to "this.firstName".
			this.propertyChanged(EmployeeFieldNames.FIRST_NAME);
		}

		return this;
	}
	
	// Not sure how to implement record_id (PK) data type: UUID
	// Dr. P --> If you look at BaseModel.java, this field is already defined for you.
	//  By extending BaseModel.java we get it for free. Look at BaseModel.java - line 18. 

	//TODO: Dr. P --> We haven't yet defined "api.Employee", therefore this code should be removed for the time being.
//	public org.npc.testmodel.api.Employee synchronize(org.npc.testmodel.api.Employee apiEmployee) {
//		this.setCount(apiEmployee.getCount());
//		this.setLookupCode(apiEmployee.getLookupCode());
//		
//		apiEmployee.setCreatedOn(this.createdOn);
//		
//		return apiEmployee;
//	}
	
	public Employee() {
		super(new EmployeeRepository());
		
		//TODO: Dr. P --> You need to set default values for your Employee fields.
		//  These are the Product fields from your copy and paste. These can be removed.
//		this.count = -1;
//		this.lookupCode = StringUtils.EMPTY;
//		this.createdOn = LocalDateTime.now();
		
		this.firstName = StringUtils.EMPTY;
		this.lastName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
		this.active = false;
		this.classification = EmployeeClassification.UNKNOWN;
		this.managerId = new UUID(0, 0);
		this.password = StringUtils.EMPTY;
		this.timeStamp = LocalDateTime.now();
	}
	
	public Employee(UUID id) {
		super(id, new EmployeeRepository());
		
		//TODO: Dr. P --> You need to set default values for your Employee fields.
		//  These are the Product fields from your copy and paste. These can be removed.
//		this.count = -1;
//		this.lookupCode = StringUtils.EMPTY;
//		this.createdOn = LocalDateTime.now();
		
		this.firstName = StringUtils.EMPTY;
		this.lastName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
		this.active = false;
		this.classification = EmployeeClassification.UNKNOWN;
		this.managerId = new UUID(0, 0);
		this.password = StringUtils.EMPTY;
		this.timeStamp = LocalDateTime.now();
	}

	//TODO: Dr. P --> We haven't yet defined "api.Employee", therefore this code should be removed for the time being.
//	public Employee(org.npc.testmodel.api.Employee apiProduct) {
//		super(apiProduct.getId(), new ProductRepository());
//		
//		this.count = apiProduct.getCount();
//		this.lookupCode = apiProduct.getLookupCode();
//
//		this.createdOn = LocalDateTime.now();
//	}
}