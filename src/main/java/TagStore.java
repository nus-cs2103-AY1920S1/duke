/**
 * Store all tags
 *
 */
public class TagStore implements TagObserver {
    public TagStore(TagObservable o) {
        o.registerObserver(this);
    }

    public void update(TagObservable o) {
    }
}
