package com.jastrzab.chat.api.chatServer;

import com.jastrzab.chat.model.requisetUtil.DTO;
import com.jastrzab.chat.model.requisetUtil.DTOUtil;
import com.jastrzab.socket.server.SocketServer;

import static com.jastrzab.chat.model.par.*;

public class ChatServer {

    SocketServer socketServer = new SocketServer(8081);

    public ChatServer() {


        socketServer.start(((request, response) -> {

            String requestData = request.getData();
            DTO dto = DTOUtil.parse(requestData);
            String requestType = dto.get(R_TYPE.v());
            switch (requestType){

                case "Login":
                    System.out.println("Start Login");
                    System.out.println(dto.get(USER_NICK.v()));
                    DTO dtoResponse = new DTO(){{
                       add(ERROR_MES.v(),"No error");
                       add(USER_ID.v(),"1");
                    }};

                    response.sendResponse(dtoResponse.toString());

                    break;

            }

        }));

    }
}
