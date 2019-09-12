

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TagCommandPostAlpha implements TagObserver {
    //implement UIObserver 
    /* before use: 
    * 1. new TaskList
    * 2. new TagCommandPostAlpha(TaskList)
    * 3. new TagStoreAlpha
    * 4. register TagStoreAlpha with TagCommandPostAlpha
    * 5. register TagCommandPostAlpha with Controller
    * 6. register UI with TagStoreAlpha
    * make store, which technically can just be taskID & string
    */
    //private AbstractTagStore<TaskInterface> store;
    private TaskModelInterface taskList;
    private List<AddTagObserver> addTagObservers; // ??
    private List<DeleteTagObserver> deleteTagObservers;
    private List<DeleteTaskObserver> deleteTaskObservers;
    private List<QueryTagObserver> queryTagObservers;
    private List<QueryTaskObserver> queryTaskObservers;
    private List<DeletePairObserver> deletePairObservers;

    public TagCommandPostAlpha(TaskModelInterface taskList)  {//AbstractTagStore<TaskInterface> store) {
        //this.store = store;
        this.taskList = taskList;
        //this.store = new AbstractTagStore<TaskInterface>() {
        //    public String tagCode(TaskInterface task) {
        //        return task.getSaveFormat();
        //    }
        //}
        this.addTagObservers = new ArrayList<>();
        this.deleteTagObservers = new ArrayList<>();
        this.deleteTaskObservers = new ArrayList<>();
        this.queryTagObservers = new ArrayList<>();
        this.queryTaskObservers = new ArrayList<>();
        this.deletePairObservers = new ArrayList<>();
    }

    public void registerAddTagObserver(AddTagObserver o) {
        this.addTagObservers.add(o); 
    }

    public void registerDeleteTagObserver(DeleteTagObserver o) {
        this.deleteTagObservers.add(o); 
    }

    public void registerDeleteTaskObserver(DeleteTaskObserver o) {
        this.deleteTaskObservers.add(o); 
    }

    public void registerQueryTagObserver(QueryTagObserver o) {
        this.queryTagObservers.add(o); 
    }

    public void registerQueryTaskObserver(QueryTaskObserver o) {
        this.queryTaskObservers.add(o); 
    }

    public void registerDeletePairObserver(DeletePairObserver o) {
        this.deletePairObservers.add(o); 
    }

    public void notifyAddTag(String tag, int taskId) {
        TaskInterface task = this.tasklist.getTask(taskId);
        for (AddTagObserver obs : addTagObservers) {
            obs.addTagUpdate(tag, task);
        }
    }
    
    //public void notifyAddTag(String tag, TaskInterface task) {
    //    for (AddTagObserver obs : addTagObservers) {
    //        obs.addTagUpdate(tag, task);
    //    }
    //}

    public void notifyDeleteTag(String tag) {
        for (DeleteTagObserver obs : deleteTagObservers) {
            obs.deleteTagUpdate(tag);
        }
    }
    
    public void notifyDeleteTask(int taskId) {
        TaskInterface task = this.tasklist.getTask(taskId);
        for (DeleteTaskObserver obs : deleteTaskObservers) {
            obs.deleteTaskUpdate(task);
        }
    }
    //public void notifyDeleteTask(TaskInterface task) {
    //    for (DeleteTaskObserver obs : deleteTaskObservers) {
    //        obs.deleteTaskUpdate(task);
    //    }
    //}

    public void notifyQueryTag(String tag) {
        for (QueryTagObserver obs : queryTagObservers) {
            obs.queryTagUpdate(tag);
        }
    }

    public void notifyQueryTask(int taskId) {
        TaskInterface task = this.tasklist.getTask(taskId);
        for (QueryTaskObserver obs : queryTaskObservers) {
            obs.queryTaskUpdate(task);
        }
    }

    //public void notifyQueryTask(TaskInterface task) {
    //    for (QueryTaskObserver obs : queryTaskObservers) {
    //        obs.queryTaskUpdate(task);
    //    }
    //}

    public void notifyDeletePair(String tag, int taskId) {
        TaskInterface task = this.tasklist.getTask(taskId);
        for (DeletePairObserver obs : DeletePairObservers) {
            obs.deletePairUpdate(tag, task);
        }
    }
    //public void notifyDeletePair(String tag, TaskInterface task) {
    //    for (DeletePairObserver obs : DeletePairObservers) {
    //        obs.deletePairUpdate(tag, task);
    //    }
    //}

    public void tagCommandUpdate(String str) {
        //parse logic on which of the notifies to call
    }

}
