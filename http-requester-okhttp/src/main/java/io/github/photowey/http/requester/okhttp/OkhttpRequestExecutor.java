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
package io.github.photowey.http.requester.okhttp;

import io.github.photowey.http.requester.api.executor.RequestExecutor;
import io.github.photowey.http.requester.core.context.RequestContext;
import io.github.photowey.http.requester.core.header.Header;
import io.github.photowey.http.requester.core.header.RequestHeaders;
import io.github.photowey.http.requester.core.parameter.MultipartParameter;
import io.github.photowey.http.requester.core.response.ResponseWrapper;
import io.github.photowey.http.requester.core.shaded.org.springframework.http.HttpMethod;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.function.Consumer;

/**
 * {@code OkhttpRequestExecutor}
 * |- {@code Okhttp} request executor.
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public interface OkhttpRequestExecutor extends RequestExecutor {

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");
    MediaType STREAM = MediaType.parse("application/octet-stream");

    // ---------------------------------------------------------

    void preHandle(RequestContext context, Request.Builder builder);

    void postHandle(RequestContext context, Response response);

    void preRequest(OkHttpClient okHttpClient, Request request);

    Request.Builder populateRequest(HttpMethod httpMethod, RequestContext context);

    Response executeRequest(OkHttpClient okHttpClient, Request request);

    Response executeBin(RequestContext context, Consumer<Response> fx);

    void executeRequest(OkHttpClient okHttpClient, Request request, Consumer<ResponseWrapper> callback);

    // ---------------------------------------------------------

    String doMultipartPost(String url, MultipartParameter parameters, Header headers);

    String doMultipartPost(RequestContext context);

    default Response doPostr(String url, String body) {
        return this.doPostr(url, body, RequestHeaders.empty());
    }

    Response doPostr(String url, String body, Header headers);
}
