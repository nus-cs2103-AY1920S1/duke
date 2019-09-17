package com.commands;

import com.TaskList;
import com.util.StaticStrings;
import com.util.Storage;
import com.util.ui.*;
import gui.GUIUi;
import com.util.stats.DukeStatistics;

public class StatsCommand extends Command {

    private DukeStatistics dukeStats;
    public StatsCommand() {
        super("stats");
        dukeStats = new DukeStatistics();
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) {
        execute(ui);
    }

    private void execute(Ui ui) {
        ui.showMessage(StaticStrings.STATS_NUM_DONE + dukeStats.getNumTasksDone(),
                StaticStrings.STATS_LIST_DONE,
                    dukeStats.getListTasksDone().toString(),
                StaticStrings.STATS_NUM_DONE_PASTWEEK + dukeStats.getNumTasksDonePastWeek(),
                StaticStrings.STATS_NUM_DONE_TODO + dukeStats.getNumToDoDone(),
                StaticStrings.STATS_NUM_DONE_DEADLINE + dukeStats.getNumDeadlineDone(),
                StaticStrings.STATS_NUM_DONE_EVENT + dukeStats.getNumEventDone(),
                "",
                StaticStrings.STATS_NUM_DELETED + dukeStats.getNumTasksDeleted(),
                StaticStrings.STATS_LIST_TASKS_DELETED,
                    dukeStats.getListTasksDeleted().toString(),
                StaticStrings.STATS_NUM_UNDONE_TASKS_DELETED + dukeStats.getNumUndoneTasksDeleted(),
                "",
                StaticStrings.STATS_SEARCHED_KEYWORDS + dukeStats.getSearchedKeywords(),
                StaticStrings.STATS_SEARCHED_KEYWORDS_PASTWEKK + dukeStats.getSearchedKeywordsPastWeek()
                );
    }

}
