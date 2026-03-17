package florian.schwul;

public class Vector {
    public float x;
    public float y;
    public Vector(float x, float y) {
        this.x=x;
        this.y=y;
    }
    public Vector normal() {
        float l = (float) Math.sqrt(x*x+y*y);
        return new Vector(x/l,y/l);
    }
    public void add(Vector v) {
        x+=v.x;
        y+=v.y;
    }
    public void multiply(float scalar) {
        x*=scalar;
        y*=scalar;
    }
    public void clear() {
        x=0;
        y=0;
    }
}
