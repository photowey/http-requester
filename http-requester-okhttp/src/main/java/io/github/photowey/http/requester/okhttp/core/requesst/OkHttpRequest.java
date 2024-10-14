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
package io.github.photowey.http.requester.okhttp.core.requesst;

import io.github.photowey.http.requester.core.request.HttpRequest;
import io.github.photowey.http.requester.okhttp.core.parameter.OkHttpMultipartParameter;

/**
 * {@code OkhttpRequest}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/15
 */
public interface OkHttpRequest extends HttpRequest {

    @Override
    OkHttpMultipartParameter multipartParameter();
}
