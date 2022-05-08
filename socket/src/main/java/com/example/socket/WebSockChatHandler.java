//package com.example.socket;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Required;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Component
//@Slf4j
//public class WebSockChatHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final ChatRoomRepository chatRoomRepository;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("payload : " + payload);
//
////        TextMessage textMessage = new TextMessage("채팅서버에 오신 것을 환영합니다");
////        session.sendMessage(textMessage);
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//        ChatRoom room = chatRoomRepository.findRoomById(chatMessage.getRoomId());
//        room.handleActions(session, chatMessage, chatRoomRepository);
//
//    }
//
////    /* Client가 접속 시 호출되는 메서드 */
////    @Override
////    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
////
////        list.add(session);
////
////        log.info(session + " 클라이언트 접속");
////    }
////
////    /* Client가 접속 해제 시 호출되는 메서드드 */
////
////    @Override
////    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
////
////        log.info(session + " 클라이언트 접속 해제");
////        list.remove(session);
////    }
//}