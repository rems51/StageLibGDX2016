package com.mygdx.game.view;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.models.World;
import com.mygdx.game.models.Bob;
import com.mygdx.game.models.Block;

 
public class WorldRenderer {
 
    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;
	
    private World world;
    private OrthographicCamera cam;
 
    /** 
     * ShapeRenderer permet de dessiner facilement les
     * formes de base
     * Sera utilisé pour des fins de débogage
     * **/
    ShapeRenderer debugRenderer = new ShapeRenderer();
 
    /** Textures **/
    private Texture bobTexture;
    private Texture blockTexture;
 
    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX; // pixels par unité pour X
    private float ppuY;
 
    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }
 
    public WorldRenderer(World world, boolean debug) {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.cam.update();
        this.debug = debug;
        spriteBatch = new SpriteBatch();
        loadTextures();
    }
 
    private void loadTextures() {
        bobTexture = new  Texture(Gdx.files.internal("images/bob_01.png"));
        blockTexture = new Texture(Gdx.files.internal("images/block.png"));
    }
 
    public void render() {
        spriteBatch.begin();
            drawBlocks();
            drawBob();
        spriteBatch.end();
        if (debug)
            drawDebug();
    }
 
    private void drawBlocks() {
        for (Block block : world.getBlocks()) {
            spriteBatch.draw(
                blockTexture, 
                block.getPosition().x * ppuX, 
                block.getPosition().y * ppuY, 
                Block.SIZE * ppuX, 
                Block.SIZE * ppuY);
        }
    }
 
    private void drawBob() {
        Bob bob = world.getBob();
        spriteBatch.draw(
            bobTexture, 
            bob.getPosition().x * ppuX, 
            bob.getPosition().y * ppuY, 
            Bob.SIZE * ppuX, 
            Bob.SIZE * ppuY);
    }
 
    private void drawDebug() {
        // Démarrage du renderer
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Line);
 
        // render blocks
        for (Block block : world.getBlocks()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x ;
            float y1 = block.getPosition().y ;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }
        // Rendre Bob
        Bob bob = world.getBob();
        Rectangle rect = bob.getBounds();
        float x1 = bob.getPosition().x ;
        float y1 = bob.getPosition().y ;
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(x1, y1, rect.width, rect.height);
        debugRenderer.end();
    }
}