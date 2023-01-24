package faculty.project.uber.service.implementation;

import faculty.project.uber.constants.ErrorConstants;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.model.users.User;
import faculty.project.uber.repository.ClientRepository;
import faculty.project.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private UserRepository userRepository;


    private ClientRepository clientRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ClientRepository clientRepository){
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format(ErrorConstants.BAD_USERNAME, username));
        } else {
            if(clientRepository.existsByEmail(user.getEmail())){
                Client c = clientRepository.findByEmail(user.getEmail());
                if(!c.getActivated()){
                    throw new EntityNotFoundException("CLIENT NOT ACTIVATED");
                }
            }
            return user;
        }


    }
}
