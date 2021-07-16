package com.commandoby.sonyShop.search;

import com.commandoby.sonyShop.classies.ShopObject;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Search<E extends ShopObject> {
    private Logger log = Logger.getLogger(getClass());

    public List<E> searchName(String partOfName, List<E> list) {
        Pattern pattern = Pattern.compile(".*" + partOfName.trim().toLowerCase() + ".*");
        List<E> filterList = new ArrayList<>();
        for (E e : list) {
            if (e.getName().toLowerCase().matches(pattern.pattern())) filterList.add(e);
        }

        if (filterList.isEmpty()) log.warn("Search for the keyword \"" + partOfName.trim() + "\" failed.");

        return filterList;
    }
}
