package com.zujuan.mpgenerator.service;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 数据库相关类生成服务
 * @author youmeng
 */
@Service
public class GeneratorService {

    @Value("${generator-config.db-info.jdbc-url}")
    private String dbUrl;
    @Value("${generator-config.db-info.driver-class-name}")
    private String dbDriverName;
    @Value("${generator-config.db-info.table-names}")
    private String dbTableNames;
    @Value("${generator-config.db-info.username}")
    private String dbUserName;
    @Value("${generator-config.db-info.password}")
    private String dbPassword;
    @Value("${generator-config.use-lombok}")
    private Boolean useLombok;
    @Value("${generator-config.author}")
    private String author;
    @Value("${generator-config.output-path}")
    private String outputPath;
    @Value("${generator-config.entity-subfix}")
    private String entitySubfix;

    /**
     * 根据数据表生成实体类
     */
   public void generate(){
       // 代码生成器
       AutoGenerator mpg = new AutoGenerator();
       /**
        * 全局配置
        */
       GlobalConfig gc = new GlobalConfig();
       //文件输出路径
       gc.setOutputDir(outputPath);
       //生成文件注释中的作者
       gc.setAuthor(author);
       //生成完打开输出目录
       gc.setOpen(true);
       //允许文件覆盖
       gc.setFileOverride(true);
       //设置试题后缀
       gc.setEntityName("%s"+entitySubfix);
       mpg.setGlobalConfig(gc);

       /**
        * 数据源配置
        */
       DataSourceConfig dsc = new DataSourceConfig();
       dsc.setUrl(dbUrl);
       dsc.setDriverName(dbDriverName);
       dsc.setUsername(dbUserName);
       dsc.setPassword(dbPassword);
       mpg.setDataSource(dsc);

       /**
        * 包配置
        */
       PackageConfig pc = new PackageConfig();
       //生成文件的包名
       pc.setModuleName("output");
       pc.setParent("com.zujuan.mpgenerator");
       mpg.setPackageInfo(pc);
       // 配置模板
       TemplateConfig templateConfig = new TemplateConfig();
       //不生成 controller
       templateConfig.setController("");
       mpg.setTemplate(templateConfig);

       /**
        * 策略配置
        */
       StrategyConfig strategy = new StrategyConfig();
       //数据库表映射到实体的命名策略
       strategy.setNaming(NamingStrategy.underline_to_camel);
       //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
       strategy.setColumnNaming(NamingStrategy.underline_to_camel);
       //lombok启用后实体上会增加@Data注解
       strategy.setEntityLombokModel(useLombok);
       if(useLombok){
           //实体字段上生成注解
           strategy.setEntityTableFieldAnnotationEnable(true);
           //链式模式
           strategy.setChainModel(true);
       }
       //设置需要生成实体的表名
       strategy.setInclude(dbTableNames.split(","));
       //controller上的@RequestMapping参数自动将驼峰改为连字符
       strategy.setControllerMappingHyphenStyle(true);
       //controller上使用@RestController注解
       strategy.setRestControllerStyle(true);
       //数据库中is开头的字段生成实体时自动去除is前缀
       strategy.setEntityBooleanColumnRemoveIsPrefix(true);
       mpg.setStrategy(strategy);

       mpg.execute();
   }
}
