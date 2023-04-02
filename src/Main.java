import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст для анализа:");

        String text = scanner.nextLine();

        List<String> textAsList = new ArrayList<String>(Arrays.asList(text.split("\s")));

        System.out.println("Количество слов в тексте - " + textAsList.size());

        Map<String, List<String>> textMap = textAsList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.toList()));

        Map<String, Integer> textMap2 = new HashMap<>();

        for (Map.Entry<String, List<String>> words : textMap.entrySet()) {
            textMap2.put(words.getKey(), words.getValue().size());
        }

        System.out.println("Количество уникальных слов в тексе - " + textMap2.size());

        Map<String, Integer> textSorted = textMap2.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey())
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap
                        (Map.Entry::getKey
                        , Map.Entry::getValue
                        , (key1, key2) -> key1
                        , LinkedHashMap::new));

        System.out.println("Топ 10 слов: ");

        for (Map.Entry<String, Integer> words : textSorted.entrySet()) {
            System.out.println(words.getValue() + " - " + words.getKey());
        }
    }
}