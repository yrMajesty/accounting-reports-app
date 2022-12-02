import java.util.ArrayList;
import java.util.HashMap;

public class YearReport {
    static HashMap<Integer, ArrayList<YearData>> yearIncome;
    static HashMap<Integer, ArrayList<YearData>> yearExpense;
    String year = "2021";

    void readYearReport() {
        yearExpense = new HashMap<>();
        yearIncome = new HashMap<>();

        String reader = Reader.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = reader.split("\n");


        for (int i = 1; i < lines.length; i++) {
            ArrayList<YearData> listYear = new ArrayList<>();
            String[] parts = lines[i].split(",");
            int month = Integer.parseInt(parts[0]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            int amount = Integer.parseInt(parts[1]);
            YearData yearData = new YearData(month, amount, isExpense);
            listYear.add(yearData);
            if (isExpense) {
                yearExpense.put(month, listYear);
            } else {
                yearIncome.put(month, listYear);
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
        for (int month : yearIncome.keySet()) {
            for (YearData income : yearIncome.get(month)) {
                yearInc = income.amount;
                for (YearData expense : yearExpense.get(month)) {
                    yearExp = expense.amount;
                    if (income.month.equals(expense.month)) {
                        profit = yearInc - yearExp;
                    }
                }
                System.out.println("Чистая прибыль в " + income.month + "месяце равна: " + profit);
            }
        }
    }

    void averageYearIncome() {
        int income = 0;
        int amount = 0;
        for (int month : yearIncome.keySet()) {
            for (YearData allIncome : yearIncome.get(month)) {
                income += allIncome.amount;
                amount++;
            }
            income /= amount;
            System.out.println("Средний доход за год: " + income);
        }
    }

    private void averageYearExpense() {
        int expense = 0;
        int amount = 0;
        for (int month : yearIncome.keySet()) {
            for (YearData allExpense : yearExpense.get(month)) {
                expense += allExpense.amount;
                amount++;

            }
            expense /= amount;
            System.out.println("Средний расход за год: " + expense);
        }
    }


}