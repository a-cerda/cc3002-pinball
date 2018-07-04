package logic;


import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;

/**
 * An interface for visiting Hittables and determining which bonuses have to be triggered in the Game controller.
 *
 * @author Andrés Cerda
 */
public interface TableVisitor
{
    /**
     * A method for visiting a bumper and invoking the corresponding ExtraBallBonus
     * @param bumper
     */
    void visitBumper(Bumper bumper);

    /**
     * A method for visiting a spotTarget and invoking a JackPotBonus
     * @param spotTarget
     */
    void visitSpotTarget(SpotTarget spotTarget);

    /**
     * A method for visiting a DropTarget and invoking a DropTargetBonus
     * @param dropTarget
     */
    void visitDropTarget(DropTarget dropTarget);
}
