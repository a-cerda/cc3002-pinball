package GUI;


import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public final class PinballFactory {
    public static Entity newPlayer(double x, double y) {
        return Entities.builder()
                .at(x, y)
                .viewFromNode(new Rectangle(100, 30, Color.BLUE))
                .bbox(new HitBox("Player",BoundingShape.box(100,30)))
                .with(new CollidableComponent(true))
                .type(PinballTypes.PLAYER)
                .build();
    }
    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(600, 600, Color.DARKGRAY))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Entity newBall(double x, double y){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(1,12));


        return Entities.builder()
                .at(x,y)
                .type(PinballTypes.BALL)
                .bbox(new HitBox("Ball",BoundingShape.circle(10)))
                .viewFromNode(new Circle(10,Color.LIGHTCORAL))
                .with(physics, new CollidableComponent(true))
                .with(new BallComponent())
                .build();
    }

    public static Entity newWalls(){
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(PinballTypes.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public static Entity newPaddle(double x, double y){
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.KINEMATIC);

        return Entities.builder()
                .at(x,y)
                .type(PinballTypes.PADDLE)
                .viewFromNodeWithBBox(new Rectangle(100,20,Color.TURQUOISE))
                .with(physicsComponent, new CollidableComponent(true))
                .with(new PaddleComponent())
                .build();
    }

    public static Entity newPopBumper(double x, double y){
        return Entities.builder()
                .build();
    }

    public static Entity newKickerBumper(double x, double y){
        return Entities.builder()
                .at(x,y)
                .build();
    }

    public static Entity newSpotTarget(double x, double y){
        return Entities.builder()
                .at(x,y)
                .build();
    }

    public static Entity newDropTarget(double x, double y){
        return Entities.builder()
                .at(x,y)
                .build();
    }

}