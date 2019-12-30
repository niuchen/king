package com.king.king.cig;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@MapperScan({
        "com.king.king.api.mapper",
})
public class MybatisConfig {
}
