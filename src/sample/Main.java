package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {


    private static boolean isGood = true;
    private static int count = 0;
    private static Stage mainWindow;
    private static AtomicInteger tick = new AtomicInteger(0);


    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("fxml/login_screen.fxml"));

        mainWindow = primaryStage;
        mainWindow.setTitle("Projekt_ask_symulacja");

        Scene scene = new Scene(root, 1400, 680);
        scene.getStylesheets().addAll("sample/css/loginStyle.css");
        mainWindow.setResizable(false);
        mainWindow.setScene(scene);
        mainWindow.show();
        mainWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public static void goToNewScene(){
        try{
            AnchorPane newRoot = FXMLLoader.load(Main.class.getResource("fxml/main_stage1.fxml"));
            NumberAxis xAxis = new NumberAxis();
            NumberAxis yAxis = new NumberAxis();

            Students student = new Students(1);

            ImageView image = new ImageView();
            image.setLayoutX(900);
            image.setLayoutY(50);
            image.setImage(new Image(new File("src/sample/graphics/anim1.jpg").toURI().toString()));


            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);

            Slider scholarship_slider = new Slider(0, 1000, student.scholarship);
            Slider hours_slider = new Slider(0, 200, student.number_of_hours);
            Slider exam_slider = new Slider(0, 20, student.number_of_exams);

            Button controll_button = new Button();

            param_init(xAxis, yAxis, series, chart, scholarship_slider, hours_slider, exam_slider, controll_button);

            newRoot.getChildren().addAll(chart, scholarship_slider, hours_slider, exam_slider, controll_button, image);

            Scene mainScene = new Scene(newRoot, 1400,680);
            mainWindow.setScene(mainScene);
            mainWindow.show();

            Thread updateThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (tick.get() % 3 == 0){
                            if(chance(15)){
                                student.happiness -=0.05;
                            }

                            if(chance(15)){
                                Platform.runLater(() -> Actions.rand_action(student));
                            }
                            Platform.runLater(() -> series.getData().add(new XYChart.Data<>(tick.get()/3 , student.happiness)));
                        }


                        if(student.scholarship != (int)scholarship_slider.getValue() || student.number_of_hours != (int)hours_slider.getValue() ||
                                        student.number_of_exams != (int)exam_slider.getValue() ){

                            System.out.println("DEbug xd");
                            student.updateHappines(student.scholarship - (int)scholarship_slider.getValue(),
                                    student.number_of_hours - (int)hours_slider.getValue(),
                                    student.number_of_exams - (int)exam_slider.getValue());

                            student.scholarship = (int)scholarship_slider.getValue();
                            student.number_of_hours = (int)hours_slider.getValue();
                            student.number_of_exams = (int)exam_slider.getValue();


                        }

                        if(tick.get() > 2 && (tick.get()/2) % 20 == 0){
                            Platform.runLater(() -> StatusSwap(controll_button));
                        }
                        if(!isGood){
                            count++;
                            if(count >= 10){
                                System.exit(0);
                            }
                        }
                        if(tick.get() % 2 == 0){
                            image.setImage(new Image(new File("src/sample/graphics/anim2.jpg").toURI().toString()));
                        }else if(tick.get() % 3 == 0){
                            image.setImage(new Image(new File("src/sample/graphics/anim3.jpg").toURI().toString()));

                        }else {
                            image.setImage(new Image(new File("src/sample/graphics/anim1.jpg").toURI().toString()));

                        }

                        if(student.happiness < 0.25){
                            Platform.runLater(() -> Actions.low_happiness_action());
                            Thread.sleep(2000);
                            System.exit(0);
                        }

                        tick.incrementAndGet();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            updateThread.setDaemon(true);
            updateThread.start();



        }catch (Exception e){
            System.out.println("Failed do load second stage FXML file");
        }


    }
    public static void param_init(NumberAxis xAxis, NumberAxis yAxis, XYChart.Series<Number, Number> series,  LineChart<Number, Number> chart, Slider s1, Slider s2, Slider s3, Button btn){
        xAxis.setAnimated(false);
        xAxis.setLabel("Dzień");

        yAxis.setAnimated(false);
        yAxis.setLabel("Zadowolenie studentów");
        s1.setOrientation(Orientation.VERTICAL);
        s2.setOrientation(Orientation.VERTICAL);
        s3.setOrientation(Orientation.VERTICAL);


        s1.setShowTickMarks(true);
        s1.setShowTickLabels(true);
        s1.setMajorTickUnit(100f);
        s1.setMinorTickCount(100);
        s1.setSnapToTicks(true);
        s1.setLayoutX(1070);
        s1.setLayoutY(300);

        s2.setShowTickMarks(true);
        s2.setShowTickLabels(true);
        s2.setMajorTickUnit(25);
        s2.setMinorTickCount(25);
        s2.setSnapToTicks(true);
        s2.setLayoutX(1170);
        s2.setLayoutY(300);

        s3.setShowTickMarks(true);
        s3.setShowTickLabels(true);
        s3.setMajorTickUnit(2f);
        s3.setMinorTickCount(2);
        s3.setBlockIncrement(1f);
        s3.setSnapToTicks(true);
        s3.setLayoutX(1270);
        s3.setLayoutY(300);

        btn.setLayoutX(1070);
        btn.setLayoutY(450);
        btn.setText("");
        btn.setPrefSize(250, 100);
        btn.setStyle("-fx-background-color: green");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                isGood  = true;
                count = 0;
                btn.setStyle("-fx-background-color: green");
            }
        });


        chart.setAnimated(false);
        chart.getData().add(series);
        chart.setLayoutX(357);
        chart.setLayoutY(282);
        chart.setPrefSize(623, 320);
        chart.setLegendVisible(false);
        chart.setCreateSymbols(false);
        chart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
    }

    private static Random random = new Random();

    private static boolean chance(int chance){
        return chance > random.nextInt(100);

    }

    private static void StatusSwap(Button btn){
        btn.setStyle("-fx-background-color: red");
        isGood = false;

    }


    public static void main(String[] args) {
        launch(args);
    }
}
