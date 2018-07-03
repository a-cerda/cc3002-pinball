package logic.bonus;

import controller.Game;

/**
 * @author Andrés Cerda
 */
public class JackPotBonus extends AbstractBonus {
    private static JackPotBonus jackPotBonus;

    public static JackPotBonus getUniqueInstance() {
        if(jackPotBonus == null){
            jackPotBonus = new JackPotBonus();

        }
        return jackPotBonus;
    }

    private JackPotBonus(){
        super();
    }
    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {

    }

    /**
     * Accept the game as a visitor in order to trigger the bonus
     *
     * @param game the game controller object
     */
    @Override
    public void accept(Game game) {

    }
}
