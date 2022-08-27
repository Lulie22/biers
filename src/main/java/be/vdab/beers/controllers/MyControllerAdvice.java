package be.vdab.beers.controllers;

import be.vdab.beers.sessions.StateMandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {
    private final StateMandje stateMandje;

    public MyControllerAdvice(StateMandje stateMandje) {
        this.stateMandje = stateMandje;
    }
    @ModelAttribute
    void extraDataToevoegenAanModel(Model model){
        model.addAttribute(stateMandje);
    }
}
