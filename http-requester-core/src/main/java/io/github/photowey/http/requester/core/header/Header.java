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

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * {@code Header}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public interface Header {

    // ----------------------------------------------------------------

    default boolean isNotEmpty() {
        return !this.isEmpty();
    }

    boolean isEmpty();

    // ----------------------------------------------------------------

    boolean containsKey(String key);

    // ----------------------------------------------------------------

    Header set(String key, Object value);

    Header set(String key, List<Object> values);

    Header set(Map<String, Object> headers);

    // ----------------------------------------------------------------

    Object get(String key);

    <T> T get(String key, Function<Object, T> transformer);

    List<Object> gets(String key);

    <T> List<T> gets(String key, Function<Object, T> transformer);

    // ----------------------------------------------------------------

    void forEach(BiConsumer<String, Object> fx);

    <T> void forEach(BiConsumer<String, T> fx, Function<Object, T> transformer);

    // ----------------------------------------------------------------

    void forEaches(BiConsumer<String, List<Object>> fx);

    <T> void forEaches(BiConsumer<String, List<T>> fx, Function<Object, T> transformer);

    // ----------------------------------------------------------------
}
