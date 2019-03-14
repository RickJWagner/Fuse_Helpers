package org.sample.TransformerExample;

public class Book {
	
	private String title;
	private String authorfname;
	private String authorlname;
	
	public Book(String title, String fname, String lname) {
		this.title = title;
		this.authorfname = fname;
		this.authorlname = lname;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorfname() {
		return authorfname;
	}
	public void setAuthorfname(String authorfname) {
		this.authorfname = authorfname;
	}
	public String getAuthorlname() {
		return authorlname;
	}
	public void setAuthorlname(String authorlname) {
		this.authorlname = authorlname;
	}

}
