package org.codetrials.examples.javascript;

import org.codetrials.bundle.Task;
import org.codetrials.bundle.entities.ExecutionResult;
import org.codetrials.bundle.entities.TaskDescription;
import org.codetrials.bundle.entities.TaskReaction;
import org.codetrials.bundle.helpers.SimpleBundleContainer;
import org.codetrials.bundle.helpers.tasks.MultipleRegexpTask;
import org.codetrials.bundle.helpers.tasks.ResourceLoader;
import org.codetrials.bundle.helpers.tasks.SingleRegexpTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Polyarnyi Nikolay
 */
@SuppressWarnings("unused")
public class JavaScriptBundle extends SimpleBundleContainer {

    private JavaScriptEngine engine;

    public JavaScriptBundle() {
        engine = new JavaScriptEngine();
    }

    @Override
    protected List<Task> createTasks() {
        try {
            ResourceLoader loader = new ResourceLoader("task_", ".txt");
            Task task0 = new Task(new TaskDescription("Test slide", "Do some compilable shit")) {

                private boolean flag = false;

                @Override
                public boolean isCompleted() {
                    return flag;
                }

                @Override
                public boolean isCommandExecutable(String s) {
                    return true;
                }

                @Override
                public TaskReaction onCommandExecuted(String s, ExecutionResult executionResult) {
                    flag = executionResult.getException() == null;
                    return new TaskReaction("");
                }
            };
            Task task1 = new SingleRegexpTask(new TaskDescription("Summary", "Show us your skills, " +
                    "in summing two numbers"), ".*\\\\+.*");
            Task task2 = new MultipleRegexpTask(loader.loadTaskDescription(2), null, "next", "\".*\"");
            ArrayList<Task> list = new ArrayList<Task>();
            list.add(task0);
            list.add(task1);
            list.add(task2);
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<Task>();
        }
    }

    @Override
    public String getBundleName() {
        return "JavaScript";
    }

    @Override
    public String getBundleDescription() {
        return "Javascript beginner tutorial.\n" +
                "From simple variables and math to some interesting things!";
    }

    @Override
    protected ExecutionResult executeCommand(String s) {
        return engine.exec(s);
    }

    public static void main(String[] args) {
        JavaScriptBundle jsb = new JavaScriptBundle();

    }
}
