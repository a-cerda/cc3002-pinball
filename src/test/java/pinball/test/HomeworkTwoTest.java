package pinball.test;


import controller.Game;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.table.NullTable;
import logic.table.PlayableTable;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HomeworkTwoTest {

    private KickerBumper kickerBumper;
    private KickerBumper upgradedKickerBumper;
    private PopBumper popBumper;
    private PopBumper upgradedPopBumper;

    private SpotTarget spotTarget;
    private DropTarget dropTarget;

    private NullTable nullTable;
    private PlayableTable playableTable;

    private ExtraBallBonus extraBallBonus;
    private JackPotBonus jackPotBonus;
    private DropTargetBonus dropTargetBonus;

    private Game game;


    @Before
    public void SetUp(){
        kickerBumper = new KickerBumper();

        upgradedKickerBumper = new KickerBumper(true);
        upgradedPopBumper = new PopBumper(true);
        popBumper = new PopBumper();
        dropTarget = new DropTarget();
        spotTarget = new SpotTarget();
        nullTable = new NullTable();
        game = Game.getUniqueGame();
        playableTable = new PlayableTable();




    }

    @Test
    public void KickerBumperTest(){

        //Testing the remainingHitsToUpgrade, standard should be 5
        int actualRemainingHits = kickerBumper.remainingHitsToUpgrade();
        int expectedRemainingHits = 5;
        assertEquals("Should have the same value",expectedRemainingHits,actualRemainingHits);
        //Now hitting the bumper, testing the points
        int actualScoreGiven = kickerBumper.hit();
        int expectedScoreGiven = 500;
        assertEquals(expectedScoreGiven,actualScoreGiven);
        //We test the score that the getScore function gives us
        int gettedScore = kickerBumper.getScore();
        assertEquals(expectedScoreGiven,gettedScore);
        //Then test the remainingHitsToUpgrade, should go down to 4
        actualRemainingHits = kickerBumper.remainingHitsToUpgrade();
        expectedRemainingHits = 4;
        assertEquals(expectedRemainingHits,actualRemainingHits);
        kickerBumper.hit();
        kickerBumper.hit();
        kickerBumper.hit();
        kickerBumper.hit();
        //Now the kickerBumper should be upgraded
        boolean isUpgraded = kickerBumper.isUpgraded();
        assertTrue(isUpgraded);
        assertTrue(upgradedKickerBumper.isUpgraded());
        assertEquals(0,upgradedKickerBumper.remainingHitsToUpgrade());
        assertEquals(1000,upgradedKickerBumper.getScore());
        upgradedKickerBumper.downgrade();
        assertFalse(upgradedKickerBumper.isUpgraded());
        expectedRemainingHits = 5;
        assertEquals(expectedRemainingHits,upgradedKickerBumper.remainingHitsToUpgrade());
        expectedScoreGiven = 500;
        assertEquals(expectedScoreGiven,upgradedKickerBumper.getScore());


    }

    @Test
    public void PopBumperTest(){
        //Testing the remainingHitsToUpgrade, standard should be 3
        int actualRemainingHits = popBumper.remainingHitsToUpgrade();
        int expectedRemainingHits = 3;
        assertEquals("Should have the same value",expectedRemainingHits,actualRemainingHits);
        //Now hitting the bumper, testing the points
        int actualScoreGiven = popBumper.hit();
        int expectedScoreGiven = 100;
        assertEquals(expectedScoreGiven,actualScoreGiven);
        //We test the score that the getScore function gives us
        int gettedScore = popBumper.getScore();
        assertEquals(expectedScoreGiven,gettedScore);
        //Then test the remainingHitsToUpgrade, should go down to 2
        actualRemainingHits = popBumper.remainingHitsToUpgrade();
        expectedRemainingHits = 2;
        assertEquals(expectedRemainingHits,actualRemainingHits);
        popBumper.hit();
        popBumper.hit();
        //Now the popBumper should be upgraded
        boolean isUpgraded = popBumper.isUpgraded();
        assertTrue(isUpgraded);
        assertTrue(upgradedPopBumper.isUpgraded());
        assertEquals(0,upgradedPopBumper.remainingHitsToUpgrade());
    }

    @Test
    public void DropTargetTest()
    {
        dropTarget.setSeed(1);
        int pointsGiven = dropTarget.hit();
        assertEquals(100,pointsGiven);
        assertTrue(dropTarget.isActive());
    }

    @Test
    public void SpotTargetTest(){
        int pointsGiven = spotTarget.hit();
        assertEquals(0,pointsGiven);


    }


}
