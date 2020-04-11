import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Fish extends Task {

    /**
     * If a Fishing spot is accessible & required items in inventory,
     * go fishing
     */
    @Override
    public boolean validate() {
        return Npcs.getNearest(nearest -> nearest.isPositionInteractable() && (nearest.getName().equals("Rod Fishing spot") || nearest.getName().equals("Net Fishing spot"))) != null
                && Players.getLocal().getTarget() == null
                && !Inventory.isFull()
                && ((Inventory.contains("Fly fishing rod") && Inventory.contains("Feather")) || (Inventory.contains("Fishing rod") && Inventory.contains("Fishing bait")) || Inventory.contains("Small fishing net"));
    }

    /**
     * Go fishing
     */
    @Override
    public int execute() {
        Log.info("Fish");

        Npc fishingSpot = Npcs.getNearest(nearest -> nearest.isPositionInteractable() && (nearest.getName().equals("Rod Fishing spot") || nearest.getName().equals("Net Fishing spot")));
        final int remainingSlots = Inventory.getFreeSlots();

        if(fishingSpot != null){
            if(Inventory.contains("Fly fishing rod") && Inventory.contains("Feather")){
                fishingSpot.interact("Lure");
            }
            else if(Inventory.contains("Fishing rod") && Inventory.contains("Fishing bait")){
                fishingSpot.interact("Bait");
            }
        }

        Time.sleepUntil(() -> Inventory.getFreeSlots() != remainingSlots, Random.mid(3000, 5000));

        return Random.mid(800, 1300);
    }
}
