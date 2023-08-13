package msn.example.security.auth;


import lombok.RequiredArgsConstructor;
import msn.example.security.config.JwtService;
import msn.example.security.user.Role;
import msn.example.security.user.User;
import msn.example.security.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatinService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationRessponse register(RegisterRequest request) {

        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        var jwtToken=jwtService.generateToken(user);

        return AuthenticationRessponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationRessponse authintcate(AuthencationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()

            )

    );
    var user=repository.findByEmail(request.getEmail())

            .orElseThrow();
        var jwtToken=jwtService.generateToken(user);

        return AuthenticationRessponse.builder()
                .token(jwtToken)
                .build();
    }
}
