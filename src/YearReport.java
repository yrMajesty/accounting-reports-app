import java.util.ArrayList;

public class YearReport {

    static ArrayList<YearData> yearExpense;
    static ArrayList<YearData> yearIncome;
    String year = "2021";

    void addYear() {
        yearExpense = new ArrayList<>();
        yearIncome = new ArrayList<>();
        String reader = Reader.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = reader.split("\n");


        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            int month = Integer.parseInt(parts[0]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            int amount = Integer.parseInt(parts[1]);
            YearData yearData = new YearData(month, amount, isExpense);

            if (isExpense) {
                yearExpense.add(yearData);
            } else {
                yearIncome.add(yearData);
            }
        }
        System.out.println("Годовой отчет подсчан и добавлен");
    }

    void yearInfo() {
        if (yearIncome == null && yearExpense == null) {
            System.out.println("Годовой отчет не найдены. Загрузите отчет и повторите попытку");
        } else {
            System.out.println(year);
            averageYearIncome();
            averageYearExpense();
            profitByMonth();
        }


    }

    void profitByMonth() {
        int profit = 0;
        int yearInc;
        int yearExp;
        for (YearData income : yearIncome) {
            yearInc = income.amount;
            for (YearData expense : yearExpense) {
                yearExp = expense.amount;
                if (income.month.equals(expense.month)) {
                    profit = yearInc - yearExp;
                }
            }
            System.out.println("Чистая прибыль в " + income.month + "месяце равна: " + profit);
        }
    }

    void averageYearIncome() {
        int income = 0;
        int amount = 0;
        for (YearData allIncome : yearIncome) {
            income += allIncome.amount;
            amount++;
        }
        income /= amount;
        System.out.println("Средний доход за год: " + income);
    }

    private void averageYearExpense() {
        int expense = 0;
        int amount = 0;
        for (YearData allExpense : yearExpense) {
            expense += allExpense.amount;
            amount++;

        }
        expense /= amount;
        System.out.println("Средний расход за год: " + expense);

    }


}