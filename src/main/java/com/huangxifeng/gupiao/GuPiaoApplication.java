package com.huangxifeng.gupiao;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GuPiaoApplication extends SpringBootServletInitializer implements WebMvcConfigurer{
	@Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	          // 注意这里要指向原先用main方法执行的App启动类
	         return builder.sources(GuPiaoApplication.class);
	     }
	/**
	 * 默认时区（亚洲/上海）
	 */
	private final static String DEFAULT_TIME_ZONE = "Asia/Shanghai";

	/**
	 * 启动
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 设置时区
		System.setProperty("user.timezone", DEFAULT_TIME_ZONE);
		TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));

		SpringApplication.run(GuPiaoApplication.class);
	}

//	/**
//	 * 配置tomcat字符转换 /Users/xinghuapan/Documents/gupiaodata/data
//	 */
//	@Bean
//	public TomcatServletWebServerFactory webServerFactory() {
//		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//		factory.addConnectorCustomizers((Connector connector) -> {
//			connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
//			connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
//		});
//		return factory;
//	}
//
//	/**
//	 * 配置消息转换器（用阿里的fastJson）
//	 */
//	// @Bean
//	// public HttpMessageConverters fastJsonConfigure() {
//	//
//	// FastJsonHttpMessageConverter converter = new
//	// FastJsonHttpMessageConverter();
//	//
//	// // 添加fastJson的配置信息，比如：是否要格式化返回的json数据
//	//// FastJsonConfig fastJsonConfig = new FastJsonConfig();
//	//// fastJsonConfig.setSerializerFeatures(
//	//// SerializerFeature.NotWriteDefaultValue,
//	//// SerializerFeature.DisableCircularReferenceDetect,
//	//// SerializerFeature.WriteEnumUsingToString
//	//// );
//	//// converter.setFastJsonConfig(fastJsonConfig);
//	//
//	// // 处理中文乱码问题
//	// List<MediaType> fastMediaTypes = Lists.newArrayList();
//	// fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//	// converter.setSupportedMediaTypes(fastMediaTypes);
//	//
//	// return new HttpMessageConverters(converter);
//	// }
//
//	/**
//	 * 拦截器
//	 *
//	 * @return
//	 */
////	@Bean
////	public HandlerInterceptor handlerInterceptor() {
////		return new HandlerInterceptor();
////	}
//
//	/**
//	 * 配置拦截器
//	 *
//	 * @param registry
//	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		// registry.addInterceptor(handlerInterceptor())
//		// .addPathPatterns("/**")
//		// 排除Api Doc的权限验证
//		// .excludePathPatterns("swagger-ui.html", "/webjars**",
//		// "/swagger-resources/**", "/static/**", "/resources/**",
//		// "/gupiao/**");
//	}
//
//	/**
//	 * 解决静态资源无法访问的问题
//	 *
//	 * @param registry
//	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// Swagger接口文档
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars*").addResourceLocations("classpath:/META-INF/resources/webjars/");
//		// 静态资源
//		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("---");
		// Swagger接口文档 
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars*").addResourceLocations("classpath:/META-INF/resources/webjars/");
		// 静态资源
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		//如果是linux 发布 去掉classPath:
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");   
		
		//registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		//registry.addResourceHandler("/css/**").addResourceLocations("/css/");   
	}

}
