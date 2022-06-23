package tgbot.router_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tgbot.router_service.model.Task;
import tgbot.router_service.model.Tracking;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackingClientIT {

    @Autowired
    private TrackingClient trackingClient;

    @Autowired
    private TaskClient taskClient;

    @Test
    void getTracking() {
        Tracking tracking = trackingClient.getTracking("1");

        assertEquals("Adding connection to users service", tracking.getTrackingNote());
    }

    @Test
    void getAllTrackings() {
        List<Tracking> allTrackings = trackingClient.getAllTrackings();

        assertNotNull(allTrackings);
        assertTrue(allTrackings.size() > 0);
    }

    @Test
    void createTracking() {
        Task task = taskClient.getTask("1");
        Tracking tracking = new Tracking("Test Note", task, 1L);

        Tracking created = trackingClient.createTracking(tracking);

        assertEquals("Test Note", created.getTrackingNote());
    }

    @Test
    void updateTracking() {
        Tracking tracking = trackingClient.getTracking("2");

        tracking.setTrackingNote("Changed Note");
        trackingClient.updateTracking("2", tracking);

        Tracking updated = trackingClient.getTracking("2");
        assertEquals("Changed Note", updated.getTrackingNote());
    }

    @Test
    void deleteTracking() {
        trackingClient.deleteTracking(7L);

        Tracking deleted = trackingClient.getTracking("7");
        assertNull(deleted);
    }
}