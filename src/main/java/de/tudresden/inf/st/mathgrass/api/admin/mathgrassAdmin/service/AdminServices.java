package de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.service;

import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.config.JWTService;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.entity.Role;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.entity.User;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.UserAuthenticationRequest;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.UserAuthenticationResponse;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.UserRegistrationRequest;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    private static final Logger logger = LogManager.getLogger(AdminServices.class);
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AdminServices(UserRepository repository, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserAuthenticationResponse adminRegistration(UserRegistrationRequest request) {
        User user = new User();
        UserAuthenticationResponse registerResponse = new UserAuthenticationResponse();
        try{
            user.setFirstname(request.getFirstName());
            user.setLastname(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.ADMIN);
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            registerResponse.setToken(jwtToken);

         }
        catch (Exception e){
            logger.error("Error in saving the user data {}",e.getMessage().toString());
        }
        return registerResponse;

    }

    public UserAuthenticationResponse studentRegistration(UserRegistrationRequest request) {
        User user = new User();
        UserAuthenticationResponse registerResponse = new UserAuthenticationResponse();
        try{
            user.setFirstname(request.getFirstName());
            user.setLastname(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.STUDENT);
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            registerResponse.setToken(jwtToken);

        }
        catch (Exception e){
            logger.error("Error in saving the user data {}",e.getMessage().toString());
        }
        return registerResponse;

    }

    public UserAuthenticationResponse userAuthentication(UserAuthenticationRequest request) {

        UserAuthenticationResponse authResponse = new UserAuthenticationResponse();

        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
            User user = repository.findByEmail(request.getEmail())
                    .orElseThrow();

            var jwtToken = jwtService.generateToken(user);
            authResponse.setUserType(user.getRole().toString());
            authResponse.setToken(jwtToken);

        }
        catch (Exception e){
            logger.error("Error in authenticating the user {}",e.getMessage().toString());
        }

        return authResponse;
    }

    public boolean checkTokenValidity(String token) {
        boolean jwtExpiration;
        try{
            jwtExpiration = jwtService.isTokenExpired(token);
        }
        catch (Exception e){
            jwtExpiration = true;
            logger.error("Token Expired {}", e.getMessage().toString());
        }

        return jwtExpiration;
    }
}
