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
import java.util.function.Function;

/**
 * {@code Parameter}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public interface Parameter {

    // ----------------------------------------------------------------

    boolean containsKey(String name);

    // ----------------------------------------------------------------

    Parameter set(String name, Object value);

    Parameter set(Map<String, Object> parameters);

    // ----------------------------------------------------------------

    Object value(String name);

    <T> T value(String name, Function<Object, T> transformer);

    // ----------------------------------------------------------------

    Map<String, Object> values();

    <T> Map<String, T> values(Function<Object, T> transformer);
}
