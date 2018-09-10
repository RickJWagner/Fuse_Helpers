package org.test.greetings2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.test.exceptions.BusinessException;
import org.test.exceptions.SystemException;

@WebService(name = "sayHello2")
public interface HelloService2 {
    @WebMethod(operationName = "sayHello2", action = "urn:greet2")
    String greet2(@WebParam(name = "message") String message, @WebParam(name = "name") String name) throws BusinessException, SystemException, Exception;
}