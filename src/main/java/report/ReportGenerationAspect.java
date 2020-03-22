package report;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Aspect
public class ReportGenerationAspect {

    @Before(value = "execution(* report.ReportService.generateReport(..)) && args(request)", argNames = "request")
    public void before(ReportGenerationRequest request)  {
        System.out.println("Generating report: " + request.getDate() + ", " + request.getCity());
    }

    @Before(value = "execution(* report.ReportService.generateReport(..)) && args(request)", argNames = "request")
    public void after(ReportGenerationRequest request)  {
        System.out.println("Report was generated: " + request.getDate() + ", " + request.getCity());
    }
}
