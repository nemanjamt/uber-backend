package faculty.project.uber.dto.message.response;

import faculty.project.uber.model.others.Message;
import lombok.Data;

@Data
public class MessageResponse {
    private String content;
    private Long id;
    private Long receiverId;
    private Long senderId;
    private int[] time;
    public MessageResponse(String content){
        this.content = content;
    }
    public MessageResponse(Message m){
        this.content = m.getMessage();
        this.id = m.getId();
        this.receiverId = m.getReceiver().getId();
        this.senderId = m.getSender().getId();
        this.time = new int[]{m.getTime().getYear(),
                m.getTime().getMonthValue(),
                m.getTime().getDayOfMonth(),
                m.getTime().getHour(),
                m.getTime().getMinute(),
                m.getTime().getSecond()};
    }
}
