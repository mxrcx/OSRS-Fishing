import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "OSRS-Fishing", developer = "Marco Schaarschmidt", desc = "Fishing-Bot for Old School RuneScape. It fishes according to the skill level automatically.", category = ScriptCategory.FISHING)
public class Main extends TaskScript {
    @Override
    public void onStart() {
        submit(new FindFishingSpot(), new Fish(), new CookFish(), new DropFish());
    }
}
