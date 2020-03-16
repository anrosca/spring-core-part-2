package report;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

public class ApplicationRunner {
    public static void main(String[] args) {
//        JasperReportGenerator generator = new JasperReportGenerator();
//        WeatherStation weatherStation = new WeatherStation();
//        ReportGenerationContextImpl context = new ReportGenerationContextImpl(generator, weatherStation);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        ReportGenerationService reportGenerationService = context.getBean(ReportGenerationService.class);
//        reportGenerationService.generateReport(LocalDate.now(), "Chisinau");
    }
}
