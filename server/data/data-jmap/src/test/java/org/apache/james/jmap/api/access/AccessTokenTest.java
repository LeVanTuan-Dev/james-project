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

package org.apache.james.jmap.api.access;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.james.jmap.api.access.exceptions.NotAnUUIDException;
import org.junit.Test;

public class AccessTokenTest {

    @Test(expected=NotAnUUIDException.class)
    public void fromStringShouldThrowWhenNotAnUUID() throws NotAnUUIDException {
        AccessToken.fromString("bad");
    }

    @Test
    public void fromStringShouldWork() throws NotAnUUIDException {
        String expectedToken = "dab315ad-a59a-4107-8d00-0fef9a0745b8";

        AccessToken accessToken = AccessToken.fromString(expectedToken);
        assertThat(accessToken.serialize()).isEqualTo(expectedToken);
    }
}