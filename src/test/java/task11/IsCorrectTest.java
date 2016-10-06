package test.java.task11;

import main.java.sbt.HomeTasks.task11.IPlugin;
import main.java.sbt.HomeTasks.task11.PluginLoader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by god on 9/21/2016.
 */
public class IsCorrectTest {
    @Test
    public void simpleCheck() {

        PluginLoader loader = new PluginLoader(IsCorrectTest.class.getClassLoader());

        IPlugin plugin = null;
        try {
            loader.setPath("C:\\Users\\god\\IdeaProjects\\Roman.Marinich\\src\\test\\java\\task11\\plugins\\");
            loader.loadPlugins();
            plugin = (IPlugin) loader.loadClass("plugin.test.Plugin")
                    .newInstance();
        } catch (Exception e) {
            fail();
        }

        ;
        assertEquals(plugin.run(), "execute method run()");
    }
}
