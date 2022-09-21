package frame;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class WriteFrame extends JFrame {
    public WriteFrame() throws HeadlessException {
        this.setTitle("글쓰기");
        this.setSize(350, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
