package Event;

import java.nio.file.Path;

/**
 *
 * @author Andry
 */
public class EditorFileProcess {
    private Process process;
    private int id;

    public EditorFileProcess(int id, Process process) {
        this.process = process;
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
