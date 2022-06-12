package tgbot.router_service.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tgbot.router_service.telegram.handlers.CallbackQueryHandler;
import tgbot.router_service.telegram.handlers.MessageHandler;

@PropertySource("/telegram.properties")
public class TelegramBot extends TelegramLongPollingBot {

    @Value( "${botName}" )
    private String botName;
    @Value( "${botToken}" )
    private String botToken;

    private final MessageHandler messageHandler;

    private final CallbackQueryHandler callbackQueryHandler;

    public TelegramBot(MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = messageHandler.processMessage(update.getMessage());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (update.hasCallbackQuery()) {
            SendMessage sendMessage = callbackQueryHandler.processCallbackQuery(update.getCallbackQuery());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


}