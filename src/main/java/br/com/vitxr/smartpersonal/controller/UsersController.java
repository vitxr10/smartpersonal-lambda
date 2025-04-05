package br.com.vitxr.smartpersonal.controller;

import br.com.vitxr.smartpersonal.entity.User;
import br.com.vitxr.smartpersonal.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping
    public ResponseEntity<String> getAll() {
        System.out.println("caiu no controller");
        var users = userRepository.getAll();

        var usersJson = gson.toJson(users);

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
        var createdUser = userRepository.create(user);
        var userJson = gson.toJson(createdUser);

        return new ResponseEntity<>(userJson, HttpStatusCode.valueOf(201));
    }
}
