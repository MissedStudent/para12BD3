import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BD bd = new BD();
        boolean bool = true;
        Scanner scanner = new Scanner(System.in);
        while (bool) {
            System.out.println();
            System.out.println("""
                    1. Вывести список всех книг одного жанра.
                    2. Найти автора, написавшего больше всего книг.
                    3. Показать все заказы одного покупателя.
                    4. Посчитать количество книг в каждом жанре.
                    5. Отобразить все книги, изданные после 1850 года.
                    6. Вывести список покупателей, сделавших больше 3 покупок.
                    7. Показать самые продаваемые книги.
                    8. Вывести информацию о всех авторах, имеющих более одной книги в магазине.
                    9. Найти книги, которые не были заказаны ни разу.
                    10. Показать сумму всех заказов.
                    11. Завершение работы.
                    """);
            int swtch = scanner.nextInt();
            switch (swtch) {
                case 1: {//1. Вывести список всех книг одного жанра.
                    System.out.println("Введите название жанра");
                    String gen = scanner.next();
                    bd.bookOfGenre(gen);
                }
                break;
                case 2: {//2. Найти автора, написавшего больше всего книг.
                    System.out.println("Автор, написавшего больше всего книг");
                    bd.biggestNumberOfBooksByAuthor();
                }
                break;
                case 3: {//3. Показать все заказы одного покупателя.
                    System.out.println("Введите фамилию покупателя");
                    String fam = scanner.next();
                    bd.ordersOfCustomer(fam);
                }
                break;
                case 4: {//4. Посчитать количество книг в каждом жанре.
                    System.out.println("Количество книг в каждом жанре");
                    bd.booksInGenres();
                }
                break;
                case 5: {//5. Отобразить все книги, изданные после 1850 года.
                    System.out.println("Книги изданные после 1850 года");
                    bd.booksAfter1850();
                }
                break;
                case 6: {//6. Вывести список покупателей, сделавших больше 5 покупок.
                    System.out.println("Покупатели, сделавшие более 3 покупок");
                    bd.fiveOrMoreOrders();
                }
                break;
                case 7: {//7. Показать самые продаваемые книги.
                    System.out.println("Самые продаваемые книги");
                    bd.theMostSellable();
                }
                break;
                case 8: {//8. Вывести информацию о всех авторах, имеющих более одной книги в магазине.
                    System.out.println("Авторы, имеющие более одной книги");
                    bd.authorsWithMoreThanOneBook();
                }
                break;
                case 9: {//9. Найти книги, которые не были заказаны ни разу.
                    System.out.println("Книги, которые никто не заказывал");
                    bd.booksThatNoOneBuy();
                }
                break;
                case 10: {//10. Показать сумму всех заказов за текущий месяц.
                    System.out.println("Сумма всех заказов");
                    bd.sum();
                }
                break;
                case 11: {
                    bool = false;
                }
                break;
                default: {
                    System.out.println("Значение выходит за предусмотренные границы");
                    bool = false;
                }
                break;
            }
        }
    }
}