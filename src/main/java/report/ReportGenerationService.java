package report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Service("ReportGenerationService")
//@Validated
public class ReportGenerationService {


    private ReportGenerationContext context;

//    @Autowired
    public ReportGenerationService(ReportGenerationContext context) {
        this.context = context;
    }

//    public ReportGenerationService() {
//    }

//    @Autowired
    public void setContext(ReportGenerationContext context) {
        this.context = context;
    }

//    @Async()
    public void generateReport(/*@Past @NotNull*/ LocalDate date, String cityName) {
        System.out.println("Thread name: " +Thread.currentThread().getName());
        context.generateReportFor(date, cityName);
    }

    public void generateMonthlyReports(String cityName) {
        LocalDate date = LocalDate.now().minusMonths(1);
        if (date.getDayOfMonth() == 1) {
            for (int day = 1; day <= date.lengthOfMonth(); ++day) {
                generateReport(date.withDayOfMonth(day), cityName);
            }
        }
    }

    private boolean isInvalidDate(LocalDate date) {
        return date == null;
    }

    public static class InvalidDateException extends RuntimeException {
    }
}
