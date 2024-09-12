package me.quyen.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/education")
public class EducationController {
    @GetMapping(value={"","/index.html","/index"})
    public String index(){ return "education/index"; }

    @GetMapping(value={"/about.html","/about"})
    public String about(){
        return "education/about";
    }
    @GetMapping(value={"/contact.html","/contact"})
    public String contact(){
        return "education/contact";
    }

    @GetMapping(value={"/course-details.html","/course-details"})
    public String courseDetails(){
        return "education/course-details";
    }

    @GetMapping(value={"/courses.html","/courses"})
    public String courses(){
        return "education/courses";
    }

    @GetMapping(value={"/events.html","/events"})
    public String events(){
        return "education/events";
    }

    @GetMapping(value={"/pricing.html","/pricing"})
    public String pricing(){
        return "education/pricing";
    }

    @GetMapping(value={"/starter-page.html","/starter-page"})
    public String starterPage(){
        return "education/starter-page";
    }

    @GetMapping(value={"/trainers.html","/trainers"})
    public String trainers(){
        return "education/trainers";
    }

}
