package com.mx.xvhx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.mx.xvhx")
@Import({ WebMvcConfig.class })
public class AppConfig {

}
