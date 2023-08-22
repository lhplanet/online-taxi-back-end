package com.sdu.servicedriveruser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author LHP
 * @date 2023-07-13 0:31
 * @description 代码生成器
 */
public class MysqlGenerator {

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8", "root","123456")
                .globalConfig(builder -> {
                    builder.author("李浩鹏").fileOverride().outputDir("E:\\Project\\CourseDesign\\online-taxi\\online-taxi-back-end\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.sdu.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\Project\\CourseDesign\\online-taxi\\online-taxi-back-end\\service-driver-user\\src\\main\\java\\com\\sdu\\servicedriveruser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_car_binding_relationship");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
