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
package io.github.photowey.http.requester.okhttp.interceptor;

import io.github.photowey.http.requester.core.context.RequestContext;
import okhttp3.Request;
import okhttp3.Response;

/**
 * {@code NoOpsRequestInterceptor}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/14
 */
public class NoOpsRequestInterceptor implements RequestInterceptor {

    @Override
    public void preHandle(RequestContext context, Request.Builder builder) {}

    @Override
    public void postHandle(RequestContext context, Response response) {}

    @Override
    public int getOrder() {
        return 0;
    }
}

