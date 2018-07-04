package logic.bonus;

import controller.Game;

public class ExtraBallBonus extends AbstractBonus implements Bonus {
    private static ExtraBallBonus extraBallBonus;

    public static ExtraBallBonus getUniqueInstance() {
        if(extraBallBonus == null){
            extraBallBonus = new ExtraBallBonus();
        }
        return extraBallBonus;
    }

    private ExtraBallBonus(){
        super();
    }

    /**
     * Gets the number of times the bonus has been triggered.
     *
     * @return number of times the bonus has been triggered
     */
    @Override
    public int timesTriggered() {
        return timesTriggered;
    }

    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        game.addBall();
        timesTriggered++;
    }



}
