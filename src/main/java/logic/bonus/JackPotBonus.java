package logic.bonus;

import controller.Game;

/**
 * @author Andrés Cerda
 */
public class JackPotBonus extends AbstractBonus {
    private static JackPotBonus jackPotBonus;
    private int pointsGiven;

    public static JackPotBonus getUniqueInstance() {
        if(jackPotBonus == null){
            jackPotBonus = new JackPotBonus();

        }
        return jackPotBonus;
    }

    private JackPotBonus(){
        super();
        this.pointsGiven = 100000;
    }
    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        game.addToScore(pointsGiven);
        timesTriggered++;
    }


}
