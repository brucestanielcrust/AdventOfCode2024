import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello, World!");

        System.out.printf("Day 1, Part 1 = %d\n", day1_01());
        System.out.printf("Day 1, Part 2 = %d\n", day1_02());
    }

    public static Map<String, List<Integer>> readDay1File() throws FileNotFoundException {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        Scanner sc = new Scanner(new File("puzzles/list.txt"));
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] lst = line.split("   ");
            left.add(Integer.parseInt(lst[0]));
            right.add(Integer.parseInt(lst[1]));
        }

        return new HashMap<String, List<Integer>>() {{
            put("left", left);
            put("right", right);
        }};
    }

    public static int day1_01() throws FileNotFoundException {
        Map<String, List<Integer>> listMap = readDay1File();
        List<Integer> left = listMap.get("left");
        List<Integer> right = listMap.get("right");

        Collections.sort(left);
        Collections.sort(right);

        int dist = 0;

        for (int i = 0; i < left.size(); i++) {
            dist += left.get(i) > right.get(i) ? left.get(i) - right.get(i) : right.get(i) - left.get(i);
        }

        return dist;
    }

    public static int day1_02() throws FileNotFoundException {
        Map<String, List<Integer>> listMap = readDay1File();
        List<Integer> left = listMap.get("left");
        List<Integer> right = listMap.get("right");

        Map<Integer, Integer> similarityMap = new HashMap<>();
        for (Integer i : right) {
            if (!similarityMap.containsKey(i)) {
                similarityMap.put(i, 1);
            } else {
                similarityMap.put(i, similarityMap.get(i) + 1);
            }
        }

        int similarity = 0;

        for (Integer i : left) {
            if (similarityMap.containsKey(i)) {
                similarity += i * similarityMap.get(i);
            }
        }

        return similarity;
    }
}