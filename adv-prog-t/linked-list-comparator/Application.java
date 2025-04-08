import linkedlists.LinkedListComparator;
import student.Student;
import student.StudentNameComparator;
import student.StudentRegistrationComparator;


public class Application {
    public static void main(String[] args) {
        LinkedListComparator<Student> nameOrdList = new LinkedListComparator<>(true, new StudentNameComparator());
        LinkedListComparator<Student> regOrdList = new LinkedListComparator<>(true, new StudentRegistrationComparator());
        Student s1 = new Student(1, "Iohannes", 50);
        Student s2 = new Student(2, "Ambrosius", 58);
        nameOrdList.insertElemOrdered(s1);
        nameOrdList.insertElemOrdered(s2);
        regOrdList.insertElemOrdered(s1);
        regOrdList.insertElemOrdered(s2);
        System.out.println("Ordered list by name: " + nameOrdList);
        System.out.println("Ordered list by registration: " + regOrdList);
    }

}
