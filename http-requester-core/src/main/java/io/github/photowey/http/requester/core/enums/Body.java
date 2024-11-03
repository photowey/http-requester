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
package io.github.photowey.http.requester.core.enums;

/**
 * {@code Body}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/14
 */
public enum Body {

    JSON("application/json; charset=UTF-8", 1),
    FORM("application/x-www-form-urlencoded", 2),

    // ----------------------------------------------------------------

    TXT("text/plain", 4),
    XML("text/xml", 8),
    HTML("text/html", 16),

    // ----------------------------------------------------------------

    BINARY("application/octet-stream", 32),

    // ----------------------------------------------------------------

    STRING("application/octet-stream", 64),
    VOID("text/void", 128),

    ;

    private final String type;
    private final int value;

    Body(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String type() {
        return type;
    }

    public int value() {
        return value;
    }
}
