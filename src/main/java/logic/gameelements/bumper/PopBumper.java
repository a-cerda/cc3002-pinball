package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper{

    //Return a default PopBumper with 3 hits to upgrade and 100 points per hit

    public PopBumper() {
        super(100, 300, 3);
    }

    //Returns an upgraded PopBumper
    public PopBumper(boolean upgraded){
        super(upgraded,100,300,3);
    }

}
