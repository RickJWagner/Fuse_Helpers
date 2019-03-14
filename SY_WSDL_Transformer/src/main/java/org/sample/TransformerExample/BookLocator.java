package org.sample.TransformerExample;

import java.util.List;

public interface BookLocator {
	
	public List<Book> findBooksByAuthorLastName(String authorLastName);

}
