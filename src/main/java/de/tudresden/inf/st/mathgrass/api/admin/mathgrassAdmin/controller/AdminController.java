package de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.entity.QuestionAnswerEntity;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.TokenValidationRequest;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.UserAuthenticationRequest;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.UserAuthenticationResponse;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.model.UserRegistrationRequest;
import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.service.AdminServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    private final AdminServices adminServices;


    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping("/adminRegistration")
    public ResponseEntity<UserAuthenticationResponse> adminRegistration(@RequestBody UserRegistrationRequest request){
        logger.info("adminRegistration Service called from AdminController");
        return ResponseEntity.ok(adminServices.adminRegistration(request));
    }

    @PostMapping("/studentRegistration")
    public ResponseEntity<UserAuthenticationResponse> studentRegistration(@RequestBody UserRegistrationRequest request){
        logger.info("studentRegistration Service called from AdminController");
        return ResponseEntity.ok(adminServices.studentRegistration(request));
    }

    @PostMapping("/userAuthentication")
    public ResponseEntity<UserAuthenticationResponse> userAuthentication(@RequestBody UserAuthenticationRequest request){
        logger.info("userAuthentication Service called from AdminController");
        return ResponseEntity.ok(adminServices.userAuthentication(request));
    }

    @PostMapping("/tokenValidity")
    public boolean checkTokenValidity(@RequestBody TokenValidationRequest request){
        logger.info("checkTokenValidity Service called from AdminController");
        return adminServices.checkTokenValidity(request.getToken());
    }
    @PostMapping("/saveGraph")
    public void getGraphJson(@RequestBody String graphJson) {
        System.out.println("Check saveGraph Json"+graphJson);
    }

    @PostMapping("/saveHints")
    public void getSaveHints(@RequestBody String saveHints) {
        System.out.println("Check saveHints Json"+saveHints);
    }

    @PostMapping("/saveQuestionAnswer")
    public void saveQuestionAnswer(@RequestBody String questionAnswerEntity) throws JsonProcessingException {
        System.out.println("Check questionAnswerEntity Json"+questionAnswerEntity);
        ObjectMapper mapper = new ObjectMapper();
        QuestionAnswerEntity question = mapper.readValue(questionAnswerEntity, QuestionAnswerEntity.class);
        System.out.println("Check the mapped json - "+question);
    }

}
