package tgbot.router_service.telegram.handlers;

import org.springframework.stereotype.Service;
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

@Service
public class MessageHandler {
    private final KeyboardsMaker keyboardsMaker;
    private final UserClient userClient;

    public MessageHandler(KeyboardsMaker keyboardsMaker, UserClient userClient) {
        this.keyboardsMaker = keyboardsMaker;
        this.userClient = userClient;
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
        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText("I don't know what to do..");
        return answer;
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
        SendMessage answer = new SendMessage();
        answer.setReplyMarkup(keyboardsMaker.getStartKeyboard());
        answer.setChatId(chatId);
        answer.setText("Hi, " + user.getFirstName() + "! Please choose an action:");
        return answer;
    }

    private SendMessage getTrackingCreationMessage(String chatId, User user, String trackingNote) {
        Task task = TaskClient.getTask("1");
        Tracking tracking = new Tracking(trackingNote, task, user.getId());
        TrackingClient.createTracking(tracking);

        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText("Success! Tracking created.");
        return answer;
    }

    private SendMessage getTrackingUpdatingMessage(String chatId, String trackingNote) {
        String argument = UserCommandsCache.getArgument(new TelegramUser(chatId));
        Tracking tracking = TrackingClient.getTracking(argument);
        tracking.setTrackingNote(trackingNote);
        TrackingClient.updateTracking(String.valueOf(tracking.getId()), tracking);

        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText("Success! Tracking updated.");
        return answer;
    }

}
