package tgbot.router_service.telegram.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserCommandsCache {

    private static final Map<TelegramUser, String> userToCommandsMap = new ConcurrentHashMap<>();

    private static final Map<TelegramUser, String> userToArgumentsMap = new ConcurrentHashMap<>();

    public static void putUserAndCommand(TelegramUser user, String command) {
        userToCommandsMap.put(user, command);
    }

    public static void putUserAndArgument(TelegramUser user, String argument) {
        userToArgumentsMap.put(user, argument);
    }

    public static String getCommand(TelegramUser user) {
        String command = userToCommandsMap.get(user);
        putUserAndCommand(user, "");
        return command;
    }

    public static String getArgument(TelegramUser user) {
        String command = userToArgumentsMap.get(user);
        putUserAndArgument(user, "");
        return command;
    }

    public static Map<TelegramUser, String> getUserToCommandsMap() {
        return  userToCommandsMap;
    }

    public static Map<TelegramUser, String> getUserToArgumentsMap() {
        return  userToArgumentsMap;
    }
}
