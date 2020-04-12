import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.regex.Pattern;

public class CookFish extends Task {
    /**
     * If Inventory is full with Raw Fish & Fire exists,
     * cook Fish
     */
    @Override
    public boolean validate() {
        return Players.getLocal().getTarget() == null
                && SceneObjects.getNearest(nearest -> nearest.getName().equals("Fire") && nearest.isPositionInteractable()) != null
                && Inventory.isFull()
                && Inventory.contains(Pattern.compile("Raw .*$"));
    }

    /**
     * Cook Fish
     */
    @Override
    public int execute() {
        Log.info("Cook");

        SceneObject fire = SceneObjects.getNearest(nearest -> nearest.getName().equals("Fire") && nearest.isPositionInteractable());
        Item item = Inventory.getFirst(Pattern.compile("Raw .*$"));
        final int remainingRawFish = Inventory.getCount(Pattern.compile("Raw .*$"));

        if(fire != null && item != null) {
                item.interact("Use");
                Time.sleep(Random.mid(200, 500));
                fire.interact("Use");
        }
        Time.sleepUntil(() -> Inventory.getCount(Pattern.compile("Raw .*$")) != remainingRawFish, Random.mid(3000, 5000));

        return Random.mid(800, 1300);
    }
}
