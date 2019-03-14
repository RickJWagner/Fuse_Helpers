package org.sample.TransformerExample;

import java.util.ArrayList;
import java.util.List;

import org.switchyard.component.bean.Service;

@Service(BookLocator.class)
public class BookLocatorBean implements BookLocator {

	private List <Book> books = null;
	
	@Override
	public List<Book> findBooksByAuthorLastName(String authorLastName) {
		
		if (null == books) {
			initBooks();
		}
		
		List<Book> ret = new ArrayList<Book>();
		for (Book book: books) {
			if (book.getAuthorlname().trim().equals(authorLastName.trim())) {
				ret.add(book);
			}
		}
		return ret;
	}

	private void initBooks() {
		books = new ArrayList<Book>();
		Book b1 = new Book("A Book","John", "Doe");
		Book b2 = new Book("A Second Book", "Jane", "Doe");
		Book b3 = new Book("A Second Book, Volume 2", "Jane", "Doe");
		Book b4 = new Book("The Grinch", "Dr.", "Suess");
		books.add(b1);
		books.add(b2);
		books.add(b3);
		books.add(b4);
	}
	
	

}
