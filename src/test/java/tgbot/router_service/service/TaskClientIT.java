package tgbot.router_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tgbot.router_service.model.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskClientIT {

    @Test
    void getTask() {
        Task task = TaskClient.getTask("1");

        assertEquals("Create Service connected to telegram", task.getTaskName());
    }

    @Test
    void getAllTask() {
        List<Task> allTasks = TaskClient.getAllTasks();

        assertNotNull(allTasks);
        assertTrue(allTasks.size() > 0);
    }


    @Test
    void createTask() {
        Task task = new Task("Test Task Name", "Test Task Note");

        Task created = TaskClient.createTask(task);

        assertEquals("Test Task Name", created.getTaskName());
        assertEquals("Test Task Note", created.getTaskNote());
    }

    @Test
    void updateTask() {
        Task task = TaskClient.getTask("2");

        task.setTaskNote("Changed Note");
        TaskClient.updateTask("2", task);

        Task updated = TaskClient.getTask("2");
        assertEquals("Changed Note", updated.getTaskNote());
    }

    @Test
    void deleteTask() {
        TaskClient.deleteTask(4);

        Task deleted = TaskClient.getTask("4");
        assertEquals(0L, deleted.getId());
    }
}