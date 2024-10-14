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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * {@code RequestParameters}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public class RequestParameters implements Parameter {

    // ----------------------------------------------------------------

    private final ConcurrentHashMap<String, Object> parameters;

    // ----------------------------------------------------------------

    private static final RequestParameters EMPTY = new RequestParameters(0);

    // ----------------------------------------------------------------

    public RequestParameters() {
        this.parameters = new ConcurrentHashMap<>();
    }

    public RequestParameters(int initialCapacity) {
        this.parameters = new ConcurrentHashMap<>(initialCapacity);
    }

    // ----------------------------------------------------------------

    public static RequestParameters empty() {
        return EMPTY;
    }

    // ----------------------------------------------------------------

    @Override
    public boolean isEmpty() {
        return this.parameters.isEmpty();
    }

    // ----------------------------------------------------------------

    @Override
    public boolean containsKey(String name) {
        return parameters.containsKey(name);
    }

    // ----------------------------------------------------------------

    @Override
    public Parameter set(String name, Object value) {
        this.parameters.put(name, value);

        return this;
    }

    @Override
    public Parameter set(Map<String, Object> parameters) {
        this.parameters.putAll(parameters);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public Object value(String name) {
        return this.parameters.get(name);
    }

    @Override
    public <T> T value(String name, Function<Object, T> transformer) {
        if (this.containsKey(name)) {
            return transformer.apply(this.parameters.get(name));
        }

        return null;
    }

    @Override
    public Map<String, Object> values() {
        return Collections.unmodifiableMap(this.parameters);
    }

    @Override
    public <T> Map<String, T> values(Function<Object, T> transformer) {
        Map<String, T> tmap = new HashMap<>();
        this.parameters.forEach((k, v) -> {
            tmap.put(k, transformer.apply(v));
        });

        return tmap;
    }

    // ----------------------------------------------------------------

    @Override
    public void forEach(BiConsumer<String, Object> fx) {
        this.parameters.forEach(fx);
    }

    @Override
    public <T> void forEach(BiConsumer<String, T> fx, Function<Object, T> transformer) {
        this.parameters.forEach((k, v) -> {
            fx.accept(k, transformer.apply(v));
        });
    }
}
