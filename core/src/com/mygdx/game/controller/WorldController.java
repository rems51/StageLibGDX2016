package com.mygdx.game.controller;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.game.models.Bob;
import com.mygdx.game.models.Bob.State;
import com.mygdx.game.models.World;
 
 
public class WorldController {
 
    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }
 
    private World   world;
    private Bob     bob;
 
    static Map<Keys, Boolean> keys = 
        new HashMap<WorldController.Keys, Boolean>();
 
    // Initialisation static du hashmap
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    };
 
    public WorldController(World world) {
        this.world = world;
        this.bob = world.getBob();
    }
 
    // ** Écran touchée ou touche appuyée *********** //
 
    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }
 
    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }
 
    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }
 
    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, false));
    }
 
    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }
 
    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }
 
    public void jumpReleased() {
        keys.get(keys.put(Keys.JUMP, false));
    }
 
    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
    }
 
    /** Méthode de mise à jour principale **/
    public void update(float delta) {
        processInput();
        bob.update(delta);
    }
 
    /** Modification des paramètres et de l'état de Bob selon les entrées **/
    private void processInput() {
        if (keys.get(Keys.LEFT)) {
            // Flèche de gauche
            bob.setFacingLeft(true);
            bob.setState(State.WALKING);
            bob.getVelocity().x = -Bob.SPEED;
        }
 
        if (keys.get(Keys.RIGHT)) {
            // Flèche de droite
            bob.setFacingLeft(false);
            bob.setState(State.WALKING);
            bob.getVelocity().x = Bob.SPEED;
        }
 
        // On immobilise Bob si les deux touches sont appuyées.
        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
            bob.setState(State.IDLE);
            // acceleration is 0 on the x
            bob.getAcceleration().x = 0;
            // horizontal speed is 0
            bob.getVelocity().x = 0;
        }
    }
}
