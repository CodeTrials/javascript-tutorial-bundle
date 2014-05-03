package org.codetrials.examples.javascript;

import org.codetrials.bundle.Task;
import org.codetrials.bundle.engines.BundleEngine;
import org.codetrials.bundle.engines.JavaScriptEngine;
import org.codetrials.bundle.entities.TaskDescription;
import org.codetrials.bundle.helpers.SimpleBundleContainer;
import org.codetrials.bundle.helpers.tasks.FreeTask;
import org.codetrials.bundle.helpers.tasks.MultipleRegexpTask;
import org.codetrials.bundle.helpers.tasks.SandboxTask;
import org.codetrials.bundle.helpers.tasks.SingleRegexpTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Polyarnyi Nikolay
 */
@SuppressWarnings("unused")
public class JavaScriptBundle extends SimpleBundleContainer {

    private BundleEngine engine;

    public JavaScriptBundle() {
        super(new JavaScriptEngine());
    }

    private TaskDescription t(int id) throws IOException {
        return resourceLoader.loadTaskDescription(id);
    }

    @Override
    protected List<Task> createTasks() {
        try {
            Task task1 = new FreeTask(t(0), "next");
            Task task2 = new FreeTask(t(1), "next");
            Task task3 = new SingleRegexpTask(t(2), "-0|-0\\.0", "Try enter \"-0\"");
            Task task4 = new MultipleRegexpTask(t(3), "Try using arithmetic operations", "next",
                    ".*\\+.*", ".*\\*.*", ".*-.*", ".*/.*");
            Task task5 = new FreeTask(t(4), "next");
            Task task6 = new FreeTask(t(5), "finish");
            Task task7 = new SandboxTask(t(6));
            ArrayList<Task> list = new ArrayList<>();
            list.add(task1);
            list.add(task2);
            list.add(task3);
            list.add(task4);
            list.add(task5);
            list.add(task6);
            list.add(task7);
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

}
