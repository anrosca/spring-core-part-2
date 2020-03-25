package com.endava.weather.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportGenerationRequest {

    @Size(min = 2)
    private String city;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
//    @IsoDateTime(pattern = "yyyy/MM/dd")
    private String date;
}
