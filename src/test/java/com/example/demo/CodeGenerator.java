package com.example.demo;

import com.example.demo.core.constant.ProjectConstant;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 * @author 张瑶
 * @date 2018/4/23 20:28
 */
public class CodeGenerator {

	// JDBC配置，请修改为你项目的实际配置
	private static final String JDBC_URL = "jdbc:mysql://localhost:3333/demo";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "123456";
	private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	private static final String PROJECT_PATH = System.getProperty("user.dir");// 获取项目在硬盘上的基础路径

	private static final String JAVA_PATH = "src/main/java"; // java文件路径
	private static final String RESOURCES_PATH = "src/main/resources";// 资源文件路径

	/**
	 * genCode("输入表名","输入自定义Model名称");
	 * 如果想创建所有表，请输入"%"
	 * @param args
	 */
	public static void main(String[] args) {
		genCode("system_log");
	}

	/**
	 * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。 如输入表名称 "t_user_detail" 将生成
	 * TUserDetail、TUserDetailMapper、TUserDetailService ...
	 * 
	 * @param tableNames
	 *            数据表名称...
	 */
	public static void genCode(String... tableNames) {
		for (String tableName : tableNames) {
			genCode(tableName, null);
		}
	}

	/**
	 * 通过数据表名称，和自定义的 Model 名称生成代码 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User"
	 * 将生成 User、UserMapper、UserService ...
	 * 
	 * @param tableName
	 *            数据表名称
	 * @param modelName
	 *            自定义的 Model 名称
	 */
	public static void genCode(String tableName, String modelName) {
		genModelAndMapper(tableName, modelName);
	}

	public static void genModelAndMapper(String tableName, String modelName) {
		Context context = getContext();

		JDBCConnectionConfiguration jdbcConnectionConfiguration = getJDBCConnectionConfiguration();
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

		PluginConfiguration pluginConfiguration = getPluginConfiguration();
		context.addPluginConfiguration(pluginConfiguration);

		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = getJavaModelGeneratorConfiguration();
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = getSqlMapGeneratorConfiguration();
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration =getJavaClientGeneratorConfiguration();
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

		TableConfiguration tableConfiguration = new TableConfiguration(context);
		tableConfiguration.setTableName(tableName);
		tableConfiguration.setDomainObjectName(modelName);
		context.addTableConfiguration(tableConfiguration);

		List<String> warnings;
		MyBatisGenerator generator;
		try {
			Configuration config = new Configuration();
			config.addContext(context);
			config.validate();
			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			warnings = new ArrayList<>();
			generator = new MyBatisGenerator(config, callback, warnings);
			generator.generate(null);
		} catch (Exception e) {
			throw new RuntimeException("生成Model和Mapper失败", e);
		}

		if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
			throw new RuntimeException("生成Model和Mapper失败：" + warnings);
		}
		if (StringUtils.isEmpty(modelName)){
			modelName = tableNameConvertUpperCamel(tableName);
		}
		System.out.println(modelName + ".java 生成成功");
		System.out.println(modelName + "Mapper.java 生成成功");
		System.out.println(modelName + "Mapper.xml 生成成功");
	}

	private static Context getContext(){
		Context context = new Context(ModelType.FLAT);
		context.setId("Potato");
		context.setTargetRuntime("MyBatis3Simple");
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
		context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
		return context;
	}

	private static JDBCConnectionConfiguration getJDBCConnectionConfiguration(){
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
		jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
		jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
		jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
		return jdbcConnectionConfiguration;
	}

	private static PluginConfiguration getPluginConfiguration(){
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
		pluginConfiguration.addProperty("mappers", ProjectConstant.MAPPER_INTERFACE_REFERENCE);
		return pluginConfiguration;
	}

	private static JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration(){
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetProject(JAVA_PATH);
		javaModelGeneratorConfiguration.setTargetPackage(ProjectConstant.MODEL_PACKAGE);
		javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
		javaModelGeneratorConfiguration.addProperty("trimStrings","true");
		return javaModelGeneratorConfiguration;
	}

	private static SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration(){
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetProject(RESOURCES_PATH);
		sqlMapGeneratorConfiguration.setTargetPackage("mapper");
		return sqlMapGeneratorConfiguration;
	}

	private static JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration(){
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetProject(JAVA_PATH);
		javaClientGeneratorConfiguration.setTargetPackage(ProjectConstant.MAPPER_PACKAGE);
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		return javaClientGeneratorConfiguration;
	}

	private static String tableNameConvertUpperCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
	}
}
