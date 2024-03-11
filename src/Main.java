import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {



        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minors = persons.stream().
                filter(x -> x.getAge() < 18).
                count();
        System.out.println("Minors: " + minors);

        List<String> list = persons.stream()
                .filter(sex -> sex.getSex().equals(Sex.MAN))
                .filter(age -> age.getAge() >= 18 && age.getAge() <= 27)
                .map(family -> family.getFamily())
                .collect(Collectors.toList());
        //System.out.println(list);

        List<String> list2 = persons.stream()
                .filter(age -> age.getAge() >= 18)
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getAge() <= 60 && person.getSex().equals(Sex.WOMAN)) ||
                        (person.getAge() <= 65 && person.getSex().equals(Sex.MAN)))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(family -> family.getFamily())
                .collect(Collectors.toList());
        System.out.println(list2.get(0));
        System.out.println(list2.get(list2.size() / 2 - 1));
        System.out.println(list2.get(list2.size() - 1));
    }
}
