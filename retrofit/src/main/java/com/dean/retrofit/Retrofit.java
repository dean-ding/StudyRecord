package com.dean.retrofit;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: tvt on 2019-09-05 19:53
 */
public class Retrofit {

    private HttpUrl baseUrl;
    private Call.Factory callFactory;
    private Map<Method, ServiceMethod> serviceMethodCache = new HashMap<>();

    private Retrofit(HttpUrl baseUrl, Call.Factory callFactory) {
        this.baseUrl = baseUrl;
        this.callFactory = callFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ServiceMethod serviceMethod = loadServiceMethod(method);
                return null;
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        if (serviceMethod != null) {
            return serviceMethod;
        }
        GET getAnnotation = method.getAnnotation(GET.class);
        POST postAnnotation = method.getAnnotation(POST.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        serviceMethod = new ServiceMethod();

        return serviceMethod;
    }

    public HttpUrl getBaseUrl() {
        return baseUrl;
    }

    public Call.Factory getCallFactory() {
        return callFactory;
    }

    public static class Builder {

        private HttpUrl baseUrl;
        private Call.Factory callFactory;

        public Builder setBaseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("baseUrl is null or empty");
            }
            this.baseUrl = new HttpUrl(baseUrl);
            return this;
        }

        public Builder setFactory(Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public Retrofit build() {
            if (baseUrl == null) {
                baseUrl = new HttpUrl("Base url");
            }
            if (callFactory == null) {
                callFactory = new Call.Factory();
            }
            return new Retrofit(baseUrl,callFactory);
        }
    }

}
