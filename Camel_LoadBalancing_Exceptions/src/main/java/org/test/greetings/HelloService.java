package org.test.greetings;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.test.exceptions.BusinessException;
import org.test.exceptions.SystemException;

@WebService(name = "sayHello")
public interface HelloService {
    @WebMethod(operationName = "sayHello", action = "urn:greet")
    String greet(@WebParam(name = "message") String message, @WebParam(name = "name") String name) throws BusinessException, SystemException, Exception;
}