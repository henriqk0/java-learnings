package student;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Scanner;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Student {
    private int reg;
    private String name;
    private float score;

    private Student instance = null;

    private Student(int reg, String name, float score) {
        this.reg = reg;
        this.name = name;
        this.score = score;
    }

    private Student(){
        Scanner s = new Scanner(System.in);
        System.out.println("Informe o nº do aluno:\n");
        this.reg = s.nextBigInteger().intValue();
        System.out.println("Informe o nome do aluno:\n");
        this.name = s.nextLine();
    }

    public Student getInstance(){
        if(this.instance == null){
            this.instance = new Student();
        }
        return this.instance;
    }

    /*
    @Override
    public String toString() {
        return this.reg + " - " + this.name;
    }*/

    /*
    @Override
    public boolean equals(Object s) {
        if (s instanceof Student) {
            return this.reg == ((Student)s).reg;
        }
        else {
            return false;
        }
    }*/
}
