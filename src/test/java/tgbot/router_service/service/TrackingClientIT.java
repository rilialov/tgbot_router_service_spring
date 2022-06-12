package tgbot.router_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tgbot.router_service.model.Task;
import tgbot.router_service.model.Tracking;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackingClientIT {

    @Test
    void getTracking() {
        Tracking tracking = TrackingClient.getTracking("1");

        assertEquals("Adding connection to users service", tracking.getTrackingNote());
    }

    @Test
    void getAllTrackings() {
        List<Tracking> allTrackings = TrackingClient.getAllTrackings();

        assertNotNull(allTrackings);
        assertTrue(allTrackings.size() > 0);
    }

    @Test
    void createTracking() {
        Task task = TaskClient.getTask("1");
        Tracking tracking = new Tracking("Test Note", task, 1L);

        Tracking created = TrackingClient.createTracking(tracking);

        assertEquals("Test Note", created.getTrackingNote());
    }

    @Test
    void updateTracking() {
        Tracking tracking = TrackingClient.getTracking("2");

        tracking.setTrackingNote("Changed Note");
        TrackingClient.updateTracking("2", tracking);

        Tracking updated = TrackingClient.getTracking("2");
        assertEquals("Changed Note", updated.getTrackingNote());
    }

    @Test
    void deleteTracking() {
        TrackingClient.deleteTracking(7L);

        Tracking deleted = TrackingClient.getTracking("7");
        assertEquals(0L, deleted.getId());
    }
}