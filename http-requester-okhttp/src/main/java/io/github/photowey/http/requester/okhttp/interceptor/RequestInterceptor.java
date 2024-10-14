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
import io.github.photowey.http.requester.core.shaded.org.springframework.core.Ordered;
import okhttp3.Request;
import okhttp3.Response;

/**
 * {@code RequestInterceptor}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/14
 */
public interface RequestInterceptor extends Ordered {

    void preHandle(RequestContext context, Request.Builder builder);

    void postHandle(RequestContext context, Response response);

    @Override
    default int getOrder() {
        return 0;
    }
}

