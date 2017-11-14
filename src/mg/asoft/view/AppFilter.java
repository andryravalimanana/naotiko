package mg.asoft.view;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class AppFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.EXE))
                    return true;
            } else {
                return false;
            }
        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "App filter";
    }
}
