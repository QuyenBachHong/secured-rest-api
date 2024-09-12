package me.quyen.utils;

import org.springframework.data.domain.Sort;

import java.util.*;

public class PaginationUtil {
    /**
     * direction value is `ASC` or `DESC`
     */
    public static Sort.Direction sortDirection(String direction){
        return Optional.ofNullable(direction)
                .stream().map(dir -> {
                    dir = dir.toLowerCase().trim();
                    if(Objects.equals(dir,"asc")){
                        return Sort.Direction.ASC;
                    }else if(Objects.equals(dir,"desc")){
                        return Sort.Direction.DESC;
                    }else return Sort.Direction.ASC;
                }).findFirst().orElse(Sort.Direction.ASC);
    }


    public static List<Sort.Order> sortOrders(String[] sort){
        List<Sort.Order> orders = new ArrayList<>();
        if(sort[0].contains(",")){
            Arrays.stream(sort)
                    .map(srt -> srt.trim().replaceAll("\\s*","").split(","))
                    .forEach(spl -> orders.add(new Sort.Order(PaginationUtil.sortDirection(spl[1]),spl[0])));
        }
        return orders;
    }

    //@@DEFAULT: @RequestParam(defaultValue = "id,asc") String[] sort
    public static Sort.Order idSortedOrder(String[] sort){
        return new Sort.Order(PaginationUtil.sortDirection(sort[1]), sort[0]);
    }
}
