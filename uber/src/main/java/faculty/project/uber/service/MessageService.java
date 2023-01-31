package faculty.project.uber.service;

import faculty.project.uber.dto.message.request.MessageRequest;
import faculty.project.uber.dto.message.response.MessageResponse;
import faculty.project.uber.model.others.Message;

import java.util.List;

public interface MessageService {

    Message saveMessage(MessageRequest request);
    List<MessageResponse> findByUsers(Long userId);
}
