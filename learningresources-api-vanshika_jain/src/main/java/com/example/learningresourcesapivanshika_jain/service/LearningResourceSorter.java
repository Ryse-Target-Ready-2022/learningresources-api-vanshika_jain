package com.example.learningresourcesapivanshika_jain.service;

import java.util.*;
import com.example.learningresourcesapivanshika_jain.entity.*;

public class LearningResourceSorter implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        LearningResource l1=(LearningResource)o1;
        LearningResource l2=(LearningResource)o2;
        double d1=(l1.getSellingPrice() - l1.getCostPrice())/l1.getSellingPrice();
        double d2=(l2.getSellingPrice() - l2.getCostPrice())/l2.getSellingPrice();
        double r=d2-d1;
        int v=(int)r;
        return v;
    }
}
