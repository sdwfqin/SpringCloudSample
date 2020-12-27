package com.sdwfqin.cloudsample.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * mysql 代码生成器
 * <p>
 *
 * @author 张钦
 * @date 2020/12/27
 */
public class MysqlBatchGenerator {


    public static String getMoudleName() {
        return "";
    }


    public static String[] gettableList() {
        return new String[]{
                "auth_permission",
                "auth_role_permission",
                "auth_role",
                "auth_user_role",
                "auth_user"
        };
    }

    public static void main(String[] args) {

        for (int i = 0; i < gettableList().length; i++) {
            generatorCode(getMoudleName(), gettableList()[i]);
        }
    }

    /**
     * RUN THIS
     */
    public static void generatorCode(String moudleName, String tableName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("张钦");
        gc.setDateType(DateType.ONLY_DATE);

        //todo 是否覆盖已有文件
        gc.setFileOverride(true);

        gc.setSwagger2(true);

        gc.setEntityName("%sDO");
//        gc.setMapperName("%sDao");

        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/cloud_sample?useUnicode=true&character_set_server=utf8mb4&characterEncoding=utf8&serverTimezone=CTT");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moudleName);
        pc.setParent("com.sdwfqin.cloudsample.auth");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mybatis/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
//        strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(tableName);
        strategy.setEntityTableFieldAnnotationEnable(true);
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        //设置逻辑删除字段
        strategy.setLogicDeleteFieldName("is_deleted");

        //设置自动填充字段
        TableFill tableFill = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill tableFill1 = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(tableFill);
        tableFills.add(tableFill1);
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
