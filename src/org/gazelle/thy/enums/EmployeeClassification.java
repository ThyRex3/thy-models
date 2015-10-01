package org.gazelle.thy.enums;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeClassification {
	UNKNOWN(0),
	CASHIER(1),
	SHIFT_MANAGER(2),
	GENERAL_MANAGER(3);
	
	private int value;
	public int getValue() {
		return value;
	}

	private EmployeeClassification(int value) {
		this.value = value;
	}
	
	private static Map<Integer, EmployeeClassification> valueMap = null;

	public static EmployeeClassification map(int key) {
		if (valueMap == null) {
			valueMap = new HashMap<Integer, EmployeeClassification>();

			for (EmployeeClassification status : EmployeeClassification.values()) {
				valueMap.put(status.getValue(), status);
			}
		}
		
		return (valueMap.containsKey(key) ? valueMap.get(key) : EmployeeClassification.UNKNOWN);
	}
}
