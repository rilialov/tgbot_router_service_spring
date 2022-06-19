package tgbot.router_service.telegram.handlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import tgbot.router_service.model.Tracking;
import tgbot.router_service.service.TrackingClient;
import tgbot.router_service.telegram.util.TelegramUser;
import tgbot.router_service.telegram.util.TrackingUtil;
import tgbot.router_service.telegram.util.UserCommandsCache;

import java.time.LocalDateTime;

@Service
public class TrackingMessagesHandler {

    private final TrackingClient trackingClient;

    private final TrackingUtil trackingUtil;

    public TrackingMessagesHandler(TrackingClient trackingClient, TrackingUtil trackingUtil) {
        this.trackingClient = trackingClient;
        this.trackingUtil = trackingUtil;
    }

    SendMessage createTracking(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please enter tracking note");
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), "createTracking");
        return sendMessage;
    }

    SendMessage listTracking(String chatId, String command, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please choose tracking for " + messageText);
        sendMessage.setReplyMarkup(trackingUtil.setTrackingListKeyBoard(Long.parseLong(chatId)));
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), command);
        return sendMessage;
    }

    SendMessage updateTracking(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please enter tracking note");
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), "updateTracking");
        UserCommandsCache.putUserAndArgument(new TelegramUser(chatId), text);
        return sendMessage;
    }

    SendMessage deleteTracking(String chatId, String text) {
        trackingClient.deleteTracking(Long.parseLong(text));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Success! Tracking deleted.");
        return sendMessage;
    }

    SendMessage closeTracking(String chatId, String text) {
        Tracking tracking = trackingClient.getTracking(text);
        tracking.setEndTime(LocalDateTime.now());
        trackingClient.updateTracking(String.valueOf(tracking.getId()), tracking);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Success! Tracking closed.");
        return sendMessage;
    }
}
