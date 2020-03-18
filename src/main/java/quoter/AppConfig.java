package quoter;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import report.*;

import java.util.List;

@Configuration/*(proxyBeanMethods = false)*/
@ComponentScan(basePackages = "report")
//@ImportResource("classpath:beans.xml")
@EnableAsync
@EnableCaching
public class AppConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache("report")
        ));
        return cacheManager;
    }

    /*@Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(5);
        return taskExecutor;
    }*/

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /*@Scope("prototype")
    @Bean(name = "quoter", initMethod = "sayQuote", destroyMethod = "destroy")
    public TerminatorQuoter terminatorQuoter() {
        TerminatorQuoter quoter = new TerminatorQuoter();
        quoter.setMessage("I'll be back!!");
        return quoter;
    }

    @Bean
    public Thermometer thermometer1() {
        System.out.println("Instantiating thermometer");
        return new Thermometer();
    }

    @Bean
    public WeatherStation weatherStation(Thermometer thermometer) {
        return new WeatherStation(thermometer);
    }

//    @Bean
//    public WeatherStation weatherStation2() {
//        return new WeatherStation(thermometer1());
//    }

    @Bean
    public JasperReportGenerator jasperReportGenerator() {
        return new JasperReportGenerator();
    }

    @Bean
    public ReportGenerationContextImpl reportGenerationContext(JasperReportGenerator jasperReportGenerator,
                                                               WeatherStation weatherStation) {
        return new ReportGenerationContextImpl(jasperReportGenerator, weatherStation);
    }

    @Bean
    public ReportGenerationService reportGenerationService(ReportGenerationContext context) {
        ReportGenerationService generationService = new ReportGenerationService();
        generationService.setContext(context);
        return generationService;
    }*/
}
