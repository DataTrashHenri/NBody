package florian.schwul;

import java.util.ArrayList;
import java.util.List;

public class Force {
    private List<Vector> vectors;
    public Force() {
        vectors = new ArrayList<>();
        vectors.add(new Vector(0,0));
    }
    public void addVectorForce(Vector v) {
        vectors.add(v);
    }
    public Vector sum() {
        Vector sum = new Vector(0,0);
        for (Vector v : vectors) {
            sum.add(v);
        }
        return sum;
    }

    public void reset() {
        vectors.clear();
        vectors.add(new Vector(0,0));
    }
}
