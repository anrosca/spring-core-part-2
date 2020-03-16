package report;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Validated
@Component
public class ReportGenerationService {

    private final ReportGenerationContext context;

    public ReportGenerationService(ReportGenerationContext context) {
        this.context = context;
    }

//    @PostConstruct
//    public void init() {
//        generateReport(LocalDate.now(), "Chisinau");
//    }

    public void generateReport(@Past LocalDate date, String cityName) {
        System.out.println("Generating report: " + date + ", " + cityName);
        if (isInvalidDate(date)) {
            throw new InvalidDateException();
        }
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

    public void generateMonthlyReportsAsync(String cityName) {
        LocalDate date = LocalDate.now().minusMonths(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        if (date.getDayOfMonth() == 1) {
            IntStream.rangeClosed(1, date.lengthOfMonth()).forEach(day -> {
                executorService.submit(() -> generateReport(date.withDayOfMonth(day), cityName));
            });
        }
    }

    private boolean isInvalidDate(LocalDate date) {
        return date == null;
    }

    public static class InvalidDateException extends RuntimeException {
    }
}
