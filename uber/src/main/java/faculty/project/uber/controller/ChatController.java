package faculty.project.uber.controller;

import faculty.project.uber.dto.message.request.MessageRequest;
import faculty.project.uber.dto.message.response.MessageResponse;
import faculty.project.uber.model.others.Message;
import faculty.project.uber.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/to-user")
//    @SendTo("/topic/user-chat")
    public void sendMessageToUser(@RequestBody MessageRequest request){
        System.out.println(request.getContent());
        System.out.println(request.getSenderId());
        System.out.println(request.getReceiverId());
        System.out.println("DOSLO??");
        Message m = messageService.saveMessage(request);
        simpMessageTemplate.convertAndSend("/topic/user/"+request.getReceiverId(), new ResponseEntity( new MessageResponse(m), HttpStatus.OK));
//        return new MessageResponse(m);
    }

    @MessageMapping("/to-admin")
    @SendTo("/topic/admin")
    public ResponseEntity sendMessageToAdmin(@RequestBody  MessageRequest request){
        System.out.println(request.getContent());
        System.out.println("DOSLO?");
        Message m = messageService.saveMessage(request);
        Long id = 1L;
//        MessageResponse m = new MessageResponse("HELLO, "+ HtmlUtils.htmlEscape(request.getContent()));
//        simpMessageTemplate.convertAndSend("/topic/admin/"+id, m);
        return new ResponseEntity(new MessageResponse(m), HttpStatus.OK);
    }
}
