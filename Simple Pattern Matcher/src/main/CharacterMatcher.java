package main;

public class CharacterMatcher {
	char currCharacter;
	CharacterMatcher nextMatcher;
	String regex;
	
	protected CharacterMatcher() {}
	
	protected CharacterMatcher(String regex) {
		if (regex.length() == 0) 
			throw new IllegalArgumentException("Invalid regex. Empty regex "+
												"will not match any strings!");
		this.regex = regex;
		this.currCharacter = regex.charAt(0);
		this.nextMatcher = this.createMatcher(1);
	}
	
	protected boolean checkCharacter(String toParse) {
		if (toParse.length() == 0) 
			return false;
		else if (currCharacter == toParse.charAt(0))
			return nextMatcher.checkCharacter(toParse.substring(1));
		else	
			return false;
	}
	
	protected CharacterMatcher createMatcher(int nextIndex) {
		String subRegex = this.regex.substring(nextIndex);
		
		if(subRegex.length() == 0)
			return new TerminalMatcher();
		
		switch(subRegex.charAt(0)) {
			case '*':
				return new MultipleWildcardMatcher(subRegex);
			case '.':
				return new SingleWildcardMatcher(subRegex);
			default:
				return new CharacterMatcher(subRegex);
		}
	}
}