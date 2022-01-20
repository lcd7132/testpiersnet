package com.dol.piersnet.controller;

import com.dol.piersnet.model.Board;
import com.dol.piersnet.model.User;
import com.dol.piersnet.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
class UserApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> all() {
        List<User> users =  repository.findAll();
        log.debug("getBoards().size() 호출전");
        log.debug("getBoards().size() : {}", users.get(0).getBoards().size());
        log.debug("getBoards().size() 호출후");
        return users;
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
//                    user.setTitle(newUser.getTitle());
//                    user.setProduct(newUser.getProduct());
//                    user.setContent(newUser.getContent());
//                    user.setContent1(newUser.getContent1());
//                    user.setContent2(newUser.getContent2());
//                    user.setContent3(newUser.getContent3());

                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());
                    user.setBoards(newUser.getBoards());
                    for(Board board : user.getBoards()) {
                        board.setUser(user);
                    }

                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}