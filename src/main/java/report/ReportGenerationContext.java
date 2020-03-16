package report;

import java.time.LocalDate;

public interface ReportGenerationContext {
    void generateReportFor(LocalDate date, String cityName);
}
