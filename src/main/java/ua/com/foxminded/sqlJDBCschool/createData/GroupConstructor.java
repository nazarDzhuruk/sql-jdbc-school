package ua.com.foxminded.sqlJDBCschool.createData;

import java.util.List;
import java.util.Set;

public class GroupConstructor {
    private Integer groupID;
    private String groupName;

    public GroupConstructor(String groupName, Integer groupID) {
        this.groupName = groupName;
        this.groupID = groupID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }
}
