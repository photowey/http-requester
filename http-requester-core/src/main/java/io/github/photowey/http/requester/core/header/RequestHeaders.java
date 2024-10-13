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
package io.github.photowey.http.requester.core.header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@code RequestHeaders}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public class RequestHeaders implements Header {

    private final ConcurrentHashMap<String, List<Object>> HEADERS;

    // ----------------------------------------------------------------

    private static final RequestHeaders EMPTY = new RequestHeaders(0);

    // ----------------------------------------------------------------

    public RequestHeaders() {
        this.HEADERS = new ConcurrentHashMap<>();
    }

    public RequestHeaders(int initialCapacity) {
        this.HEADERS = new ConcurrentHashMap<>(initialCapacity);
    }

    // ----------------------------------------------------------------

    public static RequestHeaders empty() {
        return EMPTY;
    }

    // ----------------------------------------------------------------

    @Override
    public boolean containsKey(String key) {
        return HEADERS.containsKey(key);
    }

    // ----------------------------------------------------------------

    @Override
    public Header set(String key, Object value) {
        HEADERS.put(key, Collections.singletonList(value));

        return this;
    }

    @Override
    public Header set(String key, List<Object> values) {
        HEADERS.put(key, values);

        return this;
    }

    @Override
    public Header set(Map<String, Object> headers) {
        headers.forEach(this::set);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public Object get(String key) {
        if (HEADERS.containsKey(key)) {
            return HEADERS.get(key).get(0);
        }

        return null;
    }

    @Override
    public <T> T get(String key, Function<Object, T> transformer) {
        if (HEADERS.containsKey(key)) {
            return transformer.apply(HEADERS.get(key).get(0));
        }

        return null;
    }

    // ----------------------------------------------------------------

    @Override
    public List<Object> gets(String key) {
        if (HEADERS.containsKey(key)) {
            return HEADERS.get(key);
        }

        return new ArrayList<>(0);
    }

    @Override
    public <T> List<T> gets(String key, Function<Object, T> transformer) {
        if (HEADERS.containsKey(key)) {
            return HEADERS.get(key)
                .stream()
                .map(transformer)
                .collect(Collectors.toList());
        }

        return new ArrayList<>(0);
    }

    // ----------------------------------------------------------------

    @Override
    public void forEach(BiConsumer<String, Object> fx) {
        HEADERS.forEach((k, v) -> {
            fx.accept(k, v.get(0));
        });
    }

    @Override
    public <T> void forEach(BiConsumer<String, T> fx, Function<Object, T> transformer) {
        HEADERS.forEach((k, v) -> {
            fx.accept(k, transformer.apply(v.get(0)));
        });
    }

    // ----------------------------------------------------------------

    @Override
    public void forEaches(BiConsumer<String, List<Object>> fx) {
        HEADERS.forEach(fx);
    }

    @Override
    public <T> void forEaches(BiConsumer<String, List<T>> fx, Function<Object, T> transformer) {
        HEADERS.forEach((k, v) -> {
            fx.accept(k, v.stream().map(transformer).collect(Collectors.toList()));
        });
    }
}
