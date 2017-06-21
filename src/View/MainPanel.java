package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
/**
 *
 * @author Andry
 */
public class MainPanel extends JPanel{
    private MenuBar menuBar;
    private ToolBar toolBar;
    private CenterPanel centerPanel;
    private JTextField searchTextField;
    private JTabbedPane tabbedPane;
    public static JLabel stateLabel = new JLabel("Naotiko.");
    
    public MainPanel() {
        initialiseComponent();
        JPanel northPanel = new JPanel(new GridLayout(3, 1));
        northPanel.add(menuBar);
        northPanel.add(toolBar);
        northPanel.add(searchTextField);
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        tabbedPane.addTab("Naotiko", centerPanel);
        // tabbedPane.addTab("Tasika", northPanel);
        this.add(tabbedPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.add(stateLabel);
        //add(southPanel, BorderLayout.SOUTH);
    }
    
    private void initialiseComponent(){
        menuBar = new MenuBar();
        toolBar = new ToolBar();
        centerPanel = new CenterPanel();
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tabbedPane = new JTabbedPane();
        searchTextField = new JTextField();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }
}
