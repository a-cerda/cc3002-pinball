package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {

    //Return a default KickerBumper with 5 hits to upgrade and 500 points per hit
    public KickerBumper()
    {
        super(500,1000,5);
    }
}
