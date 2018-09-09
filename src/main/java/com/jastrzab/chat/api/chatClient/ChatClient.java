package com.jastrzab.chat.api.chatClient;

import com.jastrzab.chat.model.requisetUtil.DTO;
import com.jastrzab.chat.model.requisetUtil.DTOUtil;
import com.jastrzab.socket.client.SocketClient;

import java.io.IOException;

import static com.jastrzab.chat.model.par.*;

public class ChatClient {


    SocketClient socketClient = new SocketClient();

    public ChatClient() {

        try {

            socketClient.connectTo("localhost",8081);

        } catch (IOException e) {
            System.out.println("Error IOException when connect to Socket server");
        } catch (Exception e){
            System.out.println(" Other Error when connect to Socket server");
        }


    }

    public boolean logIn(String nickName){

        DTO dto = new DTO(){{
            add(R_TYPE.v(),"Login");
            add(USER_NICK.v(),nickName);
        }};


        String resDTO = socketClient.sendRequest(dto.toString());


        DTO parse = DTOUtil.parse(resDTO);

        String userId = parse.get(USER_ID.v());
        String errorMes = parse.get(ERROR_MES.v());

        System.out.println("errorMes = " + errorMes);
        System.out.println("userId = " + userId);

        return true;


    }
}
