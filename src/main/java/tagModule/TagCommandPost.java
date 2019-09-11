package tagModule;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TagCommandPost implements TagObserver {
    private TagStore store;

    public TagCommandPost() {
        this.store = new TagStore();
    }

    public void registerObservable(TagObservable o) {
        o.registerObserver(this);
    }

    public void update(TagObservable o) {

    }

}
