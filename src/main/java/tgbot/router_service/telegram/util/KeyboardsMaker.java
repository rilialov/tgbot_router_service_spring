package tgbot.router_service.telegram.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class KeyboardsMaker {
    private final InlineKeyboardMarkup startKeyboard;
    private final InlineKeyboardMarkup administrationKeyboard;
    private final InlineKeyboardMarkup trackingKeyboard;
    private final InlineKeyboardMarkup userKeyboard;

    private final ResourceBundle resourceBundle;

    public KeyboardsMaker(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        startKeyboard = setStartKeyboard();
        administrationKeyboard = setAdministrationKeyboard();
        trackingKeyboard = setTrackingKeyboard();
        userKeyboard = setUserKeyboard();
    }

    public InlineKeyboardMarkup setStartKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(resourceBundle.getString("keyboard.start.add.user"));
        inlineKeyboardButton1.setCallbackData("addUser");
        inlineKeyboardButton2.setText(resourceBundle.getString("keyboard.start.administration"));
        inlineKeyboardButton2.setCallbackData("admin");
        inlineKeyboardButton3.setText(resourceBundle.getString("keyboard.start.tracking"));
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

    private InlineKeyboardMarkup setTrackingKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(resourceBundle.getString("keyboard.tracking.create"));
        inlineKeyboardButton1.setCallbackData("createTracking");
        inlineKeyboardButton2.setText(resourceBundle.getString("keyboard.tracking.update"));
        inlineKeyboardButton2.setCallbackData("updateTracking");
        inlineKeyboardButton3.setText(resourceBundle.getString("keyboard.tracking.delete"));
        inlineKeyboardButton3.setCallbackData("deleteTracking");
        inlineKeyboardButton4.setText(resourceBundle.getString("keyboard.tracking.close"));
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

    private InlineKeyboardMarkup setAdministrationKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText(resourceBundle.getString("keyboard.administration.users"));
        inlineKeyboardButton1.setCallbackData("users");
        inlineKeyboardButton2.setText(resourceBundle.getString("keyboard.administration.teams"));
        inlineKeyboardButton2.setCallbackData("teams");
        inlineKeyboardButton3.setText(resourceBundle.getString("keyboard.administration.tasks"));
        inlineKeyboardButton3.setCallbackData("tasks");
        inlineKeyboardButton4.setText(resourceBundle.getString("keyboard.administration.reports"));
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

    private InlineKeyboardMarkup setUserKeyboard() {
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
