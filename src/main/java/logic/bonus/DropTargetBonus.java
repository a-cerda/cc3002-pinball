package logic.bonus;

import controller.Game;

public class DropTargetBonus extends AbstractBonus {
    private static DropTargetBonus dropTargetBonus;
    private int pointsGiven = 1000000;

    public static DropTargetBonus getUniqueInstance(){
        if(dropTargetBonus == null){
            dropTargetBonus = new DropTargetBonus();
        }
        return dropTargetBonus;
    }

    private DropTargetBonus(){
        super();
    }


    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        game.addToScore(pointsGiven);
        game.upgradeAllBumpers();
        timesTriggered++;
    }


}
