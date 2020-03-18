package report;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ReportGenerationContextImpl implements ReportGenerationContext {
    private static final String REPORT_FILE_NAME_PREFIX = "reports/";
    private static final String REPORT_FILE_NAME_SUFFIX = "_temperature.pdf";

    private final JasperReportGenerator reportGenerator;
    private final WeatherStation weatherStation;

    @Override
    public void generateReportFor(LocalDate date, String cityName) {
        try {
            byte[] reportContent = reportGenerator.generate(weatherStation, cityName, date);
            Files.write(Paths.get(generateFileName(date)), reportContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateFileName(LocalDate date) {
        return REPORT_FILE_NAME_PREFIX + date + REPORT_FILE_NAME_SUFFIX;
    }
}
