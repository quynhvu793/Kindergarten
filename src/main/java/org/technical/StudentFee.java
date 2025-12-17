package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentFee {

    private int id;
    private int studentId;
    private int registeredClassId;
    private int month;
    private int year;
    private int amount;
    private boolean isPaid;

    public int getRecordCount(String env) {
        String sqlStr = "select count(*) as total from StudentFee";

        try {
            Connection conn = TestingHelper.getConnection(env);
            PreparedStatement statement = conn.prepareStatement(sqlStr);

            ResultSet resultCount = statement.executeQuery();
            return resultCount.getInt("total");
        } catch (Exception e) {
            return -1;
        }
    }

    public int addStudentFee(int registeredStudentId, int month, int amount, boolean isPaid, String env) {
        if (registeredStudentId <= 0) {
            return 0;
        }

        if (month < 1 || month > 12) {
            return 0;
        }

        if (amount <= 0) {
            return 0;
        }

        String sqlStr = "insert into StudentFee (registeredStudentId, month, amount, isPaid) values (?, ?, ?, ?)";

        try {
            Connection conn = TestingHelper.getConnection(env);
            PreparedStatement statement = conn.prepareStatement(sqlStr);
            statement.setInt(1, registeredStudentId);
            statement.setInt(2, month);
            statement.setInt(3, amount);
            statement.setBoolean(4, isPaid);
            int result = statement.executeUpdate();

            return result;
        } catch (Exception e) {
            return -1;
        }
    }
}
