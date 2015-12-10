package com.wispy.taxhelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import static com.wispy.taxhelper.Layouts.layout;

/**
 * @author Leonid_Poliakov
 */
public class Launcher {
    public static void main(String[] args) {
        new Launcher().start();
    }

    private static final DecimalFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    private Clipboard clipboard;
    private JFrame frame;
    private JTextField input;
    private JLabel output;

    private void start() {
        initClipboard();
        initFrame();
        initLayout();
        initLogic();
        calculate();
        showFrame();
    }

    private void initClipboard() {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    private void initFrame() {
        frame = new JFrame("Tax helper");
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200, 150);
    }

    private void initLayout() {
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pane.setLayout(new GridBagLayout());
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.add(pane, layout().grid(0, 0).fillAll().pad(5).build());

        input = new JTextField();
        input.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pane.add(input, layout().grid(0, 0).pad(10).padBottom(0).fillHorizontal().anchorBottom().build());

        output = new JLabel();
        output.setHorizontalAlignment(JLabel.CENTER);
        output.setFont(new Font("Tahoma", Font.PLAIN, 24));
        pane.add(output, layout().grid(0, 2).pad(10).fillHorizontal().anchorTop().build());
    }

    private void initLogic() {
        input.getDocument().addDocumentListener(new TextListener());
        frame.addMouseListener(new MouseClickListener());
    }

    private void calculate() {
        String text = input.getText();
        if (text.isEmpty()) {
            output.setText("(empty)");
            output.setForeground(Color.GRAY);
            return;
        }

        text = text.replaceAll(",", ".");
        double value;
        try {
            value = format.parse(text).doubleValue();
        } catch (ParseException e) {
            output.setText("(bad format)");
            output.setForeground(new Color(150, 0, 0));
            return;
        }

        double result = value * 1.2;
        String formattedResult = format.format(result);
        output.setText(formattedResult);
        output.setForeground(new Color(0, 100, 200));

        StringSelection stringSelection = new StringSelection(formattedResult);
        clipboard.setContents(stringSelection, null);
    }

    private void selectInput() {
        input.selectAll();
    }

    private void showFrame() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class TextListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calculate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculate();
        }
    }

    private class MouseClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            selectInput();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}