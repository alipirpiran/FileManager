package ManagerRoot;
import ManagerRoot.UI.ButtonHandle;

import java.util.ArrayList;

public class Datas {
    private static ArrayList dirs = new ArrayList();


    public static void AddDir(ButtonHandle buttonHandle){
        dirs.add(buttonHandle);
    }

    public static ArrayList getDirs(){
        return dirs;
    }
}
