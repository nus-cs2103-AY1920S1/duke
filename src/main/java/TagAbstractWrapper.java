//package tagModule;
//import tagModule.TagInterface;
/**
 * Wrap an object to store into Hashmap
 *  to store into a store of Tags
 *  provides convenient way to deal with hashCode().
 *
 */
public abstract class TagAbstractWrapper implements TagInterface {
    //private static int serial = 0;
    //private Task wrappedObject;
    //private int id;

    //public TagWrapper(Task wrappedObject) {
    //    this.wrappedObject = wrappedObject;
    //    //this.id = serial;
    //    //++serial;
    //}

    abstract String tagCode(); 

    @Override
    public int hashCode() {
        return tagCode().hashCode(); //this.wrappedObject.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } 
        if (obj == null) {
            return false;
        } 
        // anonymous classes probably arent the same class:w
        //
        //if (getClass() != obj.getClass()) {
        //    return false;
        //}
        if (this.tagCode()
            .equals(((TagAbstractWrapper) obj).tagCode())) {
            return true;
        }
        return false;
    }

}
