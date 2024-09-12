package me.quyen.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/startup-website-template")
public class StartupWebsiteTemplateController {
    @GetMapping(value={"","/index.html","/index"})
    public String index(){ return "startup-website-template/index"; }

    @GetMapping(value={"/about.html","/about"})
    public String about(){
        return "startup-website-template/about";
    }

    @GetMapping(value={"/blog.html","/blog"})
    public String blog(){
        return "startup-website-template/blog";
    }

    @GetMapping(value={"/contact.html","/contact"})
    public String contact(){
        return "startup-website-template/contact";
    }

    @GetMapping(value={"/detail.html","/detail"})
    public String detail(){
        return "startup-website-template/detail";
    }

    @GetMapping(value={"/feature.html","/feature"})
    public String feature(){
        return "startup-website-template/feature";
    }

    @GetMapping(value={"/price.html","/price"})
    public String price(){
        return "startup-website-template/price";
    }

    @GetMapping(value={"/quote.html","/quote"})
    public String quote(){
        return "startup-website-template/quote";
    }

    @GetMapping(value={"/service.html","/service"})
    public String service(){
        return "startup-website-template/service";
    }

    @GetMapping(value={"/team.html","/team"})
    public String team(){
        return "startup-website-template/team";
    }

    @GetMapping(value={"/testimonial.html","/testimonial"})
    public String testimonial(){
        return "startup-website-template/testimonial";
    }
}
