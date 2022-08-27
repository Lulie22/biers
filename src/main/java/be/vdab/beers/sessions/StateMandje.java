package be.vdab.beers.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class StateMandje {
    private static final long serialVersionUID = 1L;
    private boolean gevuld;
    public boolean isGevuld(){
        return gevuld;
    }
    public void setGevuld(boolean gevuld){
        this.gevuld = gevuld;
    }

}
