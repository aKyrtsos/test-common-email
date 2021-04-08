package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EmailTest {
	private static final String[] TEST_EMAILS = {"abc@de.com", "jake@yahoo.com", "emma@il.com", "format@example.net"};
	private static final String[] TEST_EMAILS_EMPTY = {};

	private EmailConcrete email;

	@Before
	/**
	 * Creates the email object the tests will run on
	 * @throws Exception
	 */
	public void setUpEmailTest() throws Exception{
		email = new EmailConcrete();
	}

	@After
	/**
	 * Set email to null to potentially aid in garbage collection
	 */
	public void tearDownEmailTest(){
		email = null;
	}
}
