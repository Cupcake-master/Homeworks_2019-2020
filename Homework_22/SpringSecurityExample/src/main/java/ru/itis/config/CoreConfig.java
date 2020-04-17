package ru.itis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"ru.itis.service", "ru.itis.aspect", "ru.itis.util"})
public class CoreConfig {
}
