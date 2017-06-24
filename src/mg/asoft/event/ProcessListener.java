package mg.asoft.event;

import java.nio.file.Path;

/**
 *
 * @author Andry
 */
public interface ProcessListener {
    public void processState(Boolean alive, Path p);
}
