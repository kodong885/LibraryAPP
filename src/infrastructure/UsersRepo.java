package infrastructure;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepo {

    private static List<User> users = new ArrayList<>();

    public List<User> getUserListByUserName(String userName) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < users.size(); i ++) {
            if (userName.equals(users.get(i).getUserName())) {
                userList.add(users.get(i));
            } else continue;
        }
        return userList;
    }

    public User getUser(Integer userNum, List<User> users) {
        return users.get(userNum-1);

    }

    static {
        // set users
        User user1 = new User(0L, "ko");  // you have to fix this code to generate a value;
        User user2 = new User(1L, "messi");
        User user3 = new User(2L, "ko");
        User user4 = new User(3L, "ko");
        User user5 = new User(4L, "son");
        User user6 = new User(5L, "messi");
        // add users to userRepo
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
    }
}
