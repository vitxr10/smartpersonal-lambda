package br.com.vitxr.smartpersonal.controller;

import br.com.vitxr.smartpersonal.entity.User;
import br.com.vitxr.smartpersonal.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping
    public ResponseEntity<String> getAll() {
        var users = userRepository.getAll();

        var usersJson = gson.toJson(users);
        logger.info("Lista de usuários: {}", usersJson);

        return new ResponseEntity<>(usersJson, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable String id){
        var user = userRepository.getById(id);

        if (user == null)
            return ResponseEntity.notFound().build();

        var userJson = gson.toJson(user);

        return new ResponseEntity<>(userJson, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody User user){
        logger.info("Cadastrando usuário {}", user.getEmail());
        var createdUser = userRepository.create(user);

        var userJson = gson.toJson(createdUser);
        logger.info("Usuario criado {}", userJson);

        return new ResponseEntity<>(userJson, HttpStatusCode.valueOf(201));
    }
}
