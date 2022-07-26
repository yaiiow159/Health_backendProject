package com.timmy.health.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.*;


@Slf4j
public class MybatisGenerator {
    private static final String URL = "jdbc:mysql://localhost:3306/health?serverTimezone=GMT%2b8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "timmy072869";
    private static final String ROOTPATH = System.getProperty("user.dir");
    private static final String DOMAINPATH = ROOTPATH + "/health_commons/src/main/java/com/timmy/health/domain";
    private static final String MAPPERPATH = ROOTPATH + "/health_provider/src/main/java/com/timmy/health/mapper";
    private static final String XMLPATH = ROOTPATH + "/health_provider/src/main/java/com/timmy/health/mapper";
    private static final String SERVICEPATH = ROOTPATH + "/health_interface/src/main/java/com/timmy/health/service";
    private static final String SERVICEIMPLPAHT = ROOTPATH + "/health_provider/src/main/java/com/timmy/health/service/impl";
    private static final String CONTROLLERPATH = ROOTPATH + "/health_back/src/main/java/com/timmy/health/controller";

    public static void generateTable(String @NotNull ... tableName){
        List<String> tables = new ArrayList<>(Arrays.asList(tableName));

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder.author("TimmyChung")
                        .disableOpenDir()
                        .outputDir(ROOTPATH + "src/main/java")
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd"))
                //PackageConfig
                .packageConfig(builder -> builder.parent("com.timmy")
                        .moduleName("health")
                        .entity("domain")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .mapper("mapper")
                        .xml("mapper")
                        .pathInfo(getPathInfo()))
                //StrategyConfig
                .strategyConfig(builder -> builder.addInclude(tables)
                        .addTablePrefix("t_")
                        //service
                        .serviceBuilder()
                        .superServiceClass(IService.class)
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        //entity
                        .entityBuilder()
                        .enableChainModel()
                        .enableLombok()
                        .fileOverride()
                        .enableRemoveIsPrefix()
                        .idType(IdType.ASSIGN_ID)
                        .enableTableFieldAnnotation()
                        //controller
                        .controllerBuilder()
                        .formatFileName("%sController")
                        .fileOverride()
                        .enableRestStyle()
                        //mapper
                        .mapperBuilder()
                        .superClass(BaseMapper.class)
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .fileOverride()
                        .enableMapperAnnotation()
                        .formatXmlFileName("%sMapper"))
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
    
    private static @NotNull
    Map<OutputFile, String> getPathInfo() {
        Map<OutputFile , String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, DOMAINPATH);
        pathInfo.put(OutputFile.mapper, MAPPERPATH);
        pathInfo.put(OutputFile.service, SERVICEPATH);
        pathInfo.put(OutputFile.serviceImpl, SERVICEIMPLPAHT);
        pathInfo.put(OutputFile.controller, CONTROLLERPATH);
        pathInfo.put(OutputFile.xml, XMLPATH);
        return pathInfo;
    }
}
