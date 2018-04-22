package com.example.demo.core.configurer;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName: MybatisConfigurer
 * @Description: Mybatis翻页插件配置和XML文件配置等
 * @author 张瑶
 * @date 2018年1月20日 下午4:03:46
 *
 */
@Configuration
public class MybatisConfigurer {

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTypeAliasesPackage("com.example.demo.model");
		// 配置分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		// 分页尺寸为0时查询所有纪录不再执行分页
		properties.setProperty("pageSizeZero", "true");
		// 页码<=0 查询第一页，页码>=总页数查询最后一页
		properties.setProperty("reasonable", "true");
		// 支持通过 Mapper接口参数来传递分页参数
		properties.setProperty("supportMethodsArguments", "true");
		pageHelper.setProperties(properties);
		// 添加插件
		factory.setPlugins(new Interceptor[] { pageHelper });
		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		return factory.getObject();
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		mapperScannerConfigurer.setBasePackage("com.example.demo.dao");
		return mapperScannerConfigurer;
	}
}
