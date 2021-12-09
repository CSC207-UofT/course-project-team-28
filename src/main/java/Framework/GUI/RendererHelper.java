package Framework.GUI;

import javax.swing.*;
import java.awt.*;

public class RendererHelper extends DefaultListCellRenderer {
        public static final String HTML_1 = "<html><body style='width: ";
        public static final String HTML_2 = "px'>";
        public static final String HTML_3 = "</html>";
        private final int width;

        public RendererHelper(int width) {
            this.width = width;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            String text = HTML_1 + width + HTML_2 + value.toString()
                    + HTML_3;
            return super.getListCellRendererComponent(list, text, index, isSelected,
                    cellHasFocus);
        }

}
