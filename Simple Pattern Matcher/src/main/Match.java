package main;

public class Match {
	String regex;
	HeadMatcher regexHead;
	
	public Match(String regex) {
		this.regex = regex;
		this.regexHead = new HeadMatcher(regex);
	}
	
	public int findFirstInd(String toParse) {
		return this.regexHead.validateString(toParse);
	}
}