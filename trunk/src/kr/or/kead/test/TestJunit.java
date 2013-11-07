package kr.or.kead.test;

import junit.framework.TestCase;

import org.junit.Test;

public class TestJunit extends TestCase{
	String message;
	
	@Test
	public void testSalutationMessage() {
		message = "TestJumint1 실행";
		System.out.println(message);
	}

}
