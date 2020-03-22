package report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Service
@Validated
@RequiredArgsConstructor
public class ReportGenerationService {
    private static final String REPORT_FILE_NAME_PREFIX = "reports/";
    private static final String REPORT_FILE_NAME_SUFFIX = "_temperature.pdf";

    private final JasperReportGenerator reportGenerator;
    private final WeatherStation weatherStation;

    public Report generateReportFor(@Past @NotNull LocalDate date, @Size(min = 2) String cityName) {
        try {
            byte[] reportContent = reportGenerator.generate(weatherStation, cityName, date);
            return Report.builder()
                    .content(reportContent)
                    .fileName(generateFileName(date))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateFileName(LocalDate date) {
        return REPORT_FILE_NAME_PREFIX + date + REPORT_FILE_NAME_SUFFIX;
    }

    public void generateMonthlyReports(String cityName) {
        LocalDate date = LocalDate.now().minusMonths(1);
        if (date.getDayOfMonth() == 1) {
            for (int day = 1; day <= date.lengthOfMonth(); ++day) {
                generateReportFor(date.withDayOfMonth(day), cityName);
            }
        }
    }

    private boolean isInvalidDate(LocalDate date) {
        return date == null;
    }

    public static class InvalidDateException extends RuntimeException {
    }
}
