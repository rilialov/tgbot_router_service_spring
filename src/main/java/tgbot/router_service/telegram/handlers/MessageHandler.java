package tgbot.router_service.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import tgbot.router_service.model.Task;
import tgbot.router_service.model.Tracking;
import tgbot.router_service.service.TaskClient;
import tgbot.router_service.service.TrackingClient;
import tgbot.router_service.service.UserClient;
import tgbot.router_service.telegram.util.KeyboardsMaker;
import tgbot.router_service.telegram.util.TelegramUser;
import tgbot.router_service.telegram.util.UserCommandsCache;
import tgbot.users.service.GetUserResponse;
import tgbot.users.service.UserDTO;

import java.util.ResourceBundle;

public class MessageHandler {
    private final KeyboardsMaker keyboardsMaker;

    private final UserClient userClient;

    private final TaskClient taskClient;

    private final TrackingClient trackingClient;

    private final ResourceBundle resourceBundle;

    public MessageHandler(KeyboardsMaker keyboardsMaker, UserClient userClient, TaskClient taskClient,
                          TrackingClient trackingClient, ResourceBundle resourceBundle) {
        this.keyboardsMaker = keyboardsMaker;
        this.userClient = userClient;
        this.taskClient = taskClient;
        this.trackingClient = trackingClient;
        this.resourceBundle = resourceBundle;
    }

    public SendMessage processMessage(Message message) {
        final String chatId = message.getChatId().toString();
        String command = UserCommandsCache.getCommand(new TelegramUser(message.getChatId().toString()));

        if (message.getText().equals("/start")) {
            return getStartMessage(chatId, message.getFrom());
        } else if (!command.equals("")) {
            if (command.equals("createTracking")) {
                return getTrackingCreationMessage(chatId, message.getFrom(), message.getText());
            }
            if (command.equals("updateTracking")) {
                return getTrackingUpdatingMessage(chatId, message.getText());
            }
        }
        return getSendMessage(chatId, resourceBundle.getString("message.unknown"));
    }

    private SendMessage getStartMessage(String chatId, User user) {
        GetUserResponse response = userClient.getUserById(Long.parseLong(chatId));
        UserDTO userDTO = response.getUserDTO();
        if (userDTO == null) {
            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setChatID(Long.parseLong(chatId));
            newUserDTO.setFirstName(user.getFirstName());
            newUserDTO.setNickname(user.getUserName());
            newUserDTO.setLastName(user.getLastName());
            userClient.saveUser(newUserDTO);
        } else {
            userDTO.setNickname(user.getUserName());
            userDTO.setLastName(user.getLastName());
            userClient.saveUser(userDTO);
        }

        SendMessage answer = getSendMessage(chatId, resourceBundle.getString("message.hi") + ", " +
                user.getFirstName() + "! " + resourceBundle.getString("message.choose"));
        answer.setReplyMarkup(keyboardsMaker.getStartKeyboard());
        return answer;
    }

    private SendMessage getTrackingCreationMessage(String chatId, User user, String trackingNote) {
        Task task = taskClient.getTask("1");
        Tracking tracking = new Tracking(trackingNote, task, user.getId());
        trackingClient.createTracking(tracking);
        return getSendMessage(chatId, resourceBundle.getString("message.tracking.created"));
    }

    private SendMessage getTrackingUpdatingMessage(String chatId, String trackingNote) {
        String argument = UserCommandsCache.getArgument(new TelegramUser(chatId));
        Tracking tracking = trackingClient.getTracking(argument);
        tracking.setTrackingNote(trackingNote);
        trackingClient.updateTracking(String.valueOf(tracking.getId()), tracking);
        return getSendMessage(chatId, resourceBundle.getString("message.tracking.updated"));
    }

    private SendMessage getSendMessage(String chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText(text);
        return answer;
    }

}
