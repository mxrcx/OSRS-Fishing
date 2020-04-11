import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class FindFishingSpot extends Task {
    @Override
    public boolean validate() {
        return Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Rod Fishing spot")) == null
                && Players.getLocal().getTarget() == null;
    }

    @Override
    public int execute() {
        Log.info("Walk to fishing spot");

        return Random.mid(800, 1300);
    }
}
