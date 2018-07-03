package logic.bonus;

import controller.Game;

public class DropTargetBonus extends AbstractBonus {
    private static DropTargetBonus dropTargetBonus;

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
