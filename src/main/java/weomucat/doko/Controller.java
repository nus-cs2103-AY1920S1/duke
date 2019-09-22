package weomucat.doko;

import static weomucat.doko.Doko.THREAD_POLL_SLEEP_DURATION;

import java.util.LinkedList;
import weomucat.doko.command.Command;
import weomucat.doko.command.DisplayErrorCommand;
import weomucat.doko.command.listener.ByeCommandListener;
import weomucat.doko.command.listener.CommandListener;
import weomucat.doko.exception.DokoException;
import weomucat.doko.exception.DokoRuntimeException;
import weomucat.doko.parser.FullCommandParser;
import weomucat.doko.ui.listener.UserInputListener;
import weomucat.doko.ui.message.Message;

/**
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 * A Controller accepts user input and converts it to commands for the model or view.
 */
public class Controller implements ByeCommandListener, UserInputListener {

  private HeterogeneousContainers<CommandListener> commandListeners;

  private boolean running;
  private LinkedList<String> userInputQueue;
  private LinkedList<Command<?>> commandQueue;

  /**
   * Default constructor.
   */
  public Controller() {
    this.commandListeners = new HeterogeneousContainers<>();

    this.userInputQueue = new LinkedList<>();
    this.commandQueue = new LinkedList<>();

    // Add self to bye listener to stop running.
    this.commandListeners.add(ByeCommandListener.class, this);
  }

  /**
   * Adds a CommandListener.
   * When a Command is received, all listeners will be notified.
   *
   * @param c        class of CommandListener
   * @param listener CommandListener
   */
  <T extends CommandListener> void addListener(Class<T> c, T listener) {
    this.commandListeners.add(c, listener);
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;
  }

  @Override
  public void userInputUpdate(String userInput) {
    this.userInputQueue.addLast(userInput);
  }

  @Override
  public void commandUpdate(Command<?> command) {
    this.commandQueue.addLast(command);
  }

  /**
   * Block main thread to query for commands & user input.
   */
  public void run() {
    this.running = true;

    // Main loop to get user input.
    while (this.running) {
      try {
        if (!this.commandQueue.isEmpty()) {
          Command<?> command = this.commandQueue.removeFirst();

          // Finally, run the command.
          command.run(this.commandListeners);
        } else if (!this.userInputQueue.isEmpty()) {
          String userInput = this.userInputQueue.removeFirst();

          // Parse command
          Command<?> command = new FullCommandParser(userInput).parse();

          // Add command to back of queue.
          commandUpdate(command);
        } else {
          Thread.sleep(THREAD_POLL_SLEEP_DURATION);
        }
      } catch (DokoException | DokoRuntimeException e) {
        // Add error command to front of queue.
        this.commandQueue.addFirst(
            new DisplayErrorCommand(new Message().addBody("OOPS!!! " + e.getMessage())));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}