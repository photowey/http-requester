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
package io.github.photowey.http.requester.core.parameter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code RequestMultipartParameters}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public class RequestMultipartParameters extends AbstractMultipartParameter {

    private final ConcurrentHashMap<String, Object> ctx;

    // ----------------------------------------------------------------

    private static final RequestMultipartParameters EMPTY = new RequestMultipartParameters(0);

    // ----------------------------------------------------------------

    public RequestMultipartParameters() {
        this.ctx = new ConcurrentHashMap<>();
    }

    public RequestMultipartParameters(int initialCapacity) {
        this.ctx = new ConcurrentHashMap<>(initialCapacity);
    }

    // ----------------------------------------------------------------

    public static RequestMultipartParameters empty() {
        return EMPTY;
    }

    // ----------------------------------------------------------------

    @Override
    public boolean containsKey(String name) {
        return this.ctx.containsKey(name);
    }

    // ----------------------------------------------------------------

    public <T> RequestMultipartParameters set(String key, T value) {
        this.ctx.put(key, value);

        return this;
    }

    public <T> RequestMultipartParameters set(Map<String, T> parameters) {
        this.ctx.putAll(parameters);

        return this;
    }

    // ----------------------------------------------------------------

    public boolean isMultipart(String name) {
        Object parameter = this.get(name);

        return this.isMultipart(parameter);
    }

    // ----------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> get() {
        return Collections.unmodifiableMap((Map<String, T>) this.ctx);
    }

    // ----------------------------------------------------------------

    public MultipartParameter getMultipartParameter(String name) {
        Object parameter = this.get(name);
        if (this.isMultipart(parameter)) {
            return (MultipartParameter) parameter;
        }

        return null;
    }

    public MultipartParameter toMultipartParameter(Object parameter) {
        if (this.isMultipart(parameter)) {
            return (MultipartParameter) parameter;
        }

        throw new RuntimeException("parameter isn't a instance of MultipartParameter");
    }
}

