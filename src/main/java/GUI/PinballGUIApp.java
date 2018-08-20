package GUI;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;
import facade.HomeworkTwoFacade;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import GUI.BallComponent;
import java.awt.*;
import java.util.ArrayList;


public class PinballGUIApp extends GameApplication {

    private int playerSpeed = 2;
    private double paddleAngle = 1;
    private HomeworkTwoFacade facade = new HomeworkTwoFacade();

    private BallComponent getBallComponent(){
        return getGameWorld().getSingleton(PinballTypes.BALL).get().getComponent(BallComponent.class);
    }

    private PaddleComponent getPaddleComponent() {
        return getGameWorld().getSingleton(PinballTypes.PADDLE).get().getComponent(PaddleComponent.class);
    }

    @Override
    protected void initSettings(GameSettings settings) {


        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Basic Game App");

    }

    @Override
    protected void initGame(){

        facade.setGameTable(facade.newFullPlayableTable("Table1",5,0.3,4,3));

        GameWorld world = getGameWorld();
        Entity bg = PinballFactory.newBackground();
        Entity player = PinballFactory.newPlayer(300,500);
        Entity ball = PinballFactory.newBall(300,300);
        Entity walls = PinballFactory.newWalls();
        Entity paddle = PinballFactory.newPaddle(200,250);
        //
        world.addEntities(bg,player,ball,walls,paddle);

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
                getGameWorld().getEntitiesByType(PinballTypes.PLAYER).forEach(entity -> entity.translateX(playerSpeed));
            }
        },KeyCode.D);
        input.addAction(new UserAction("Move Up") {
            /**
             * Called as long as the trigger is being held (pressed).
             * Starts from the next tick from the one when was triggered
             */
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(PinballTypes.PLAYER).forEach(entity -> entity.translateY(-playerSpeed));
            }
        },KeyCode.W);
        input.addAction(new UserAction("Move Left") {
            /**
             * Called as long as the trigger is being held (pressed).
             * Starts from the next tick from the one when was triggered
             */
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(PinballTypes.PLAYER).forEach(entity -> entity.translateX(-playerSpeed));
            }
        },KeyCode.A);
        input.addAction(new UserAction("Move Down") {
            /**
             * Called as long as the trigger is being held (pressed).
             * Starts from the next tick from the one when was triggered
             */
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(PinballTypes.PLAYER).forEach(entity -> entity.translateY(playerSpeed));
            }
        },KeyCode.S);
        input.addAction(new UserAction("Left Paddle") {
            /**
             * Called as long as the trigger is being held (pressed).
             * Starts from the next tick from the one when was triggered
             */
            @Override
            protected void onAction() {

                getPaddleComponent().activate(1);

            }
        },KeyCode.Z);
    }


    @Override
    public void initPhysics(){
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(PinballTypes.BALL,PinballTypes.WALL) {
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
                            facade.dropBall();
                            if(facade.gameOver()){
                                Text gameOverText = getUIFactory().newText("GAME OVER",Color.WHITE,50);
                                gameOverText.setX(300);
                                gameOverText.setY(300);
                                getGameScene().addUINode(gameOverText);
                            }
                    }
                }
        );
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(PinballTypes.PLAYER,PinballTypes.WALL) {

                    @Override
                    protected void onHitBoxTrigger(Entity player, Entity wall, HitBox boxPlayer, HitBox boxWall) {
                        if(boxWall.getName().equals("RIGHT")){
                            player.setPosition(boxWall.getHeight(),boxWall.getWidth());
                        }

                    }
                }
        );

        getBallComponent().release();

    }

    @Override
    protected void initUI() {

        Text textPixels = getUIFactory().newText("qwe",Color.WHITE, 22);
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100
        textPixels.setText("Score: "+facade.getCurrentScore());

        getGameScene().addUINode(textPixels); // add to the scene graph

    }

    @Override
    protected void preInit(){
        //getAudioPlayer().loopBGM("bgmusic2.mp3");
        //getAssetLoader().loadMusic("bgmusic2.mp3");
        //getAudioPlayer().loopBGM("bg.mp3");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
