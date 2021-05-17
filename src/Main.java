import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        List<Person> personList =  IntStream.range(1,20)
                .mapToObj(i-> new Person(i,"Person"+i,"Surname"+(i+10),10+(int)(Math.random()*20)+""))
                .collect(Collectors.toList());

        Function<Person, Integer> intAge= p ->Integer.parseInt(p.getAge());
        Predicate<Person> plus18 = p -> intAge.apply(p)>18;

        //18 yaşdan böyük olan
        personList.stream().filter(plus18).forEach(System.out::println);
        // orta yaş
        float avarage =(float) personList
                .stream()
                .parallel()
                .reduce(0,(avg,p) ->avg+= intAge.apply(p) ,(avg,b)->avg+=b)/personList.size();
        System.out.println(avarage);
        //18 yaşdan böyük olanlarin yaşin cemi
        int plus18sum = personList
                .stream()
                .parallel()
                .filter(plus18)
                .reduce(0,(sum,p) ->sum+= intAge.apply(p) ,(sum,b)->sum+=b);
        System.out.println(plus18sum);
    }
}
