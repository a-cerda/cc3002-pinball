package logic;


import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.updates.*;

/**
 * An interface for visiting Hittables and determining which bonuses have to be triggered in the Game controller.
 *
 * @author Andrés Cerda
 */
public interface TableVisitor
{
/*    *//**
     * A method for visiting a bumper and invoking the corresponding ExtraBallBonus
     * @param bumper
     *//*
    void visitBumper(Bumper bumper);

    *//**
     * A method for visiting a spotTarget and invoking a JackPotBonus
     * @param spotTarget
     *//*
    void visitSpotTarget(SpotTarget spotTarget);

    *//**
     * A method for visiting a DropTarget and invoking a DropTargetBonus
     * @param
     *//*
    void visitDropTarget(DropTarget dropTarget);*/

    void visitHitUpdate(HitUpdate hitUpdate);

    void visitSpotTargetUpdate(SpotTargetUpdate spotTargetUpdate);

    void visitUpgradeBumperUpdate(UpgradeBumperUpdate upgradeBumperUpdate);

    void visitDropTargetUpdate(DropTargetUpdate dropTargetUpdate);

    void visitDropTargetExtraBallUpdate(DropTargetExtraBallUpdate dropTargetExtraBallUpdate);
}
