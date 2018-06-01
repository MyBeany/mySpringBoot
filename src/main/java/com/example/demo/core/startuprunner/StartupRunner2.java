package com.example.demo.core.startuprunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -1)
public class StartupRunner2 implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner2.class);

    @Override
    public void run(String... args) throws Exception{
        logger.info("服务器启动成功！<<<<使用CommandLineRunner接口");
    }
}
