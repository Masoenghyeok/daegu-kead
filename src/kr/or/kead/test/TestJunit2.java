package kr.or.kead.test;
import junit.framework.TestCase;

import org.junit.Test;

public class TestJunit2 extends TestCase{
	String message;
	
	@Test
	public void testSalutationMessage() {
		message = "TestJunit2 실행";
		System.out.println(message);
	}
}
