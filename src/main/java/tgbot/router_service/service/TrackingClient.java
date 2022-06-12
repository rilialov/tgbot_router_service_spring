package tgbot.router_service.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tgbot.router_service.model.Tracking;

import java.util.List;

public class TrackingClient {
    private static final String SERVER_ADDRESS = "http://localhost:8081";

    private static final WebClient webClient = WebClient.create(SERVER_ADDRESS);

    private static final String TRACKINGS_URI = "/trackings/";

    public static Tracking getTracking(String id) {
        return webClient
                .get()
                .uri(TRACKINGS_URI + id)
                .retrieve()
                .bodyToMono(Tracking.class)
                .block();
    }

    public static List<Tracking> getAllTrackings() {
        return webClient
                .get()
                .uri(TRACKINGS_URI)
                .retrieve()
                .bodyToFlux(Tracking.class)
                .collectList()
                .block();
    }

    public static Tracking createTracking(Tracking tracking) {
        return webClient
                .post()
                .uri(TRACKINGS_URI)
                .body(Mono.just(tracking), Tracking.class)
                .retrieve()
                .bodyToMono(Tracking.class)
                .block();
    }

    public static void updateTracking(String id, Tracking tracking) {
        webClient
                .put()
                .uri(TRACKINGS_URI + id)
                .body(Mono.just(tracking), Tracking.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public static void deleteTracking(long id) {
        webClient
                .delete()
                .uri(TRACKINGS_URI + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
