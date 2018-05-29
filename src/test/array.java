package test;

public class array {
        public static void main(String[] argv) {
            int[] x; // x is a reference to int[]
            x = new int[10]; // 利用new指令產生物件
            for (int i = 0; i < x.length; i++) { // 此物件有一個object variable length，用以紀錄該陣列的長度
                x[i] = i;
                System.out.println(x[i]);
            }
        }
    }
