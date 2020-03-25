package com.endava.weather.service;

import com.endava.weather.report.Report;
import com.endava.weather.report.WeatherStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class ReportGenerationService {
    private static final String REPORT_FILE_NAME_PREFIX = "reports/";
    private static final String REPORT_FILE_NAME_SUFFIX = "_temperature.pdf";

    private final JasperReportGenerator reportGenerator;
    private final WeatherStation weatherStation;

    public Report blah(LocalDate date, String cityName) {
        return this.generateReportFor(date, cityName);
    }

    public Report generateReportFor(LocalDate date, String cityName) {
        try {
//            System.out.println("Starting generating report: " + LocalDateTime.now());
            byte[] reportContent = reportGenerator.generate(weatherStation, cityName, date);
            return Report.builder()
                    .content(reportContent)
                    .fileName(generateFileName(date))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } /*finally {
//            System.out.println("Report was generated: " + LocalDateTime.now());
        }*/
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
}

