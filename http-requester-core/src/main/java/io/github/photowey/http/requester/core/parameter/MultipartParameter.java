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

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * {@code MultipartParameter}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public interface MultipartParameter {

    String name();

    byte[] bytes();

    // ----------------------------------------------------------------

    default boolean isNotEmpty() {
        return !this.isEmpty();
    }

    boolean isEmpty();

    // ----------------------------------------------------------------

    boolean containsKey(String name);

    // ----------------------------------------------------------------

    MultipartParameter set(String name, Object value);

    MultipartParameter set(String name, byte[] value);

    MultipartParameter set(Map<String, Object> parameters);

    // ----------------------------------------------------------------

    Object value(String name);

    <T> T value(String name, Function<Object, T> transformer);

    // ----------------------------------------------------------------

    Map<String, Object> values();

    <T> Map<String, T> values(Function<Object, T> transformer);

    // ----------------------------------------------------------------

    void forEach(BiConsumer<String, Object> fx);

    <T> void forEach(BiConsumer<String, T> fx, Function<Object, T> transformer);
}
