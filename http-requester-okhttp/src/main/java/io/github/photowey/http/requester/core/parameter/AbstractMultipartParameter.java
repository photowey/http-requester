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

import io.github.photowey.http.requester.core.query.AbstractQuery;

/**
 * {@code AbstractMultipartParameter}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public abstract class AbstractMultipartParameter extends AbstractQuery {

    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key) {
        return (T) this.get().get(key);
    }

    public boolean isMultipart(Object target) {
        return target instanceof MultipartParameter;
    }

    public boolean isNotMultipart(Object target) {
        return !this.isMultipart(target);
    }

    public boolean isContainTextParameter() {
        return this.get().values().stream().anyMatch(this::isNotMultipart);
    }

    public String toQuery() {
        StringBuilder builder = new StringBuilder();
        if (this.isContainTextParameter()) {
            builder.append("?");
        }

        this.get().forEach((k, v) -> {
            if (this.isNotMultipart(v)) {
                builder.append(k).append("=").append(v).append("&");
            }
        });

        return builder.toString().replaceAll("&*$", "");
    }
}

