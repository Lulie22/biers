package be.vdab.beers.controllers;

import be.vdab.beers.domain.Bestelbon;
import be.vdab.beers.form.BestelLijn;
import be.vdab.beers.services.BestelbonService;
import be.vdab.beers.services.BestelbonlijnService;
import be.vdab.beers.services.BierService;
import be.vdab.beers.sessions.Mandje;
import be.vdab.beers.sessions.StateMandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BestelbonlijnService bestelbonlijnService;
    private final StateMandje stateMandje;
    private final BierService bierService;

    public MandjeController(Mandje mandje, StateMandje stateMandje, BierService bierService,
                            BestelbonlijnService bestelbonlijnService) {
        this.mandje = mandje;
        this.stateMandje = stateMandje;
        this.bierService = bierService;
        this.bestelbonlijnService = bestelbonlijnService;
    }

    @GetMapping
    public ModelAndView toonMandje() {
        if (!stateMandje.isGevuld()){
            return new ModelAndView("mandje","isGevuld",false);
        }
        var modelAndView = maakMandje();
        modelAndView.addObject(new Bestelbon(0, null, null, null, null, null));
        return modelAndView;
    }

    @PostMapping("bevestigen")
    public ModelAndView bevestigen(@Valid Bestelbon bestelbon, Errors errors, RedirectAttributes redirect) {
        //System.out.println(bestelbon.getId() + bestelbon.getNaam() + bestelbon.getGemeente());
        if (errors.hasErrors()) {
            return maakMandje();
        }
        if (mandje.isGevuld()) {
            var bestelbonId = bestelbonlijnService.bestelbonBevestigen(mandje.getBieren(), bestelbon);
            //var besteler = bestelbon
            mandje.delete();
            stateMandje.setGevuld(false);
            //System.out.println(bestelbon.getId() + bestelbon.getNaam() + bestelbon.getGemeente());
            redirect.addAttribute("toegevoegd", bestelbonId);
            redirect.addFlashAttribute("bestelbon",bestelbon);
            return new ModelAndView("redirect:/mandje/bestelbon");
        } else {
            return maakMandje();
        }
    }

    private ModelAndView maakMandje() {
        var modelAndView = new ModelAndView("mandje");
        if (mandje.isGevuld()) {
            List<BestelLijn> mandjeList = new ArrayList<>();
            mandje.getBieren().entrySet().stream().forEach(entry ->
                    bierService.findById(entry.getKey()).ifPresent(bier -> {
                        var teBetalen = bier.teBetalen(entry.getValue());
                        mandjeList.add(new BestelLijn(bier, entry.getValue(), teBetalen));
                    }));
            modelAndView.addObject("mandje", mandjeList);
        }
        modelAndView.addObject("totaal", mandje.getTotaal());
        return modelAndView;
    }

    @GetMapping("bestelbon")
    public ModelAndView toonBevestigen() {
        //System.out.println(bestelbon.getNaam());
        return new ModelAndView("bestelbon");
               // .addObject("bestelbon",bestelbon);
    }


    @GetMapping("annuleren")
    public String annuleren() {
        mandje.delete();
        stateMandje.setGevuld(false);
        return "redirect:/";
    }

    @GetMapping("verwijderen/{id}")
    public String verwijder(@PathVariable long id) {

        var totaal = mandje.getTotaal();
        var aantal = mandje.getBieren().get(id);

        bierService.findById(id).ifPresent(bier -> {
            var teBetalen = bier.teBetalen(aantal);
            mandje.removeItem(id,totaal.subtract(teBetalen));
        });
        if (mandje.getBieren().isEmpty()){
            stateMandje.setGevuld(false);
            return "redirect:/";
        }
        return "redirect:/mandje";
    }
}
