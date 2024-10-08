import java.sql.*;
import java.util.Random;

public class BD {
    public static String url = "jdbc:mysql://localhost/bookshop";
    public static String user = "root";
    public static String password = "";

    // открыть соединение
    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //закрыть соединение
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void bookOfGenre(String genre_name) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT books.book_title FROM `books`,`genres` WHERE books.genre_id=genres.id_genre AND genres.genre_name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, genre_name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String book = resultSet.getString("book_title");
                System.out.println(book);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void biggestNumberOfBooksByAuthor() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT authors.author_surname,authors.author_name,authors.author_lastname,COUNT(*) AS number_of_books FROM `books`,`authors` WHERE authors.id_author=books.author_id GROUP BY authors.author_surname,authors.author_name,authors.author_lastname ORDER BY number_of_books DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("author_surname");
                String au_name = resultSet.getString("author_name");
                String au_last = resultSet.getString("author_lastname");
                int numberOfBooks = resultSet.getInt("number_of_books");
                System.out.println(au_sur + " " + au_name + " " + au_last + " " + numberOfBooks);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void ordersOfCustomer(String customer_surname) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT books.book_title, orders.order_sum FROM `books`,`orders`,`customers` WHERE customers.customer_surname=? AND customers.id_customer=orders.customer_id AND books.id_book=orders.book_id";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer_surname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("book_title");
                int sum = resultSet.getInt("order_sum");
                System.out.println(au_sur + " " + sum);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void booksInGenres() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT genres.genre_name,COUNT(*) AS number_of_books FROM `books`,`genres` WHERE books.genre_id=genres.id_genre GROUP BY genres.genre_name";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("genre_name");
                int numberOfBooks = resultSet.getInt("number_of_books");
                System.out.println(au_sur + " " + numberOfBooks);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void booksAfter1850() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT books.book_title FROM `books` WHERE books.year>1850";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("book_title");
                System.out.println(au_sur);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void fiveOrMoreOrders() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT customers.customer_surname, customers.customer_name,customers.customer_lastname, COUNT(orders.id_order) AS number_of_orders FROM `customers`,`orders` WHERE customers.id_customer=orders.customer_id GROUP BY customers.customer_surname,customers.customer_name,customers.customer_lastname HAVING number_of_orders>2";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("customer_surname");
                String au_name = resultSet.getString("customer_name");
                String au_last = resultSet.getString("customer_lastname");
                int numberOfBooks = resultSet.getInt("number_of_orders");
                System.out.println(au_sur + " " + au_name + " " + au_last + " " + numberOfBooks);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void theMostSellable() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT books.book_title,COUNT(orders.book_id) AS counter FROM `books`,`orders` WHERE books.id_book=orders.book_id GROUP BY books.book_title ORDER BY counter desc LIMIT 3";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("book_title");
                String au_name = resultSet.getString("counter");
                System.out.println(au_sur + " " + au_name);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void authorsWithMoreThanOneBook() {
        Connection connection = null;
        try{
            connection = openConnection();
            String query = "SELECT authors.author_surname,authors.author_name,authors.author_lastname, COUNT(books.id_book) as counter FROM `authors`, `books` WHERE authors.id_author = books.author_id GROUP BY authors.author_surname,authors.author_name,authors.author_lastname HAVING counter>1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("author_surname");
                String au_name = resultSet.getString("author_name");
                String au_last = resultSet.getString("author_lastname");
                int count = resultSet.getInt("counter");
                System.out.println(au_sur+" "+au_name+" "+au_last+" "+count);
            }
            closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void booksThatNoOneBuy(){
        Connection connection = null;
        try{
            connection = openConnection();
            String query = "SELECT books.book_title FROM `books` LEFT JOIN `orders` ON books.id_book=orders.book_id WHERE orders.customer_id IS NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("book_title");
                System.out.println(au_sur);
            }
            closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void sum(){
        Connection connection = null;
        try{
            connection = openConnection();
            String query = "SELECT SUM(orders.order_sum) AS order_sum FROM `orders`";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String au_sur = resultSet.getString("order_sum");
                System.out.println(au_sur);
            }
            closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}