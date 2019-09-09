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
     * Testing method, to evaluate if it has been added
     */
    public boolean containsTag(String strTag, TagInterface obj) {
        /* TODO this part, and return streams based on query */
    }

    /**
     * inserts a tag and the object into the store 
     */
    public void insertTag(String strTag, TagInterface obj) {
        insertTagKeyStore(strTag, obj);
        insertObjKeyStore(obj, strTag);
    }

    /**
     * Insert a n object based on tag into a tag->obj store.
     */
    private void insertTagKeyStore(String strKey, TagInterface objVal) {
        if (! tagKeyStore.containsKey(strKey)) {
            initTagKeyStore(strKey);
        }

        List<TagInterface> xs = tagKeyStore.get(strKey);
        if (xs.contains(objVal)) {
            return;
        } else {
            xs.add(objVal);
        }
    }

    /**
     * Insert an object based on the tag it has, obj->tag store.
     */
    private void insertObjKeyStore(TagInterface objKey, String strVal) {
        if (! objKeyStore.containsKey(objKey)) {
            initObjKeyStore(objKey);
        }

        List<String> xs = objKeyStore.get(objKey);
        if (xs.contains(strVal)) {
            return;
        } else {
            xs.add(strVal);
        }
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
