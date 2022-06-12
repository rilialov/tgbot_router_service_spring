package tgbot.router_service;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tgbot.router_service.telegram.TelegramBot;

public class ServletInitializer extends SpringBootServletInitializer {
    private final TelegramBot telegramBot;

    public ServletInitializer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        try {
            System.out.println("servlet configure");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
            System.out.println("bot registered");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return application.sources(TgbotRouterServiceSpringApplication.class);
    }

}
