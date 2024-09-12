package me.quyen.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/real-estate")
public class RealEstateController {
    @GetMapping(value={"","/index.html","/index"})
    public String index(){ return "real-estate/index"; }

    @GetMapping(value={"/about.html","/about"})
    public String about(){
        return "real-estate/about";
    }

    @GetMapping(value={"/404.html","/404"})
    public String error404(){
        return "real-estate/404";
    }

    @GetMapping(value={"/contact.html","/contact"})
    public String contact(){
        return "real-estate/contact";
    }

    @GetMapping(value={"/property-agent.html","/property-agent"})
    public String propertyAgent(){
        return "real-estate/property-agent";
    }

    @GetMapping(value={"/property-list.html","/property-list"})
    public String propertyList(){
        return "real-estate/property-list";
    }

    @GetMapping(value={"/property-type.html","/property-type"})
    public String propertyType(){
        return "real-estate/property-type";
    }

    @GetMapping(value={"/testimonial.html","/testimonial"})
    public String testimonial(){
        return "real-estate/testimonial";
    }

}
