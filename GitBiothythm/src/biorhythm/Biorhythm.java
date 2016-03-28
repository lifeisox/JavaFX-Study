package biorhythm;

/*
 * Biorhythm (Author. Byungseon Kim, Date. 2 January 2016)
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Biorhythm extends Application {
    private LocalDate birth = LocalDate.of(1962, 8, 11);
    private LocalDate now = LocalDate.now();
    private DropShadow dropShadow;
    private BioGraphValue[] bioGraph = {
        new BioGraphValue("Intellectual", 33, Color.GREEN, true),
        new BioGraphValue("Emotional", 28, Color.RED, true),
        new BioGraphValue("Physical", 23, Color.BLUE, true),
        new BioGraphValue("intuition", 38, Color.BLUEVIOLET, false),
        new BioGraphValue("Spritual", 53, Color.DARKGREEN, false),
        new BioGraphValue("Awareness", 48, Color.DEEPSKYBLUE, false),
        new BioGraphValue("Aesthetic", 43, Color.FIREBRICK, false)
    };
    CheckBox[] checkBox = new CheckBox[bioGraph.length];
    private final double PI = 3.141592654;
    private GraphicsContext gc;
    DatePicker dateNow;
    
    @Override
    public void start(Stage stage) {
        dropShadow = new DropShadow(5, 5, 5, Color.rgb(150, 50, 50, .6));
        
        VBox vBox1 = dateEntry();
        VBox vBox2 = buttonEntry();
        VBox vBox3 = basicEntry();
        VBox vBox4 = optionEntry();
        
        HBox hBox = new HBox(0, vBox1, vBox2, vBox3, vBox4);
        hBox.setStyle("-fx-background-color: palegreen;");
        
        Canvas canvas = new Canvas(800, 400);
        gc = canvas.getGraphicsContext2D();
        
        VBox screen = new VBox(0, hBox, canvas);
        
        Scene scene = new Scene(screen);
        stage.setTitle("Biorhythm");
        stage.setScene(scene);
        stage.show();
        
        drawBiorhythm();
        
    }
    
    public static void main(String[] ar) {
        Application.launch(ar);
    }
    
    private VBox dateEntry() {
        Label label1 = new Label("Enter the day of Birth");
        label1.setFont(Font.font("arial", FontWeight.BOLD, 14));
        
        DatePicker dateBirth = new DatePicker(birth);
        dateBirth.setStyle("-fx-font-family: arial; -fx-font-size: 14");
        dateBirth.setMaxWidth(150);
        dateBirth.setEffect(dropShadow);
        dateBirth.setOnAction(event -> { birth = dateBirth.getValue(); });
        
        Label label2 = new Label("Biorhythm charts date");
        label2.setStyle("-fx-font-family: arial; -fx-font-weight: bold; -fx-font-size: 14");
        label2.setPadding(new Insets(10, 0, 0, 0));
        
        dateNow = new DatePicker(now);
        dateNow.setStyle("-fx-font-family: arial; -fx-font-size: 14");
        dateNow.setMaxWidth(150);
        dateNow.setEffect(dropShadow);
        dateNow.setOnAction(event -> { now = dateNow.getValue(); });
        VBox vBox = new VBox(5, label1, dateBirth, label2, dateNow);
        vBox.setPadding(new Insets(20, 25, 25, 20));
        vBox.setStyle("-fx-background-color: gold;");
        vBox.setAlignment(Pos.CENTER_LEFT);
        
        return vBox;
    }
    
    private VBox buttonEntry() {
        Button btnCalculate = new Button("Calculate");
        btnCalculate.setPrefWidth(150);
        btnCalculate.setEffect(dropShadow);
        btnCalculate.setOnAction(event -> { drawBiorhythm(); });
        
        Button btnMinimum = new Button("Minimum");
        btnMinimum.setPrefWidth(150);
        btnMinimum.setEffect(dropShadow);
        btnMinimum.setOnAction(event -> { minBiorhythm(); });
        
        Button btnMaximum = new Button("Maximum");
        btnMaximum.setPrefWidth(150);
        btnMaximum.setEffect(dropShadow);
        btnMaximum.setOnAction(event -> { maxBiorhythm(); });
        
        Button btnDanger = new Button("Danger");
        btnDanger.setPrefWidth(150);
        btnDanger.setEffect(dropShadow);
        btnDanger.setOnAction(event -> { dangerBiorhythm(); });
        
        VBox vbox = new VBox(10, btnCalculate, btnMinimum, btnMaximum, btnDanger);
        vbox.setPadding(new Insets(20, 25, 25, 20));
        vbox.setStyle("-fx-background-color: greenyellow;");
        vbox.setAlignment(Pos.CENTER);
        
        return vbox;
    }
    
    private VBox basicEntry() {
        for (int i=0; i < 3; i++) {
            checkBox[i] = new CheckBox(bioGraph[i].getName());
            checkBox[i].setAllowIndeterminate(false);
            checkBox[i].setFont(Font.font("arial", FontWeight.BOLD, 14));
            checkBox[i].setTextFill(bioGraph[i].getColor());
            checkBox[i].setEffect(dropShadow);
            checkBox[i].setSelected(true);
        }
        
        checkBox[0].setOnAction(event -> { bioGraph[0].setCheck(checkBox[0].isSelected()); });
        checkBox[1].setOnAction(event -> { bioGraph[1].setCheck(checkBox[1].isSelected()); });
        checkBox[2].setOnAction(event -> { bioGraph[2].setCheck(checkBox[2].isSelected()); });
        
        VBox vbox = new VBox(15, checkBox[0], checkBox[1], checkBox[2]);
        vbox.setPadding(new Insets(20, 25, 25, 20));
        vbox.setStyle("-fx-background-color: powderblue;");
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPrefWidth(150);
        
        return vbox;
    }
    
    private VBox optionEntry() {
        for (int i=3; i < checkBox.length; i++) {
            checkBox[i] = new CheckBox(bioGraph[i].getName());
            checkBox[i].setAllowIndeterminate(false);
            checkBox[i].setFont(Font.font("arial", FontWeight.BOLD, 14));
            checkBox[i].setTextFill(bioGraph[i].getColor());
            checkBox[i].setEffect(dropShadow);
        }
        
        checkBox[3].setOnAction(event -> { bioGraph[3].setCheck(checkBox[3].isSelected()); });
        checkBox[4].setOnAction(event -> { bioGraph[4].setCheck(checkBox[4].isSelected()); });
        checkBox[5].setOnAction(event -> { bioGraph[5].setCheck(checkBox[5].isSelected()); });
        checkBox[6].setOnAction(event -> { bioGraph[6].setCheck(checkBox[6].isSelected()); });
        
        VBox vbox = new VBox(15, checkBox[3], checkBox[4], checkBox[5], checkBox[6]);
        vbox.setPadding(new Insets(20, 25, 25, 20));
        vbox.setStyle("-fx-background-color: palegreen;");
        vbox.setAlignment(Pos.CENTER_LEFT);
        
        return vbox;
    }
    
    private void maxBiorhythm() {
        double max = 0;
        int maxDays = 0;
        
        for (long i = birth.until(LocalDate.now(), ChronoUnit.DAYS); i < 36500; i++) {
            double sum = computeSin(i, 0) + computeSin(i, 1) + computeSin(i, 2);
            if (sum > max) {
                max = sum;
                maxDays = (int)i;
            }
        }
        
        now = birth.plusDays(maxDays);
        dateNow.setValue(now);
        
        drawBiorhythm();
    }
    
    private void minBiorhythm() {
        double min = 0;
        long minDays = 0;
        
        for (long i = birth.until(LocalDate.now(), ChronoUnit.DAYS); i < 36500; i++) {
            double sum = computeSin(i, 0) + computeSin(i, 1) + computeSin(i, 2);
            if (sum < min) {
                min = sum;
                minDays = i;
            }
        }
        
        now = birth.plusDays(minDays);
        dateNow.setValue(now);
        
        drawBiorhythm();    
    }
    
    private void dangerBiorhythm() {
        double min = 1.0;
        long minDays = 0;
        double svs1, svs2, svs3;
        long basicDays;
        
        basicDays = birth.until(LocalDate.now(), ChronoUnit.DAYS);
        svs1 = computeSin(basicDays, 0);
        svs2 = computeSin(basicDays, 1);
        svs3 = computeSin(basicDays, 2);
        for (long i = basicDays; i < 36500; i++) {
            double s1 = computeSin(i, 0);
            double s2 = computeSin(i, 1);
            double s3 = computeSin(i, 2);
            if (svs1 >= s1 && svs2 >= s2 && svs3 >= s3) {
                double sum = Math.abs(s1) + Math.abs(s2) + Math.abs(s3);
                if (sum < min) {
                    min = sum;
                    minDays = i;
                }
            }
            svs1 = s1;  svs2 = s2;  svs3 = s3;
        }
        
        now = birth.plusDays(minDays);
        dateNow.setValue(now);
        
        drawBiorhythm();         
    }
    
    private void drawBiorhythm() {
        createGrid();
        for (int i=0; i < bioGraph.length; i++) 
            if (bioGraph[i].getCheck()) 
                createGraph(i);
    }
    
    private void createGrid() {
        LocalDate temp = now;
        int count = 0;
        gc.clearRect(0, 0, 799, 399);
        gc.setStroke(Color.LIGHTGREY);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("arial", 9));
        gc.setLineWidth(1.0);
        for (double x = 25.5; x <= 775.5; x+=25.0) {
            gc.moveTo(x, 25.5);
            gc.lineTo(x, 351.5);
            gc.stroke();
            temp = now.plusDays(count++);
            gc.fillText(temp.getMonthValue() + "." + temp.getDayOfMonth(), x, 365.5);
        }
        gc.moveTo(25.5, 25.5);
        gc.lineTo(775.5, 25.5);
        gc.stroke();
        
        gc.moveTo(25.5, 188.5);
        gc.lineTo(775.5, 188.5);
        gc.stroke();
        
        gc.moveTo(25.5, 351.5);
        gc.lineTo(775.5, 351.5);
        gc.stroke();
    }
    
    private void createGraph(int index) {
        double x = 25.5;
        long daysOfLife = birth.until(now, ChronoUnit.DAYS); 
        
        gc.setFill(bioGraph[index].getColor());
        for (double i = 0; i <= 30.0; i+=0.04) {
            double y = computeSin(daysOfLife + i, index) * (-163.0) + 163.0 + 25.5;
            gc.fillRect(x - 1.0, y - 1.0, 2.0, 2.0);
            x += 1.0;
        }
    }
    
    private double computeSin(double days, int index) {
        return Math.sin((days / bioGraph[index].getCycle()) * 2.0 * PI);
    }
}
