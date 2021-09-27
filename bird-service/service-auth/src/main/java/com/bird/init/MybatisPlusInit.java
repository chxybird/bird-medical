package com.bird.init;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/9 10:15
 * @Description 代码自动生成器
 */
public class MybatisPlusInit {
    public static void main(String[] args) {
        //创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("李璞");//设置作者姓名
//        String projectPath = System.getProperty("user.dir");//获取当前项目的路径
        String projectPath = "F:\\workspace\\DistributedMicroservices\\bird-medical\\bird-service\\service-auth";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(false);//重新生成文件是否覆盖 默认false
        gc.setIdType(IdType.AUTO);//设置主键生成策略
        gc.setOpen(false);//是否打开输出目录 默认true
        gc.setSwagger2(true);//是否生成swagger注解，默认false
        gc.setDateType(DateType.ONLY_DATE);//设置日期的类型
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        //自定义文件命名 %s会自动填充实体表的名称
        gc.setMapperName("I%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dc = new DataSourceConfig();
        dc.setDbType(DbType.MYSQL);// 数据库类型
        dc.setUrl("jdbc:mysql://www.bird-medical.com:3306/service-auth?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
        dc.setDriverName("com.mysql.cj.jdbc.Driver");
        dc.setUsername("root");
        dc.setPassword("root");
        mpg.setDataSource(dc);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.bird");
        pc.setMapper("dao");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        //策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setNaming(NamingStrategy.underline_to_camel);//设置表映射成实体的策略 下划线转驼峰
        stConfig.setInclude("t_\\w*");//设置要映射的表 正则表达式匹配所有带t_前缀的表
        stConfig.setTablePrefix("t_");//不生成前缀 表名为t_student的表会生成student的实体类
        stConfig.setEntityLombokModel(true);//自动添加lombok注解
        stConfig.setRestControllerStyle(true);
        //逻辑删除
//        stConfig.setLogicDeleteFieldName("is_deleted");//逻辑删除属性字段名
        //配置创建时间和更新时间
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        stConfig.setTableFillList(tableFills);
        //设置乐观锁
//        stConfig.setVersionFieldName("version");
        //设置restful
        stConfig.setRestControllerStyle(true);//启用
        mpg.setStrategy(stConfig);

        // 这个是必要的,否则程序会报空指针异常
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        //如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mappers/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);
        //执行
        mpg.execute();
    }
}
