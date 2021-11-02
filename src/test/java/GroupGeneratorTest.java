import org.junit.jupiter.api.Test;
import ua.com.foxminded.sqlJDBCschool.createData.GroupConstructor;
import ua.com.foxminded.sqlJDBCschool.createData.GroupsGenerator;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;
import java.util.stream.Collectors;

public class GroupGeneratorTest {
    @Test
    public void createGroupsTest(){
        GroupsGenerator generator = new GroupsGenerator();
        List<String> groups = generator.group().stream().map(GroupConstructor::getGroupName)
                .collect(Collectors.toList());
        List<String> groupsRandom = generator.group().stream().map(GroupConstructor::getGroupName)
                .collect(Collectors.toList());
        assertNotSame(groups, groupsRandom);
        assertThat(groups, hasSize(10));
    }
}
