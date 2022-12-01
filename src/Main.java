import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearReport yearReport = new YearReport();
        MonthReport monthReport = new MonthReport();

        while (true) {
            printMenu();   // запуск меню
            int command = scanner.nextInt();  // выбор пользователя

            if (command == 1) { // Считать все месячные отчёты
                monthReport.addMonth();
            } else if (command == 2) {  // Считать годовой отчёт
                yearReport.addYear();
            } else if (command == 3) {    // Сверить отчёты
                monthReport.monthYearStatistics();
            } else if (command == 4) {    //Вывести информацию о всех месячных отчётах
                monthReport.printStatistics();
            } else if (command == 5) {   // Вывести информацию о годовом отчёте
                yearReport.yearInfo();
            } else if (command == 0) {    // Выход
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды нет.");
            }

        }
    }

    public static void printMenu() {    // Вызов меню
        System.out.println("Выберите команду:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }

}






