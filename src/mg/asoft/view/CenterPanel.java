package mg.asoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author Andry
 */
public class CenterPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;

    public CenterPanel() {
        initialiseTable();
        JPanel p = new JPanel(new BorderLayout());
        p.add(scrollPane, BorderLayout.CENTER);
        this.add(p);
    }

    private void initialiseTable() {
        table = new JTable();
        table.setRowHeight(40);
        scrollPane = new JScrollPane(table);
        scrollPane.setAutoscrolls(true);

    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
