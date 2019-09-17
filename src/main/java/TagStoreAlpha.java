//package tagModule;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TagStoreAlpha extends AbstractTagStore<TaskInterface>
    implements AddTagObserver, QueryTagObserver, 
    QueryTaskObserver, DeletePairObserver, DeleteTaskObserver,
    DeleteTagObserver, UpdateTagObserver, UpdateTaskObserver,
    QueryAllTagsObserver {

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

    public void addTagUpdate(String tag, TaskInterface task) {
        super.addTaggedItem(tag, task);
        String msg = "added";
        this.notifyPairFeedbackObservers(tag, task, msg);
    }

    public void deleteTagUpdate(String tag) {
        boolean success = super.deleteTag(tag);
        String msg = "";
        if (success) {
            msg = "deleted successfully";
        } else {
            msg = "deleted unsuccessfully or cannot be found";
        }
        this.notifyTagFeedbackObservers(tag, msg);
    }            

    public void deleteTaskUpdate(TaskInterface task) {
        boolean success = super.deleteItem(task);
        String msg = "";
        if (success) {
            msg = "deleted successfully";
        } else {
            msg = "deleted unsuccessfully or cannot be found";
        }
        this.notifyTaskFeedbackObservers(task, msg);
    }

    public void updateTagUpdate(String oldTag, 
        String updatedTag) {
        boolean success = super.updateTag(oldTag, updatedTag);
        String msg = "";
        if (success) {
            msg = String.
                format("updated to %s successfully", updatedTag);
        } else {
            msg = "updated unsuccessfully or cannot be found";
        }
        this.notifyTagFeedbackObservers(oldTag, msg);
    }            


    public void updateTaskUpdate(TaskInterface oldTask, 
        TaskInterface updatedTask) {
        boolean success = super.updateItem(oldTask, updatedTask);
        String msg = "";
        if (success) {
            msg = String
                .format("updated successfully, now:%n%s", 
                updatedTask.toString());
        } else {
            msg = "updated unsuccessfully or cannot be found";
        }
        this.notifyTaskFeedbackObservers(oldTask, msg);
    }            

    public void queryTagUpdate(String tag) {
        System.out.println("TSA.queryTagUpdate<<<<");
        String msg = "tag";
        Stream<TaskInterface> stream = super.queryByTag(tag); 
        notifyQueryFeedbackObservers(tag, stream, msg); 
    }

    public void queryTaskUpdate(TaskInterface task) {
        String msg = "task";
        Stream<String> stream = super.queryByItem(task); 
        notifyQueryFeedbackObservers(task, stream, msg); 
    }

    public void queryAllTagsUpdate() {
        Stream<String> stream = super.queryAllTags();
        notifyQueryFeedbackObservers("all (view only)", 
            stream, "all tags");
        //String all = super.queryAllTags();
        //List<String> xs = new ArrayList<>();
        //xs.add(all);
        //notifyQueryFeedbackObservers("all", xs.stream(), "all");
    }

    public void deletePairUpdate(String tag, TaskInterface task) {
        String msg = "";
        boolean success = super.deletePair(tag, task);
        if (success) {
            msg = "deleted successfully";
        } else {
            msg = "deleted unsuccessfully or is not found";
        }
        notifyPairFeedbackObservers(tag, task, msg);
    }

    public void notifyPairFeedbackObservers(String tag, TaskInterface task, String msg) {
        for (PairFeedbackObserver o : pairFeedbackObservers) {
            //o.pairFeedbackUpdate(header, tag, mid, task, footer);
            o.pairFeedbackUpdate(tag, task, msg);
        }
    }

    public <T,E> void notifyQueryFeedbackObservers(T searchTerm, 
        Stream<E> stream, String msg) {
        for (QueryFeedbackObserver o : queryFeedbackObservers) {
            o.queryFeedbackUpdate(searchTerm, stream, msg);
        }
    }


    public void notifyTagFeedbackObservers(String tag, String msg) {
        for (TagFeedbackObserver o : tagFeedbackObservers) {
            //o.pairFeedbackUpdate(header, tag, mid, task, footer);
            o.tagFeedbackUpdate(tag, msg);
        }
    }

    public void notifyTaskFeedbackObservers(TaskInterface task, String msg) {
        for (TaskFeedbackObserver o : taskFeedbackObservers) {
            //o.pairFeedbackUpdate(header, tag, mid, task, footer);
            o.taskFeedbackUpdate(task, msg);
        }
    }
    /* TODO bulk appendTag */

}
