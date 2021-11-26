package main;

public class SingleWildcardMatcher extends CharacterMatcher {

	protected SingleWildcardMatcher(String regex){
		super(regex);
	}

	@Override
	protected boolean checkCharacter(String toParse) {
		if (toParse.length() == 0) 
			return false;
		else
			return nextMatcher.checkCharacter(toParse.substring(1));
	}
}
