import tagModule.TagObserver;
import tagModule.TagObservable;

public class TagObserverStub implements TagObserver {

    public int timesUpdated;

    public TagObserverStub(TagObservable o) {
        o.registerObserver(this);
        this.timesUpdated = 0;
    }

    public void update(TagObservable o) {
        ++this.timesUpdated;
    }

    public int getTimesUpdated() {
        return this.timesUpdated;
    }
}
