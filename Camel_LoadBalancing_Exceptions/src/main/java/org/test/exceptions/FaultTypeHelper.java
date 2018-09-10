package org.test.exceptions;

import java.util.ArrayList;

public class FaultTypeHelper {

	public static Throwable createBusinessFault(int i, ArrayList arrayList) {
		return new BusinessException("BusinessException made from FaultTypeHelper");
	}

	public static Throwable createSystemFault(int i) {
		return new SystemException("SystemException made from FaultTypeHelper");
	}

}
