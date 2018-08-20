package GUI;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.RotationComponent;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PaddleComponent extends Component {
    RotationComponent rotationComponent = new RotationComponent();
    private double initialAngle = 45;
    PhysicsComponent physicsComponent;

    @Override
    public void onAdded() {
        super.onAdded();
        this.entity.getRotationComponent().rotateBy(initialAngle);
    }

    @Override
    public void onUpdate(double tpf) {

    }



    public void activate(double tpf){
        this.physicsComponent.setAngularVelocity(1*tpf);

        this.physicsComponent.setAngularVelocity(-1*tpf);
    }

}
