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
package io.github.photowey.http.requester.okhttp.core.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

/**
 * {@code OkHttpResponse}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OkHttpResponse implements Serializable, Closeable {

    private static final long serialVersionUID = -6817218074165093620L;

    private String body;
    // Optional
    private Request request;
    private Response response;

    @Override
    public void close() throws IOException {
        this.response.close();
    }

    public boolean isSuccessful() {
        return this.response.isSuccessful();
    }

    public boolean isFailed() {
        return !this.isSuccessful();
    }
}

