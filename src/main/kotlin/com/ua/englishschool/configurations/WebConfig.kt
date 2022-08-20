package com.ua.englishschool.configurations

import com.ua.englishschool.interceptors.GlobalHandlerInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry

@Configuration
class WebConfig : DelegatingWebMvcConfiguration() {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(GlobalHandlerInterceptor())
    }
}