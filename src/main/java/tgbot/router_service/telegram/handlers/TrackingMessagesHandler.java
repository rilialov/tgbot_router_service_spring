package tgbot.router_service.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import tgbot.router_service.model.Tracking;
import tgbot.router_service.service.TrackingClient;
import tgbot.router_service.telegram.util.TelegramUser;
import tgbot.router_service.telegram.util.TrackingUtil;
import tgbot.router_service.telegram.util.UserCommandsCache;


import java.time.LocalDateTime;

public class TrackingMessagesHandler {

    static SendMessage createTracking(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please enter tracking note");
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), "createTracking");
        return sendMessage;
    }

    static SendMessage listTracking(String chatId, String command, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please choose tracking for " + messageText);
        sendMessage.setReplyMarkup(TrackingUtil.setTrackingListKeyBoard(Long.parseLong(chatId)));
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), command);
        return sendMessage;
    }

    static SendMessage updateTracking(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please enter tracking note");
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), "updateTracking");
        UserCommandsCache.putUserAndArgument(new TelegramUser(chatId), text);
        return sendMessage;
    }

    static SendMessage deleteTracking(String chatId, String text) {
        TrackingClient.deleteTracking(Long.parseLong(text));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Success! Tracking deleted.");
        return sendMessage;
    }

    static SendMessage closeTracking(String chatId, String text) {
        Tracking tracking = TrackingClient.getTracking(text);
        tracking.setEndTime(LocalDateTime.now());
        TrackingClient.updateTracking(String.valueOf(tracking.getId()), tracking);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Success! Tracking closed.");
        return sendMessage;
    }
}
