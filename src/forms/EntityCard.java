package forms;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class EntityCard extends JFrame {
    private JPanel rootPanel;
    private JPanel dataPanel;
    private JLabel loadingLabel;
    private JLabel titleLabel;
    private JButton backButton;
    private JPanel infoPanel;
    private JTable table1;
    private JLabel dataTitleLabel;
    private JPanel optionsPanel;

    public JPanel getOptionsPanel() {
        return optionsPanel;
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(JPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getDataTitleLabel() {
        return dataTitleLabel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JPanel getDataPanel() {
        return dataPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new FormLayout("fill:4dlu:noGrow,fill:50dlu:noGrow,fill:151dlu:grow,left:4dlu:noGrow,fill:60dlu:noGrow,fill:4dlu:noGrow", "center:4dlu:noGrow,top:52dlu:noGrow,center:80dlu:noGrow,center:4dlu:noGrow,top:d:noGrow,center:38px:noGrow,top:4dlu:noGrow,center:23px:noGrow,center:246px:grow,top:4dlu:noGrow,center:4dlu:noGrow"));
        rootPanel.setBackground(new Color(-1));
        rootPanel.setForeground(new Color(-1));
        dataPanel = new JPanel();
        dataPanel.setLayout(new BorderLayout(0, 0));
        dataPanel.setBackground(new Color(-1));
        dataPanel.setForeground(new Color(-1));
        CellConstraints cc = new CellConstraints();
        rootPanel.add(dataPanel, cc.xyw(2, 9, 4, CellConstraints.DEFAULT, CellConstraints.TOP));
        loadingLabel = new JLabel();
        loadingLabel.setAlignmentX(0.5f);
        loadingLabel.setBackground(new Color(-1));
        loadingLabel.setForeground(new Color(-1));
        loadingLabel.setHorizontalAlignment(0);
        loadingLabel.setHorizontalTextPosition(11);
        loadingLabel.setIcon(new ImageIcon(getClass().getResource("/loadgif.gif")));
        loadingLabel.setInheritsPopupMenu(false);
        loadingLabel.setText("");
        loadingLabel.setVisible(true);
        dataPanel.add(loadingLabel, BorderLayout.NORTH);
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$("Segoe UI Black", -1, 16, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16777216));
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setText("Entity");
        rootPanel.add(titleLabel, cc.xyw(3, 2, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
        backButton = new JButton();
        backButton.setBackground(new Color(-1));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setDefaultCapable(true);
        backButton.setEnabled(true);
        backButton.setFocusPainted(false);
        backButton.setFocusable(true);
        backButton.setForeground(new Color(-1));
        backButton.setIcon(new ImageIcon(getClass().getResource("/buttons/button_back.png")));
        backButton.setInheritsPopupMenu(false);
        backButton.setOpaque(false);
        backButton.setPressedIcon(new ImageIcon(getClass().getResource("/buttons/button_back_pressed.png")));
        backButton.setRequestFocusEnabled(true);
        backButton.setRolloverEnabled(true);
        backButton.setRolloverIcon(new ImageIcon(getClass().getResource("/buttons/button_back_selected.png")));
        backButton.setSelected(false);
        backButton.setSelectedIcon(new ImageIcon(getClass().getResource("/buttons/button_back_selected.png")));
        backButton.setText("");
        backButton.setVisible(true);
        rootPanel.add(backButton, cc.xy(2, 2, CellConstraints.CENTER, CellConstraints.CENTER));
        dataTitleLabel = new JLabel();
        Font dataTitleLabelFont = this.$$$getFont$$$("Segoe UI Black", -1, 16, dataTitleLabel.getFont());
        if (dataTitleLabelFont != null) dataTitleLabel.setFont(dataTitleLabelFont);
        dataTitleLabel.setForeground(new Color(-16777216));
        dataTitleLabel.setHorizontalAlignment(0);
        dataTitleLabel.setText("EntityData");
        rootPanel.add(dataTitleLabel, cc.xywh(2, 6, 4, 2, CellConstraints.DEFAULT, CellConstraints.FILL));
        final JLabel label1 = new JLabel();
        label1.setAutoscrolls(true);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setIcon(new ImageIcon(getClass().getResource("/unknownImage.png")));
        label1.setMinimumSize(new Dimension(-1, -1));
        label1.setPreferredSize(new Dimension(-1, -1));
        label1.setRequestFocusEnabled(false);
        label1.setText("");
        rootPanel.add(label1, cc.xy(5, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.add(infoPanel, cc.xyw(2, 3, 2, CellConstraints.FILL, CellConstraints.FILL));
        table1 = new JTable();
        table1.setBackground(new Color(-1));
        table1.setForeground(new Color(-16777216));
        table1.setGridColor(new Color(-986896));
        table1.setShowVerticalLines(false);
        infoPanel.add(table1, BorderLayout.CENTER);
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BorderLayout(0, 0));
        optionsPanel.setBackground(new Color(-1));
        rootPanel.add(optionsPanel, cc.xyw(2, 5, 4, CellConstraints.DEFAULT, CellConstraints.FILL));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
