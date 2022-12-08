package it.step.db;

import it.step.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerDB {


    /**
     * Returns a database connection.
     * @return {@link Connection}
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/step";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Reads all employees.
     * @return {@link List}
     */
    public List<Person> read() {
        try {
            List<Person> employees = new ArrayList<>();
            String sql = "select id, name, surname from employee where id > ? order by id asc";

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                employees.add(new Person(id, name, surname));
            }
            preparedStatement.close();
            connection.close();
            return employees;
        } catch (SQLException ex) {
            System.out.printf("Error. Could not read employees. Reason: %s ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Creates an employee.
     * @param emp {@link Person}
     */
    public void create(Person emp) {
        try {
            String sqlTemplate = "INSERT INTO employee(name, surname) values(?, ?)";

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlTemplate);
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getSurname());

            int rows = preparedStatement.executeUpdate();
            String message = rows == 0 ? "EROARE. Numar de randuri neasteptat." : "Succes. Am adaugat " + rows;
            System.out.println(message);

        } catch (SQLException ex) {
            System.out.printf("Error. Could not create employee. Reason: %s ", ex.getMessage());
        }

    }


    /**
     * Updates an employee
     * @param updatedPerson {@link Person}
     */
    public void update(Person updatedPerson) {
        try {
            String sql = "UPDATE employee SET name=? , surname = ? where id=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setString(2, updatedPerson.getSurname());
            preparedStatement.setInt(3, updatedPerson.getId());

            int rows = preparedStatement.executeUpdate();
            String message = rows == 1 ? "Succes. Am adaugat " + rows : "EROARE. Numar de randuri neasteptat.";
            System.out.println(message);

        } catch (SQLException ex) {
            System.out.printf("Error. Could not update employee. Reason: %s ", ex.getMessage());
        }


    }


    /**
     * Deletes an employee.
     * @param modifiedPerson {@link Person}
     */
    public void delete(Person modifiedPerson) {
        try {
            String sql = "DELETE FROM employee where id=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, modifiedPerson.getId());

            int rows = preparedStatement.executeUpdate();
            String message = rows == 1 ? "Succes. Am sters:  " + rows : "EROARE. Numar de randuri neasteptat.";
            System.out.println(message);

        } catch (SQLException ex) {
            System.out.printf("Error. Could not delete employee. Reason: %s ", ex.getMessage());
        }


    }


}
