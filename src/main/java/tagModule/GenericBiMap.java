package tagModule;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
/**
 * Store all tags
 *
 */
public class GenericBiMap<E,T> {
    HashMap<T, List<E>> objKeyStore;
    HashMap<E, List<T>> tagKeyStore;

    public GenericBiMap() {
        this.objKeyStore = new HashMap<>();
        this.tagKeyStore = new HashMap<>();
    }



    /**
     * Testing method, to evaluate if it has been added
     *  it is inefficint on purpose to check sync.
     */
    public boolean containsTagPair(E strTag, T obj) {
        /* these parts, and return streams based on query */
        boolean strTagContains = 
            queryByStringTag(strTag)
                .anyMatch(x->x.equals(obj));
        boolean objHasTag = 
            queryByObjTag(obj)
                .anyMatch(x -> x.equals(strTag));

        //assert here if either differs we have a problem

        // if false && true or true && false, something is wrong
        return strTagContains && objHasTag;
    }


    // Test feature only
    public String queryAll() {
        StringBuilder sb = new StringBuilder();
        List<E> tagList = tagKeyStore.keySet()
            .stream().collect(Collectors.toList());

        for (E tag : tagList) {
            sb.append(tag + ":\n");
            queryByStringTag(tag).forEach(x -> sb.append(x + "\n"));
            sb.append("\n");
        }
        return sb.toString();

    }

    /**
     * Return Stream of hits if string tag can be found in store
     */
    public Stream <T> queryByStringTag(E strKey) {
        if (! tagKeyStore.containsKey(strKey)) {
            return Stream.empty();
        } else {
            return tagKeyStore.get(strKey).stream();
        }
    }

    /**
     * Return Stream of hits if obj can be found in store
     */
    public Stream <E> queryByObjTag(T obj) {
        if (! objKeyStore.containsKey(obj)) {
            return Stream.empty();
        } else {
            return objKeyStore.get(obj).stream();
        }
    }


    // this is used so when you set an object to done you can replace them here
    /**
     * replace an object with a new object keeping all tags
     */
    public boolean replaceTaggedObject(T oldObj, T updatedObj) {
        //get oldObj->List<E>
        //assign updatedObj->List<E> into hashmap
        //for List<E> replaceTagKeyStore(key)

        if (! objKeyStore.containsKey(oldObj)) {
            return false;
        }

        List<E> xs = objKeyStore.get(oldObj);
        objKeyStore.put(updatedObj, xs);
        objKeyStore.remove(oldObj);

        boolean result = xs.stream()
            .map(x->replaceTagKeyStore(x,oldObj, updatedObj))
            .allMatch(x->x);
        /*TODO remove the forEach here*/
        //map and then forAll

        return result; //if this is false, SHIIIIIT

    }
    /**
     * Goto Tag KeyStore replace object with replacement
     */
    private boolean replaceTagKeyStore(E strKey, T oldObj, T updatedObj) {
        //delete Tag, DONOT USE THE PUBLIC METHOD
        //alternatively, replace a one for one but im not doing
        //that now
        //arrayList.replace()?
        if (!deleteTagKeyStore(strKey, oldObj)) return false;
        insertTagKeyStore(strKey, updatedObj);
        return true;

    }

    /**
     * replace a tag with a new tag keeping all objs
     */
    public boolean replaceTag(E oldTag, E updatedTag) {
        //get oldTag->List<T>
        //assign updatedTag->List<Obj> into hashmap
        //for List<Obj> replaceObjKeyStore(updatedTag)

        if (! tagKeyStore.containsKey(oldTag)) {
            return false;
        }

        List<T> xs = tagKeyStore.get(oldTag);
        tagKeyStore.put(updatedTag, xs);
        tagKeyStore.remove(oldTag);

        boolean result = xs.stream()
            .map(x->replaceObjKeyStore(x,oldTag, updatedTag))
            .allMatch(x->x);

        return result; //if this is false, SHIIIIIT

    }

    /**
     * Goto Tag KeyStore replace object with replacement
     */
    private boolean replaceObjKeyStore(T objKey, E oldTag, E updatedTag) {

        if (!deleteObjKeyStore(objKey, oldTag)) return false;
        insertObjKeyStore(objKey, updatedTag);
        return true;

    }


    /**
     * Delete an object and all instance of it from tags
     */
    public boolean deleteTaggedObject(T delObj) {
        if (! objKeyStore.containsKey(delObj)) {
            return false;
        }

        List<E> tagList = objKeyStore.get(delObj);

        //remove objKey from objHashTable
        objKeyStore.remove(delObj);

        //remove obj from tagHashTable
        boolean result = tagList.stream()
            .map(tag ->deleteTagKeyStore(tag, delObj))
            .allMatch(x->x);

        return result; //if this is false, SHIIIIIT
    }


    /**
     * Delete an tag and all instance of it from objs 
     */
    public boolean deleteTag(E delTag) {
        if (! tagKeyStore.containsKey(delTag)) {
            return false;
        }

        List<T> objList = tagKeyStore.get(delTag);

        //remove objKey from objHashTable
        tagKeyStore.remove(delTag);

        //remove obj from tagHashTable
        boolean result = objList.stream()
            .map(x->deleteObjKeyStore(x, delTag))
            .allMatch(x->x);

        return result; //if this is false, SHIIIIIT
    }


    /* delete object so need to delete both tables all inst */

    /**
     * Delete a remove a tag-obj relationship from all stores
     */
    public boolean deleteTagFromObject(E str, T obj) {
        if (! deleteTagKeyStore(str, obj)) return false;
        if (! deleteObjKeyStore(obj, str)) return false;
        return true;
    }


    /**
     * Goto ObjkeyStore, and remove a tag given the object
     *  INTERNAL METHOD ONLY.
     */
    private boolean deleteObjKeyStore(T objKey, E strVal) {
        if (! objKeyStore.containsKey(objKey)) {
            return false;
        }

        List<E> xs = objKeyStore.get(objKey);
        if (xs.contains(strVal)) {
            int indexToRemove = xs.indexOf(strVal);
            assert xs.size() > indexToRemove 
                : "OwO objKeyStore index not in bound OwO";
            xs.remove(indexToRemove);
            return true;
        } else {
            return false;
        }
    }

    private boolean deleteObjKeyStore(T objKey, int index) {
        if (! objKeyStore.containsKey(objKey)) {
            return false;
        }

        List<E> xs = objKeyStore.get(objKey);
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
    private boolean deleteTagKeyStore(E strKey, T objVal) {
        if (! tagKeyStore.containsKey(strKey)) {
            // tag not found in store
            return false;
        }

        List<T> xs = tagKeyStore.get(strKey);
        if (xs.contains(objVal)) {
            int indexToRemove = xs.indexOf(objVal);
            assert xs.size() > indexToRemove 
                : "OwO tagKeyStore index not in bound OwO";
            xs.remove(indexToRemove);
            return true;
        } else {
            // object is not tagged to the given strKey tag
            return false;
        }
    }
    private boolean deleteTagKeyStore(E strKey, int index) {
        if (! tagKeyStore.containsKey(strKey)) {
            return false;
        }

        List<T> xs = tagKeyStore.get(strKey);
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
    public void insertTag(E strTag, T obj) {
        insertTagKeyStore(strTag, obj);
        insertObjKeyStore(obj, strTag);
    }

    /**
     * Insert a n object based on tag into a tag->obj store.
     */
    private void insertTagKeyStore(E strKey, T objVal) {
        if (! tagKeyStore.containsKey(strKey)) {
            initTagKeyStore(strKey);
        }

        List<T> xs = tagKeyStore.get(strKey);
        if (xs.contains(objVal)) {
            return;
        } else {
            xs.add(objVal);
        }
    }

    /**
     * Insert an object based on the tag it has, obj->tag store.
     */
    private void insertObjKeyStore(T objKey, E strVal) {
        if (! objKeyStore.containsKey(objKey)) {
            initObjKeyStore(objKey);
        }

        List<E> xs = objKeyStore.get(objKey);
        if (xs.contains(strVal)) {
            //duplicate
            return;
        } else {
            xs.add(strVal);
        }
    }

    /**
     * Initializes List in Hashmap before it can be used.
     */
    private void initTagKeyStore(E tagStr) {
        List<T> objList = new ArrayList<>();
        tagKeyStore.put(tagStr, objList);
    }

    /**
     * Initializes List in Hashmap before it can be used.
     */
    private void initObjKeyStore(T objKey) {
        List<E> tagList = new ArrayList<>();
        objKeyStore.put(objKey, tagList);
    }

}
