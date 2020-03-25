package com.endava.weather.aspect;

import com.endava.weather.report.Report;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class ExecutionTimeLoggingAspect {
/*

    @Before("execution(* com.endava.weather.service.ReportGenerationService.generateReportFor(..))")
    public void onStart() {
        System.out.println("Starting generating report: " + LocalDateTime.now());
    }

    @AfterReturning(pointcut =
            "execution(* com.endava.weather.service.ReportGenerationService.generateReportFor(..))",
    returning = "report")
    public void onReturn(Report report) {
        System.out.println("Successfully generated report: " + report.getFileName());
    }

    @AfterThrowing(pointcut =
            "execution(* com.endava.weather.service.ReportGenerationService.generateReportFor(..))",
            throwing = "ex")
    public void onError(Throwable ex) {
        System.out.println("Exception thrown: " + ex);
    }

    @After("execution(* com.endava.weather.service.ReportGenerationService.generateReportFor(..))")
    public void onEnd() {
        System.out.println("Report was generated: " + LocalDateTime.now());
    }
*/

    @Around("execution(* com.endava.weather.service.ReportGenerationService.generateReportFor(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("Starting generating report: " + LocalDateTime.now());
        try {
            Report report = (Report) joinPoint.proceed();
            System.out.println("Report was generated: " + LocalDateTime.now());
            System.out.println("Successfully generated report: " + report.getFileName());
            return report;
        } catch (Throwable e) {
            System.out.println("Exception thrown: " + e);
            return null;
        }
    }
}
