import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //create person list
        List<Person> personList =  IntStream.range(1,20)
                .mapToObj(i-> new Person(i,"Person"+i,"Surname"+(i+10),10+(int)(Math.random()*20)+""))
                .collect(Collectors.toList());

        Function<Person, Integer> intAge= p ->Integer.parseInt(p.getAge());
        Predicate<Person> plus18 = p -> intAge.apply(p)>18;

        //18 yaşdan böyük olan
        personList.stream().filter(plus18).forEach(System.out::println);
        // orta yaş

        //1-ci method
        double avarage =(double) personList
                .stream()
                .parallel()
                .reduce(0,(avg,p) ->avg+= intAge.apply(p) ,(avg,b)->avg+=b)/personList.size();
        System.out.println(avarage);

        //2-ci method
        personList.stream().mapToInt(p->intAge.apply(p)).average().ifPresent(System.out::println);

        //18 yaşdan böyük olanlarin yaşin cemi

        //1-ci method
        int plus18sum = personList
                .stream()
                .parallel()
                .filter(plus18)
                .reduce(0,(sum,p) ->sum+= intAge.apply(p) ,(sum,b)->sum+=b);
        System.out.println(plus18sum);

        //2-ci method
        int plus18sum2 =  personList.stream().parallel().filter(plus18).mapToInt(p->intAge.apply(p)).sum();
        System.out.println(plus18sum2);

    }
}
