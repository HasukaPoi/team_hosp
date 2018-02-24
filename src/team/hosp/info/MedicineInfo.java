package team.hosp.info;

/**
 * Created by sunsetwan on 07/07/2017.
 * Last updated by hasuka on 2017/7/8.
 */
public class MedicineInfo {
    private String name;
    private int amount;
    private int price;


    public MedicineInfo(String name, int price, int amount) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

}

