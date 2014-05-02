package org.codetrials.examples.javascript;

import org.codetrials.bundle.Task;
import org.codetrials.bundle.engines.BundleEngine;
import org.codetrials.bundle.engines.JavaScriptEngine;
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
import java.util.regex.Pattern;

/**
 * @author Polyarnyi Nikolay
 */
@SuppressWarnings("unused")
public class JavaScriptBundle extends SimpleBundleContainer {

    private BundleEngine engine;

    public JavaScriptBundle() {
        super(new JavaScriptEngine());
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
                    flag = executionResult != null && executionResult.getException() == null;
                    return new TaskReaction("");
                }
            };
            Task task1 = new SingleRegexpTask(new TaskDescription("Summary", "Show us your skills, " +
                    "in summing two numbers"), ".*\\+.*");
            Task task2 = new MultipleRegexpTask(loader.loadTaskDescription(2), "write string, mafaka",
                    "next", "\\s*\".*\"\\s*");
            ArrayList<Task> list = new ArrayList<>();
            list.add(task0);
            list.add(task1);
            list.add(task2);
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
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

    public static void main(String[] args) {
        JavaScriptBundle jsb = new JavaScriptBundle();
        Pattern p = Pattern.compile("\\s*\".*\"\\s*");
        System.out.println(p.matcher(" \" abdf \"   ").matches());
    }
}
