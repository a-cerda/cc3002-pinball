package logic.bonus;

import java.util.Observable;

public abstract class AbstractBonus extends Observable implements Bonus{
    protected int timesTriggered;

    protected AbstractBonus(){
        this.timesTriggered = 0;
    }

    public int timesTriggered(){
        return timesTriggered;
    };


}
