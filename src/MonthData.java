public class MonthData {
    String item_name;
    int quantity;
    int sumOfOne;
    boolean isExpense;

    public MonthData(String item_name, boolean isExpense, int sumOfOne, int quantity) {
        this.item_name = item_name;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.isExpense = isExpense;
    }
}

