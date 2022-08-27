package be.vdab.beers.controllers;

import be.vdab.beers.exceptions.BrouwerNietGevondenException;
import be.vdab.beers.services.BierService;
import be.vdab.beers.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("brouwers")
public class BrouwerController {
    private final BrouwerService brouwerService;
    private final BierService bierService;

    public BrouwerController(BrouwerService brouwerService, BierService bierService) {
        this.brouwerService = brouwerService;

        this.bierService = bierService;
    }

    @GetMapping
    public ModelAndView getBrouwers() {
        return new ModelAndView("brouwers", "brouwers", brouwerService.findBrouwersMetNaamEnGemeente());
    }

    @GetMapping("{id}/bieren")
    public ModelAndView findBierenByBrouwerId(@PathVariable long id) {
        var modelAndView = new ModelAndView("bieren");
            brouwerService.findById(id).ifPresent(brouwer ->
                    modelAndView.addObject("brouwer", brouwer)
                            .addObject("bieren", bierService.findBierByBrouwerId(id)));
        return modelAndView;
    }
}
