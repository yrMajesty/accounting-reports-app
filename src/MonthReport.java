import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport {


    static HashMap<Integer, ArrayList<MonthData>> monthValue;


    void readMonthReport() {
        monthValue = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            ArrayList<MonthData> list = new ArrayList<>();
            String reader = Reader.readMonthNumber(i);
            String[] lines = reader.split("\n");

            for (int j = 1; j < lines.length; j++) {
                String[] parts = lines[j].split(",");
                String item_name = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sumOfOne = Integer.parseInt(parts[3]);

                MonthData monthData = new MonthData(item_name, isExpense, quantity, sumOfOne);
                list.add(monthData);
            }
            monthValue.put(i, list);
        }
        System.out.println("Отчеты по месяцам подсчитаны и добавлены");
    }

    void printStatistics() {
        if (monthValue == null) {
            System.out.println("Отчеты не найдены. Загрузите отчеты и повторите попытку");
        } else {
            System.out.println("Самый прибыльный товар (по месяцам):");
            maxIncome();
            System.out.println("Самая большая трата (за месяц):");
            maxExpense();
        }
    }

    private void maxIncome() {
        int maxIncome;
        int income;
        String itemName = "";
        for (int month : monthValue.keySet()) {
            System.out.println("Номер месяца: " + month);
            maxIncome = 0;
            for (MonthData goods : monthValue.get(month)) {
                if (!goods.isExpense) {
                    income = 0;
                    income = (goods.quantity * goods.sumOfOne);
                    if (maxIncome < income) {
                        maxIncome = income;
                        itemName = goods.item_name;
                    }
                }
            }
            System.out.println("Товар: " + itemName + "\nПрибыль составила:" + maxIncome);
        }
    }


    private void maxExpense() {

        int expense;
        int maxExpense;
        String expenseName = "";
        for (int month : monthValue.keySet()) {
            maxExpense = 0;
            for (MonthData goods : monthValue.get(month)) {
                if (goods.isExpense) {
                    expense = 0;
                    expense += (goods.quantity * goods.sumOfOne);
                    if (maxExpense < expense) {
                        maxExpense = expense;
                        expenseName = goods.item_name;
                    }

                }
            }
            System.out.println("Потрачено на: " + expenseName + "\nТрата  составила: " + maxExpense);
        }
    }

    void checkReport() {
    YearReport yearReport = new YearReport();
        if (yearReport.yearIncome == null && yearReport.yearExpense == null && MonthReport.monthValue == null) {
            System.out.println("Отчеты не считаны. Считайте отчеты и повторите попытку");
        } else {
            monthYearStatistics();
        }

    }

    void monthYearStatistics() {
        int amount = 0;
        int expense;
        int income;

        for (int month : monthValue.keySet()) {
            expense = 0;
            income = 0;

            for (MonthData goods : monthValue.get(month)) {
                if (goods.isExpense) {
                    expense += goods.quantity * goods.sumOfOne;
                } else {
                    income += goods.quantity * goods.sumOfOne;
                }
            }
            System.out.println("Месяц" + month + "\nРасходы: " + expense + "\nДоходы: " + income);
            for (int incomes : YearReport.yearIncome.keySet()) {
                for (YearData yearsIncome : YearReport.yearIncome.get(month)) {
                    if (yearsIncome.month.equals(month) && yearsIncome.amount != income) {
                        amount++;
                        System.out.println("Месяц: " + month + "\nНесовпадение дохода месячного отчета с годовым отчетом");
                    }
                }
            }
            for (int expenses : YearReport.yearExpense.keySet()) {
                for (YearData yearsExpense : YearReport.yearExpense.get(month)) {
                    if (yearsExpense.month.equals(month) && yearsExpense.amount != expense) {
                        amount++;
                        System.out.println("Месяц: " + month + "\nНесовпадение расходов месячного отчета с годовым отчетом");
                    }
                }
            }
            if (amount == 0) {
                System.out.println("Отчеты проверены, расхождений нет");
            }
        }
    }
}