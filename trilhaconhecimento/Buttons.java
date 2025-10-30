
package trilhaconhecimento;

import java.awt.event.ActionListener;

public class Buttons {

    int x1, y1, x2, y2, id;
    ActionListener action;
    String urlMaterial;

    public Buttons(int x, int y, int l, int a, int id, ActionListener action, String url) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = l;
        this.y2 = a;
        this.id = id;
        this.action = action;
        this.urlMaterial = url;
    }
}
