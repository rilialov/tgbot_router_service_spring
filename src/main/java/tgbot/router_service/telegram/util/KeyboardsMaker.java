package tgbot.router_service.telegram.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardsMaker {
    private final InlineKeyboardMarkup startKeyboard;
    private final InlineKeyboardMarkup administrationKeyboard;
    private final InlineKeyboardMarkup trackingKeyboard;
    private final InlineKeyboardMarkup userKeyboard;

    public KeyboardsMaker() {
        startKeyboard = setStartKeyboard();
        administrationKeyboard = setAdministrationKeyboard();
        trackingKeyboard = setTrackingKeyboard();
        userKeyboard = setUserKeyboard();
    }

    public static InlineKeyboardMarkup setStartKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Add me to users list");
        inlineKeyboardButton1.setCallbackData("addUser");
        inlineKeyboardButton2.setText("Administration");
        inlineKeyboardButton2.setCallbackData("admin");
        inlineKeyboardButton3.setText("Tracking");
        inlineKeyboardButton3.setCallbackData("track");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardMarkup setTrackingKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Create Tracking");
        inlineKeyboardButton1.setCallbackData("createTracking");
        inlineKeyboardButton2.setText("Update Tracking");
        inlineKeyboardButton2.setCallbackData("updateTracking");
        inlineKeyboardButton3.setText("Delete Tracking");
        inlineKeyboardButton3.setCallbackData("deleteTracking");
        inlineKeyboardButton4.setText("Close Tracking");
        inlineKeyboardButton4.setCallbackData("closeTracking");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardMarkup setAdministrationKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Users");
        inlineKeyboardButton1.setCallbackData("users");
        inlineKeyboardButton2.setText("Teams");
        inlineKeyboardButton2.setCallbackData("teams");
        inlineKeyboardButton3.setText("Tasks");
        inlineKeyboardButton3.setCallbackData("tasks");
        inlineKeyboardButton4.setText("Reports");
        inlineKeyboardButton4.setCallbackData("reports");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardMarkup setUserKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Update user");
        inlineKeyboardButton1.setCallbackData("updateUser");
        inlineKeyboardButton2.setText("Delete user");
        inlineKeyboardButton2.setCallbackData("deleteUser");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getStartKeyboard() {
        return startKeyboard;
    }

    public InlineKeyboardMarkup getAdministrationKeyboard() {
        return administrationKeyboard;
    }

    public InlineKeyboardMarkup getTrackingKeyboard() {
        return trackingKeyboard;
    }

    public InlineKeyboardMarkup getUserKeyboard() {
        return userKeyboard;
    }
}
