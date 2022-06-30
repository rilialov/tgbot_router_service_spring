package tgbot.router_service.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import tgbot.router_service.model.Tracking;
import tgbot.router_service.service.TrackingClient;
import tgbot.router_service.telegram.util.TelegramUser;
import tgbot.router_service.telegram.util.TrackingUtil;
import tgbot.router_service.telegram.util.UserCommandsCache;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TrackingMessagesHandler {

    private final TrackingClient trackingClient;

    private final TrackingUtil trackingUtil;

    private final ResourceBundle resourceBundle;

    public TrackingMessagesHandler(TrackingClient trackingClient, TrackingUtil trackingUtil, ResourceBundle resourceBundle) {
        this.trackingClient = trackingClient;
        this.trackingUtil = trackingUtil;
        this.resourceBundle = resourceBundle;
    }

    SendMessage createTracking(String chatId) {
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), "createTracking");
        return getSendMessage(chatId, resourceBundle.getString("message.tracking.creation.note"));
    }

    SendMessage listTracking(String chatId, String command, String messageText) {
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), command);
        SendMessage sendMessage = getSendMessage(chatId, resourceBundle.getString("message.tracking.list")
                + " " + messageText);
        sendMessage.setReplyMarkup(trackingUtil.setTrackingListKeyBoard(Long.parseLong(chatId)));
        return sendMessage;
    }

    SendMessage updateTracking(String chatId, String text) {
        UserCommandsCache.putUserAndCommand(new TelegramUser(chatId), "updateTracking");
        UserCommandsCache.putUserAndArgument(new TelegramUser(chatId), text);
        return getSendMessage(chatId, resourceBundle.getString("message.tracking.creation.note"));
    }

    SendMessage deleteTracking(String chatId, String text) {
        trackingClient.deleteTracking(Long.parseLong(text));
        return getSendMessage(chatId, resourceBundle.getString("message.tracking.deleted"));
    }

    SendMessage closeTracking(String chatId, String text) {
        Tracking tracking = trackingClient.getTracking(text);
        tracking.setEndTime(LocalDateTime.now());
        trackingClient.updateTracking(String.valueOf(tracking.getId()), tracking);

        return getSendMessage(chatId, resourceBundle.getString("message.tracking.closed"));
    }

    private SendMessage getSendMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }
}
