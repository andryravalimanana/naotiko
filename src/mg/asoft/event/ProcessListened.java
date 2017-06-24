package mg.asoft.event;

/**
 *
 * @author Andry
 */
public interface ProcessListened {
    public void addProcessData(EditorFileProcess editorFileProcess);
    public void deleteProcessListener();
    public void updateProcessListener();
}
