package tgbot.router_service.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tgbot.router_service.model.Report;

import java.util.List;

public class ReportClient {

    private static final String SERVER_ADDRESS = "http://localhost:8081";

    private static final WebClient webClient = WebClient.create(SERVER_ADDRESS);

    private static final String REPORTS_URI = "/reports/";

    public static Report getReport(String id) {
        return webClient
                .get()
                .uri(REPORTS_URI + id)
                .retrieve()
                .bodyToMono(Report.class)
                .block();
    }

    public static List<Report> getAllReports() {
        return webClient
                .get()
                .uri(REPORTS_URI)
                .retrieve()
                .bodyToFlux(Report.class)
                .collectList()
                .block();
    }

    public static Report createReport(Report report) {
        return webClient
                .post()
                .uri(REPORTS_URI)
                .body(Mono.just(report), Report.class)
                .retrieve()
                .bodyToMono(Report.class)
                .block();
    }

    public static void updateReport(String id, Report report) {
        webClient
                .put()
                .uri(REPORTS_URI + id)
                .body(Mono.just(report), Report.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public static void deleteReport(long id) {
        webClient
                .delete()
                .uri(REPORTS_URI + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
