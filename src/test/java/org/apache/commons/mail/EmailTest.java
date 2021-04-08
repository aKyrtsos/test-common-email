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
	
	@Test
	/**
	 * Tests adding emails from an array to bcc. We have four test emails, so the size after the operation should be four as well
	 * @throws Exception
	 */
	public void testAddBcc() throws Exception{
		email.addBcc(TEST_EMAILS);
		
		assertEquals(4, email.getBccAddresses().size());
	}
	
	@Test(expected = EmailException.class)
	/**
	 * Tests to ensure an exception is thrown when a given email list is empty
	 * @throws Exception
	 */
	public void bccEmailException() throws Exception{
		email.addBcc(TEST_EMAILS_EMPTY); 

	}
	
		
	@Test
	/**
	 * Ensures addCc works by comparing the size of all cc addresses after the operation, similar to what was done in bcc
	 * @throws Exception
	 */
	public void testAddCc() throws Exception{
		email.addCc(TEST_EMAILS[0]);
		
		assertEquals(1, email.getCcAddresses().size());
	}
	
	@Test
	/**
	 * Same as above. Adds two heads then ensures the size matches the number of insertions(2)
	 * @throws Exception
	 */
	public void testAddHeader() throws Exception{
		email.addHeader("name", "value");
		email.addHeader("name2", "value2");
		
		assertEquals(2, email.headers.size());
	}
}
