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
 * {@code RequestExecutor}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public interface RequestExecutor {

    // ---------------------------------------------------------

    String doGet(String url);

    String doGet(String url, Parameter parameters);

    String doGet(String url, Header headers);

    String doGet(String url, Parameter parameters, Header headers);

    // ---------------------------------------------------------

    String doPost(String url, Parameter parameters);

    String doPost(String url, Parameter parameters, Header headers);

    // ---------------------------------------------------------

    String doPost(String url, String body);

    String doPost(String url, String body, Header headers);

    String execute(RequestContext context);
}
