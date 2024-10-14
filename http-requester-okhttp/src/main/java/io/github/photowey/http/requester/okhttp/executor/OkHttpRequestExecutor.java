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
package io.github.photowey.http.requester.okhttp.executor;

import io.github.photowey.http.requester.api.executor.RequestExecutor;
import io.github.photowey.http.requester.api.getter.BeanFactoryGetter;
import io.github.photowey.http.requester.core.exception.RequesterException;
import io.github.photowey.http.requester.core.header.Header;
import io.github.photowey.http.requester.core.header.RequestHeaders;
import io.github.photowey.http.requester.core.parameter.Parameter;
import io.github.photowey.http.requester.okhttp.core.context.OkHttpRequestContext;
import io.github.photowey.http.requester.okhttp.core.parameter.OkHttpMultipartParameter;
import io.github.photowey.http.requester.okhttp.core.response.OkHttpResponse;
import okhttp3.*;
import org.springframework.beans.factory.BeanFactoryAware;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code OkhttpRequestExecutor}
 * |- {@code Okhttp} request executor.
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public interface OkHttpRequestExecutor extends BeanFactoryAware, BeanFactoryGetter, RequestExecutor {

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");
    MediaType STREAM = MediaType.parse("application/octet-stream");

    // ---------------------------------------------------------

    OkHttpClient okhttpClient();

    // ---------------------------------------------------------

    void preHandle(OkHttpRequestContext ctx, Request.Builder builder);

    void postHandle(OkHttpRequestContext ctx, Response response);

    // ---------------------------------------------------------

    Request.Builder toRequestBuilder(OkHttpRequestContext ctx);

    default void preRequest(Request request) {
        this.preRequest(this.okhttpClient(), request);
    }

    default void preRequest(OkHttpClient okhttpClient, Request request) {}

    default void postRequest(Request request, Response response) {
        this.postRequest(this.okhttpClient(), request, response);
    }

    default void postRequest(OkHttpClient okhttpClient, Request request, Response response) {}

    // ---------------------------------------------------------

    Response execute(OkHttpRequestContext context, Consumer<Response> fx);

    default Response execute(Request request) {
        return this.execute(this.okhttpClient(), request);
    }

    Response execute(OkHttpClient okhttpClient, Request request);

    default void execute(Request request, Consumer<OkHttpResponse> fx) {
        this.execute(this.okhttpClient(), request, fx);
    }

    void execute(OkHttpClient okhttpClient, Request request, Consumer<OkHttpResponse> fx);

    // ---------------------------------------------------------

    String multipart(OkHttpRequestContext context);

    Response tryMultipart(OkHttpRequestContext context);

    String multipart(String url, OkHttpMultipartParameter parameters, Header headers);

    Response tryMultipart(String url, OkHttpMultipartParameter parameters, Header headers);

    // ----------------------------------------------------------------

    /**
     * Try to post
     * |- Don't forget to close the response body.
     *
     * @param url  the request url.
     * @param body the request body.
     * @return {@link Response}
     */
    default Response tryPost(String url, String body) {
        return this.tryPost(url, body, RequestHeaders.empty());
    }

    /**
     * Try to post
     * |- Don't forget to close the response body.
     *
     * @param url     the request url.
     * @param body    the request body.
     * @param headers the request headers.
     * @return {@link Response}
     */
    Response tryPost(String url, String body, Header headers);

    // ---------------------------------------------------------

    default Response sync(Request request) {
        return this.sync(this.okhttpClient(), request);
    }

    default Response sync(OkHttpClient okhttpClient, Request request) {
        Objects.requireNonNull(okhttpClient);

        try {
            return okhttpClient.newCall(request).execute();
        } catch (Exception e) {
            throw new RequesterException(e);
        }
    }

    default void async(Request request, Callback callback) {
        this.async(this.okhttpClient(), request, callback);
    }

    default void async(OkHttpClient okhttpClient, Request request, Callback callback) {
        Objects.requireNonNull(okhttpClient);

        try {
            okhttpClient.newCall(request).enqueue(callback);
        } catch (Exception e) {
            throw new RequesterException(e);
        }
    }

    // ---------------------------------------------------------

    default String appendParameters(Parameter parameters, String url) {
        return this.appendParameters(parameters, url, (x) -> {});
    }

    default String appendParameters(Parameter parameters, String url, Consumer<StringBuilder> fx) {
        if (Objects.isNull(parameters) || Objects.isNull(url)) {
            return url;
        }

        StringBuilder buf = new StringBuilder(url);
        if (!url.endsWith(QUESTION_MARK)) {
            buf.append(QUESTION_MARK);
        }

        StringBuilder tmp = new StringBuilder();
        parameters.forEach((k, v) -> tmp.append(AND).append(k).append(EQUAL).append(v));
        String rvt = tmp.toString().replaceAll(REGEX_TRIM_PREFIX_AND, EMPTY_STRING);
        buf.append(rvt);

        fx.accept(buf);

        return buf.toString();
    }

    default String toUrl(Request request) {
        if (Objects.isNull(request)) {
            return null;
        }

        return request.url().toString();
    }
}
