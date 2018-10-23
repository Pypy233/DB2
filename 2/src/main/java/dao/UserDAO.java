package dao;

import domain.User;
import util.DateUtil;
import util.FeeCalculator;
import util.HQLUtil;

import java.util.ArrayList;

public class UserDAO {
    public boolean addUser(String name, String password) {
        User user = new User(name, password);
        try {
            HQLUtil.add(user);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User findUserByUserName(String username) {
        String oprdStr = "from User where username = '" + username + "'";
        ArrayList<User> list = (ArrayList<User>) HQLUtil.find(oprdStr);
        if(list.size() == 0 || list == null) {
           return new User();
        }
        return list.get(0);
    }

    public double getCallFee(String username) {
        User user = findUserByUserName(username);
        return FeeCalculator.getCallFee(user, DateUtil.getCurrentDate());
    }

    public double getDataFee(String username) {
        User user = findUserByUserName(username);
        return FeeCalculator.getDataFee(user, DateUtil.getCurrentDate());
    }
}
