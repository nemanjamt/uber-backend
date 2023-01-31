package faculty.project.uber.dto.message.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String content;

    private Long receiverId;
    private Long senderId;
    public MessageRequest(String content){
        this.content = content;
    }
    public MessageRequest(String content, Long senderId, Long receiverId){
        this.content = content;
        this.receiverId = receiverId;
        this.senderId = senderId;
    }
}
