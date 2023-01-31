package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.message.request.MessageRequest;
import faculty.project.uber.dto.message.response.MessageResponse;
import faculty.project.uber.model.enums.MessageType;
import faculty.project.uber.model.others.Message;
import faculty.project.uber.model.users.User;
import faculty.project.uber.repository.MessageRepository;
import faculty.project.uber.service.MessageService;
import faculty.project.uber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;

    @Override
    public Message saveMessage(MessageRequest request) {
        Message m = messageBuilder(request);
        return messageRepository.save(m);
    }

    @Override
    public List<MessageResponse> findByUsers(Long userId) {
        return messageRepository.findByUsers(userId).stream().map( m -> new MessageResponse(m)).toList();
    }


    Message messageBuilder(MessageRequest request ){
        Message m = new Message();
        m.setMessage(request.getContent());
        User receiver = userService.getById(request.getReceiverId());
        User sender = userService.getById(request.getSenderId());
        m.setReceiver(receiver);
        m.setSender(sender);
        m.setTime(LocalDateTime.now());
        m.setType(MessageType.SUPPORT);
        return m;
    }
}
