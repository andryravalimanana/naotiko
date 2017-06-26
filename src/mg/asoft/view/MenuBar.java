package mg.asoft.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Andry
 */
public class MenuBar extends JMenuBar {

    // les menus

    private JMenu fileMenu;
    private JMenu EditMenu;
    private JMenu ViewMenu;
    private JMenu ToolsMenu;
    private JMenu aboutMenu;
    private JMenu helpMenu;
    // Items pour les menus
    // Menu file
    private JMenuItem newMenuItem;
    private JMenuItem importMenuItem;
    private JMenuItem exportMenuItem;
    private JMenuItem clearDatabaseItem;
    private JMenuItem exitMenuItem;
    // meny edit
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem editNoteMenuItem;
    // menu view
    private JMenuItem statisticMenuItem;
    private JMenuItem ConfigMenuItem;
    // menu about
    private JMenuItem developpersMenuItem;
    private JMenuItem naotikoMenuItem;

    public MenuBar() {
        initialiseComponent();
        // add item to file menu
        fileMenu.add(newMenuItem);
        fileMenu.add(importMenuItem);
        fileMenu.add(exportMenuItem);
        fileMenu.add(clearDatabaseItem);
        fileMenu.add(exitMenuItem);
        // add item to edit menu
        EditMenu.add(copyMenuItem);
        EditMenu.add(cutMenuItem);
        EditMenu.add(pasteMenuItem);
        EditMenu.add(editNoteMenuItem);
        // add item to view menu
        ViewMenu.add(statisticMenuItem);
        ViewMenu.add(ConfigMenuItem);
        // add item to about menu
        aboutMenu.add(developpersMenuItem);
        aboutMenu.add(naotikoMenuItem);
        // add menu to the menu bar
        this.add(fileMenu);
        this.add(EditMenu);
        this.add(ViewMenu);
        this.add(aboutMenu);
        this.add(helpMenu);

        // raccourcie claviers
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        importMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        clearDatabaseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        pasteMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK)));
        editNoteMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)));
        statisticMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK)));
        ConfigMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK)));
        developpersMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK)));
        naotikoMenuItem.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK)));
        helpMenu.setMnemonic(KeyEvent.VK_F1);
    }

    private void initialiseComponent() {
        // initialise menu
        fileMenu = new JMenu("Failina");
        EditMenu = new JMenu("Hanova");
        ViewMenu = new JMenu("Hijery");
        ToolsMenu = new JMenu("Fitaovana");
        aboutMenu = new JMenu("Mombamomba");
        helpMenu = new JMenu("Fanampiana");
        // initialise file items
        newMenuItem = new JMenuItem("Vaovao", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/new.png")));
        importMenuItem = new JMenuItem("Hanafatra", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/import.png")));
        exportMenuItem = new JMenuItem("Hanondrana", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/export.png")));
        clearDatabaseItem = new JMenuItem("Hanadio", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/clear.png")));
        exitMenuItem = new JMenuItem("Hiala", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/exit.png")));
        // initialise edit items
        copyMenuItem = new JMenuItem("Kaopia", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/copy.png")));
        cutMenuItem = new JMenuItem("Hafindra", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/cut.png")));
        pasteMenuItem = new JMenuItem("Hametaka", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/paste.png")));
        editNoteMenuItem = new JMenuItem("Hanova Naoty", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/edit.png")));
        //initialise view items
        statisticMenuItem = new JMenuItem("Statisitika", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/stat.png")));
        ConfigMenuItem = new JMenuItem("Kaonfigy", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/config.png")));
        // initialise about items
        developpersMenuItem = new JMenuItem("Nikaody", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/developper.png")));
        naotikoMenuItem = new JMenuItem("Naotiko", new ImageIcon(MenuBar.class.getResource("/mg/asoft/img/logo.png")));
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(JMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public JMenu getEditMenu() {
        return EditMenu;
    }

    public void setEditMenu(JMenu EditMenu) {
        this.EditMenu = EditMenu;
    }

    public JMenu getViewMenu() {
        return ViewMenu;
    }

    public void setViewMenu(JMenu ViewMenu) {
        this.ViewMenu = ViewMenu;
    }

    public JMenu getToolsMenu() {
        return ToolsMenu;
    }

    public void setToolsMenu(JMenu ToolsMenu) {
        this.ToolsMenu = ToolsMenu;
    }

    public JMenu getAboutMenu() {
        return aboutMenu;
    }

    public void setAboutMenu(JMenu aboutMenu) {
        this.aboutMenu = aboutMenu;
    }

    public JMenuItem getNewMenuItem() {
        return newMenuItem;
    }

    public void setNewMenuItem(JMenuItem newMenuItem) {
        this.newMenuItem = newMenuItem;
    }

    public JMenuItem getExportMenuItem() {
        return exportMenuItem;
    }

    public void setExportMenuItem(JMenuItem exportMenuItem) {
        this.exportMenuItem = exportMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public void setExitMenuItem(JMenuItem exiMenuItem) {
        this.exitMenuItem = exiMenuItem;
    }

    public JMenuItem getImportMenuItem() {
        return importMenuItem;
    }

    public void setImportMenuItem(JMenuItem importMenuItem) {
        this.importMenuItem = importMenuItem;
    }

    public JMenuItem getCopyMenuItem() {
        return copyMenuItem;
    }

    public void setCopyMenuItem(JMenuItem copyMenuItem) {
        this.copyMenuItem = copyMenuItem;
    }

    public JMenuItem getCutMenuItem() {
        return cutMenuItem;
    }

    public void setCutMenuItem(JMenuItem cutMenuItem) {
        this.cutMenuItem = cutMenuItem;
    }

    public JMenuItem getPasteMenuItem() {
        return pasteMenuItem;
    }

    public void setPasteMenuItem(JMenuItem pasteMenuItem) {
        this.pasteMenuItem = pasteMenuItem;
    }

    public JMenuItem getEditNoteMenuItem() {
        return editNoteMenuItem;
    }

    public void setEditNoteMenuItem(JMenuItem editNoteMenuItem) {
        this.editNoteMenuItem = editNoteMenuItem;
    }

    public JMenuItem getStatisticMenuItem() {
        return statisticMenuItem;
    }

    public void setStatisticMenuItem(JMenuItem statisticMenuItem) {
        this.statisticMenuItem = statisticMenuItem;
    }

    public JMenuItem getConfigMenuItem() {
        return ConfigMenuItem;
    }

    public void setConfigMenuItem(JMenuItem ConfigMenuItem) {
        this.ConfigMenuItem = ConfigMenuItem;
    }

    public JMenuItem getDeveloppersMenuItem() {
        return developpersMenuItem;
    }

    public void setDeveloppersMenuItem(JMenuItem developpersMenuItem) {
        this.developpersMenuItem = developpersMenuItem;
    }

    public JMenuItem getNaotikoMenuItem() {
        return naotikoMenuItem;
    }

    public void setNaotikoMenuItem(JMenuItem naotikoMenuItem) {
        this.naotikoMenuItem = naotikoMenuItem;
    }

    public JMenuItem getClearDatabaseItem() {
        return clearDatabaseItem;
    }

    public void setClearDatabaseItem(JMenuItem clearDatabaseItem) {
        this.clearDatabaseItem = clearDatabaseItem;
    }

}
