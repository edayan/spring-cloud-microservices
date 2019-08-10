package com.appdeveloperblog.photoapp.api.users.ui.controllers;

import com.appdeveloperblog.photoapp.api.users.service.UserService;
import com.appdeveloperblog.photoapp.api.users.shared.UserDTO;
import com.appdeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;
import com.appdeveloperblog.photoapp.api.users.ui.model.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersRestController {

    private final Environment environment;
    private final UserService userService;

    @Autowired
    public UsersRestController(final Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working port:" + environment.getProperty("local.server.port");
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO userDto = modelMapper.map(createUserRequestModel, UserDTO.class);
        UserDTO createdUSer = userService.createUser(userDto);
        CreateUserResponseModel response = modelMapper.map(createdUSer, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
