package tgbot.management_service.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tgbot.users.service.*;

public class UserClient extends WebServiceGatewaySupport {
    private final String USERS_URI = "http://localhost:8080/ws/users";
    private final String NAMESPACE_URI = "http://users.tgbot/service";

    public GetUserResponse getUserById(long chatId) {
        GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest();
        getUserByIdRequest.setChatId(chatId);
        return (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, getUserByIdRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/GetUserByIdRequest"));
    }

    public GetUserResponse getUserByNick(String nickname) {
        GetUserByNickRequest getUserByNickRequest = new GetUserByNickRequest();
        getUserByNickRequest.setNickname(nickname);
        return (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, getUserByNickRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/GetUserByNickRequest"));
    }

    public GetAllUsersResponse getAllUsers() {
        GetAllUsersRequest getUsersRequest = new GetAllUsersRequest();
        return (GetAllUsersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, getUsersRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/GetUsersRequest"));
    }

    public GetUserResponse saveUser(UserDTO userDTO) {
        SaveUserRequest saveUserRequest = new SaveUserRequest();
        saveUserRequest.setUserDTO(userDTO);
        return (GetUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, saveUserRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/SaveUserRequest"));
    }

    public GetBooleanResponse deleteUser(long chatId) {
        DeleteUserByIdRequest deleteUserByIdRequest = new DeleteUserByIdRequest();
        deleteUserByIdRequest.setChatId(chatId);
        return (GetBooleanResponse) getWebServiceTemplate()
                .marshalSendAndReceive(USERS_URI, deleteUserByIdRequest,
                        new SoapActionCallback(NAMESPACE_URI + "/DeleteUserByIdRequest"));
    }
}
