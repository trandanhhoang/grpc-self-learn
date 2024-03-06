package com.example.serviceselflearn.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Reference: https://micrometer.io/docs/concepts#_the_timed_annotation
 */
@Configuration
@EnableAspectJAutoProxy
public class MonitorAutoTimingConfig {

  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }
}
