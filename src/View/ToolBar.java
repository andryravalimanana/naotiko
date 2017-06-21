package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Andry
 */
public class ToolBar extends JToolBar {

    private JButton newButton;
    private JButton importButton;
    private JButton editNoteButton;
    private JButton statisticButton;
    private JButton configButton;
    private JButton aboutButton;

    public ToolBar() {
        initialiseComponent();
        add(newButton);
        add(importButton);
        add(editNoteButton);
        add(statisticButton);
        add(configButton);
        add(aboutButton);
    }

    private void initialiseComponent() {
        newButton = new JButton(new ImageIcon(ToolBar.class.getResource("/img/new.png")));
        importButton = new JButton(new ImageIcon(ToolBar.class.getResource("/img/import.png")));
        editNoteButton = new JButton(new ImageIcon(ToolBar.class.getResource("/img/edit.png")));
        statisticButton = new JButton(new ImageIcon(ToolBar.class.getResource("/img/stat.png")));
        configButton = new JButton(new ImageIcon(ToolBar.class.getResource("/img/config.png")));
        aboutButton = new JButton(new ImageIcon(ToolBar.class.getResource("/img/author.png")));
    }

    public JButton getNewButton() {
        return newButton;
    }

    public void setNewButton(JButton newButton) {
        this.newButton = newButton;
    }

    public JButton getImportButton() {
        return importButton;
    }

    public void setImportButton(JButton importButton) {
        this.importButton = importButton;
    }

    public JButton getEditNoteButton() {
        return editNoteButton;
    }

    public void setEditNoteButton(JButton editNoteButton) {
        this.editNoteButton = editNoteButton;
    }

    public JButton getStatisticButton() {
        return statisticButton;
    }

    public void setStatisticButton(JButton statisticButton) {
        this.statisticButton = statisticButton;
    }

    public JButton getConfigButton() {
        return configButton;
    }

    public void setConfigButton(JButton configButton) {
        this.configButton = configButton;
    }

    public JButton getAboutButton() {
        return aboutButton;
    }

    public void setAboutButton(JButton aboutButton) {
        this.aboutButton = aboutButton;
    }
}
