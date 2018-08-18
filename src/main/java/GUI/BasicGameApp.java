package GUI;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;


public class BasicGameApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {


        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Basic Game App");

    }

    @Override
    protected void initGame(){
        GameWorld world = getGameWorld();
        Entity bg = ExampleGameFactory.newBackground();
        Entity player = ExampleGameFactory.newPlayer(300,500);
        Entity ball = ExampleGameFactory.newBall(300,300);
        Entity walls = ExampleGameFactory.newWalls();
        world.addEntities(bg,player,ball,walls);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Right") {
            /**
             * Called as long as the trigger is being held (pressed).
             * Starts from the next tick from the one when was triggered
             */
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.PLAYER).forEach(entity -> entity.translateX(5));
            }
        },KeyCode.D);
    }

    @Override
    public void initPhysics(){
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(ExampleType.BALL,ExampleType.WALL) {
                    /**
                     * Called once per collision during the same tick when collision occurred.
                     * Only the first hit box in the collision is passed.
                     *
                     * @param ball    first entity
                     * @param wall    second entity
                     * @param boxBall hit box of first entity
                     * @param boxWall hit box of second entity
                     */
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if(boxWall.getName().equals("BOT"))
                            ball.removeFromWorld();
                    }
                }
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
