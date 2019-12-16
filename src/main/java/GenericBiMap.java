//package tagModule;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
/**
 * Store all tags
 *
 */
public class GenericBiMap<L,R> {
    HashMap<R, List<L>> leftKeyStore;
    HashMap<L, List<R>> rightKeyStore;

    public GenericBiMap() {
        this.leftKeyStore = new HashMap<>();
        this.rightKeyStore = new HashMap<>();
    }



    /**
     * Resting method, to evaluate if it has been added
     *  it is inefficint on purpose to check sync.
     */
    public boolean containsRelation(L leftKey, R rightKey) {
        /* these parts, and return streams based on query */
        boolean leftKeyContains = 
            queryByLeftKey(leftKey)
                .anyMatch(x->x.equals(rightKey));
        boolean objHasTag = 
            queryByRightKey(rightKey)
                .anyMatch(x -> x.equals(leftKey));

        //assert here if either differs we have a problem

        // if false && true or true && false, something is wrong
        return leftKeyContains && objHasTag;
    }


    //// Test feature only
    //public String queryAll() {
    //    StringBuilder sb = new StringBuilder();
    //    List<L> tagList = rightKeyStore.keySet()
    //        .stream().collect(Collectors.toList());

    //    for (L tag : tagList) {
    //        sb.append(tag + ":\n");
    //        queryByLeftKey(tag).forEach(x -> sb.append(x + "\n"));
    //        sb.append("\n");
    //    }
    //    return sb.toString();

    //}


    public Stream<String> queryAll() {
        List<String> out = new ArrayList<>();
        List<L> tagList = rightKeyStore.keySet()
            .stream().collect(Collectors.toList());

        for (L tag : tagList) {
            StringBuilder sb = new StringBuilder();
            sb.append(tag + ":\n");
            queryByLeftKey(tag).forEach(x -> sb.append(x + "\n"));
            sb.append("\n");
            out.add(sb.toString());
        }
        return out.stream(); //sb.toString();

    }
    /**
     * Return Stream of hits if string tag can be found in store
     */
    public Stream <R> queryByLeftKey(L leftKey) {
        if (! rightKeyStore.containsKey(leftKey)) {
            return Stream.empty();
        } else {
            return rightKeyStore.get(leftKey).stream();
        }
    }

    /**
     * Return Stream of hits if obj can be found in store
     */
    public Stream <L> queryByRightKey(R rightKey) {
        if (! leftKeyStore.containsKey(rightKey)) {
            return Stream.empty();
        } else {
            return leftKeyStore.get(rightKey).stream();
        }
    }


    // this is used so when you set an object to done you can replace them here
    /**
     * replace an object with a new object keeping all tags
     */
    public boolean updateRight(R oldRightKey, R updatedRightKey) {
        //get oldRightKey->List<L>
        //assign updatedRightKey->List<L> into hashmap
        //for List<L> replaceInLeftKeyStore(key)

        if (! leftKeyStore.containsKey(oldRightKey)) {
            return false;
        }

        List<L> xs = leftKeyStore.get(oldRightKey);
        leftKeyStore.put(updatedRightKey, xs);
        leftKeyStore.remove(oldRightKey);

        boolean result = xs.stream()
            .map(x->replaceInLeftKeyStore(x,oldRightKey, updatedRightKey))
            .allMatch(x->x);
        /*TODO remove the forEach here*/
        //map and then forAll

        return result; //if this is false, SHIIIIIT

    }
    /**
     * Goto Tag KeyStore replace object with replacement
     */
    private boolean replaceInLeftKeyStore(L leftKey, R oldRightVal, R updatedRightVal) {
        //delete Tag, DONOT USE THE PUBLIC METHOD
        //alternatively, replace a one for one but im not doing
        //that now
        //arrayList.replace()?
        if (!deleteFromLeftKeyStore(leftKey, oldRightVal)) return false;
        insertLeftKeyStore(leftKey, updatedRightVal);
        return true;

    }

    /**
     * replace a tag with a new tag keeping all objs
     */
    public boolean updateLeft(L oldLeftKey, L updatedLeftKey) {
        //get oldLeftKey->List<R>
        //assign updatedLeftKey->List<Obj> into hashmap
        //for List<Obj> replaceInRightKeyStore(updatedLeftKey)

        if (! rightKeyStore.containsKey(oldLeftKey)) {
            return false;
        }

        List<R> xs = rightKeyStore.get(oldLeftKey);
        rightKeyStore.put(updatedLeftKey, xs);
        rightKeyStore.remove(oldLeftKey);

        boolean result = xs.stream()
            .map(x->replaceInRightKeyStore(x,oldLeftKey, updatedLeftKey))
            .allMatch(x->x);

        return result; //if this is false, SHIIIIIT

    }

    /**
     * Goto Tag KeyStore replace object with replacement
     */
    private boolean replaceInRightKeyStore(R rightKey, L oldLeftVal, L updatedLeftVal) {

        if (!deleteFromRightKeyStore(rightKey, oldLeftVal)) return false;
        insertRightKeyStore(rightKey, updatedLeftVal);
        return true;

    }


    /**
     * Delete an object and all instance of it from tags
     */
    public boolean deleteRight(R rightKey) {
        if (! leftKeyStore.containsKey(rightKey)) {
            return false;
        }

        List<L> tagList = leftKeyStore.get(rightKey);

        //remove rightKey from objHashTable
        leftKeyStore.remove(rightKey);

        //remove obj from tagHashTable
        boolean result = tagList.stream()
            .map(tag ->deleteFromLeftKeyStore(tag, rightKey))
            .allMatch(x->x);

        return result; //if this is false, SHIIIIIT
    }


    /**
     * Delete an tag and all instance of it from objs 
     */
    public boolean deleteLeft(L leftKey) {
        if (! rightKeyStore.containsKey(leftKey)) {
            return false;
        }

        List<R> objList = rightKeyStore.get(leftKey);

        //remove rightKey from objHashTable
        rightKeyStore.remove(leftKey);

        //remove obj from tagHashTable
        boolean result = objList.stream()
            .map(x->deleteFromRightKeyStore(x, leftKey))
            .allMatch(x->x);

        return result; //if this is false, SHIIIIIT
    }


    /* delete object so need to delete both tables all inst */

    /**
     * Delete a remove a tag-obj relationship from all stores
     */
    public boolean deleteRelation(L str, R obj) {
        if (! deleteFromLeftKeyStore(str, obj)) return false;
        if (! deleteFromRightKeyStore(obj, str)) return false;
        return true;
    }


    /**
     * Goto ObjkeyStore, and remove a tag given the object
     *  INTERNAL METHOD ONLY.
     */
    private boolean deleteFromRightKeyStore(R rightKey, L leftVal) {
        if (! leftKeyStore.containsKey(rightKey)) {
            return false;
        }

        List<L> xs = leftKeyStore.get(rightKey);
        if (xs.contains(leftVal)) {
            int indexToRemove = xs.indexOf(leftVal);
            assert xs.size() > indexToRemove 
                : "OwO leftKeyStore index not in bound OwO";
            xs.remove(indexToRemove);
            return true;
        } else {
            return false;
        }
    }

    private boolean deleteFromRightKeyStore(R rightKey, int index) {
        if (! leftKeyStore.containsKey(rightKey)) {
            return false;
        }

        List<L> xs = leftKeyStore.get(rightKey);
        if (xs.size() > index) {
            xs.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete a tag from an object in a tag key store
     *  @return false on failure to locate Tag or Object not 
     *  tagged to object.
     */
    private boolean deleteFromLeftKeyStore(L leftKey, R rightVal) {
        if (! rightKeyStore.containsKey(leftKey)) {
            // tag not found in store
            return false;
        }

        List<R> xs = rightKeyStore.get(leftKey);
        if (xs.contains(rightVal)) {
            int indexToRemove = xs.indexOf(rightVal);
            assert xs.size() > indexToRemove 
                : "OwO rightKeyStore index not in bound OwO";
            xs.remove(indexToRemove);
            return true;
        } else {
            // object is not tagged to the given leftKey tag
            return false;
        }
    }
    private boolean deleteFromLeftKeyStore(L leftKey, int index) {
        if (! rightKeyStore.containsKey(leftKey)) {
            return false;
        }

        List<R> xs = rightKeyStore.get(leftKey);
        if (xs.size() > index) {
            xs.remove(index);
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * inserts a tag and the object into the store 
     */
    public void insertRelation(L leftKey, R rightKey) {
        insertLeftKeyStore(leftKey, rightKey);
        insertRightKeyStore(rightKey, leftKey);
    }

    /**
     * Insert a n object based on tag into a tag->obj store.
     */
    private void insertLeftKeyStore(L leftKey, R rightVal) {
        if (! rightKeyStore.containsKey(leftKey)) {
            initLeftKeyStore(leftKey);
        }

        List<R> xs = rightKeyStore.get(leftKey);
        if (xs.contains(rightVal)) {
            return;
        } else {
            xs.add(rightVal);
        }
    }

    /**
     * Insert an object based on the tag it has, obj->tag store.
     */
    private void insertRightKeyStore(R rightKey, L leftVal) {
        if (! leftKeyStore.containsKey(rightKey)) {
            initRightKeyStore(rightKey);
        }

        List<L> xs = leftKeyStore.get(rightKey);
        if (xs.contains(leftVal)) {
            //duplicate
            return;
        } else {
            xs.add(leftVal);
        }
    }

    /**
     * Initializes List in Hashmap before it can be used.
     */
    private void initLeftKeyStore(L leftKey) {
        List<R> objList = new ArrayList<>();
        rightKeyStore.put(leftKey, objList);
    }

    /**
     * Initializes List in Hashmap before it can be used.
     */
    private void initRightKeyStore(R rightKey) {
        List<L> tagList = new ArrayList<>();
        leftKeyStore.put(rightKey, tagList);
    }

}
