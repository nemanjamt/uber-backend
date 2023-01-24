package faculty.project.uber.controller;

import faculty.project.uber.dto.client.request.ClientRegistrationRequest;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.service.ClientService;
import faculty.project.uber.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    EmailService emailService;
    @GetMapping("/all")
    ResponseEntity findAll(){
        return new ResponseEntity(clientService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity createClient(@Valid @RequestBody ClientRegistrationRequest request){
        Client c = clientService.registrateClient(request);
        emailService.sendRegistrationEmail(c);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/confirm/{token}")
    ResponseEntity confirmAccount(@PathVariable String token){
        System.out.println(token);
        System.out.println("TOKENN");
        clientService.confirmRegistration(token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
