package msn.example.security.config;


import lombok.RequiredArgsConstructor;
import msn.example.security.user.UserRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
  private final UserRepository repository;

  @Bean
public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider autheProvider=new DaoAuthenticationProvider();
        autheProvider.setUserDetailsService(userDetailsService());
        autheProvider.setPasswordEncoder(passwordEncoder());
        return autheProvider;
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();
    }
@Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }


}
