package com.mmall.concurrency.syncContainer;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

@Slf4j
@NotThreadSafe
public class VectorExample3 {

    // foreach
    private static void test1(Vector<Integer> v1) {
        // 遍历的时候不去做更新，在循环中做标记，循环结束后，再去删除
        for (Integer i : v1) {
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    // iterator
    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator = v1.iterator();
        while(iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    // for
    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        // java.util.ConcurrentModificationException
        test1(vector);

        // java.util.ConcurrentModificationException
//        test2(vector);

//        test3(vector);
    }
}
