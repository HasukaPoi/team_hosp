package team.hosp.info;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunsetwan on 07/07/2017.
 * Last updated by hssuka on 2017/7/12.
 */
public class MedicineTable {
    private String[] McolumnName;

    MedicineTable() {
        McolumnName = new String[]{"MName", "MAmount", "MPrice"};
        //refresh();
    }

    //TODO
   /* private Object[][] getInfo()throws Exception {
        java.util.List<MedicineInfo> dList = DBUtils.findAllStu();
        int num = mList.size();
        Object[][] data = new Object[num][3];
        int index = 0;
        for (MedicineInfo medicine : mList) {
            data[index][0] = medicine.getMName();
            data[index][1] = medicine.getMAmount();
            data[index][2] = medicine.getMPrice();

            ++index;
        }
        return data;
    }*/

    public void List_Item_To_String(List<DepartInfo> dlist) {
        //int List_Length = dlist.size();
        for (DepartInfo Item : dlist) {
            System.out.print(Item.dname);

        }

    }

    java.util.List<MedicineInfo> MedicineInfoList = new ArrayList<MedicineInfo>();
    //TODO
    //have to get Info from MySQL and put the Info into the ArrayList


}
