package com.example.jpasecurity;

import com.example.jpasecurity.models.AuthenticationRequest;
import com.example.jpasecurity.models.AuthenticationResponse;
import com.example.jpasecurity.service.MyUserDetailsService;
import com.example.jpasecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
    public String home(){
        return ("<h1>Welcome</h1>");
    }
    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome User</h1>");
    }
    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                           authenticationRequest.getPassWord())
           );
       } catch (BadCredentialsException e){
           throw new Exception("Incorrect username or password", e);
       }
       final UserDetails userDetails = userDetailsService
               .loadUserByUsername(authenticationRequest.getUserName());
       final String jwt = jwtUtil.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
