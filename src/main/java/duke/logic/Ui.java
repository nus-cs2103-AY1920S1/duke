package duke.logic;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TaskWithOrder;
import duke.trivia.QuestionList;
import duke.trivia.TriviaQuestion;

/**
 * prints all the messages to console
 */
public class Ui {
    /**
     * returns duke logo
     */
    public static String welcome() {
        String logo = " ____        _        \n" 
                    + "|  _ \\ _   _| | _____ \n" 
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n" 
                    + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello, I'm Duke. " + "What can I do for you?";
    }

    /**
     * returns task added confirmation
     * 
     * @param list task list to get the added task
     */
    public String taskAdded(TaskList list) {
        return "Got it. I've added this task: \n" + list.get(list.list.size()) + "\nNow you have "
                + list.list.size() + " tasks in the list.";
    }

    /**
     * returns goodbye message
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * returns task set as done confirmation
     * 
     * @param list task list
     * @param item task index in list to be set as done
     */
    public String done(TaskList list, int item) {
        return "Nice! I've marked this task as done:\n" + list.get(item);
    }

    /**
     * returns out full task list
     * 
     * @param list
     */
    public String list(TaskList list) {
        if(list.list.size() == 0){
            return "oh bother! there aren't any tasks in the list!";
        }
        return list + "";
    }

    /**
     * returns delete task confirmation
     * 
     * @param t the task that was deleted.
     */
    public String delete(Task t) {
        return "Noted. I've removed this task:\n" + t;
    }

    /**
     * returns list of tasks
     * 
     * @param matchingTasks
     */
    public String found(ArrayList<TaskWithOrder> matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "oh, fiddlesticks! there are no tasks matching your search!";
        } else {
            String res = "Here are the matching tasks in your list:\n";
            for (TaskWithOrder t : matchingTasks){
                res+= t + "\n";
            }
            return res;
        }
    }

    public String askQuestion(QuestionList qList){
        return qList.getCurrentQuestion().question;
    }

    public String checkTriviaAnswer(QuestionList qList, String answer){
        if (qList.getCurrentQuestion().isCorrect(answer)){
            qList.advance();
            qList.isAsking = false;
            return "Oh, that's wonderful! You've gotten it right!";
        }
        else {
            return "I'm sorry, that's not quite right. Please try again, dear.\n(or say 'give up' to see the answer)";
        }
    }

	public String getAnswer(QuestionList qList) {
        String res = qList.getCurrentQuestion().answer;
        qList.advance();
        qList.isAsking = false;
        return res;
	}

	public String questionAdded(QuestionList qList) {
		return "cool! i've added this question to the list: \n" + qList.questions.get(qList.questions.size() -1);
    }
    
    public String showQuestions(QuestionList qList){
        return "here are all the questions you've stored:\n" + qList; 
    }

	public String questionDelete(TriviaQuestion t) {
        return "Noted. I've removed this question:\n" + t;
	}
}