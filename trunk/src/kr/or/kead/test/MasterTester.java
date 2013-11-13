package kr.or.kead.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import kr.or.kead.ui.LoginJoin;

public class MasterTester extends TestCase {

	public static void main(String[] args) {
//		junit.textui.TestRunner.run(suite());

	}
	
	public static Test suite() {
		TestSuite result = new TestSuite();
		result.addTest(new TestSuite(LoginJoin.class));
		return result;
	}

}
