package forum.controller;

import forum.service.ForumCrudService;
import forum.service.ForumService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final ForumCrudService service;

    public IndexController(ForumCrudService service) {
        this.service = service;
    }

    @GetMapping({"/index", "/"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("posts", service.getAll());
        return "index";
    }
}
