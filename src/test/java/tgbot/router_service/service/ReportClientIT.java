package tgbot.router_service.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tgbot.router_service.model.Report;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportClientIT {

    @Test
    void getReport() {
        Report report = ReportClient.getReport("2");

        assertEquals(864000L, report.getFullTime());
    }

    @Test
    void getAllReports() {
        List<Report> allReports = ReportClient.getAllReports();

        assertNotNull(allReports);
        assertTrue(allReports.size() > 0);
    }

    @Test
    void createReport() {
        Report report = new Report(LocalDate.now(), 2L);

        Report created = ReportClient.createReport(report);

        assertEquals(2L, created.getUser());
    }

    @Test
    void updateReport() {
        Report report = ReportClient.getReport("1");

        report.setFullTime(888000L);
        ReportClient.updateReport("1", report);

        Report updated = ReportClient.getReport("1");
        assertEquals(888000L, updated.getFullTime());
    }

    @Test
    @Disabled
    void deleteReport() {
        ReportClient.deleteReport(10L);

        Report deleted = ReportClient.getReport("10");
        assertNull(deleted);
    }
}