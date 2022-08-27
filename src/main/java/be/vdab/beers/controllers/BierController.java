package be.vdab.beers.controllers;

import be.vdab.beers.exceptions.BierNietGevondenException;
import be.vdab.beers.form.AantalBierForm;
import be.vdab.beers.services.BierService;
import be.vdab.beers.sessions.Mandje;
import be.vdab.beers.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("bieren")
public class BierController {
    private final BierService bierService;
    private final Mandje mandje;
    private final StateMandje stateMandje;

    public BierController(BierService bierService, Mandje mandje, StateMandje stateMandje) {

        this.bierService = bierService;
        this.mandje = mandje;
        this.stateMandje = stateMandje;
    }

    @GetMapping("{id}/bier")
    public ModelAndView findById(@PathVariable long id) {
        var modelAndView = new ModelAndView("bier");
        bierService.findById(id).ifPresent(bier ->
                modelAndView.addObject(bier)
                        .addObject("aantalBierForm", new AantalBierForm(null)));
        return modelAndView;
    }

    @PostMapping("{id}/toevoegen")
    public ModelAndView toevoegen(@PathVariable long id, @Valid AantalBierForm bierForm, Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("bier");
            try {
                bierService.findById(id).ifPresent(bier ->
                        modelAndView.addObject("bier", bier));

            } catch (BierNietGevondenException ex) {
                modelAndView.addObject("BierNietGevonden", true);
            }
            return modelAndView;
        }
        bierService.findById(id).ifPresent(bier -> {
            mandje.toevoeg(id, bierForm.getAantal());
            mandje.addTotaal(bier.teBetalen(bierForm.getAantal()));
            stateMandje.setGevuld(true);
        });
        return new ModelAndView("redirect:/mandje");
    }
}
