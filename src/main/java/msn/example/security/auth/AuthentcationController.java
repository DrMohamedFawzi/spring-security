package msn.example.security.auth;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthentcationController {
  private final AuthenticatinService service;
@PostMapping("/Register")
    public ResponseEntity<AuthenticationRessponse>register(
            @RequestBody RegisterRequest request
){
return ResponseEntity.ok(service.register(request));
}
    @PostMapping("/Authincate")
    public ResponseEntity<AuthenticationRessponse>register(
            @RequestBody AuthencationRequest request
    ){
        return ResponseEntity.ok(service.authintcate(request));

    }

}
