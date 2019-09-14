//package tagModule;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public abstract class AbstractTagStore<E> {
    // Left:= strTag, Right:=TagInterface
    //private GenericBiMap<String, TagInterface> store;
    private GenericBiMap<String, TagGenericWrapper> store;

    /**
     * Used only in this class, the purpose why an innerclass
     *  and not an outer class is so you can subclass the 
     *  outerclass and override the tagCode() ,ethod
     *  to allow for unique identification
     *  to not have to implement hashCode and equals
     *  in the tagged class
     */
    private class TagGenericWrapper implements TagInterface {
        private final E contains;

        public TagGenericWrapper(E contains) {
            this.contains = contains;
        }

        public E getContained() {
            return this.contains;
        }

        public int hashCode() {
            return tagCode().hashCode();
        }

        @Override
        public String toString() {
            return this.contains.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } 
            if (obj == null) {
                return false;
            } 
            if (this.tagCode()
                .equals(((TagGenericWrapper) obj)
                .tagCode())) {

                return true;
            }
            return false;
        }

        public String tagCode() {
            return AbstractTagStore.this.tagCode(contains);
        }
    }

    public AbstractTagStore() {
        this.store = new GenericBiMap<>();
    }

    //public void registerObservable(TagObservable o) {
    //    o.registerObserver(this);
    //}

    //public void update(TagObservable o) {

    //}

    /**
     * inserts a tag-item relation into storage
     */
    public void addTaggedItem(String tag, E item) {
        this.store
            .insertRelation(tag, new TagGenericWrapper(item)); 
    }


    public boolean deleteTag(String tag) {
        return this.store.deleteLeft(tag);
    }

    public boolean deleteItem(E item) {
        return this.store
            .deleteRight(new TagGenericWrapper(item)); 
    }

    public boolean deletePair(String tag, E item) {
        return this.store
            .deleteRelation(tag, new TagGenericWrapper(item)); 
    }
    
    public boolean updateItem(E oldItem, E updatedItem) {
        return this.store
            .updateRight(new TagGenericWrapper(oldItem), 
                    new TagGenericWrapper(updatedItem));
    }

    public boolean updateTag(String oldTag, String updatedTag) {
        return this.store
            .updateLeft(oldTag, updatedTag);
    }

    public Stream<E> queryByTag(String tag) {
        return this.store
            .queryByLeftKey(tag)
            .map(x -> x.getContained());
    }

    public Stream<String> queryByItem(E item) {
        return this.store
            .queryByRightKey(new TagGenericWrapper(item));
    }

    public Stream<String> queryAllTags() {
        return this.store.queryAll();
    }

    // note for this one, remember to convert from 
    // the wrapper class to the containedItem
    /* TODO more unit tests for everything besides add*/


    public boolean containsRelation(String tag, E item) {
        return this.store.containsRelation(tag, 
               new TagGenericWrapper(item));
    }

    abstract public String tagCode(E contains); 

}
