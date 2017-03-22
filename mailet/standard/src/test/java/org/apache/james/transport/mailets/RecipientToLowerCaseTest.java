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

package org.apache.james.transport.mailets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

import org.apache.mailet.MailAddress;
import org.apache.mailet.base.test.FakeMail;
import org.junit.Before;
import org.junit.Test;

public class RecipientToLowerCaseTest {

	private RecipientToLowerCase testee;

	@Before
	public void setUp() {
		testee = new RecipientToLowerCase();
	}

	@Test
	public void serviceShouldPutRecipientToLowerCase() throws Exception {
		/*
		 * Question 1
		 * 
		 * - Create a FakeMail (threw builder()) with an address containing
		 * Upper case as Recipient - Process it with the mailet - It should have
		 * the recipient as lowerCase after processing
		 */

		FakeMail fakeMail = FakeMail.builder().recipient(new MailAddress("VanTuan@gmail.com")).build();

		testee.service(fakeMail);

		// when fake return string -- it is a ignore case - so we need to
		// convert it to string

		Collection<MailAddress> result = fakeMail.getRecipients();

		ArrayList<String> resultAsString = new ArrayList<String>();

		for (MailAddress address : result) {
			resultAsString.add(address.toString());
		}

		assertThat(resultAsString).containsOnly("vantuan@gmail.com");
	}

	@Test
	public void serviceShouldHaveNoEffectWhenNoRecipient() throws Exception {
		/*
		 * Question 2
		 * 
		 * - Create a FakeMail (threw builder()) without recipients - Process it
		 * with the mailet - It should have no recipient after processing
		 */

		FakeMail fakeMail = FakeMail.builder().build();

		Collection<MailAddress> result = fakeMail.getRecipients();

		ArrayList<String> resultAsString = new ArrayList<String>();

		for (MailAddress address : result) {
			resultAsString.add(address.toString());
		}
		
		assertThat(resultAsString).isEmpty();
	}
}
