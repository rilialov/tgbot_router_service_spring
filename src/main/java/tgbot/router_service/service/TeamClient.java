package tgbot.router_service.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tgbot.users.service.*;

public class TeamClient extends WebServiceGatewaySupport {

    private final String USERS_URI = "http://localhost:8080/ws/users";
    private final String NAMESPACE_URI = "http://users.tgbot/service";

    public GetTeamResponse getTeamById(long id) {
        GetTeamByIdRequest getTeamByIdRequest = new GetTeamByIdRequest();
        getTeamByIdRequest.setId(id);
        return (GetTeamResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, getTeamByIdRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/GetTeamByIdRequest"));
    }

    public GetAllTeamsResponse getAllTeams() {
        GetAllTeamsRequest getAllTeamsRequest = new GetAllTeamsRequest();
        return (GetAllTeamsResponse) getWebServiceTemplate().
                marshalSendAndReceive(USERS_URI, getAllTeamsRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/GetAllTeamsRequest"));
    }

    public GetTeamResponse saveTeam(TeamDTO teamDTO) {
        SaveTeamRequest saveTeamRequest = new SaveTeamRequest();
        saveTeamRequest.setTeamDTO(teamDTO);
        return (GetTeamResponse) getWebServiceTemplate().
                marshalSendAndReceive(USERS_URI, saveTeamRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/SaveTeamRequest"));
    }

    public GetBooleanResponse deleteTeamById(long id) {
        DeleteTeamByIdRequest deleteTeamByIdRequest = new DeleteTeamByIdRequest();
        deleteTeamByIdRequest.setId(id);
        return (GetBooleanResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, deleteTeamByIdRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/DeleteTeamByIdRequest"));
    }
}
