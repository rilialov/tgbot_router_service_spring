package tgbot.router_service.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tgbot.router_service.model.Tracking;

import java.util.List;

public class TrackingClient {

    private WebClient webClient;

    private static final String TRACKINGS_URI = "/trackings/";

    public void setParameters(String address) {
        webClient = WebClient.create(address);
    }

    public Tracking getTracking(String id) {
        return webClient
                .get()
                .uri(TRACKINGS_URI + id)
                .retrieve()
                .bodyToMono(Tracking.class)
                .block();
    }

    public List<Tracking> getAllTrackings() {
        return webClient
                .get()
                .uri(TRACKINGS_URI)
                .retrieve()
                .bodyToFlux(Tracking.class)
                .collectList()
                .block();
    }

    public Tracking createTracking(Tracking tracking) {
        return webClient
                .post()
                .uri(TRACKINGS_URI)
                .body(Mono.just(tracking), Tracking.class)
                .retrieve()
                .bodyToMono(Tracking.class)
                .block();
    }

    public void updateTracking(String id, Tracking tracking) {
        webClient
                .put()
                .uri(TRACKINGS_URI + id)
                .body(Mono.just(tracking), Tracking.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteTracking(long id) {
        webClient
                .delete()
                .uri(TRACKINGS_URI + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
