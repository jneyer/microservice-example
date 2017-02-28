package com.example;

import org.springframework.context.annotation.Configuration;

import com.giantrobotlabs.annotation.EnableApiConfig;
import com.giantrobotlabs.annotation.EnableH2Config;
import com.giantrobotlabs.annotation.EnableResourceHandler;

@Configuration
@EnableApiConfig
@EnableH2Config
@EnableResourceHandler
public class SpringConfig {

}
