package ManagerRoot.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Process {
    public static ArrayList sort(ArrayList<LocalItems> arrayList, Sort sort){
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(arrayList);

        switch (sort){
            case Name:
                Collections.sort(localArrayList , new SortOption());

                break;
            case Size:
                break;
        }

        return localArrayList;
    }

    public static ArrayList reversList(ArrayList arrayList){
        ArrayList newArr = new ArrayList();
        for(int i=arrayList.size() - 1; i >= 0; i--){
            newArr.add(arrayList.get(i));
        }
        return newArr;
    }



}

class SortOption implements Comparator<LocalItems>{

    @Override
    public int compare(LocalItems o1, LocalItems o2) {

        int name = o1.path.toAbsolutePath().getFileName().compareTo(o2.path.toAbsolutePath().getFileName());
        int type = o1.type.compareTo(o2.type);

        if(type == 0){
            return (name==0)?type:name;
        }else {
            return type;
        }
    }


}
