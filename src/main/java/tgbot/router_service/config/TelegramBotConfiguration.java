package tgbot.router_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tgbot.router_service.service.TaskClient;
import tgbot.router_service.service.TrackingClient;
import tgbot.router_service.service.UserClient;
import tgbot.router_service.telegram.TelegramBot;
import tgbot.router_service.telegram.handlers.CallbackQueryHandler;
import tgbot.router_service.telegram.handlers.MessageHandler;
import tgbot.router_service.telegram.handlers.TrackingMessagesHandler;
import tgbot.router_service.telegram.util.KeyboardsMaker;
import tgbot.router_service.telegram.util.TrackingUtil;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class TelegramBotConfiguration {

    private final UserClient userClient;

    private final TaskClient taskClient;

    private final TrackingClient trackingClient;

    private final TrackingUtil trackingUtil;

    private final ResourceBundle resourceBundle;

    public TelegramBotConfiguration(UserClient userClient, TaskClient taskClient,
                                    TrackingClient trackingClient, TrackingUtil trackingUtil,
                                    @Value("${telegram.messages.language}") String language) {
        this.userClient = userClient;
        this.taskClient = taskClient;
        this.trackingClient = trackingClient;
        this.trackingUtil = trackingUtil;
        resourceBundle = ResourceBundle.getBundle("i18n/messages", new Locale(language));
    }

    @Bean
    public TelegramBotsApi registerBot(TelegramBot telegramBot) {
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }

    @Bean
    public KeyboardsMaker keyboardsMaker() {
        return new KeyboardsMaker(resourceBundle);
    }

    @Bean
    public MessageHandler messageHandler(KeyboardsMaker keyboardsMaker) {
        return new MessageHandler(keyboardsMaker, userClient, taskClient, trackingClient, resourceBundle);
    }

    @Bean
    public TrackingMessagesHandler trackingMessagesHandler() {
        return new TrackingMessagesHandler(trackingClient, trackingUtil, resourceBundle);
    }

    @Bean
    public CallbackQueryHandler callbackQueryHandler(KeyboardsMaker keyboardsMaker,
                                                     TrackingMessagesHandler trackingMessagesHandler) {
        return new CallbackQueryHandler(keyboardsMaker, userClient, trackingMessagesHandler, resourceBundle);
    }

}
