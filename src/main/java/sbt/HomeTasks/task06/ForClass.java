package sbt.HomeTasks.task06;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ForClass {
    int versionOfClass();
}
