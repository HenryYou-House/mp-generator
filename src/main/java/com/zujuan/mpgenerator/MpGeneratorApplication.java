package com.zujuan.mpgenerator;

import com.zujuan.mpgenerator.service.GeneratorService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpGeneratorApplication  implements CommandLineRunner {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GeneratorService generatorService;

    public static void main(String[] args) {
        SpringApplication.run(MpGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args){
        logger.info("开始生成");
        //生成实体类
        generatorService.generate();
        logger.info("生成结束");
    }
}
