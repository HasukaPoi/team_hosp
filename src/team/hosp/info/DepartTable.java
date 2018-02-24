package team.hosp.info;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunsetwan on 07/07/2017.
 * Last updated by Hasuka on 2017/7/12.
 */
public class DepartTable {
    private String[] DcolumnName;

    DepartTable() {
        DcolumnName = new String[] {"DepartName", "DoctorName"};
        //TODO
        //refresh();
    }





    public void List_Item_To_String(List<DepartInfo> dlist) {
        //int List_Length = dlist.size();
        for(DepartInfo Item : dlist ) {
            System.out.print(Item.dname);

        }

    }

    java.util.List<DepartInfo> departInfoList = new ArrayList<DepartInfo>();
    //TODO
    //have to get Info from MySQL and put the Info into the ArrayList





}