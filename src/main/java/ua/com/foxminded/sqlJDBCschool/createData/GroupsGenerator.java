package ua.com.foxminded.sqlJDBCschool.createData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupsGenerator {
    public List<GroupConstructor> group(){
        List<String> generatedNames = groupName();
        List<Integer> groupsID = groupID();
        return IntStream.range(0, generatedNames.size())
                .mapToObj(i -> new GroupConstructor(generatedNames.get(i), groupsID.get(i)))
                .collect(Collectors.toList());
    }

    private List<String> groupName(){
        List<String> possibleGroups = new ArrayList<>();
        possibleGroups.add("AT_");
        possibleGroups.add("BQ_");
        possibleGroups.add("CH_");
        possibleGroups.add("SH_");
        possibleGroups.add("MA_");
        possibleGroups.add("PP_");
        possibleGroups.add("KL_");
        possibleGroups.add("MN_");
        possibleGroups.add("NN_");
        possibleGroups.add("BO_");
        return possibleGroups.stream().map(possibleGroup -> possibleGroup + shuffle("93421"))
                .collect(Collectors.toList());
    }
    private List<Integer> groupID() {
        Random random = new Random();
        return IntStream.range(0, 10).mapToObj(i -> random.nextInt(9999))
                .collect(Collectors.toList());
    }
    private String shuffle(String numbers) {
        StringBuilder result = new StringBuilder(numbers);
        int i = result.length();
        Random randomizer = new Random();
        while (i > 1) {
            int randomPoint = randomizer.nextInt(i);
            char randomChar = result.charAt(randomPoint);
            result.setCharAt(i - 1, randomChar);
            i--;
        }
        return result.toString();
    }
}
