package main;

/**
 * HeadMatcher object used to represent a the start of a regular 
 * expression matcher chain.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
public class HeadMatcher extends CharacterMatcher {
	
	/*
	 * HeadMatcher constructor to begin creating the chain of CharacterMatchers.
	 * HeadMatcher doesn't represent a character in the regular expression, so 
	 * the next matcher starts at index 0.
	 * @param regex - String representation of the regular expression. Used to
	 * 					create the chain of CharacterMatchers.
	 */
	protected HeadMatcher(String regex) {
		this.regex = regex;
		this.nextMatcher = this.createMatcher(0);
	}
	
	/**
	 * Begins the validation process of the passed in string to our regular
	 * expression. Validates the check starting point incrementally.
	 * @param toMatch - String to be validated.
	 * @return	The integer index of the first matching substring. If no 
	 * 			matching substring found, returns -1.
	 */
	protected int validateString(String toMatch) {
		int startIndex = 0;
		while(startIndex < toMatch.length()) {
			String nextSubstring = toMatch.substring(startIndex);
			if (this.nextMatcher.checkCharacter(nextSubstring)) {
				return startIndex;
			}
			else
				startIndex++;
		}
		return -1;
	}
	
	/**
	 * Validates if the passed in string matches the regular expression
	 * represented by the chain of CharacterMatchers.
	 * @param toMatch - String to be validated.
	 * @return	True if all successive matchers return true on their 
	 * 			validated substrings. False otherwise.
	 */
	@Override
	protected boolean checkCharacter(String toMatch) {
		return this.nextMatcher.checkCharacter(toMatch);
	}
}