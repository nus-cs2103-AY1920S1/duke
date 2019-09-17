//package tagModule;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
//the idea of using a whole lot of Observers to wire
//several classes together and the new implementation
//of command is inspired by Weomucat (in 2103T Duke)
//this is a simplified version of it which is not as elegant

public class TagCommandPostAlpha implements TagCommandObserver, 
    UpdateTaskCommandObserver, DeleteTaskCommandObserver {
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
    private PrimaryStoreInterface<TaskInterface> tasklist;
    private List<AddTagObserver> addTagObservers; // ??
    private List<DeleteTagObserver> deleteTagObservers;
    private List<DeleteTaskObserver> deleteTaskObservers;
    private List<QueryTagObserver> queryTagObservers;
    private List<QueryTaskObserver> queryTaskObservers;
    private List<DeletePairObserver> deletePairObservers;
    private List<UpdateTagObserver> updateTagObservers;
    private List<UpdateTaskObserver> updateTaskObservers;
    private List<QueryAllTagsObserver> queryAllTagsObservers;

    public TagCommandPostAlpha(PrimaryStoreInterface<TaskInterface> tasklist)  {//AbstractTagStore<TaskInterface> store) {
        //this.store = store;
        this.tasklist = tasklist;
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
        this.updateTagObservers = new ArrayList<>();
        this.updateTaskObservers = new ArrayList<>();
        this.queryAllTagsObservers = new ArrayList<>();
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

    public void registerUpdateTagObserver(UpdateTagObserver o) {
        this.updateTagObservers.add(o); 
    }

    public void registerUpdateTaskObserver(UpdateTaskObserver o) {
        this.updateTaskObservers.add(o); 
    }

    public void registerDeletePairObserver(DeletePairObserver o) {
        this.deletePairObservers.add(o); 
    }

    public void registerQueryAllTagsObserver(
        QueryAllTagsObserver o) {
        this.queryAllTagsObservers.add(o); 
    }

    private void notifyAddTag(String tag, int taskId) {
        TaskInterface task = this.tasklist.getItem(taskId);
        for (AddTagObserver obs : addTagObservers) {
            obs.addTagUpdate(tag, task);
        }
    }
    
    //public void notifyAddTag(String tag, TaskInterface task) {
    //    for (AddTagObserver obs : addTagObservers) {
    //        obs.addTagUpdate(tag, task);
    //    }
    //}

    private void notifyDeleteTag(String tag) {
        for (DeleteTagObserver obs : deleteTagObservers) {
            obs.deleteTagUpdate(tag);
        }
    }
    
    private void notifyDeleteTask(int taskId) {
        TaskInterface task = this.tasklist.getItem(taskId);
        for (DeleteTaskObserver obs : deleteTaskObservers) {
            obs.deleteTaskUpdate(task);
        }
    }

    private void notifyDeleteTask(TaskInterface task) {
        for (DeleteTaskObserver obs : deleteTaskObservers) {
            obs.deleteTaskUpdate(task);
        }
    }

    private void notifyQueryTag(String tag) {
        for (QueryTagObserver obs : queryTagObservers) {
            obs.queryTagUpdate(tag);
        }
    }

    private void notifyQueryTask(int taskId) {
        TaskInterface task = this.tasklist.getItem(taskId);
        for (QueryTaskObserver obs : queryTaskObservers) {
            obs.queryTaskUpdate(task);
        }
    }

    private void notifyUpdateTag(String oldTag, 
        String updatedTag) {
        for (UpdateTagObserver obs : updateTagObservers) {
            obs.updateTagUpdate(oldTag, updatedTag);
        }
    }

    private void notifyUpdateTask(TaskInterface oldTask, 
        TaskInterface updatedTask) {
        for (UpdateTaskObserver obs : updateTaskObservers) {
            obs.updateTaskUpdate(oldTask, updatedTask);
        }
    }

    //public void notifyQueryTask(TaskInterface task) {
    //    for (QueryTaskObserver obs : queryTaskObservers) {
    //        obs.queryTaskUpdate(task);
    //    }
    //}

    private void notifyDeletePair(String tag, int taskId) {
        TaskInterface task = this.tasklist.getItem(taskId);
        for (DeletePairObserver obs : deletePairObservers) {
            obs.deletePairUpdate(tag, task);
        }
    }

    private void notifyQueryAllTags() {
        for (QueryAllTagsObserver obs : queryAllTagsObservers) {
            obs.queryAllTagsUpdate();
        }
    }
    //public void notifyDeletePair(String tag, TaskInterface task) {
    //    for (DeletePairObserver obs : DeletePairObservers) {
    //        obs.deletePairUpdate(tag, task);
    //    }
    //}

    public void updateTaskCommandUpdate(TaskInterface oldTask,
        TaskInterface updatedTask) {
        notifyUpdateTask(oldTask, updatedTask);
    }

    public void deleteTaskCommandUpdate(TaskInterface task) {
        notifyDeleteTask(task);
    }

    public void tagCommandUpdate(String command) {
        //parse logic on which of the notifies to call
        String[] cmds = command.trim().split(" ");
        //String subcmd = cmds[1];
        //String body = cmds[2];

        System.out.println("cmdread <<<<");
        //System.out.println("cmds0: " + cmds[0]);
        //System.out.println("cmds1: " + cmds[1]);
        //System.out.println("cmds2: " + cmds[2]);
        //System.out.println("cmds3: " + cmds[3]);


        assert cmds.length > 2 
            : "tag command is too showt";

        if (cmds[1].toUpperCase().equals("ADDTAG")) {
            System.out.println("ADDTAG <<<<");
            int taskId = Integer.valueOf(cmds[3]) - 1;
            notifyAddTag(cmds[2], taskId);
        } else if 
            (cmds[1].toUpperCase().equals("DELETETAG")) {
            notifyDeleteTag(cmds[2]);
        } else if 
            (cmds[1].toUpperCase().equals("DELETETASK")) {
            int taskId = Integer.valueOf(cmds[2]) - 1;
            notifyDeleteTask(taskId);
        } else if 
            (cmds[1].toUpperCase().equals("QUERYTAG")) {
            System.out.println("TPCPA.querytag <<<<");    
            System.out.println(cmds[2]);    
            notifyQueryTag(cmds[2]);
        } else if 
            (cmds[1].toUpperCase().equals("QUERYTASK")) {
            int taskId = Integer.valueOf(cmds[2]) - 1;
            notifyQueryTask(Integer.valueOf(taskId));
        } else if 
            (cmds[1].toUpperCase().equals("DELETEPAIR")) {
            int taskId = Integer.valueOf(cmds[3]) - 1;
            notifyDeletePair(cmds[2], taskId);
        } else if 
            (cmds[1].toUpperCase().equals("UPDATETAG")) {
            notifyUpdateTag(cmds[2], cmds[3]);
        } else if 
            (cmds[1].toUpperCase().equals("ALL")) {
            notifyQueryAllTags();
        } else {
            /* TODO */
            //deletetask, donetask
            //queryall tags, query tags and tasks
            //whatever display could be collected into a list
            //and then loaded into tasklist for user to interact
            //we then have to store the notags
            //rewire list to point here, and add and delete
        }

    }

}
