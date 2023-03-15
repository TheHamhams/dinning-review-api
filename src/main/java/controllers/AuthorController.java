package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorController authorController;

    public AuthorController(AuthorController authorController) {
        this.authorController = authorController;
    }
}
