package tgbot.router_service.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tgbot.router_service.model.Task;

import java.util.List;

public class TaskClient {

    private static final String SERVER_ADDRESS = "http://localhost:8081";

    private static final WebClient webClient = WebClient.create(SERVER_ADDRESS);

    private static final String TASKS_URI = "/tasks/";

    public static Task getTask(String id) {
        return webClient
                .get()
                .uri(TASKS_URI + id)
                .retrieve()
                .bodyToMono(Task.class)
                .block();
    }

    public static List<Task> getAllTasks() {
        return webClient
                .get()
                .uri(TASKS_URI)
                .retrieve()
                .bodyToFlux(Task.class)
                .collectList()
                .block();
    }

    public static Task createTask(Task task) {
        return webClient
                .post()
                .uri(TASKS_URI)
                .body(Mono.just(task), Task.class)
                .retrieve()
                .bodyToMono(Task.class)
                .block();
    }

    public static void updateTask(String id, Task task) {
        webClient
                .put()
                .uri(TASKS_URI + id)
                .body(Mono.just(task), Task.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public static void deleteTask(long id) {
        webClient
                .delete()
                .uri(TASKS_URI + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}