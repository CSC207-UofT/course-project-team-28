package Framework.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * The helper function that make JList auto change rows after a pre-fixed line length is reached.
 */
public class RendererHelper extends DefaultListCellRenderer {
        public static final String HTML_1 = "<html><body style='width: ";
        public static final String HTML_2 = "px'>";
        public static final String HTML_3 = "</html>";
        private final int width;

    /**
     * The constructor of RendererHelper
     * @param width the with pre-fixed for Jlist
     */
        public RendererHelper(int width) {
            this.width = width;
        }

    /**
     * Return a component that has been configured to display the specified value.
     * @param list the Jlist we will be printing
     * @param value the value returned by list.gerModel().getElementAt(index)
     * @param index the cells index
     * @param isSelected True if specified cell is selected
     * @param cellHasFocus True if the specified cell has the focus
     * @return A component whose print() method will render the specified value
     */
        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            String text = HTML_1 + width + HTML_2 + value.toString()
                    + HTML_3;
            return super.getListCellRendererComponent(list, text, index, isSelected,
                    cellHasFocus);
        }

}
