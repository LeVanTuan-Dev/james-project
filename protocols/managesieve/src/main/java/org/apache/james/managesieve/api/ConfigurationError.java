/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */

package org.apache.james.managesieve.api;

/**
 * <code>ConfigurationError</code>
 */
public class ConfigurationError extends ManageSieveError {

    /**
     * 
     */
    private static final long serialVersionUID = 5350261185844873258L;

    /**
     * Creates a new instance of ConfigurationError.
     *
     */
    public ConfigurationError() {
    }

    /**
     * Creates a new instance of ConfigurationError.
     *
     * @param message
     */
    public ConfigurationError(String message) {
        super(message);
    }

    /**
     * Creates a new instance of ConfigurationError.
     *
     * @param cause
     */
    public ConfigurationError(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new instance of ConfigurationError.
     *
     * @param message
     * @param cause
     */
    public ConfigurationError(String message, Throwable cause) {
        super(message, cause);
    }

}