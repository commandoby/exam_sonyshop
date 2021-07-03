package com.commandoby.sonyShop.controllers.search;

import com.commandoby.sonyShop.dao.domain.ShopObject;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearch<T extends ShopObject> {
    private Logger log = Logger.getLogger(getClass());

    public List<T> searchName(String partOfName, List<T> list) {
        List<T> filterList = new ArrayList<>();
        for (T e : list)
            if (e.getName().toLowerCase().contains(partOfName.trim().toLowerCase()))
                filterList.add(e);

        if (filterList.isEmpty()) log.warn("Search for the keyword \"" + partOfName.trim() + "\" failed.");

        return filterList;
    }
}
