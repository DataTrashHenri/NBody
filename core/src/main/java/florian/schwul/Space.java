package florian.schwul;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import java.util.ArrayList;
import java.util.List;

public class Space {
    List<Body> bodies;
    public Space() {
        bodies = new ArrayList<>();
    }
    public void addBody(Body b) {
        bodies.add(b);
    }
    public void simulateTick() {
        for(int i = 0;i< bodies.size();i++) {
            bodies.get(i).clearVelocity();
            bodies.get(i).clearForce();

            for ( int j=0; j<bodies.size();j++) {
                if (i!=j) {
                    bodies.get(i).addForceTowards(bodies.get(j));
                }
            }
            bodies.get(i).updateVelocityOnForce();
            bodies.get(i).applyVelocity();
        }
    }
    public void drawBodies(ShapeRenderer shapeRenderer) {
        for (Body body:bodies) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.FOREST);
            shapeRenderer.circle(body.x,body.y, (float) Math.sqrt(body.mass));
        }
    }
    public void drawTraces(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        for (Body b: bodies) {
            for (Vector v : b.trace) shapeRenderer.rect(v.x-1,v.y-1,2,2);
        }
    }
}
