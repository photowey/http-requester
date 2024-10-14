/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.photowey.http.requester.core.exception;

/**
 * {@code RequesterException}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/14
 */
public class RequesterException extends RuntimeException {

    public RequesterException() {
        super();
    }

    public RequesterException(String message) {
        super(message);
    }

    public RequesterException(String message, Object... args) {
        super(String.format(message, args));
    }

    public RequesterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequesterException(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
    }

    public RequesterException(Throwable cause) {
        super(cause);
    }

    protected RequesterException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
