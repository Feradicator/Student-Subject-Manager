package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.JwtProvider;
import com.app.exception.UserException;
import com.app.model.Admin;
import com.app.model.User;
import com.app.repository.AdminRepository;
import com.app.repository.UserRepository;
import com.app.request.LoginRequest;
import com.app.response.AuthResponse;
import com.app.role.UserRole;
import com.app.service.UserService;



@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminRepository userRepo;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService customUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
   

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler( @Valid @RequestBody Admin user) throws UserException {
        System.out.println(user);
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
       
        
        Admin isEmailExist = userRepo.findByEmail(email);
        if (isEmailExist != null) {
            throw new UserException("Email Is Already Used With Another Account");
        }

        Admin createdUser = new Admin();
        createdUser.setName(name);
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
       
       
        Admin savedUser = userRepo.save(createdUser);
       
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,
                password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication,savedUser.getId());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setStatus(true);
        authResponse.setJwt(token);
        authResponse.setMessage("signup success");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>loginHandler(@RequestBody LoginRequest loginRequest)
    {
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Admin user=userRepo.findByEmail(username);
        Authentication authentication=authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication (authentication);
        String token = jwtProvider.generateToken(authentication,user.getId());
        AuthResponse authResponse=new AuthResponse(token, "Signin Success",true);
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);

         
    }

    private Authentication authenticate(String username, String password)
    { 
        UserDetails userDetails =customUserService.loadUserByUsername(username);
        if(userDetails==null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password...");
            }
            
            return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            
        
    }

        
}
