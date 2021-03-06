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
	
	@Test
	/**
	 * Test to ensure addheader can correctly catch null names
	 */
	public void testAddHeaderNullName() {
		boolean exceptionCaught = false;
		
		try {
			email.addHeader("", "value");
		} catch(Exception e){
			exceptionCaught = true;
			assertFalse("Exception occured as expected", false);
		}
		
		if(!exceptionCaught) {
			assertFalse("Exception did not occur as expected", true);
		}
	}
	
		
	@Test
	/**
	 * Test to ensure addheader can correctly catch null values
	 */
	public void testAddHeaderNullValue() {
		boolean exceptionCaught = false;
		
		try {
			email.addHeader("name", "");
		} catch(Exception e){
			exceptionCaught = true;
			assertFalse("Exception occured as expected", false);
		}
		
		if(!exceptionCaught) {
			assertFalse("Exception did not occur as expected", true);
		}
	}
	
		
	@Test
	/**
	 * Tests gethostname by first assigning a hostname
	 * @throws EmailException
	 */
	public void testGetHostName() throws EmailException {
		email.setHostName("hostname");
		assertEquals("hostname", email.getHostName());
	}
	
		
	@Test
	/**
	 * Ensures gethostname operates correctly without assigning a hostname prior
	 * @throws EmailException
	 */
	public void testGetHostNameNull() throws EmailException {
		assertEquals(null, email.getHostName());
	}
	
		
	@Test
	/**
	 * Tests the getSentDate function by supplying a new time based on the current time and confirms by comparing the getSendDate value
	 * @throws EmailException
	 */
	public void testSentDate() throws EmailException {
		Date currentTime = new Date();
		email.setSentDate(currentTime);
		assertEquals(currentTime, email.getSentDate());
	}
	
		
	@Test
	public void testSentDateEmpty() throws EmailException {
		assertEquals(new Date(), email.getSentDate());
	}
	
	
	
	@Test
	public void testGetSocketConnectionTimeout() throws EmailException {		
		assertEquals(60000, email.getSocketConnectionTimeout());
	}
	
		
	@Test
	public void testSetFrom() throws EmailException {		
		email.setFrom(TEST_EMAILS[2]);
		
		assertEquals(TEST_EMAILS[2], email.getFromAddress().toString());
	}
	
	
	
	@Test
	public void testBuildMimeMessage() throws EmailException {
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}
	
	@Test
	public void testBuildMimeMessageSubject() throws EmailException {
		email.setSubject("Important Test");
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}
	
	@Test
	public void testBuildMimeMessageSubjectCharset() throws EmailException {
		email.setSubject("Important Test");
		email.setCharset("US-ASCII");
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}
	
	@Test
	public void testBuildMimeMessageContent() throws EmailException {
		email.setContent(TEST_EMAILS, "list");
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}
	
	@Test
	public void testBuildMimeMessageTo() throws EmailException {
		email.addTo(TEST_EMAILS[0]);
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}

	@Test
	public void testBuildMimeMessageBcc() throws EmailException {
		email.addBcc(TEST_EMAILS[1]);
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}
	
	@Test
	public void testBuildMimeMessageReply() throws EmailException {
		email.addReplyTo(TEST_EMAILS[2]);
		email.setHostName("hostname");
		email.setFrom("fromAddress@mail.com");
		email.addCc(TEST_EMAILS[0]);
		email.buildMimeMessage();

		assertNotEquals(email.message, null);
	}
}
