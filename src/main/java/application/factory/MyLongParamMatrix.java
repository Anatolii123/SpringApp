package application.factory;

import java.util.InputMismatchException;

public class MyLongParamMatrix extends ParamMatrix<Long> {

    public MyNumeric<Long>[][] createContent(int a, int b, String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.print("Введите количество строк: ");
//        int a = in.nextInt();
//        System.out.print("Введите количество столбцов: ");
//        int b = in.nextInt();
        int c = Integer.parseInt(args[a]);
        int d = Integer.parseInt(args[b]);

        MyNumeric<Long>[][] result = new MyLong[c][d];
        this.setA(c);
        this.setB(d);

        for(int i = 0; i < c; i++) {
            for(int j = 0; j < d; j++) {
                while (true) {
                    try {
//                        Scanner in2 = new Scanner(System.in);
//                        System.out.print("Введите число: ");
//                        result[i][j] = new MyLong(in2.nextLong());
                        result[i][j] = new MyLong(Long.parseLong(args[c*i+j+2+a]));
                    } catch (InputMismatchException e) {
                        System.out.println("Try once more");
                        j--;
                        continue;
                    }
                    finally {
                        break;
                    }
                }
            }
            System.out.println();
        }

        for (int i = 0; i < c; i++) {
            for(int j = 0; j < d; j++){
                System.out.print(result[i][j].toString() + "\t");
            }
            System.out.println();
        }
        System.out.println();
        this.setContent(result);
        return result;
    }
}
