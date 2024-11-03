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
package io.github.photowey.http.requester.api.executor;

import io.github.photowey.http.requester.core.context.RequestContext;
import io.github.photowey.http.requester.core.header.Header;
import io.github.photowey.http.requester.core.parameter.Parameter;

/**
 * {@code DeleteExecutor}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/11/03
 */
public interface DeleteExecutor {

    String delete(String url);

    String delete(String url, Parameter parameters);

    String delete(String url, Header headers);

    String delete(String url, Parameter parameters, Header headers);

    String delete(RequestContext context);
}
