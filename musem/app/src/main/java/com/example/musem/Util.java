package com.example.musem;
import com.example.musem.model.Museum;

import java.util.ArrayList;
import java.util.List;


public class Util {

    public static ArrayList<Museum> populateMuseumList() {
        ArrayList<Museum> museumList = new ArrayList<>();

        museumList.add(new Museum(
                1,
                "St Fagans National Museum of History",
                R.drawable.m1,
                "40+ re-erected historical buildings, traditional crafts, live demonstrations.",
                1948,
                "Cardiff, Wales",
                51.4963,
                -3.2325
        ));

        museumList.add(new Museum(
                2,
                "National Slate Museum",
                R.drawable.m2,
                "Quarrymen's houses, slate-splitting demos, restored workshops.",
                1972,
                "Llanberis, Gwynedd",
                53.1267,
                -4.1183
        ));

        museumList.add(new Museum(
                3,
                "Big Pit National Coal Museum",
                R.drawable.m3,
                "Underground mine tours, mining tools, interactive coal industry displays.",
                1983,
                "Blaenavon, Torfaen",
                51.7803,
                -3.0702
        ));

        return museumList;
    }
}
