package quoter;

import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import report.A;
import report.ReportGenerationService;

import java.time.LocalDate;

public class ApplicationRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(AppConfig.class);
//        context.getBean(A.class);
//        TerminatorQuoter quoter = (TerminatorQuoter) context.getBean("second");
//        quoter.sayQuote();
//        quoter.setMessage("Hasta la vista, baby!");
//
//        context.getBean(TerminatorQuoter.class).sayQuote();
//
//        System.out.println( context.getBean(TerminatorQuoter.class));
//        System.out.println( context.getBean(TerminatorQuoter.class));
//        System.out.println( context.getBean(TerminatorQuoter.class));

        ReportGenerationService generationserivce = context.getBean(
                ReportGenerationService.class);
        generationserivce.generateReport(LocalDate.of(2011 ,11, 28), "Chisinau");
        generationserivce.generateReport(LocalDate.of(2011 ,11, 28), "Chisinau");

        CacheManager cacheManager = context.getBean(CacheManager.class);
        cacheManager.getCache("report");

        generationserivce.generateReport(LocalDate.of(2011 ,11, 28), "Chisinau");
        context.close();
    }
}
