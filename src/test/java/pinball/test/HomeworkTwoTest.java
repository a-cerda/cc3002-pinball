package pinball.test;


import controller.Game;
import facade.HomeworkTwoFacade;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.NullTable;
import logic.table.PlayableTable;


import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private HomeworkTwoFacade hw2;



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
        playableTable = new PlayableTable("test1",5,0.4);

        hw2= new HomeworkTwoFacade();


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

    }

    @Test
    public void SpotTargetTest(){
        int pointsGiven = spotTarget.hit();
        assertEquals(0,pointsGiven);



    }
    @Test
    public void GameTest(){
        game.addBall();
        assertEquals(4,game.getAvailableBalls());
        game.triggerExtraBallBonus();
        assertEquals(5,game.getAvailableBalls());
        game.dropBall();
        game.dropBall();
        assertEquals(3,game.getAvailableBalls());
        game.setCurrentTable(playableTable);
        playableTable.upgradeAllBumpers();
        System.out.println(game.getAvailableBalls());
        List<Bumper> bumperList = game.getBumpers();
        for (Bumper b:
             bumperList) {
            assertTrue(b.isUpgraded());
        }
    }

    @Test
    public void testTableUpdates() {

        hw2.setGameTable(hw2.newFullPlayableTable("Test", 10, 0.5, 10, 5));
        // targets
        List<Target> targets = hw2.getTargets();
        List<Target> dropTargetList = targets
                .stream()
                .filter(target -> target instanceof DropTarget)
                .collect(Collectors.toList());

        assertEquals(0, hw2.getCurrentTable().getCurrentlyDroppedDropTargets());
        dropTargetList.get(0).hit();
        assertEquals(1, hw2.getCurrentTable().getCurrentlyDroppedDropTargets());
        dropTargetList.get(1).hit();
        assertEquals(2, hw2.getCurrentTable().getCurrentlyDroppedDropTargets());
        dropTargetList.get(2).hit();
        assertEquals(3, hw2.getCurrentTable().getCurrentlyDroppedDropTargets());
        dropTargetList.get(3).hit();
        assertEquals(4, hw2.getCurrentTable().getCurrentlyDroppedDropTargets());
        assertFalse(dropTargetList
                .stream()
                .map(Target::isActive)
                .reduce(
                        true,
                        (a, b) -> a && b));
        hw2.getCurrentTable().resetDropTargets();
        assertEquals(0, hw2.getCurrentTable().getCurrentlyDroppedDropTargets());
        assertTrue(dropTargetList
                .stream()
                .map(Target::isActive)
                .reduce(
                        true,
                        (a, b) -> a && b));

        // bumpers
        List<Bumper> bumpers = hw2.getBumpers();
        hw2.getCurrentTable().upgradeAllBumpers();
        assertTrue(bumpers
                .stream()
                .map(Bumper::isUpgraded)
                .reduce(
                        true,
                        (a, b) -> a && b));
    }
    @Test
    public void testFullPlayableTable() {
        String name = "Test table";
        int numberOfBumpers = 20;
        int numberOfSpot = 10;
        int numberOfDrop = 20;

        // only spot
        Table tableOnlySpot = hw2.newFullPlayableTable(name, numberOfBumpers, 1, numberOfSpot, 0);
        // basic
        assertEquals(name, tableOnlySpot.getTableName());
        assertEquals(0, tableOnlySpot.getNumberOfDropTargets());
        assertEquals(0, tableOnlySpot.getCurrentlyDroppedDropTargets());
        assertFalse(tableOnlySpot.getBumpers().isEmpty());
        assertEquals(numberOfBumpers, tableOnlySpot.getBumpers().size());
        assertFalse(tableOnlySpot.getTargets().isEmpty());
        assertEquals(numberOfSpot, tableOnlySpot.getTargets().size());
        long countOfSpot = tableOnlySpot.getTargets()
                .stream()
                .filter(target -> target instanceof SpotTarget)
                .count();
        long countOfDrop = tableOnlySpot.getTargets()
                .stream()
                .filter(target -> target instanceof DropTarget)
                .count();
        assertEquals(0, countOfDrop);
        assertEquals(numberOfSpot, countOfSpot);
        assertTrue(tableOnlySpot.isPlayableTable());

        // only drop
        Table tableOnlyDrop = hw2.newFullPlayableTable(name, numberOfBumpers, 1, 0, numberOfDrop);
        // basic
        assertEquals(name, tableOnlyDrop.getTableName());
        assertEquals(numberOfDrop, tableOnlyDrop.getNumberOfDropTargets());
        assertEquals(0, tableOnlyDrop.getCurrentlyDroppedDropTargets());
        assertFalse(tableOnlyDrop.getBumpers().isEmpty());
        assertEquals(numberOfBumpers, tableOnlyDrop.getBumpers().size());
        assertFalse(tableOnlyDrop.getTargets().isEmpty());
        assertEquals(numberOfDrop, tableOnlyDrop.getTargets().size());
        countOfSpot = tableOnlyDrop.getTargets()
                .stream()
                .filter(target -> target instanceof SpotTarget)
                .count();
        countOfDrop = tableOnlyDrop.getTargets()
                .stream()
                .filter(target -> target instanceof DropTarget)
                .count();
        assertEquals(numberOfDrop, countOfDrop);
        assertEquals(0, countOfSpot);
        assertTrue(tableOnlyDrop.isPlayableTable());

        // both
        Table normalTable = hw2.newFullPlayableTable(name, numberOfBumpers, 1, numberOfSpot, numberOfDrop);
        // basic
        assertEquals(name, normalTable.getTableName());
        assertEquals(numberOfDrop, normalTable.getNumberOfDropTargets());
        assertEquals(0, normalTable.getCurrentlyDroppedDropTargets());
        assertFalse(normalTable.getBumpers().isEmpty());
        assertEquals(numberOfBumpers, normalTable.getBumpers().size());
        assertFalse(normalTable.getTargets().isEmpty());
        assertEquals(numberOfSpot + numberOfDrop, normalTable.getTargets().size());
        countOfSpot = normalTable.getTargets()
                .stream()
                .filter(target -> target instanceof SpotTarget)
                .count();
        countOfDrop = normalTable.getTargets()
                .stream()
                .filter(target -> target instanceof DropTarget)
                .count();
        assertEquals(numberOfDrop, countOfDrop);
        assertEquals(numberOfSpot, countOfSpot);
        assertTrue(normalTable.isPlayableTable());
    }
    @Test
    public void testInitialSetting() {
        // general state
        assertFalse(hw2.gameOver());
        assertEquals(0, hw2.getCurrentScore());
        assertEquals(3, hw2.getAvailableBalls());
        assertTrue(hw2.getBumpers().isEmpty());
        assertTrue(hw2.getTargets().isEmpty());
        assertEquals("", hw2.getTableName());
        assertFalse(hw2.isPlayableTable());

        // bonus
        assertEquals(0, hw2.getDropTargetBonus().timesTriggered());
        assertEquals(0, hw2.getExtraBallBonus().timesTriggered());
        assertEquals(0, hw2.getJackPotBonus().timesTriggered());

        // table
        Table table = hw2.getCurrentTable();
        assertEquals("", table.getTableName());
        assertEquals(0, table.getNumberOfDropTargets());
        assertEquals(0, table.getCurrentlyDroppedDropTargets());
        assertTrue(table.getBumpers().isEmpty());
        assertTrue(table.getTargets().isEmpty());
        assertFalse(table.isPlayableTable());
    }
    @Test
    public void testNewTableBehaviour() {
        String name = "Test table";
        Table newTable = hw2.newFullPlayableTable(name, 10, 1, 10, 10);

        hw2.setGameTable(newTable);
        assertFalse(hw2.getBumpers().isEmpty());
        assertFalse(hw2.getTargets().isEmpty());
        assertEquals(name, hw2.getTableName());
        assertTrue(hw2.isPlayableTable());
        assertEquals(newTable, hw2.getCurrentTable());
    }
    @Test
    public void testBasicBumperBehaviour() {
        hw2.setGameTable(hw2.newFullPlayableTable("Test", 10, 0.5, 10, 5));
        List<Bumper> bumpers = hw2.getBumpers();
        List<Bumper> popBumperList = bumpers
                .stream()
                .filter(bumper -> bumper instanceof PopBumper)
                .collect(Collectors.toList());
        List<Bumper> kickerBumperList = bumpers
                .stream()
                .filter(bumper -> bumper instanceof KickerBumper)
                .collect(Collectors.toList());

        int expectedScore = 0;
        assertEquals(expectedScore, hw2.getCurrentScore());

        // hit all
        bumpers.forEach(Hittable::hit);
        // count expected score
        int popBumperBaseScore=100;
        int kickerBumperBaseScore = 500;
        int baseScore = popBumperList.size() * popBumperBaseScore + kickerBumperList.size() * kickerBumperBaseScore;
        expectedScore += baseScore;
        assertEquals(expectedScore, hw2.getCurrentScore());
        //check remaining hits to upgrade
        assertTrue(popBumperList
                .stream()
                .allMatch(bumper -> bumper.remainingHitsToUpgrade() == 2));
        assertTrue(kickerBumperList
                .stream()
                .allMatch(bumper -> bumper.remainingHitsToUpgrade() == 4));

        // hit everything 2 times
        repeat(2, () -> bumpers.forEach(Hittable::hit));
        // pop upgraded
        int popBumperUpgradeScore = 300;
        expectedScore += (baseScore + popBumperList.size() * popBumperUpgradeScore + kickerBumperList.size() * kickerBumperBaseScore);
        assertEquals(expectedScore, hw2.getCurrentScore());
        // check that pop is upgraded
        assertTrue(popBumperList
                .stream()
                .map(Bumper::isUpgraded)
                .reduce(
                        true,
                        (a, b) -> a && b));
        assertFalse(kickerBumperList
                .stream()
                .map(Bumper::isUpgraded)
                .reduce(
                        false,
                        (a, b) -> a || b));
        //check remaining hits to upgrade
        assertTrue(popBumperList
                .stream()
                .allMatch(bumper -> bumper.remainingHitsToUpgrade() == 0));
        assertTrue(kickerBumperList
                .stream()
                .allMatch(bumper -> bumper.remainingHitsToUpgrade() == 2));

        // 2 more times
        repeat(2, () -> bumpers.forEach(Hittable::hit));
        int kickerBumperUpgradeScore = 1000;
        expectedScore += popBumperList.size() * popBumperUpgradeScore * 2 +
                kickerBumperList.size() * kickerBumperUpgradeScore + kickerBumperList.size() * kickerBumperBaseScore;
        assertEquals(expectedScore, hw2.getCurrentScore());
        // everything should be upgraded
        assertTrue(bumpers
                .stream()
                .map(Bumper::isUpgraded)
                .reduce(
                        true,
                        (a, b) -> a && b));
        //check remaining hits to upgrade
        assertTrue(popBumperList
                .stream()
                .allMatch(bumper -> bumper.remainingHitsToUpgrade() == 0));
        assertTrue(kickerBumperList
                .stream()
                .allMatch(bumper -> bumper.remainingHitsToUpgrade() == 0));

        // hit 6th time
        bumpers.forEach(Hittable::hit);
        expectedScore += popBumperList.size() * popBumperUpgradeScore + kickerBumperList.size() * kickerBumperUpgradeScore;
        assertEquals(expectedScore, hw2.getCurrentScore());

        // downgrade and hit
        bumpers.forEach(Bumper::downgrade);
        assertFalse(bumpers
                .stream()
                .map(Bumper::isUpgraded)
                .reduce(
                        false,
                        (a, b) -> a || b));
        bumpers.forEach(Hittable::hit);
        expectedScore += baseScore;
        assertEquals(expectedScore, hw2.getCurrentScore());


        // upgrade and hit
        bumpers.forEach(Bumper::upgrade);
        assertTrue(bumpers
                .stream()
                .map(Bumper::isUpgraded)
                .reduce(
                        true,
                        (a, b) -> a && b));
        bumpers.forEach(Hittable::hit);
        expectedScore += popBumperList.size() * popBumperUpgradeScore + kickerBumperList.size() * kickerBumperUpgradeScore;
        assertEquals(expectedScore, hw2.getCurrentScore());
    }
    @Test
    public void testJackPotBonus() {
        hw2.setGameTable(hw2.newFullPlayableTable("Test", 10, 0.5, 10, 5));
        List<Target> targets = hw2.getTargets();
        List<Target> spotTargetList = targets
                .stream()
                .filter(target -> target instanceof SpotTarget)
                .collect(Collectors.toList());

        int expectedScore = 0;
        assertEquals(expectedScore, hw2.getCurrentScore());
        spotTargetList.forEach(Hittable::hit);
        // check all inactive
        assertFalse(spotTargetList
                .stream()
                .map(Target::isActive)
                .reduce(
                        false,
                        (a, b) -> a || b));
        int spotTargetScore = 0;
        int jackPotBonusScore = 100000;
        expectedScore += spotTargetScore * spotTargetList.size() + jackPotBonusScore * spotTargetList.size();
        assertEquals(expectedScore, hw2.getCurrentScore());

        // times triggered
        assertEquals(spotTargetList.size(), hw2.getJackPotBonus().timesTriggered());
    }
    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }
}

