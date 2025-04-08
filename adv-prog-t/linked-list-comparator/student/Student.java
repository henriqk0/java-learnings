package student;


public class Student {
    public int reg;
    public String name;
    public float score;

    public Student(int reg, String name, float score) {
        this.reg = reg;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.reg + " - " + this.name;
    }

    @Override
    public boolean equals(Object s) {
        if (s instanceof Student) {
            return this.reg == ((Student)s).reg;
        }
        else {
            return false;
        }
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
