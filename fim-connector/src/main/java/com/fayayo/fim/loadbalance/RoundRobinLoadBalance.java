
package com.fayayo.fim.loadbalance;

import com.fayayo.fim.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//轮训
public class RoundRobinLoadBalance<T>{

    private static AtomicInteger idx = new AtomicInteger(0);


    public T doSelect(List<T> urls) {

        int index = getNextNonNegative();
        for (int i = 0; i < urls.size(); i++) {
            T ref = urls.get((i + index) % urls.size());
            return ref;
        }
        return null;
    }

    // get non-negative int
    private int getNextNonNegative() {
        return MathUtil.getNonNegative(idx.incrementAndGet());
    }


    public static void main(String[] args) {

        List<Integer>list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);

        RoundRobinLoadBalance<Integer>balance=new RoundRobinLoadBalance<>();

        System.out.println(balance.doSelect(list));

        RoundRobinLoadBalance<Integer>balance1=new RoundRobinLoadBalance<>();

        System.out.println(balance1.doSelect(list));

        RoundRobinLoadBalance<Integer>balance2=new RoundRobinLoadBalance<>();

        System.out.println(balance2.doSelect(list));

    }

}
