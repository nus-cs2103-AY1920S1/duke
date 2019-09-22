package duke.tag;

import duke.task.TaskList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Manages and keeps track of all the tags created.
 */
public class TagManager {
    private HashMap<Tag, Integer> tagList = new HashMap<Tag, Integer>();

    /**
     * Initializes tags in taglist.
     * @param tasks tasklist which tracks all tasks.
     */
    public void initTags(TaskList tasks) {
        tasks.getStream().map(t -> t.getTagSet()).forEach(ts -> putTags(ts));
    }

    /**
     * Enters tags tagged with a task into the taglist.
     * Also keeps track of the number of tasks tagged with the tag.
     * @param tagSet set of tag tagged with the task.
     */
    public void putTags(HashSet<Tag> tagSet) {
        Iterator<Tag> value = tagSet.iterator();
        while (value.hasNext()) {
            Tag currTag = value.next();
            addTag(currTag);
        }
    }

    /**
     * Adds a tag into the list.
     * @param tag tag to be added.
     */
    public void addTag(Tag tag) {
        boolean hasTag = tagList.containsKey(tag);
        if (hasTag) {
            int oldVal = tagList.get(tag);
            tagList.replace(tag, oldVal + 1);
        } else {
            tagList.put(tag, 1);
        }
    }

    /**
     * Decreases the number of task associated with the tags of deleted task.
     * Remove the tag from the taglist if count reaches 0.
     * @param tagSet set of tags tagged with the task.
     */
    public void decreaseCount(HashSet<Tag> tagSet) {
        Iterator<Tag> value = tagSet.iterator();
        while (value.hasNext()) {
            Tag currTag = value.next();
            rmTag(currTag);
        }
    }

    /**
     * Removes a tag from the list.
     * @param tag tag to be removed.
     */
    public void rmTag(Tag tag) {
        int count = tagList.get(tag) - 1;
        if (count <= 0) {
            tagList.remove(tag);
        } else {
            tagList.replace(tag, count);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Tag> value = tagList.keySet().iterator();
        while (value.hasNext()) {
            sb.append(value.next() + "\n");
        }
        return sb.toString();
    }
}
