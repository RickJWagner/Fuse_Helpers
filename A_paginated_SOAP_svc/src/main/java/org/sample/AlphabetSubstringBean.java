package org.sample;

import java.util.ArrayList;
import java.util.List;

import org.switchyard.component.bean.Service;

@Service(AlphabetSubstring.class)
public class AlphabetSubstringBean implements AlphabetSubstring {
	
	String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
		
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
