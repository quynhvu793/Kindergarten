package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentFee {

    private int id;
    private int registeredStudentId;
    private int month;
    private int amount;
    private boolean isPaid;

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

        try (Connection conn = TestingHelper.getConnection(env); PreparedStatement statement = conn.prepareStatement(sqlStr);) {
            statement.setInt(1, registeredStudentId);
            statement.setInt(2, month);
            statement.setInt(3, amount);
            statement.setBoolean(4, isPaid);
            int result = statement.executeUpdate();

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
