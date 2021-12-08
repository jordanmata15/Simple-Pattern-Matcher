package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Match;

/**
 * Testing for the Match object.
 * @author Jordan Mata
 * @date Dec 1, 2021
 *
 */
class MatchTester {
	
	@Test
	void testSimpleCharacter() {
		Match letterMatcher = new Match("cat");
		assertEquals(0, letterMatcher.findFirstInd("cat"));
		assertEquals(0, letterMatcher.findFirstInd("catacaa"));
		assertEquals(4, letterMatcher.findFirstInd("cacacat"));
		assertEquals(2, letterMatcher.findFirstInd("c.cataac"));
		assertEquals(4, letterMatcher.findFirstInd("cutacat"));
		assertEquals(4, letterMatcher.findFirstInd("cac*cat"));
		assertEquals(-1, letterMatcher.findFirstInd("cacc*t"));
		assertEquals(-1, letterMatcher.findFirstInd("cacac.t"));
		assertEquals(-1, letterMatcher.findFirstInd("cacact"));
		assertEquals(-1, letterMatcher.findFirstInd("*"));
		assertEquals(-1, letterMatcher.findFirstInd("."));
		assertEquals(-1, letterMatcher.findFirstInd(""));
		
		Match numberMatcher = new Match("1234");
		assertEquals(0, numberMatcher.findFirstInd("1234csdt"));
		assertEquals(3, numberMatcher.findFirstInd("caa1234"));
		assertEquals(3, numberMatcher.findFirstInd("caa1234csdt"));
		assertEquals(1, numberMatcher.findFirstInd(" 1234 "));
		assertEquals(-1, numberMatcher.findFirstInd("caa123*4csdt"));
		assertEquals(-1, numberMatcher.findFirstInd("caa123.4csdt"));
		assertEquals(-1, numberMatcher.findFirstInd("123 4"));
		assertEquals(-1, numberMatcher.findFirstInd("123"));
		assertEquals(-1, numberMatcher.findFirstInd("*"));
		assertEquals(-1, numberMatcher.findFirstInd("."));
		assertEquals(-1, numberMatcher.findFirstInd(""));
		
		Match symbolMatcher = new Match("$'#!");
		assertEquals(0, symbolMatcher.findFirstInd("$'#!"));
		assertEquals(2, symbolMatcher.findFirstInd(".*$'#!-"));
		assertEquals(3, symbolMatcher.findFirstInd("caa$'#!csdt"));
		assertEquals(1, symbolMatcher.findFirstInd(" $'#! "));
		assertEquals(-1, symbolMatcher.findFirstInd("caa$'#*!csdt"));
		assertEquals(-1, symbolMatcher.findFirstInd("caa$'#.!csdt"));
		assertEquals(-1, symbolMatcher.findFirstInd("$'# !"));
		assertEquals(-1, symbolMatcher.findFirstInd("$'#.!"));
		assertEquals(-1, symbolMatcher.findFirstInd("*"));
		assertEquals(-1, symbolMatcher.findFirstInd("."));
		assertEquals(-1, symbolMatcher.findFirstInd(""));
		
		Match mixedMatcher = new Match("# of '!' is 3");
		assertEquals(0, mixedMatcher.findFirstInd("# of '!' is 3"));
		assertEquals(2, mixedMatcher.findFirstInd(".*# of '!' is 32"));
		assertEquals(5, mixedMatcher.findFirstInd("some # of '!' is 3 -"));
		assertEquals(1, mixedMatcher.findFirstInd(" # of '!' is 3 "));
		assertEquals(-1, mixedMatcher.findFirstInd("3c-# of '!' is*3-a3"));
		assertEquals(-1, mixedMatcher.findFirstInd("caa# of '!' is.3 -sd"));
		assertEquals(-1, mixedMatcher.findFirstInd("# of '!' is.3"));
		assertEquals(-1, mixedMatcher.findFirstInd("# of '!' is  3"));
		assertEquals(-1, mixedMatcher.findFirstInd("*"));
		assertEquals(-1, mixedMatcher.findFirstInd("."));
		assertEquals(-1, mixedMatcher.findFirstInd(""));
	}
		
	@Test
	void testDotWildCard() {
		Match letterMatcher = new Match("c.t");
		assertEquals(0, letterMatcher.findFirstInd("cat"));
		assertEquals(0, letterMatcher.findFirstInd("cbt"));
		assertEquals(0, letterMatcher.findFirstInd("c.t"));
		assertEquals(0, letterMatcher.findFirstInd("c-t"));
		assertEquals(0, letterMatcher.findFirstInd("c*t"));
		assertEquals(0, letterMatcher.findFirstInd("cutcat"));
		assertEquals(0, letterMatcher.findFirstInd("c-tacaa"));
		assertEquals(4, letterMatcher.findFirstInd("cacac.t"));
		assertEquals(2, letterMatcher.findFirstInd("c*c-taac"));
		assertEquals(0, letterMatcher.findFirstInd("cutact"));
		assertEquals(4, letterMatcher.findFirstInd("cac*cat"));
		assertEquals(3, letterMatcher.findFirstInd("cacc*t"));
		assertEquals(4, letterMatcher.findFirstInd("cacac.t"));
		assertEquals(-1, letterMatcher.findFirstInd("cacact"));
		assertEquals(-1, letterMatcher.findFirstInd("ct"));
		assertEquals(-1, letterMatcher.findFirstInd("*"));
		assertEquals(-1, letterMatcher.findFirstInd("."));
		assertEquals(-1, letterMatcher.findFirstInd(""));
		
		Match dotOnlyMatcher = new Match(".");
		assertEquals(0, dotOnlyMatcher.findFirstInd("c"));
		assertEquals(0, dotOnlyMatcher.findFirstInd("."));
		assertEquals(0, dotOnlyMatcher.findFirstInd("*"));
		assertEquals(0, dotOnlyMatcher.findFirstInd(".."));
		assertEquals(0, dotOnlyMatcher.findFirstInd("Any 1 possible string!"));
		assertEquals(-1, dotOnlyMatcher.findFirstInd(""));
		
		Match dotMatcher = new Match(".a.b");
		assertEquals(0, dotMatcher.findFirstInd("aabb"));
		assertEquals(0, dotMatcher.findFirstInd("baab"));
		assertEquals(0, dotMatcher.findFirstInd("carb"));
		assertEquals(0, dotMatcher.findFirstInd("barb"));
		assertEquals(-1, dotMatcher.findFirstInd("ab"));
		assertEquals(-1, dotMatcher.findFirstInd("bbaa"));
		assertEquals(-1, dotMatcher.findFirstInd("aaba"));
		assertEquals(-1, dotMatcher.findFirstInd("aab"));
		
		Match mixedMatcher = new Match("# of '.' is 3");
		assertEquals(0, mixedMatcher.findFirstInd("# of '!' is 3"));
		assertEquals(2, mixedMatcher.findFirstInd(".*# of 't' is 32"));
		assertEquals(5, mixedMatcher.findFirstInd("some # of '-' is 3 -"));
		assertEquals(1, mixedMatcher.findFirstInd(" # of '.' is 3 "));
		assertEquals(-1, mixedMatcher.findFirstInd("3c-# of '.' is*3-a3"));
		assertEquals(-1, mixedMatcher.findFirstInd("caa# of '' is.3 -sd"));
		assertEquals(-1, mixedMatcher.findFirstInd("# of '!' is.3"));
		assertEquals(-1, mixedMatcher.findFirstInd("# of '!' is  3"));
		assertEquals(-1, mixedMatcher.findFirstInd("*"));
		assertEquals(-1, mixedMatcher.findFirstInd("."));
		assertEquals(-1, mixedMatcher.findFirstInd(""));
	}
	
	@Test
	void testStarWildCard() {
		Match letterMatcher = new Match("c*t");
		assertEquals(0, letterMatcher.findFirstInd("ct"));
		assertEquals(0, letterMatcher.findFirstInd("cat"));
		assertEquals(0, letterMatcher.findFirstInd("cbt"));
		assertEquals(0, letterMatcher.findFirstInd("c.t"));
		assertEquals(0, letterMatcher.findFirstInd("c-t"));
		assertEquals(0, letterMatcher.findFirstInd("c*t"));
		assertEquals(0, letterMatcher.findFirstInd("cutcat"));
		assertEquals(0, letterMatcher.findFirstInd("c-tacaa"));
		assertEquals(0, letterMatcher.findFirstInd("cacac.t"));
		assertEquals(0, letterMatcher.findFirstInd("c*c-taac"));
		assertEquals(0, letterMatcher.findFirstInd("cutact"));
		assertEquals(1, letterMatcher.findFirstInd("ac*cat"));
		assertEquals(2, letterMatcher.findFirstInd("bacc*t"));
		assertEquals(2, letterMatcher.findFirstInd("bacac.t"));
		assertEquals(0, letterMatcher.findFirstInd("cacact"));
		assertEquals(-1, letterMatcher.findFirstInd("aaaaat"));
		assertEquals(-1, letterMatcher.findFirstInd("caaaaaa"));
		assertEquals(-1, letterMatcher.findFirstInd("*"));
		assertEquals(-1, letterMatcher.findFirstInd("."));
		assertEquals(-1, letterMatcher.findFirstInd(""));

		Match starOnlyMatcher = new Match("*");
		assertEquals(0, starOnlyMatcher.findFirstInd("c"));
		assertEquals(0, starOnlyMatcher.findFirstInd("."));
		assertEquals(0, starOnlyMatcher.findFirstInd("*"));
		assertEquals(0, starOnlyMatcher.findFirstInd(".."));
		assertEquals(0, starOnlyMatcher.findFirstInd("Any 1 possible string!"));
		assertEquals(-1, starOnlyMatcher.findFirstInd(""));

		Match starMatcher = new Match("*a*b");
		assertEquals(0, starMatcher.findFirstInd("aabb"));
		assertEquals(0, starMatcher.findFirstInd("baab"));
		assertEquals(0, starMatcher.findFirstInd("carb"));
		assertEquals(0, starMatcher.findFirstInd("barb"));
		assertEquals(0, starMatcher.findFirstInd("ab"));
		assertEquals(0, starMatcher.findFirstInd("aaba"));
		assertEquals(0, starMatcher.findFirstInd("aab"));
		assertEquals(-1, starMatcher.findFirstInd("bbaa"));
		
		Match mixedMatcher = new Match("# of '*' is 3");
		assertEquals(0, mixedMatcher.findFirstInd("# of '' is 3"));
		assertEquals(0, mixedMatcher.findFirstInd("# of '!' is 3"));
		assertEquals(0, mixedMatcher.findFirstInd("# of '!!' is 3"));
		assertEquals(2, mixedMatcher.findFirstInd(".*# of 'this' is 3"));
		assertEquals(5, mixedMatcher.findFirstInd("some # of '-' is 3 -"));
		assertEquals(1, mixedMatcher.findFirstInd(" # of '' is 3 "));
		assertEquals(-1, mixedMatcher.findFirstInd("3c-# of '.' is*3-a3"));
		assertEquals(-1, mixedMatcher.findFirstInd("caa# of '' is.3 -sd"));
		assertEquals(-1, mixedMatcher.findFirstInd("# of '!' is.3"));
		assertEquals(-1, mixedMatcher.findFirstInd("# of '!' is  3"));
		assertEquals(-1, mixedMatcher.findFirstInd("*"));
		assertEquals(-1, mixedMatcher.findFirstInd("."));
		assertEquals(-1, mixedMatcher.findFirstInd(""));
	}
	
	@Test
	void testDotAndStar() {
		Match mixedMatcher = new Match("..... of '*' is *");
		assertEquals(0, mixedMatcher.findFirstInd("count of '' is 0"));
		assertEquals(0, mixedMatcher.findFirstInd("index of 'n' is 3"));
		assertEquals(0, mixedMatcher.findFirstInd("sum   of '1024 + 1024' is 2048"));
		assertEquals(0, mixedMatcher.findFirstInd("Name  of 'the president' is Joseph Biden"));
		assertEquals(12, mixedMatcher.findFirstInd("The country south of 'the United States' is Mexico."));
		
		Match oneCharOrMoreMatcher = new Match("*.");
		assertEquals(0, oneCharOrMoreMatcher.findFirstInd("a"));
		assertEquals(-1, oneCharOrMoreMatcher.findFirstInd(""));
	}
}