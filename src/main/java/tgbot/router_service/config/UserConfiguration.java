package tgbot.management_service.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import tgbot.management_service.service.TeamClient;
import tgbot.management_service.service.UserClient;

@Configuration
public class UserConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("tgbot.users.service");
        return marshaller;
    }

    @Bean
    public UserClient userClient(Jaxb2Marshaller marshaller) {
        UserClient client = new UserClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public TeamClient teamClient(Jaxb2Marshaller marshaller) {
        TeamClient client = new TeamClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
