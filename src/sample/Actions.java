package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.Random;

public class Actions {

    public static void rand_action(Students student) {
        Random random = new Random();
        int number = random.nextInt(5);
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Event");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        Optional<ButtonType> result;

        switch (number) {
            case 0:

                alert.setContentText("Studenci proszą Cię o przedłużenie sesji, zgadzasz się?");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    student.happiness += 0.05;
                } else if (result.get() == buttonTypeTwo) {
                    student.happiness -= 0.05;
                }
                break;
            case 1:

                alert.setContentText("Studenci proszą o rozłożenie opłat za powtarzanie przedmiotu \n" +
                        "zgadzasz się?");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    student.happiness += 0.05;
                } else if (result.get() == buttonTypeTwo) {
                    student.happiness -= 0.05;
                }
                break;
            case 2:

                alert.setContentText("Studenci chcą zmniejszenia liczby zajęć o 10h tygodniowo \n" +
                        "Zgadzasz się?");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    student.number_of_hours -= 40;
                } else if (result.get() == buttonTypeTwo) {
                    student.happiness -= 0.05;
                }
                break;
            case 3:

                alert.setContentText("Studenci chcą zorganizować juwenalia \n" +
                        "zgadzasz się?");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    student.happiness += 0.1;
                } else if (result.get() == buttonTypeTwo) {
                    student.happiness -= 0.15;
                }
                break;
            case 4:

                alert.setContentText("Dziś jest wyjątkowe święto! \n" +
                        "Czy chcesz ogłosić godziny dziekańskie?");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    student.happiness += 0.05;
                } else if (result.get() == buttonTypeTwo) {
                    student.happiness += 0;
                }
                break;
        }

    }

    public static void low_happiness_action() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Event");
        alert.setContentText("Poziom zadowolenia studentów spadł poniżej minimalnego poziomu. Koniec symulacji!");
        ButtonType buttonTypeOne = new ButtonType("Ok");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            System.out.println("Koniec symulacji");
        }

    }
}