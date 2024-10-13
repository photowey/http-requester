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

import okhttp3.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

/**
 * {@code ResponseWrapper}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
public class ResponseWrapper implements Serializable, Closeable {

    private static final long serialVersionUID = -6817218074165093620L;

    private String body;
    private Response response;

    public static ResponseWrapperBuilder builder() {
        return new ResponseWrapperBuilder();
    }

    @Override
    public void close() throws IOException {
        this.response.close();
    }

    public boolean isSuccessful() {
        return this.response.isSuccessful();
    }

    public String getBody() {
        return this.body;
    }

    public String body() {
        return this.body;
    }

    public Response getResponse() {
        return this.response;
    }

    public Response response() {
        return this.response;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public void setResponse(final Response response) {
        this.response = response;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseWrapper)) {
            return false;
        } else {
            ResponseWrapper other = (ResponseWrapper) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$body = this.getBody();
                Object other$body = other.getBody();
                if (this$body == null) {
                    if (other$body != null) {
                        return false;
                    }
                } else if (!this$body.equals(other$body)) {
                    return false;
                }

                Object this$response = this.getResponse();
                Object other$response = other.getResponse();
                if (this$response == null) {
                    if (other$response != null) {
                        return false;
                    }
                } else if (!this$response.equals(other$response)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseWrapper;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $body = this.getBody();
        result = result * 59 + ($body == null ? 43 : $body.hashCode());
        Object $response = this.getResponse();
        result = result * 59 + ($response == null ? 43 : $response.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ResponseWrapper(body=" + this.getBody() + ", response=" + this.getResponse() + ")";
    }

    public ResponseWrapper() {
    }

    public ResponseWrapper(final String body, final Response response) {
        this.body = body;
        this.response = response;
    }

    public static class ResponseWrapperBuilder {
        private String body;
        private Response response;

        ResponseWrapperBuilder() {
        }

        public ResponseWrapperBuilder body(final String body) {
            this.body = body;
            return this;
        }

        public ResponseWrapperBuilder response(final Response response) {
            this.response = response;
            return this;
        }

        public ResponseWrapper build() {
            return new ResponseWrapper(this.body, this.response);
        }

        @Override
        public String toString() {
            return "ResponseWrapper.ResponseWrapperBuilder(body=" + this.body + ", response=" + this.response + ")";
        }
    }
}

