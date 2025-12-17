package org.technical;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Attendance {

    private int id;
    private int registeredStudentId;
    private boolean isAbsent;
    private Date date;

    public Attendance() {

    }

    public int addAttendance(int registeredStudentId, boolean isAbsent, String date, String env) {
        Date attendanceDate;
        if (registeredStudentId <= 0) {
            return 0;
        }

        try {
            attendanceDate = Date.valueOf(date);
        } catch (IllegalArgumentException e) {
            return 0;
        }

        String sqlStr = "insert into Attendance (registeredStudentId, isAbsent, date) values (?, ?, ?)";

        try (Connection conn = TestingHelper.getConnection(env); PreparedStatement statement = conn.prepareStatement(sqlStr);) {
            statement.setInt(1, registeredStudentId);
            statement.setBoolean(2, isAbsent);
            statement.setDate(3, attendanceDate);

            int result = statement.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
