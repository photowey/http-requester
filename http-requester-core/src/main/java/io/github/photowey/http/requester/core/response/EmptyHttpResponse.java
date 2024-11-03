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
package io.github.photowey.http.requester.core.response;

import io.github.photowey.http.requester.core.enums.Body;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code EmptyHttpResponse}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/11/03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmptyHttpResponse implements HttpResponse<Void> {

    private static final long serialVersionUID = 157040778950912338L;

    private Body type;

    @Override
    public Body type() {
        return Body.VOID;
    }

    @Override
    public Void body() {
        return null;
    }
}
