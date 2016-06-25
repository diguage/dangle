package com.diguage.dangle.studies.guava.fn;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author diguage
 * @since 2016/6/17.
 */
public class DateFormatFunction implements Function<Date, String> {
    //    @Override
    public String apply(Date input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        return dateFormat.format(input);
    }
}

class DatePredicate implements Predicate<Date> {
    public boolean apply(Date input) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(input);

        return instance.get(Calendar.YEAR) > 1902;
    }
}

class FunctionMain {
    public static void main(String[] args) {
        Function<Date, String> function = new Function<Date, String>() {
            //    @Override
            public String apply(Date input) {
                return new
                        SimpleDateFormat("dd/mm/yyyy").format(input);
            }
        };
        List<Date> dates = ImmutableList.of(new Date(1, 2, 3), new Date(2, 2, 3), new Date(3, 2, 3));

// 转化
        System.out.println("--DateFormatFunction------------------------");
        Function dff = new DateFormatFunction();
        Iterator<String> dateString = Iterators.transform(dates.iterator(), dff);
        ArrayList<String> arrayList = Lists.newArrayList(dateString);
        for (String s : arrayList) {
            System.out.println(s);
        }


// 转化2
        System.out.println("--Function------------------------");
        Iterator<String> dateString2 = Iterators.transform(dates.iterator(), function);
        ArrayList<String> arrayList2 = Lists.newArrayList(dateString2);
        for (String s : arrayList2) {
            System.out.println(s);
        }

// 选择
        System.out.println("--DatePredicate------------------------");
        DatePredicate predicate = new DatePredicate();
        ArrayList<Date> filterList = Lists.newArrayList(Iterators.filter(dates.iterator(), predicate));
        for (Date date : filterList) {
            System.out.println(date.toString());
        }
//        是否支持串联 并集或交集?

        System.out.println("--FluentIterable------------------------");
        Set<String> fift = Sets.newHashSet(FluentIterable.from(dates).filter(predicate).transform(dff));
        for (String s : fift) {
            System.out.println(s);
        }

        System.out.println("--Functions------------------------");
        Map<Integer, Integer> map = ImmutableMap.of(1, 1, 2, 2, 3, 3);
        Function<Integer, Integer> mapFunction = Functions.forMap(map, 0);
        Map<Integer, Integer> transformValuesMap = Maps.transformValues(map, mapFunction);
        System.out.println(transformValuesMap.get(1));
        System.out.println(transformValuesMap.get(4));
//        Collections2.filter()
    }
}
