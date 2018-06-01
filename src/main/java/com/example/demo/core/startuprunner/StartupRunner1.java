package com.example.demo.core.startuprunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartupRunner1 implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner1.class);

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        logger.info("服务器启动成功！<<<<使用ApplicationRunner接口");
    }
}
