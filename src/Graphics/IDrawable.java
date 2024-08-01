/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Interface IDrawable
 */
package Graphics;

import java.awt.*;

public interface IDrawable {
	 public final static String PICTURE_PATH = "src/Graphics/graphics2/";
	 public void loadImages(String nm);
	 public void drawObject (Graphics g);
}
