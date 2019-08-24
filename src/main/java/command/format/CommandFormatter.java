package com.leeyiyuan.command.format;

import com.leeyiyuan.command.format.CommandParseException;
import com.leeyiyuan.command.Command;

public abstract class CommandFormatter {

    public abstract Command parse(String text) throws CommandParseException;

}
