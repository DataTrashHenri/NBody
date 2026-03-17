package florian.schwul;

import com.badlogic.gdx.Gdx;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static florian.schwul.Constants.G;

public class Body extends Movable{
    Force totalForce;
    //public static double G = 6.674e-11;
    public float mass;
    private Vector velocity;
    List<Vector> trace;
    Vector baseVelocity;
    public Body(float x,float y,float mass,float bx, float by) {
        this.x = x;
        this.y = y;
        totalForce = new Force();
        this.mass = mass;
        velocity = new Vector(0,0);
        trace = new ArrayList<>();
        baseVelocity = new Vector(bx,by);
    }
    public void addForceTowards(Body b) {
        float r = (float) Math.sqrt((x-b.x)*(x-b.x) + (y-b.y)*(y-b.y));
        float f = (float) (G * (mass*b.mass)/(r*r));
        Vector v = new Vector(b.x-x,b.y-y).normal();
        v.multiply(f);
        totalForce.addVectorForce(v);
    }
    public void updateVelocityOnForce() {
        Vector acceleration = totalForce.sum();
        acceleration.multiply(1/mass);
        velocity.add(acceleration);
    }
    public void applyVelocity() {
        trace.add(new Vector(x,y));
        if (trace.size()>10000) trace.remove(0);
        x+= velocity.x;
        y+= velocity.y;
    }
    public void clearVelocity() {
        velocity.clear();
        //velocity.add(baseVelocity);
    }
    public void clearForce() {
        Vector pre =totalForce.sum();
        totalForce.reset();
        totalForce.addVectorForce(pre);
    }

}
