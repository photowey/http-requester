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

import io.github.photowey.http.requester.core.property.HttpProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.X509TrustManager;

/**
 * {@code AbstractOkHttpRequestExecutor}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/10/14
 */
@Slf4j
public abstract class AbstractOkHttpRequestExecutor implements OkHttpRequestExecutor {

    private final OkHttpClient okhttpClient;

    private ConfigurableListableBeanFactory beanFactory;

    public AbstractOkHttpRequestExecutor(OkHttpClient okhttpClient) {
        this.okhttpClient = okhttpClient;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    // ----------------------------------------------------------------

    @Override
    public OkHttpClient okhttpClient() {
        return this.okhttpClient;
    }

    // ----------------------------------------------------------------

    public HttpProperties httpProperties() {
        return this.beanFactory.getBean(HttpProperties.class);
    }

    public X509TrustManager trustAnyTrustManager() {
        return this.beanFactory.getBean(X509TrustManager.class);
    }

    public HostnameVerifier trustAnyHostnameVerifier() {
        return this.beanFactory.getBean(HostnameVerifier.class);
    }

    // ----------------------------------------------------------------
}
