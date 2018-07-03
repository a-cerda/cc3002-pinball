package logic.bonus;

public abstract class AbstractBonus implements Bonus{
    protected int timesTriggered;


    protected AbstractBonus(){
        this.timesTriggered = 0;
    }

    public int timesTriggered(){
        return timesTriggered;
    };


}
