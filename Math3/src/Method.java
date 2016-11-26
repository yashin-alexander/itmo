
import java.util.ArrayList;
import java.util.Iterator;

public class Method {

    public static ArrayList MakeFunction(ArrayList<Point> point_list) {
        float tmp_y = 0;
        int counter = 0;

        Iterator<Point> iter = point_list.iterator();
        Iterator<Point> next_iter = point_list.iterator();

        ArrayList<Float> value_list = new ArrayList<>();        //лист разностей
        ArrayList<Float> final_list = new ArrayList<>();

        Point trash = next_iter.next(); //для хранения верхнего элемента
        float q = trash.getY();         //забрали верхний элемент

        final_list.add(new Float(q)); //вернули на верх 1 элемент

        while (next_iter.hasNext()) {
            Point point = iter.next();
            Point next_point = next_iter.next();
            tmp_y = point.getY() - next_point.getY();
            value_list.add(new Float(tmp_y));
            counter++;                                  //запись в лист разностей
        }

        while (counter != 0) {
            int pointer = 0;
            tmp_y = value_list.get(pointer++);
            final_list.add(new Float(tmp_y));

            for (int i = 0; i < counter - 1; i++) {
                Float next_value = value_list.get(pointer);
                Float value = value_list.get(pointer - 1);

                tmp_y = value - next_value;
                value_list.remove(pointer - 1);
                value_list.add(pointer - 1, new Float(tmp_y));
                pointer++;
            }
            counter--;
        }
        return final_list;
    }

    public static float Calculation(ArrayList<Float> final_list, float tmp_x, float h, float first_abcyss) {

        float q = (tmp_x-first_abcyss) / h;
        int sign =1;
        if (tmp_x>=first_abcyss)
            sign=-1;


        float sum = final_list.get(0);



        for (int i = 1; i <= final_list.size()-1; i++) {
           int c;
           float tmp = 1;
           c=0;
           while (c<i) {
               tmp = tmp * (q - c)*sign;              //произведение вида n*(n-1)*(n-2)...
               c++;

           }
            sum=(final_list.get(i))*(tmp/factorial(c))+sum;
        }
        return sum;
    }


    public static int factorial(int num) {
        int fact=1;
        for (; num > 0; fact *= num--);
        return fact;
    }
}