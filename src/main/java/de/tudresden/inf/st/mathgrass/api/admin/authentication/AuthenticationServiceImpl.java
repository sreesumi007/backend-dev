package de.tudresden.inf.st.mathgrass.api.admin.authentication;

import de.tudresden.inf.st.mathgrass.api.admin.authentication.entitiy.LoginAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public LoginAuthentication fetchUserByEmailAndPassword(String email, String password) {
        return authenticationRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public String saveUser(LoginAuthentication loginAuthentication) {
        authenticationRepository.save(loginAuthentication);
        return "Saved Successfully";
    }


}
