package florian.schwul;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    Space space;
    ShapeRenderer shapeRenderer;

    Vector v = new Vector(0,1);
    float t =0;
    float mass =100;


    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        space = new Space();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        shapeRenderer.setAutoShapeType(true);
        v = new Vector((float) Math.sin(t), (float) Math.cos(t)).normal();
        shapeRenderer.begin();

        space.drawTraces(shapeRenderer);
        space.drawBodies(shapeRenderer);

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) mass+=10;
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) mass-=10;

        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            Body b = new Body(50,300f,mass,v.x*30,v.y*30);
            b.totalForce.addVectorForce(new Vector(0,50));
            space.addBody(b);

            Body c = new Body(300f,550f,mass,v.x*30,v.y*30);
            c.totalForce.addVectorForce(new Vector(50,0));
            space.addBody(c);

            Body d = new Body(550,300f,mass,v.x*30,v.y*30);
            d.totalForce.addVectorForce(new Vector(0,-50));
            space.addBody(d);

            Body e = new Body(300,50,mass,v.x*30,v.y*30);
            e.totalForce.addVectorForce(new Vector(-50,0));
            space.addBody(e);
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
        {
            Body b = new Body(mouseX,mouseY,mass,v.x*30,v.y*30);
            b.totalForce.addVectorForce(new Vector(v.x*30,v.y*30));
            space.addBody(b);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.E)) t+=0.05f;
        if ( Gdx.input.isKeyPressed(Input.Keys.Q)) t-=0.05f;



        shapeRenderer.rect(mouseX-(mass/2)/10,mouseY-(mass/2)/10,mass/10,mass/10);
        shapeRenderer.line(mouseX,mouseY,(mouseX+v.x*40),(mouseY+v.y*40));

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) space.bodies.clear();

        shapeRenderer.end();
        batch.end();
        space.simulateTick();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
