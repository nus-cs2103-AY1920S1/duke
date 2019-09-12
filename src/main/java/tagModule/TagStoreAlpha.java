public class TagStoreAlpha extends AbstractTagStore<TaskInterface>
    implements AddTagObserver, QueryTagObserver, 
    QueryTaskObserver, DeletePairObserver, DeleteTaskObserver,
    DeleteTagObserver {

    /* before use, please register with TagCommandPost */
    //UItaskfeedback, UItagfeedback, UIpairfeedback, UIquery

    /* NOTE: AbstractTagStore doesnt support all ops yet */
    //private TaskModelInterface tasklist;

    public TagStoreAlpha() { //TaskModelInterface tasklist) {
        super();
        //this.tasklist = tasklist;
    }

    public String tagCode(TaskInterface task) {
        return task.getSaveFormat();
    }

    public void addTagUpdate(String tag, TaskInterface task) {
        super.store.addTaggedItem(tag, task);
    }

    public void deleteTagUpdate(String tag) {
        super.store.deleteTag(tag)
    }            

    public void deleteTaskUpdate(TaskInterface task) {
        super.store.deleteItem(task);
    }

    public void queryTagUpdate(String tag) {
    }

    public void queryTaskUpdate(String tag) {
    }

    public void deletePairUpdate(String tag, TaskInterface task) {
    }
}
