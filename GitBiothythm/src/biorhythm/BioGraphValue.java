package biorhythm;

import javafx.scene.paint.Color;
public class BioGraphValue {
    final private String name;
    final private int cycle;
    final private Color color;
    private boolean check;
    
    public BioGraphValue(String name, int cycle, Color color, boolean check) {
        this.name = name;
        this.cycle = cycle;
        this.color = color;
        this.check = check;
    }
    
    public void setCheck(boolean check) {
        this.check = check;
    }
    
    public boolean getCheck() { return this.check; }
    
    public String getName() { return this.name; }
    
    public int getCycle() { return this.cycle; }
    
    public Color getColor() { return this.color; }
}
