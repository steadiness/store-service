package edu.storeservice.core.ch3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.NoSuchElementException;

import static edu.storeservice.core.ch3.ConnectionConst.*;

@Slf4j
public class AccountJdbcRepository {

    public Account findByName(String name) throws SQLException {
        String sql = "select * from account where name = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet  = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setName(resultSet.getString("name"));
                account.setBalance(resultSet.getInt("balance"));
                return account;
            } else {
                throw new NoSuchElementException(name + "으로 된 고객은 없습니다.");
            }

        } catch (SQLException e) {
            log.error(String.valueOf(e));
            throw e;
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }
    public void update(Account account, int money) throws SQLException {
        String sql = "update account set balance=? where id=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, money);
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(String.valueOf(e));
            throw e;
        }finally {
            close(connection, preparedStatement, null);
        }
    }

    public Account save(Account account) throws SQLException {
        String sql = "insert into account(name, balance) values(?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setInt(2, account.getBalance());
            preparedStatement.executeUpdate();
            return account;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    public void delete() throws SQLException {
        String sql = "delete from account";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }


    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }
}

