package tgbot.router_service.telegram.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import tgbot.router_service.model.Tracking;
import tgbot.router_service.service.TrackingClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrackingUtil {

    private static List<Tracking> getUserTrackings(long user) {
        List<Tracking> userTrackings = new ArrayList<>();
        List<Tracking> trackings = TrackingClient.getAllTrackings();
        for (Tracking tracking: trackings) {
            if (tracking.getUser() == user && tracking.getStartTime().toLocalDate().isEqual(LocalDate.now())) {
                userTrackings.add(tracking);
            }
        }
        return userTrackings;
    }

    public static InlineKeyboardMarkup setTrackingListKeyBoard(long user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<Tracking> trackings = getUserTrackings(user);

        for (Tracking tracking: trackings) {
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            inlineKeyboardButton1.setText(tracking.getTrackingNote());
            inlineKeyboardButton1.setCallbackData(String.valueOf(tracking.getId()));
            keyboardButtonsRow1.add(inlineKeyboardButton1);
            rowList.add(keyboardButtonsRow1);
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

}
