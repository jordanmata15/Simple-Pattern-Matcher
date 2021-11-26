package main;

public class TerminalMatcher extends CharacterMatcher {

	protected TerminalMatcher() {}
	
	@Override
	protected boolean checkCharacter(String toParse) {
		return true;
	}
}
