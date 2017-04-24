import java.util.ArrayList;

public class Method {
    Actions a;
    public static ArrayList MakeFunction(ArrayList<Point> point_list, double size, double a_border){
        ArrayList<Point> final_list = new ArrayList<>();

        for(int i=0; i<=2700; i++){
            double current_part = a_border+(i*size/1350.0);

            double calc_polynomial = 0;
            for (int j = 0; j<point_list.size();j++) {
                double polynomial = 1;
                for (int n = 0; n < point_list.size(); n++) {

                    if (j != n) {
                        polynomial *= ((current_part-point_list.get(n).getX())/(point_list.get(j).getX() -point_list.get(n).getX()));
                    }
                }
                calc_polynomial+=(polynomial * (point_list.get(j).getY()));
            }
            final_list.add(new Point(current_part, calc_polynomial));
            //System.out.printf("Final Point %f %f \n",current_part,calc_polynomial);
        }
        return final_list;
    }


    public static void ChebyshevPolynomialValue(float a_border, float b_border, int number_of_points, int function_number){
        double some_x_coordinate;

        for(int current_number=0; current_number<number_of_points;current_number++) {

            some_x_coordinate = ((a_border + b_border) / 2.0) + ((b_border - a_border) / 2.0)*Math.cos(((2.0 * current_number + 1.0)*3.14159265) / (2.0 * number_of_points));
            MakePoint(function_number,some_x_coordinate,current_number);
        }
        System.out.println();
    }

    public static void MakePoint(int function_number,double some_x_coordinate, int current_number){
        switch (function_number) {
            case 0:
                Window.point_list.add(new Point(some_x_coordinate,Math.sin(some_x_coordinate)));
                System.out.printf("%f %f\n",some_x_coordinate,Math.sin(some_x_coordinate));
                break;
            case 1:
                Window.point_list.add(new Point(some_x_coordinate,Math.sin(some_x_coordinate)*Math.sin(some_x_coordinate)));
                System.out.printf("%f %f\n",some_x_coordinate,Math.sin(some_x_coordinate)*Math.sin(some_x_coordinate));
                break;
            case 2:
                Window.point_list.add(new Point(some_x_coordinate,2-3*some_x_coordinate+4*some_x_coordinate*some_x_coordinate-6*some_x_coordinate*some_x_coordinate*some_x_coordinate));
                System.out.printf("%f %f\n",some_x_coordinate,2-3*some_x_coordinate+4*some_x_coordinate*some_x_coordinate-6*some_x_coordinate*some_x_coordinate*some_x_coordinate);
                break;
            case 3:
                Window.point_list.add(new Point(some_x_coordinate,Math.sin(some_x_coordinate)-Math.cos(some_x_coordinate)));
                System.out.printf("%f %f\n",some_x_coordinate,Math.sin(some_x_coordinate)-Math.cos(some_x_coordinate));
                break;
            case 4:
                Window.point_list.add(new Point(some_x_coordinate,16*Math.sin(some_x_coordinate)*Math.sin(some_x_coordinate)*Math.sin(some_x_coordinate)));
                System.out.printf("%f %f\n",some_x_coordinate,16*Math.sin(some_x_coordinate)*Math.sin(some_x_coordinate)*Math.sin(some_x_coordinate));
                break;
            case 5:
                Window.point_list.add(new Point(some_x_coordinate, Window.entered_points[current_number]));
                break;

        }
    }
}