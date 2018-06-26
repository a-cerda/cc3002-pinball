package pinball.test;


import logic.bonus.Bonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

public class HomeworkTwoTest {

    private KickerBumper kickerBumper;
    private PopBumper popBumper;

    private SpotTarget target1;
    private DropTarget target2;

    private NullTable nullTable;
    private PlayableTable playableTable;

    private ExtraBallBonus extraBallBonus;
    private JackPotBonus jackPotBonus;
    private DropTargetBonus dropTargetBonus;


    @Before
    public void SetUp(){
        kickerBumper = new KickerBumper();
        popBumper = new PopBumper();

    }

}
