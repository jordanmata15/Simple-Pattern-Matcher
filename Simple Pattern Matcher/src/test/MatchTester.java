package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Match;

class MatchTester {

	@Test
	void test() {
		Match matchA = new Match("c.t");
		assertEquals(0, matchA.findFirstInd("cat"));
		assertEquals(4, matchA.findFirstInd("cacacat"));
		assertEquals(4, matchA.findFirstInd("cacac.t"));
		assertEquals(0, matchA.findFirstInd("cutact"));
		assertEquals(-1, matchA.findFirstInd("cacact"));
		
		Match matchB  = new Match("c*t");
		// regex at the beginning
		assertEquals(0, matchB.findFirstInd("cat"));
		assertEquals(0, matchB.findFirstInd("ct"));
		assertEquals(0, matchB.findFirstInd("caadfsdcsdt"));
		assertEquals(0, matchB.findFirstInd("caatcaat"));
		// regex in the middle
		assertEquals(2, matchB.findFirstInd("abcatat"));
		assertEquals(2, matchB.findFirstInd("abctat"));
		assertEquals(2, matchB.findFirstInd("abcaasdsftat"));
		assertEquals(2, matchB.findFirstInd("abcaadtcacat"));
		// regex at the end
		assertEquals(5, matchB.findFirstInd("ababact"));
		assertEquals(5, matchB.findFirstInd("ababacat"));
		assertEquals(5, matchB.findFirstInd("ababacaasfdst"));
		assertEquals(5, matchB.findFirstInd("ababacatcaaat"));
	}

}
