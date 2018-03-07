package org.sample;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class PaginatedAlphabetGetter {
	
	String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	public static void main(String [] args) throws Exception {
		PaginatedAlphabetGetter atest = new PaginatedAlphabetGetter();
		System.out.println(atest.getAlphabetPortion(new PaginationArguments(0,26)));	
	}
	
	@WebMethod
	public List<String> getAlphabetPortion(PaginationArguments args) throws Exception {
		if (args.getStart() < 0 || args.getStop() < 0 || (args.getStart() > args.getStop()) || args.getStop() > 26) {
			throw new Exception("Invalid arguments!");
		}
		
		List <String> ret = new ArrayList<String>();
		
		for (int idx = args.getStart();idx < args.getStop();) {
			String aRet = new String(ALPHABET.substring(idx, idx + 1));
			ret.add(aRet);
			idx++;
		}
		return ret;
	}

}
