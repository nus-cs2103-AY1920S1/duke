import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Store all tags
 *
 */
public class TagStore implements TagObserver {
    HashMap<TagInterface, List<String>> objKeyStore;
    HashMap<String, List<TagInterface>> tagKeyStore;

    public TagStore() {
        this.objKeyStore = new HashMap<>();
        this.tagKeyStore = new HashMap<>();
    }

    public TagStore(TagObservable o) {
        o.registerObserver(this);
    }

    /**
     * Initializes List in Hashmap before it can be used.
     */
    private void initTagKeyStore(String tagStr) {
        List<TagInterface> objList = new ArrayList<>();
        tagKeyStore.put(tagStr, objList);
    }

    /**
     * Initializes List in Hashmap before it can be used.
     */
    private void initObjKeyStore(TagInterface objKey) {
        List<String> tagList = new ArrayList<>();
        objKeyStore.put(objKey, tagList);
    }

    public void update(TagObservable o) {

    }
}
