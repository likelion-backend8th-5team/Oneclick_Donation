package com.example.OneclickDonation.controller;

import com.example.OneclickDonation.dto.PostDto;
import com.example.OneclickDonation.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/create")
    public String create() {
        return "/post/create";
    }
    @PostMapping("/create")
    public String createPost(
            @RequestParam("postTitle") String title,
            @RequestParam("postContents") String description
    ) {
        Long newId = postService.create(new PostDto(title, description)).getId();
        return String.format("redirect:/post/%d", newId);
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        PostDto post = postService.readOne(id);
        model.addAttribute("post", post);
        return "/post/view";
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        PostDto post = postService.readOne(id);
        model.addAttribute("post", post);
        return "/post/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("contents") String description
    ) {
        postService.update(id, new PostDto(title, description));
        return "redirect:/post/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/post/create";
    }
}
