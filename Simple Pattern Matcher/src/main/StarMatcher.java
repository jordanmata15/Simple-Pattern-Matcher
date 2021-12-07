package main;

/**
 * StarMatcher object used to represent a '*' character in the 
 * regular expression.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
public class StarMatcher extends CharacterMatcher {

	/**
	 * Use parent constructor to generate correct next CharacterMatcher type.
	 * @param regex - String representation of the regular expression. Uses 
	 * 					the first character in the string for this Matcher object.
	 * 					Assumes '.' and '*' are the only special characters.
	 */
	protected StarMatcher(String regex){
		super(regex);
	}
	
	/**
	 * Validates that this character any number of any character (or none at all) to 
	 * emulate the behavior of the '*' in regular expressions. Passes the substring 
	 * beginning at index 0,1,2,...,(length of the string) to the next character 
	 * matcher for it to validate the rest of the string. This allows it to check
	 * if any of them match.
	 * @param toMatch - String to be validated.
	 * @return 	True if first character of toParse matches the character held
	 * 			in this matcher and all successive matchers also return true
	 * 			on their validated substrings. False otherwise.
	 */
	protected boolean checkCharacter(String toMatch) {
		if (toMatch.length() == 0)
			return nextMatcher.checkCharacter(toMatch);
		while (toMatch.length() > 0) {
			if (nextMatcher.checkCharacter(toMatch))
				return true;
			else
				toMatch = toMatch.substring(1);
		}
		return false;
	}
}