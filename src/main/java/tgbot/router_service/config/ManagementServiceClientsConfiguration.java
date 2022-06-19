package tgbot.router_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tgbot.router_service.service.ReportClient;
import tgbot.router_service.service.TaskClient;
import tgbot.router_service.service.TrackingClient;

@Configuration
public class ManagementServiceClientsConfiguration {

    @Value("${clients.server.address}")
    private String SERVER_ADDRESS;

    @Bean
    public ReportClient reportClient() {
        ReportClient reportClient = new ReportClient();
        reportClient.setParameters(SERVER_ADDRESS);
        return reportClient;
    }

    @Bean
    public TaskClient taskClient() {
        TaskClient taskClient = new TaskClient();
        taskClient.setParameters(SERVER_ADDRESS);
        return taskClient;
    }

    @Bean
    public TrackingClient trackingClient() {
        TrackingClient trackingClient = new TrackingClient();
        trackingClient.setParameters(SERVER_ADDRESS);
        return trackingClient;
    }
}
