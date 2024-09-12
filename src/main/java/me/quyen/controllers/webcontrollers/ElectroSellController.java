package me.quyen.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/electro-sell")
public class ElectroSellController {
    @GetMapping(value={"","/index.html","/index"})
    public String index(){ return "electro-sell/index"; }

    @GetMapping(value={"/blank.html","/blank"})
    public String about(){
        return "electro-sell/blank";
    }

    @GetMapping(value={"/checkout.html","/checkout"})
    public String contact(){
        return "electro-sell/checkout";
    }

    @GetMapping(value={"/product.html","/product"})
    public String courseDetails(){
        return "electro-sell/product";
    }

    @GetMapping(value={"/store.html","/store"})
    public String courses(){
        return "electro-sell/store";
    }


}
