//package tagModule;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TagStoreAlpha extends AbstractTagStore<TaskInterface>
    implements AddTagObserver, QueryTagObserver, 
    QueryTaskObserver, DeletePairObserver, DeleteTaskObserver,
    DeleteTagObserver {

    /* before use, please register with TagCommandPost */
    // UI registers here for UItaskfeedback, UItagfeedback, UIpairfeedback, UIquery

    /* NOTE: AbstractTagStore doesnt support all ops yet */
    //private TaskModelInterface tasklist;
    private List<TaskFeedbackObserver> taskFeedbackObservers;
    private List<TagFeedbackObserver> tagFeedbackObservers;
    private List<PairFeedbackObserver> pairFeedbackObservers;
    private List<QueryFeedbackObserver> queryFeedbackObservers;

    public TagStoreAlpha() { //TaskModelInterface tasklist) {
        super();
        //this.tasklist = tasklist;
        taskFeedbackObservers = new ArrayList<>();
        tagFeedbackObservers = new ArrayList<>();
        pairFeedbackObservers = new ArrayList<>();
        queryFeedbackObservers = new ArrayList<>();
    }

    public String tagCode(TaskInterface task) {
        return task.getSaveFormat();
    }

    public void addTagUpdate(String tag, TaskInterface task) {
        super.addTaggedItem(tag, task);
        String header = "Alwight! Adding\n";
        String mid = " - ";
        String footer = "\n";
        this.notifyPairFeedbackObservers(header, tag, mid, task, footer);
    }

    public void deleteTagUpdate(String tag) {
        super.deleteTag(tag);
    }            

    public void deleteTaskUpdate(TaskInterface task) {
        super.deleteItem(task);
    }

    public void queryTagUpdate(String tag) {
    }

    public void queryTaskUpdate(TaskInterface task) {
    }

    public void deletePairUpdate(String tag, TaskInterface task) {
    }

    public void registerTaskFeedbackObserver(TaskFeedbackObserver o) {
        this.taskFeedbackObservers.add(o);
    }

    public void registerTagFeedbackObserver(TagFeedbackObserver o) {
        this.tagFeedbackObservers.add(o);
    }

    public void registerPairFeedbackObserver(PairFeedbackObserver o) {
        this.pairFeedbackObservers.add(o);
    }

    public void registerQueryFeedbackObserver(QueryFeedbackObserver o) {
        this.queryFeedbackObservers.add(o);
    }

    public void notifyPairFeedbackObservers(String header, String tag, String mid, TaskInterface task, String footer) {
        for (PairFeedbackObserver o : pairFeedbackObservers) {
            o.pairFeedbackUpdate(header, tag, mid, task, footer);
        }
    }



}
