package main;

/**
 * CharacterMatcher object that are chained together to represent
 * a regular expression.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
public class CharacterMatcher {
	protected char currentCharacter;
	protected CharacterMatcher nextMatcher;
	protected String regex;
	
	protected CharacterMatcher() {}
	
	/**
	 * Constructor for the CharacterMatcher object. Creates a chain of
	 * CharacterMatcher objects for every character in the regular
	 * expression.
	 * @param regex - String representation of the regular expression. Uses 
	 * 					the first character in the string for this Matcher object.
	 * 					Assumes '.' and '*' are the only special characters.
	 */
	protected CharacterMatcher(String regex) {
		this.regex = regex;
		this.currentCharacter = regex.charAt(0);
		this.nextMatcher = this.createMatcher(1);
	}
	
	/**
	 * Validates if the first/next character in toParse is the correct 
	 * character held in this character matcher. Passes the substring 
	 * beginning at index 1 to the next character matcher for it to validate
	 * the rest of the string.
	 * @param toParse - String to be validated.
	 * @return 	True if first character of toParse matches the character held
	 * 			in this matcher and all successive matchers also return true
	 * 			on their validated substrings. False otherwise.
	 */
	protected boolean checkCharacter(String toParse) {
		if (toParse.length() == 0) 
			return false;
		else if (this.currentCharacter == toParse.charAt(0)) {
			String restOfString = toParse.substring(1);
			return this.nextMatcher.checkCharacter(restOfString);
		}
		else	
			return false;
	}
	
	/**
	 * Assists in constructing the appropriate next character matcher object.
	 * If the string is empty, constructs a terminal matcher.
	 * @param nextIndex - The index of where the next character matcher should 
	 * 						start (what character it should represent).
	 * @return	The character matcher object corresponding to the next character.
	 */
	protected CharacterMatcher createMatcher(int nextIndex) {
		String subRegex = this.regex.substring(nextIndex);
		
		if(subRegex.length() == 0)
			return new TerminalMatcher();
		
		switch(subRegex.charAt(0)) {
			case '*':
				return new StarMatcher(subRegex);
			case '.':
				return new DotMatcher(subRegex);
			default:
				return new CharacterMatcher(subRegex);
		}
	}
}