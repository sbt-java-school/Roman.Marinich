package main.java.sbt.HomeTasks.Six;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by god on 8/30/2016.
 */

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ForClass {
    int versionOfClass();
}
