package com.example.efabackend.service;
import com.example.efabackend.Dto.LoginDto;
import com.example.efabackend.Dto.RegisterDto;
import com.example.efabackend.Repo.UserRatingRepository;
import com.example.efabackend.entity.User;
import com.example.efabackend.response.LoginResponse;
import com.example.efabackend.response.RegisterResponse;
import com.example.efabackend.service.impl.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class userImpl implements userService {

    @Autowired
    private UserRatingRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public RegisterResponse addUser(RegisterDto registerDto) {
        User existingUser = userRepo.findByEmail(registerDto.getEmail());
        if (existingUser != null) {
            return new RegisterResponse("User with email " + registerDto.getEmail() + " already exists",false);
        }
        User user = new User(
                registerDto.getId(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                registerDto.getName(),
                registerDto.getRole()
        );
        userRepo.save(user);
        return new RegisterResponse("User added",true);
    }


    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        User user = userRepo.findByEmail(loginDto.getEmail());

        if (user != null) {
            String password = loginDto.getPassword();
            String encodedPassword = user.getPassword();

            if (passwordEncoder.matches(password, encodedPassword)) {
                String token = jwtService.generateToken(user.getEmail());
                return new LoginResponse("Login Success", true,token);
            } else {
                return new LoginResponse("Password Not Match", false,null);
            }
        } else {
            return new LoginResponse("Email Not Exists", false,null);
        }
    }

    @Override
    public User findbyEmail(String email) {
        return  this.userRepo.findByEmail(email);
    }


}
