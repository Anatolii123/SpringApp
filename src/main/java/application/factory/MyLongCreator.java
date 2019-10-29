package application.factory;

import java.util.Scanner;

public class MyLongCreator extends ContentCreator {

    protected Operations[][] create(int a, int b) {
        Operations[][] result = new MyLong[a][b];
        Scanner in = new Scanner(System.in);
        for(int i = 0; i < a; i++) {
            for(int j = 0; j < b; j++) {
                System.out.print("Введите число: ");
                result[i][j] = new MyLong(in.nextLong());
            }
            System.out.println();
        }
        return result;
    }
}
