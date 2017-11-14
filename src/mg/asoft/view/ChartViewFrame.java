package mg.asoft.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.chart.BarChart;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mg.asoft.database.NaotyDAO;
import mg.asoft.model.Naoty;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Andry
 */
public class ChartViewFrame extends JFrame {

    private JFreeChart chart;
    private NaotyDAO naotyDAO;

    public ChartViewFrame(NaotyDAO naotyDAO) {
        this.naotyDAO = naotyDAO;
        initComponent();
        JPanel panel = new JPanel(new BorderLayout());
        ChartPanel cPanel = new ChartPanel(chart);
        panel.add(cPanel, BorderLayout.CENTER);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Statistika isam-bolana");
        this.setIconImage(new ImageIcon(getClass().getResource("/mg/asoft/img/logo.png")).getImage());
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponent() {
        final Map<Integer, Integer> dayRepartition = new HashMap<Integer, Integer>();
        for (int i = 1; i < 32; i++) {
            dayRepartition.put(i, 0);
        }
        ArrayList<Naoty> listeNote = naotyDAO.findByKeyword("");
        for (Naoty naoty : listeNote) {
            int day = naoty.getDate().getDay();
            incrementNb(day, dayRepartition);
        }
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i < 8; i++) {
            dataset.addValue(dayRepartition.get(i), "Herinandro 1", new Integer(i));
        }
        for (int i = 8; i < 15; i++) {
            dataset.addValue(dayRepartition.get(i), "Herinandro 2", new Integer(i));
        }
        for (int i = 15; i < 22; i++) {
            dataset.addValue(dayRepartition.get(i), "Herinandro 3", new Integer(i));
        }
        for (int i = 22; i < 32; i++) {
            dataset.addValue(dayRepartition.get(i), "Herinandro 4", new Integer(i));
        }
        chart = ChartFactory.createBarChart("", "Andro", "Naoty",
                dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    private void incrementNb(int day, Map<Integer, Integer> repartition) {
        Integer nb = repartition.get(day);
        nb++;
        repartition.put(day, nb);
    }

}
