package main;

/**
 * Match object used to create a regular expression and validate
 * strings against it.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
public class Match {
	private HeadMatcher regexHead;
	
	/**
	 * Constructor for the regular expression. 
	 * @param regex - String representation of the regular expression.
	 */
	public Match(String regex) {
		this.regexHead = new HeadMatcher(regex);
	}
	
	/**
	 * Used to find the first index where a string matches the 
	 * regular expression represented by this Match object.
	 * @param toParse - String to validate against.
	 * @return	True if the passed in string can be represented
	 * 			by the regular expression (assuming only '.' and
	 * 			'*' as special characters).
	 */
	public int findFirstInd(String toParse) {
		return this.regexHead.validateString(toParse);
	}
}