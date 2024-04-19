package co.istad.jbsdemo.spring_security_basic.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {
    @GetMapping
    public String getAllArticles() {
        return "Getting all articles from the database !";
    }

    @GetMapping("/read/{id}")
    public String readSingleArticle(@PathVariable String id) {
        return "Reading on article on" + id;
    }

    @PostMapping("/write")
    public String createArticle() {
        return "Create the new article";
    }

    @DeleteMapping("/delete/{id}")
    public String DeleteArticle(@PathVariable String id) {
        return "Delete article on " + id;
    }

}