package com.tysng.duke.dto;

import com.tysng.duke.domain.Task;
import com.tysng.duke.util.Index;
import lombok.Builder;
import lombok.Data;

/**
 * This class parses raw user input text into a Command object that can be process by Duke.
 */
@Builder
@Data
public class ParsedCommand {
    private Index targetIndex;
    private Task addedTask;
    private String keyword;
}
