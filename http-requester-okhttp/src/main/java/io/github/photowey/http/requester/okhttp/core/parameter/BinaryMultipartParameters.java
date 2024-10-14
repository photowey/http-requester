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
package io.github.photowey.http.requester.okhttp.core.parameter;

import io.github.photowey.http.requester.okhttp.executor.OkHttpRequestExecutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.net.URLConnection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * {@code BinaryMultipartParameters}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BinaryMultipartParameters implements OkHttpMultipartParameter {

    private String name;
    private byte[] bytes;

    // ----------------------------------------------------------------

    @Override
    public boolean isEmpty() {
        return null == this.bytes || this.bytes.length == 0;
    }

    @Override
    public boolean containsKey(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    @Override
    public OkHttpMultipartParameter set(String name, Object value) {
        throw new UnsupportedOperationException("Unsupported now");
    }

    @Override
    public OkHttpMultipartParameter set(String name, byte[] value) {
        this.name = name;

        return this;
    }

    @Override
    public OkHttpMultipartParameter set(Map<String, Object> parameters) {
        throw new UnsupportedOperationException("Unsupported now");
    }

    @Override
    public Object value(String name) {
        throw new UnsupportedOperationException("Unsupported now");
    }

    @Override
    public <T> T value(String name, Function<Object, T> transformer) {
        throw new UnsupportedOperationException("Unsupported now");
    }

    @Override
    public Map<String, Object> values() {
        throw new UnsupportedOperationException("Unsupported now");
    }

    @Override
    public <T> Map<String, T> values(Function<Object, T> transformer) {
        throw new UnsupportedOperationException("Unsupported now");
    }

    @Override
    public void forEach(BiConsumer<String, Object> fx) {
        fx.accept(this.name, this.bytes);
    }

    @Override
    public <T> void forEach(BiConsumer<String, T> fx, Function<Object, T> transformer) {
        fx.accept(this.name, transformer.apply(this.bytes));
    }

    // ----------------------------------------------------------------

    @Override
    public MediaType tryMediaType() {
        String mimeType = URLConnection.guessContentTypeFromName(this.getName());
        MediaType contentType = MediaType.parse(mimeType);
        if (null == contentType) {
            contentType = OkHttpRequestExecutor.STREAM;
        }

        return contentType;
    }

    // ----------------------------------------------------------------

    @Override
    public RequestBody toRequestBody() {
        MediaType contentType = this.tryMediaType();
        byte[] input = this.bytes();

        return RequestBody.create(contentType, input);
    }

    // ----------------------------------------------------------------

    @Override
    public String name() {
        return name;
    }

    @Override
    public byte[] bytes() {
        return bytes;
    }

    // ----------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
