package report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public List<Report> getAll() {
        return reportService.getAll();
    }

    @GetMapping("{reportId}")
    public Report getById(@PathVariable("reportId") long reportId) {
        return reportService.getById(reportId);
    }

    @GetMapping(value = "{reportId}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public HttpEntity<InputStreamResource> downloadReport(@PathVariable("reportId") long reportId) {
        Report report = reportService.getById(reportId);
        InputStreamResource payload = new InputStreamResource(new ByteArrayInputStream(report.getContent()));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + report.getFileName());
        return new HttpEntity<>(payload, headers);
    }


    @DeleteMapping("{reportId}")
    public ResponseEntity<?> deleteById(@PathVariable("reportId") long reportId) {
        reportService.deleteById(reportId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody @Validated ReportGenerationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new IllegalArgumentException(allErrors.toString());
        }
        Report report = reportService.generateReport(request);
        return ResponseEntity.ok(report);
    }
}
