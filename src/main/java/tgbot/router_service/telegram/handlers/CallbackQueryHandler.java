package tgbot.router_service.telegram.handlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import tgbot.router_service.service.UserClient;
import tgbot.router_service.telegram.util.KeyboardsMaker;
import tgbot.router_service.telegram.util.TelegramUser;
import tgbot.router_service.telegram.util.UserCommandsCache;
import tgbot.users.service.GetUserResponse;
import tgbot.users.service.UserDTO;

@Service
public class CallbackQueryHandler {
    private final KeyboardsMaker keyboardsMaker;

    private final UserClient userClient;

    private final TrackingMessagesHandler trackingMessagesHandler;

    public CallbackQueryHandler(KeyboardsMaker keyboardsMaker, UserClient userClient,
                                TrackingMessagesHandler trackingMessagesHandler) {
        this.keyboardsMaker = keyboardsMaker;
        this.userClient = userClient;
        this.trackingMessagesHandler = trackingMessagesHandler;
    }

    public SendMessage processCallbackQuery(CallbackQuery callbackQuery) {
        final String chatId = callbackQuery.getMessage().getChatId().toString();
        String data = callbackQuery.getData();
        String command = UserCommandsCache.getCommand(new TelegramUser(callbackQuery.getMessage().getChatId().toString()));

        switch (data) {
            case "admin":
                return getAdministration(chatId);
            case "users":
                return manageUsers(chatId);
            case "addUser":
                return addUsers(chatId, callbackQuery.getMessage().getFrom());
            case "track":
                return manageTrackings(chatId);
            case "createTracking":
                return trackingMessagesHandler.createTracking(chatId);
            case "updateTracking":
                return trackingMessagesHandler.listTracking(chatId, "updateTracking", "update");
            case "deleteTracking":
                return trackingMessagesHandler.listTracking(chatId, "deleteTracking", "delete");
            case "closeTracking":
                return trackingMessagesHandler.listTracking(chatId, "closeTracking", "close");
            default:
                if (!command.equals("")) {
                    if (command.equals("updateTracking")) {
                        return trackingMessagesHandler.updateTracking(chatId, callbackQuery.getData());
                    }
                    if (command.equals("deleteTracking")) {
                        return trackingMessagesHandler.deleteTracking(chatId, callbackQuery.getData());
                    }
                    if (command.equals("closeTracking")) {
                        return trackingMessagesHandler.closeTracking(chatId, callbackQuery.getData());
                    }
                }
                SendMessage answer = new SendMessage();
                answer.setChatId(chatId);
                answer.setText("I don't know what to do..");
                return answer;
        }
    }

    private SendMessage manageTrackings(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please choose an action from keyboard");
        sendMessage.setReplyMarkup(keyboardsMaker.getTrackingKeyboard());
        return sendMessage;
    }

    private SendMessage getAdministration(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please choose an action from keyboard");
        sendMessage.setReplyMarkup(keyboardsMaker.getAdministrationKeyboard());
        return sendMessage;
    }

    private SendMessage manageUsers(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please choose an action from keyboard");
        sendMessage.setReplyMarkup(keyboardsMaker.getUserKeyboard());
        return sendMessage;
    }

    private SendMessage addUsers(String chatId, User user) {
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

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Success! User added.");
        return sendMessage;
    }
}
