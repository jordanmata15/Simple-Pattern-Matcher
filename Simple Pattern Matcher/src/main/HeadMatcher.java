package main;

public class HeadMatcher extends CharacterMatcher {
	
	protected HeadMatcher(String regex) {
		this.regex = regex;
		this.nextMatcher = this.createMatcher(0);
	}
	
	protected int validateString(String toParse) {
		int startIndex = 0;
		while(startIndex < toParse.length()) {
			if (this.nextMatcher.checkCharacter(toParse.substring(startIndex))) {
				return startIndex;
			}
			else
				startIndex++;
		}
		return -1;
	}
	
	@Override
	protected boolean checkCharacter(String toParse) {
		return this.nextMatcher.checkCharacter(toParse);
	}
}