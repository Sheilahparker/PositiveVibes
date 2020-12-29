package com.tts.PositiveVibes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.PositiveVibes.model.Tag;
import com.tts.PositiveVibes.model.User;
import com.tts.PositiveVibes.model.Vibe;
import com.tts.PositiveVibes.model.VibeDisplay;
import com.tts.PositiveVibes.repository.TagRepository;
import com.tts.PositiveVibes.service.UserService;
import com.tts.PositiveVibes.service.Vibeservice;


@Controller
public class VibeController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private Vibeservice Vibeservice;
    
	@Autowired
	private TagRepository tagRepository;
	
    @GetMapping(value= {"/vibes", "/"})
    public String getFeed(@RequestParam(value="filter", required=false) String filter, Model model){
    	User loggedInUser = userService.getLoggedInUser();
    	List<VibeDisplay> vibes = new ArrayList<>();
    	
    	if (filter == null) {
    		filter = "all";
    	}
    	if (filter.equalsIgnoreCase("following")) {
    		List<User> following = loggedInUser.getFollowing();
    		Vibes = Vibeservice.findAllByUsers(following);
    		model.addAttribute("filter", "following");
    	}
    	else {
    		Vibes = Vibeservice.findAll();
    		model.addAttribute("filter", "all");
    	}

        model.addAttribute("vibeList", vibes);
        return "feed";
    }
    
    @GetMapping(value = "/vibes/new")
    public String getVibeForm(Model model) {
        model.addAttribute("vibe", new Vibe());
        return "newVibe";
    }
    
    @PostMapping(value = "/vibes")
    public String submitVibeForm(@Valid Vibe vibe, BindingResult bindingResult, Model model) {
    	User user = userService.getLoggedInUser();
        if (!bindingResult.hasErrors()) {
        	vibe.setUser(user);
            Vibeservice.save(vibe);
            model.addAttribute("successMessage", "Vibe successfully created!");
            model.addAttribute("vibe", new Vibe());
        }
        return "newVibe";
    }
    
    @GetMapping(value = "/vibes/{tag}")
    public String getvibesByTag(@PathVariable(value="tag") String tag, Model model) {
    	List<VibeDisplay> vibes = Vibeservice.findAllWithTag(tag);
    	model.addAttribute("vibeList", vibes);
    	model.addAttribute("tag", tag);
    	return "taggedVibes";
    }

    @GetMapping(value = "/tags")
    public String getTags(Model model) {
    	List<Tag> tag = (List<Tag>)tagRepository.findAll();
    	model.addAttribute("tagList", tag);
    	return "tags";
    }
}