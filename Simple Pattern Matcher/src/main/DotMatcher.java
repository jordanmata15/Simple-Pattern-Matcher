package main;

/**
 * DotMatcher object used to represent a '.' character in the 
 * regular expression.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
public class DotMatcher extends CharacterMatcher {

	/**
	 * Use parent constructor to generate correct next CharacterMatcher type.
	 * @param regex - String representation of the regular expression. Uses 
	 * 					the first character in the string for this Matcher object.
	 * 					Assumes '.' and '*' are the only special characters.
	 */
	protected DotMatcher(String regex){
		super(regex);
	}

	/**
	 * Validates that this character is any character. Passes the substring 
	 * beginning at index 1 to the next character matcher for it to validate
	 * the rest of the string.
	 * @param toMatch - String to be validated.
	 * @return 	True if first character of toParse matches the character held
	 * 			in this matcher and all successive matchers also return true
	 * 			on their validated substrings. False otherwise.
	 */
	@Override
	protected boolean checkCharacter(String toMatch) {
		if (toMatch.length() == 0) 
			return false;
		else {
			String stringSuffix = toMatch.substring(1);
			return this.nextMatcher.checkCharacter(stringSuffix);
		}
	}
}