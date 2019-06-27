package sample;
import java.util.Random;

public class Students {

    public int semester;
    public int number_of_students;
    public float happiness;
    public int number_of_hours;
    public int number_of_exams;
    public int scholarship;


    public Students(int semester){
        Random rand = new Random();
        this.semester = semester;
        this.happiness = 1;
        this.number_of_students = rand.nextInt(201) + 400;
        this.number_of_hours = rand.nextInt(101) + 80;
        this.number_of_exams = rand.nextInt(8) + 2;
        this.scholarship = rand.nextInt(500) + 500;
        this.happiness = this.happiness - ((float)number_of_hours/number_of_students) - ((float)number_of_exams/((float)number_of_students/20)) + (float)scholarship/(number_of_students*10);


    }

    public void updateHappines(int scholar, int hours, int exams){
        System.out.println("sch: " + scholar + " hours: " + hours + " exams: " + exams);


        this.happiness = this.happiness - (float)scholar/(number_of_students*5) + (float)hours/number_of_students + (float)exams/((float)(number_of_students/20));
    }


}
