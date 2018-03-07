package org.sample;

public class PaginationArguments {

	private int start;
	private int stop;
	
	public PaginationArguments(int start, int stop) {
		this.start = start;
		this.stop = stop;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getStop() {
		return stop;
	}
	public void setStop(int stop) {
		this.stop = stop;
	}
	
	
}
