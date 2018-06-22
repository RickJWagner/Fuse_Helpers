package com.example.switchyard.sy_producers;

import javax.naming.NamingException;

public interface MsgAppender {
	
	String appendGreeting(String msg) throws NamingException ;

}
