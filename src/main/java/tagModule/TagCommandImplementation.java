package tagModule;

import java.util.List;
import java.util.ArrayList;
/**
 * Encapsulates Tag Command  updates all Observers
 */
public class TagCommandImplementation implements TagObservable {
    private List<TagObserver> observers;

    public TagCommandImplementation() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(TagObserver o) {
        this.observers.add(o);
    }

    private void notifyObservers() {
        for (TagObserver o : this.observers) {
            o.update(this);
        }
    }

    /**
     * Call  when you need to add a tag
     */
    public void execute(String command) {

        this.notifyObservers();
    }

}
