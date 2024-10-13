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
package io.github.photowey.http.requester.core.property;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code HttpProperties}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpProperties implements Serializable {

    private static final long serialVersionUID = -5453604230161791240L;

    private OkHttp okHttp = new OkHttp();
    private Httpclient httpclient = new Httpclient();
    private Vertx vertx = new Vertx();

    private Logger logger = new Logger();

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Http implements Serializable {

        private static final long serialVersionUID = 474081402674142730L;

        protected int connectTimeout = 5;
        protected int readTimeout = 30;
        protected int writeTimeout = 30;
        protected int maxIdleConnections = 200;
        protected long keepAliveDuration = 300L;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class OkHttp extends Http {

        private static final long serialVersionUID = 5660434329991461992L;

        /**
         * Default enabled.
         */
        private boolean enabled = true;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Httpclient extends Http {

        private static final long serialVersionUID = 5241132779981226884L;

        private boolean enabled = false;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Vertx extends Http {

        private static final long serialVersionUID = 4627517257771439464L;

        private boolean enabled = false;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Logger implements Serializable {

        private static final long serialVersionUID = 5231829045141727090L;

        private Sign sign = new Sign();

        /**
         * Ignore API list for logging.
         */
        protected List<String> ignoreApis = new ArrayList<>();

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Sign implements Serializable {

            private static final long serialVersionUID = -8433567457964722096L;

            private boolean enabled = true;
            private boolean force = false;
        }
    }
}

