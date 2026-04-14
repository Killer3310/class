import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Family 
{
    private Map<String, List<Person>> name2person = new HashMap<String, List<Person>>();
    public List<Person> get(String name)
    {
        List<Person> ret = new ArrayList<Person>(name2person.get(name));
        ret.sort((Person a, Person b) -> { return a.getBirthDate().compareTo(b.getBirthDate()); });
        return ret;
    }
    public void add(Person... ppl)
    {
        for (Person p : ppl)
        {
            String name = p.getFirstName() + ' ' + p.getLastName();
            if (name2person.containsKey(name)) name2person.get(name).add(p);
            else name2person.put(name, (List<Person>)new ArrayList<Person>() {{add(p);}});
        }
    }
}
