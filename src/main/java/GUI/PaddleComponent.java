package GUI;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.RotationComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.Timer;
import javafx.util.Duration;
import kotlin.jvm.functions.Function0;

public class PaddleComponent extends Component {
    private double initialAngle = 45;
    PhysicsComponent physicsComponent;
    Timer timer = new Timer();

    @Override
    public void onAdded() {
        super.onAdded();
        this.entity.getRotationComponent().rotateBy(initialAngle);
    }

    @Override
    public void onUpdate(double tpf) {
        limitAngle();
    }

    private void limitAngle() {
        if(entity.getRotation()>80){

            this.physicsComponent.setAngularVelocity(1);
        }
        //if (entity.getRotation()<45){
            //this.physicsComponent.setAngularVelocity(0);
            //this.entity.getRotationComponent().rotateBy(10);
        //}
    }


    public void activate(double tpf){
        this.physicsComponent.setAngularVelocity(-1*tpf);



    }

}
