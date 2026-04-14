import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws NegativeLifespanException, ParentingAgeException, IOException, FileNotFoundException, AmbiguousPersonException
    {
        // Person[] people = new Person[10];
        List<Person> people2 = new ArrayList<>(List.of(
            new Person("Wawa1", "Pluh", LocalDate.of(1990, 1, 4)),
            new Person("Wawa1", "Pluh", LocalDate.of(2007, 1, 4)),
            new Person("Wawa3", "Pluh", LocalDate.of(2030, 1, 4)),
            new Person("Wawa4", "Pluh", LocalDate.of(2090, 1, 4))
        ));

        people2.forEach((Person p) -> { System.out.println(p.toString()); });
        people2.get(0).adopt(people2.get(2));
        people2.get(0).adopt(people2.get(3));
        Family f = new Family();
        
        f.add(people2.toArray(new Person[people2.size()]));
        
        System.out.println(f.get("Wawa1 Pluh"));
        System.out.println(people2.get(0).getYoungestChild());

        System.out.println(Person.fromCsv("family.csv"));
    }
}