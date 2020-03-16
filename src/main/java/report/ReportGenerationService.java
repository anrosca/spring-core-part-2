package report;

import java.time.LocalDate;

public class ReportGenerationService {

    private ReportGenerationContext context;

    public ReportGenerationService(ReportGenerationContext context) {
        this.context = context;
    }

    public void generateReport(LocalDate date, String cityName) {
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

    private boolean isInvalidDate(LocalDate date) {
        return date == null;
    }

    public static class InvalidDateException extends RuntimeException {
    }
}
