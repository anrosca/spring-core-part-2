package com.endava.weather.web;

import com.endava.weather.report.Report;
import com.endava.weather.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Report> getAll() {
        return reportService.getAll();
    }

    @GetMapping(params = "reportId")
    public ResponseEntity<Report> getById(@RequestParam("reportId") long reportId) {
        Report report = reportService.getById(reportId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("{reportId}/download")
    public HttpEntity<InputStreamResource> downloadReport(@PathVariable long reportId) {
        Report report = reportService.getById(reportId);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(report.getContent()));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + report.getFileName());
        return new HttpEntity<>(resource, headers);
    }

    @DeleteMapping("{reportId}")
    public ResponseEntity<?> deleteById(@PathVariable long reportId) {
        reportService.deleteById(reportId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Report> createReport(@RequestBody @Validated ReportGenerationRequest request) {
        Report report = reportService.generateReport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }
}
