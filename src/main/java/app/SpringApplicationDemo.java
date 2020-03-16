package app;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import quoter.Quoter;
import quoter.TerminatorQuoter;
import report.ReportGenerationService;


import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;

public class SpringApplicationDemo {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Quoter quoter = context.getBean(Quoter.class);
        quoter.sayQuote();
        context.getBean(ReportGenerationService.class).generateReport(LocalDate.of(2011, 11, 27), "hisinau");
        context.getBean(ReportGenerationService.class).generateReport(LocalDate.of(2011, 11, 27), "hisinau");
//        System.out.println(context.getBean("movieQuoter"));
//        System.out.println(context.getBean("movieQuoter"));
//        System.out.println(context.getBean("movieQuoter"));
        context.close();
    }
}

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("report")
@EnableCaching
class Config extends CachingConfigurerSupport {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }


    @Bean
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
        return cacheManager;
    }

    @Scope("prototype")
    @Bean(destroyMethod = "destroy")
    public Quoter movieQuoter() {
        return new TerminatorQuoter();
    }
}
