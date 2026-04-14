import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Comparable<Person>
{
    private String firstName, lastName;
    private LocalDate birthDate, deathDate;
    private Set<Person> children = new HashSet<Person>();
    public Person(String firstName, String lastName, LocalDate birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        deathDate = null;
    }
    public Person(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate) throws NegativeLifespanException
    {
        this(firstName, lastName, birthDate);
        if (deathDate.isBefore(birthDate)) throw new NegativeLifespanException();
        this.deathDate = deathDate;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return firstName + " " + lastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public LocalDate getDeathDate() { return deathDate; }
    public boolean adopt(Person p) throws ParentingAgeException
    {
        if (birthDate.getYear() - p.getBirthDate().getYear() <= 15) throw new ParentingAgeException();
        children.add(p);
        return true;
    }
    public Person getYoungestChild()
    {
        if (children.isEmpty()) return null;
        return Collections.max(children);
    }
    public List<Person> getChildren()
    {
        return List.copyOf(children);
    }
    @Override
    public String toString() 
    {
        return firstName + ' ' + lastName + ' ' + birthDate.toString();
    }
    @Override
    public int compareTo(Person p) 
    {
        return birthDate.compareTo(p.birthDate);
    }
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static Person fromCsvLine(String line) throws NegativeLifespanException
    {
        String[] s = line.split(",");
        String[] n = s[0].split(" ");
        return new Person(
            n[0], n[1], 
            LocalDate.parse(s[1], format), 
            s[2].length() == 0 ? null : LocalDate.parse(s[2], format)
        );
    }
    public static List<Person> fromCsv(String path) throws NegativeLifespanException, AmbiguousPersonException, FileNotFoundException, IOException
    {
        File f = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(f));
        List<Person> ret = new ArrayList<Person>();
        String[] lines = (String[])br.lines().toArray();
        for (String line : lines) 
        {
            Person p = fromCsvLine(line);
            if (ret.stream().anyMatch(p2 -> p.firstName == p2.firstName && p.lastName == p2.lastName)) 
            {
                br.close();
                throw new AmbiguousPersonException();
            }
            ret.add(p);
        }
        for (int i = 0; i < lines.length; i++)
        {
            String[] s = lines[i].split(",");
            for (int j = 0; j < lines.length; j++)
            {
                if (i == j) continue;
                if (ret.get(j).getName() == s[0])
                {
                    try
                    {
                        ret.get(i).adopt(ret.get(j));
                    }
                    catch (ParentingAgeException e)
                    {
                        
                    }
                }
            }
        }
        br.close();
        return ret;
    }
}
