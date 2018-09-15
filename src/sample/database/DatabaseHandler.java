package sample.database;

import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }

    //Write
    public void registerUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," + Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + ")" + "VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        if(user.getUsername().equals("") || user.getPassword().equals("")) {
            System.out.println("Incorrect information");
        }else{
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD + "=?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        }
        return resultSet;
    }

    public void insertTask(Task task) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.TASKS_TABLE + "(" + Const.USERS_ID + "," + Const.TASKS_TASK + "," + Const.TASKS_DATE + "," + Const.TASKS_DESCRIPTION + ")" + "VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setInt(1, task.getUserId());
        preparedStatement.setString(2, task.getTask());
        preparedStatement.setTimestamp(3, task.getDateCreated());
        preparedStatement.setString(4, task.getDescription());
        preparedStatement.executeUpdate();
    }

    //Read

    //Update

    //Delete

}
