package forum.controller;

import forum.model.Post;
import forum.service.ForumCrudService;
import forum.service.ForumService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final ForumCrudService service;

    public PostController(ForumCrudService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "post/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        service.save(post);
        return "redirect:/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.findById(id));
        return "post/edit";
    }

    @GetMapping("/topic")
    public String postInfo(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", service.findById(id));
        return "topic";
    }

}
