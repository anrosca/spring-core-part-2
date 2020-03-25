package com.endava.weather.service;

import com.endava.weather.report.Report;
import com.endava.weather.repository.ReportRepository;
import com.endava.weather.web.ReportGenerationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
//@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;

    private final ReportGenerationService reportGenerationService;

    @Autowired
    public ReportService(ReportRepository reportRepository, ReportGenerationService reportGenerationService) {
        this.reportRepository = reportRepository;
        this.reportGenerationService = reportGenerationService;
    }

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public Report getById(long reportId) {
        return reportRepository.findById(reportId);
    }

    public void deleteById(long reportId) {
        reportRepository.deleteById(reportId);
    }

    public Report generateReport(ReportGenerationRequest request) {//yyyy-MM-dd HH:mm:ss
        Report report = reportGenerationService.generateReportFor(LocalDate.parse(request.getDate()),
                request.getCity());
        reportRepository.save(report);
        return report;
    }

}
