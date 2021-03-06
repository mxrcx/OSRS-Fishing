import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.regex.Pattern;

public class DropFish extends Task {

    /**
     * If Inventory is full with Fish,
     * drop Fish
     */
    @Override
    public boolean validate() {
        return Players.getLocal().getTarget() == null
                && Inventory.isFull()
                && (Inventory.contains(Pattern.compile("Burnt .*$")) || Inventory.contains(item -> item.containsAction("Eat")));
    }

    /**
     * Drop Fish
     */
    @Override
    public int execute() {
        Log.info("Drop");
        final int remainingSlots = Inventory.getFreeSlots();

        if(Inventory.contains(Pattern.compile("Burnt .*$"))){
            Inventory.getFirst(Pattern.compile("Burnt .*$")).interact("Drop");
        }
        else if(Inventory.contains(item -> item.containsAction("Eat"))){
            Inventory.getFirst(item -> item.containsAction("Eat")).interact("Drop");
        }

        Time.sleepUntil(() -> Inventory.getFreeSlots() != remainingSlots, Random.mid(1500, 2000));

        return Random.mid(800, 1300);
    }
}
