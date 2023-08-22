package com.sdu.serviceorder.generator;

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

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-order?characterEncoding=utf-8&serverTimezone=GMT%2B8", "root","123456")
                .globalConfig(builder -> {
                    builder.author("李浩鹏").fileOverride().outputDir("E:\\Project\\CourseDesign\\online-taxi\\online-taxi-back-end\\service-order\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.sdu.serviceorder").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\Project\\CourseDesign\\online-taxi\\online-taxi-back-end\\service-order\\src\\main\\java\\com\\sdu\\serviceorder\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order_info");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
