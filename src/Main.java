
/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.0.5
 * @since 2023-04-10
 */

import java.awt.EventQueue;
import schedule.builder.gui.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MainGui().setVisible(true);
        });
    }
}