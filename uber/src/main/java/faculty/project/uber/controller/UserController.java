package faculty.project.uber.controller;


import faculty.project.uber.dto.user.request.ChangePasswordRequest;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/{id}")
    ResponseEntity findOne(@PathVariable Long id){
        return new ResponseEntity(userService.findOne(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity changeUserData(@RequestBody ChangeUserDataRequest req, @PathVariable Long id){

        return new ResponseEntity(userService.changeUserData(id,req),HttpStatus.OK);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PutMapping("/changePassword/{id}")
    ResponseEntity changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest req){

        userService.changePassword(id,req);
        return new ResponseEntity(HttpStatus.OK);
    }

}
