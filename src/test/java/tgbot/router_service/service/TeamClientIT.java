package tgbot.router_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tgbot.users.service.GetTeamResponse;
import tgbot.users.service.TeamDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamClientTest {

    @Autowired
    private TeamClient teamClient;

    @Test
    void getTeamById() {
        GetTeamResponse teamResponse = teamClient.getTeamById(1L);
        TeamDTO teamDTO = teamResponse.getTeamDTO();

        assertEquals("Admin Team", teamDTO.getTeamName());
        assertEquals("Grey", teamDTO.getTeamColor());
    }

    @Test
    void getAllTeams() {

    }

    @Test
    void saveTeam() {
    }

    @Test
    void deleteTeamById() {
    }
}