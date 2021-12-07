package main;

/**
 * TerminalMatcher object used to indicate the end of the regular 
 * expression.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
public class TerminalMatcher extends CharacterMatcher {

	protected TerminalMatcher() {}
	
	/**
	 * Overridden method designed to act as an indicator that the string
	 * matches completely. If the matching chain reaches this matcher,
	 * then all other conditions of the regular expression hold/match.
	 * @param toMatch - String to be validated.
	 * @return	True.
	 */
	@Override
	protected boolean checkCharacter(String toMatch) {
		return true;
	}
}