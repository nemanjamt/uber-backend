package faculty.project.uber.controller;

import faculty.project.uber.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findByUser/{userId}")
    public ResponseEntity findByUsers(@PathVariable Long userId){
        return new ResponseEntity( messageService.findByUsers(userId),HttpStatus.OK);
    }
}