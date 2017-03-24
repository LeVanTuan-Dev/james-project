/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.transport.matchers;

import org.apache.mailet.MailAddress;
import org.apache.mailet.base.test.FakeMail;
import org.apache.mailet.base.test.FakeMatcherConfig;
import org.apache.james.transport.matchers.TooMuchRecipients;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.ImmutableList;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import javax.mail.MessagingException;

public class TooMuchRecipientsTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private TooMuchRecipients testee;

	@Before
	public void setUp() {
		testee = new TooMuchRecipients();
	}

	@Test(expected = MessagingException.class)
	public void initShouldThrowOnAbsentCondition() throws MessagingException {
		testee.init(FakeMatcherConfig.builder().matcherName("matcherName").build());
	}

	@Test(expected = NumberFormatException.class)
	public void initShouldThrowOnInvalidCondition() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("a").matcherName("matcherName").build());
	}

	@Test(expected = NumberFormatException.class)
	public void initShouldThrowOnEmptyCondition() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("").matcherName("matcherName").build());
	}

	@Test(expected = MessagingException.class)
	public void initShouldThrowOnZeroCondition() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("0").matcherName("matcherName").build());
	}

	@Test(expected = MessagingException.class)
	public void initShouldThrowOnNegativeCondition() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("-20").matcherName("matcherName").build());
	}

	@Test
	public void matchShouldReturnNoRecipientWhenMailHaveNoRecipient() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("3").matcherName("matcherName").build());
		Collection<MailAddress> listMailAddress = testee.match(FakeMail.builder().build());
		assertThat(listMailAddress).isEmpty();
	}

	@Test
	public void matchShouldAcceptMailsUnderLimit() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("3").matcherName("matcherName").build());
		Collection<MailAddress> listMailAddress = testee
				.match(FakeMail.builder().recipient(new MailAddress("tuan@gmail.com")).build());
		assertThat(listMailAddress).isEmpty();
	}

	@Test
	public void matchShouldAcceptMailsAtLimit() throws Exception {
		testee.init(FakeMatcherConfig.builder().condition("3").matcherName("matcherName").build());
		
		ImmutableList<MailAddress> recipients = ImmutableList.of(new MailAddress("tuan@gmail.com"),
				new MailAddress("phuc@gmail.com"), 
				new MailAddress("da@gmail.com"));
		
		Collection<MailAddress> listMailAddress = testee
				.match(FakeMail.builder().recipients(recipients).build());
		assertThat(listMailAddress).isEmpty();
	}

	@Test
	public void matchShouldRejectMailsOverLimit() throws Exception {
		
		testee.init(FakeMatcherConfig.builder().condition("3").matcherName("matcherName").build());
		
		ImmutableList<MailAddress> recipients = ImmutableList.of(new MailAddress("tuan@gmail.com"),
				new MailAddress("phuc@gmail.com"), 
				new MailAddress("da@gmail.com"),
				new MailAddress("cuong@gmail.com"));
		
		Collection<MailAddress> listMailAddress = testee
				.match(FakeMail.builder().recipients(
						recipients).build());
		
		assertThat(listMailAddress).isEqualTo(listMailAddress);
	}

}
