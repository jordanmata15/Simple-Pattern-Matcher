package main;

public class MultipleWildcardMatcher extends CharacterMatcher {

	protected MultipleWildcardMatcher(String regex){
		super(regex);
	}
	
	protected boolean checkCharacter(String toParse) {
		if (toParse.length() == 0) 
			return false;
		while(toParse.length() > 0) {
			if (nextMatcher.checkCharacter(toParse)) 
				return true;
			else toParse = toParse.substring(1);
		}
		return false;
	}
}
