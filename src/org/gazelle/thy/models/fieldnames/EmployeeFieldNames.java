package org.gazelle.thy.models.fieldnames;

import org.npc.dataaccess.model.BaseFieldNames;

public class EmployeeFieldNames extends BaseFieldNames {
	//TODO: Dr. P --> These string literals need to match the column names in your CREATE TABLE SQL.
	public static final String TIME_STAMP = "timestamp";
	public static final String PASSWORD = "password";
	public static final String MANAGER = "manager";
	public static final String CLASSIFICATION = "classfication";
	public static final String ACTIVE = "active";
	public static final String EMPLOYEE_ID = "employeedid";
	public static final String LAST_NAME = "lastname";
	public static final String FIRST_NAME = "firstname";
	//TODO: Dr. P --> The record ID field/column name is already defined in BaseFieldNames.
	//  If you look is expecting the record ID column to be named "id". This constant
	//  should be removed.
//	public static final String RECORD_ID = "recordid";
}
