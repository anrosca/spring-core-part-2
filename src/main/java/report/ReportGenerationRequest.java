package report;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ReportGenerationRequest {

    @Size(min = 2)
    private String city;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String date;
}
