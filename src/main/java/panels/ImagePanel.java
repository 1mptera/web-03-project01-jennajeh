package panels;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String image) {
        this(new ImageIcon(image).getImage());
    }

    public ImagePanel(Image image) {
        this.backgroundImage = image;
        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
