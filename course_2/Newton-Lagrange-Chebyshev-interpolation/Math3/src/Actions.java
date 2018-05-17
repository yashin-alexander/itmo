import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;



public class Actions extends JPanel {

    public JButton coordinates = new JButton("Find that one");

    JLabel status_message;
    JTextField first_field;
    JTextField second_field;
    JTextField third_field;
    public JButton refresh = new JButton("Refresh");
    public JButton start_interpolate = new JButton("Start Interpolate");
    public JButton minus = new JButton("-");
    public JButton plus = new JButton("+");

    JTextField find_some_x;
    JLabel y_was_found;

    int functionSelector;



    String functions[] = {
        " ", "1)2-3x+4x^2-6x^3 (h=0.05 [1;1.25])", "2)2-3x+4x^2-6x^3 (h=1 [-4;4])", "3)2-3x+4x^2-6x^3 (h=10 [-40;40])", "4)Wrong 1","5)Wrong 2","6)Wrong 3","Clear bluelines"};

    JComboBox comboBox = new JComboBox(functions);

    int status=0;



    public static float min_x;
    public static float min_y;
    public static float max_x;
    public static float max_y;
    public static float h = 0;
    Window w;
    Field f;
    Actions(Window w,Field f) {

        this.f=f;
        this.w=w;

        this.setPreferredSize(new Dimension(1500, 72));
        this.setBackground(Color.decode("#2ecc71"));


        status_message = new JLabel("Enter the numbers X, Y and step");
        first_field = new JTextField(10);
        second_field = new JTextField(10);
        third_field = new JTextField(5);

        find_some_x = new JTextField(5);
        y_was_found = new JLabel("Enter X for take interpolation value");
        find_some_x.setEnabled(false);

        JButton enter = new JButton("Enter the point");

        JComboBox comboBox = new JComboBox(functions);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getActionCommand().equals("Enter the point")) {
                    int error_code = 0;
                    float x = 0;
                    float y = 0;

                    if (status == 0) {
                        first_field.setEnabled(false);
                        third_field.setEnabled(false);
                        coordinates.setEnabled(true);
                        find_some_x.setEnabled(true);
                    }
                    try {
                        x = Float.valueOf(first_field.getText());
                    } catch (NumberFormatException e) {
                        error_code = 1;
                    }
                    try {
                        y = Float.valueOf(second_field.getText());
                    } catch (NumberFormatException e) {
                        error_code = 1;
                    }
                    try {
                        h = Float.valueOf(third_field.getText());
                    } catch (NumberFormatException e) {
                        error_code = 1;
                    }
                    if (error_code == 0&&h>=0) {
                        x = x + h * status;
                        status_message.setText("Last X: " + x + "   Y: " + y);
                        Window.point_list.add(new Point(x, y));
                        Window.points_counter=Window.points_counter+2;


                    } else {
                        status_message.setText("Uncorrect values");

                        if (status == 0 && (x == 0 || y == 0 || h == 0)) {
                            first_field.setEnabled(true);
                            third_field.setEnabled(true);
                            coordinates.setEnabled(false);
                            find_some_x.setEnabled(false);
                            //status=-1;
                        }
                        status--;       //в случае неверного ввода вернуть счетчик для h на место
                    }
                    status++;
                }
                if (Window.points_counter==26){
                    enter.setEnabled(false);
                    start_interpolate.doClick();}
            }
        });

        start_interpolate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (a.getActionCommand().equals("Start Interpolate")) {

                    f.Clear();
                    Iterator<Point> iter = Window.point_list.iterator();

                    min_x = Window.point_list.get(0).getX();
                    max_x = Window.point_list.get(0).getX();
                    min_y = Window.point_list.get(0).getY();
                    max_y = Window.point_list.get(0).getY();

                    while (iter.hasNext()) {
                        Point point = iter.next();
                        float tmp_x = point.getX();
                        float tmp_y = point.getY();

                        if (tmp_x < min_x)
                            min_x = tmp_x;
                        if (tmp_x > max_x)
                            max_x = tmp_x;
                        if (tmp_y < min_y)
                            min_y = tmp_y;
                        if (tmp_y > max_y)
                            max_y = tmp_y;
                    }
                    System.out.printf("minx %f \n", min_x);
                    System.out.printf("maxx %f \n", max_x);
                    System.out.printf("miny %f \n", min_y);
                    System.out.printf("maxy %f \n", max_y);

                    Window.final_list = Method.MakeFunction(Window.point_list);
                    for (int i = 0; i <= 2700; i=i+2) {
                        float tmp_x = min_x + ((max_x - min_x) / 1350) * i/2;
                        float tmp_y = Method.Calculation(Window.final_list, tmp_x, h, Window.point_list.get(0).getX()); //получили значения ф-ции



                        System.out.printf("Total_value of %f = %f\n",tmp_x,tmp_y);

                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.graphics_list[i] = (graphics_x);
                        Window.graphics_list[i + 1] = (graphics_y);


                    }

                }
                Iterator<Point> iter = Window.point_list.iterator();        //вывод точек
                for(int i=0; iter.hasNext();i=i+2){
                    Point point = iter.next();
                    float tmp_x = point.getX();
                    float tmp_y = point.getY();

                    int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                    int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));
                    Window.entered_graphics_list[i]=(graphics_x);
                    Window.entered_graphics_list[i + 1] = (graphics_y);

                }
                f.Draw();

            }
        });

        coordinates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int error_code=0;
                float tmp_x=0;

                try {
                    tmp_x = Float.valueOf(find_some_x.getText());
                } catch (NumberFormatException e) {
                    error_code = 1;
                }
                if (error_code==0) {
                    ArrayList<Float> final_list = Method.MakeFunction(Window.point_list);
                    y_was_found.setText(Float.toString(Method.Calculation(final_list, tmp_x, h, Window.point_list.get(0).getX())));
                }
                else
                    y_was_found.setText("Uncorrect values");
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.point_list.removeAll(Window.point_list);
                Window.final_list.removeAll(Window.final_list);
                Window.points_counter=0;
                f.Clear();
                f.Draw();
                f.ClearBlack();
                first_field.setEnabled(true);
                third_field.setEnabled(true);
                coordinates.setEnabled(false);
                find_some_x.setEnabled(false);
                enter.setEnabled(true);


                y_was_found.setText("Find that one");
                f.coordinates.setText("Click for find the value for the abcyss clicked");
                status_message.setText("Enter the numbers X, Y and step");
                status = 0;
                Window.zoom = 0;

            }

        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.zoom=0;
                functionSelector = comboBox.getSelectedIndex();
                System.out.println(functionSelector);

                if (functionSelector == 1) {
                    refresh.doClick();
                    Window.parent_counter=1;

                    min_x=1;
                    max_x=1.25f;
                    min_y=-7.21875f;
                    max_y=-3;
                    for(int i=0;i<2700;i=i+2) {

                        float tmp_x = 1 + (0.0125f*i/135f);
                        float tmp_y = 2 - (3*tmp_x) + (4*tmp_x*tmp_x) - (6*tmp_x*tmp_x*tmp_x);

                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.parent_graph[i] = (graphics_x);
                        Window.parent_graph[i + 1] = (graphics_y);
                    }

                    Window.point_list.add(new Point(1, -3));
                    Window.point_list.add(new Point(1.05f, -3.68575f));
                    Window.point_list.add(new Point(1.1f, -4.446f));
                    Window.point_list.add(new Point(1.15f, -5.28525f));
                    Window.point_list.add(new Point(1.2f, -6.208f));
                    Window.point_list.add(new Point(1.25f, -7.21875f));

                    Window.points_counter = 12;
                    h = 0.05f;
                    System.out.printf("MININ%f\n",min_y);
                    start_interpolate.doClick();
                    }


                if(functionSelector==2){
                    refresh.doClick();
                    Window.parent_counter=1;

                    min_x=-4;
                    max_x=4;
                    min_y=-330;
                    max_y=462;
                    for(int i=0;i<2700;i=i+2) {

                        float tmp_x = -4 + (0.4f*i / 135);
                        float tmp_y = 2 - (3*tmp_x) + (4*tmp_x*tmp_x) - (6*tmp_x*tmp_x*tmp_x);

                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.parent_graph[i] = (graphics_x);
                        Window.parent_graph[i + 1] = (graphics_y);
                    }

                    Window.point_list.add(new Point(-4, 462));
                    Window.point_list.add(new Point(-3, 209));
                    Window.point_list.add(new Point(-2, 72));
                    Window.point_list.add(new Point(-1, 15));
                    Window.point_list.add(new Point(0, 2));
                    Window.point_list.add(new Point(1, -3));
                    Window.point_list.add(new Point(2, -36));
                    Window.point_list.add(new Point(3, -133));
                    Window.point_list.add(new Point(4, -330));
                    Window.points_counter = 18;
                    h = 1;

                    start_interpolate.doClick();

                    }

                if(functionSelector==3){
                    refresh.doClick();
                    Window.parent_counter=1;

                    min_x=-40;
                    max_x=40;
                    min_y=-377718;
                    max_y=390522;
                    for(int i=0;i<2700;i=i+2) {

                        float tmp_x = -40 + (4f * i / 135f);
                        float tmp_y = 2 - (3*tmp_x) + (4*tmp_x*tmp_x) - (6*tmp_x*tmp_x*tmp_x);
                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.parent_graph[i] = (graphics_x);
                        Window.parent_graph[i + 1] = (graphics_y);
                    }

                    Window.point_list.add(new Point(-40, 390522));
                    Window.point_list.add(new Point(-30, 165692));
                    Window.point_list.add(new Point(-20, 49662));
                    Window.point_list.add(new Point(-10, 6432));
                    Window.point_list.add(new Point(0, 2));
                    Window.point_list.add(new Point(10, -5628));
                    Window.point_list.add(new Point(20, -46458));
                    Window.point_list.add(new Point(30, -158488));
                    Window.point_list.add(new Point(40, -377718));
                    Window.points_counter = 18;
                    h = 10;

                    start_interpolate.doClick();

                }
                if (functionSelector == 4) {
                    refresh.doClick();
                    Window.parent_counter=1;

                    min_x=1;
                    max_x=1.25f;
                    min_y=-7.21875f;
                    max_y=-3;
                    for(int i=0;i<2700;i=i+2) {

                        float tmp_x = 1 + (0.0125f*i /135f);
                        float tmp_y = 2 - (3*tmp_x) + (4*tmp_x*tmp_x) - (6*tmp_x*tmp_x*tmp_x);

                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.parent_graph[i] = (graphics_x);
                        Window.parent_graph[i + 1] = (graphics_y);
                    }

                    Window.point_list.add(new Point(1, -3));
                    Window.point_list.add(new Point(1.05f, -3.68575f));
                    Window.point_list.add(new Point(1.1f, -4.446f));
                    Window.point_list.add(new Point(1.15f, -5.28525f));
                    Window.point_list.add(new Point(1.2f, -7));
                    Window.point_list.add(new Point(1.25f, -7.21875f));

                    Window.points_counter = 12;
                    h = 0.05f;

                    start_interpolate.doClick();
                }

                if(functionSelector==5){
                    refresh.doClick();
                    Window.parent_counter=1;

                    min_x=-4;
                    max_x=4;
                    min_y=-330;
                    max_y=462;
                    for(int i=0;i<2700;i=i+2) {

                        float tmp_x = -4 + (0.4f*i / 135);
                        float tmp_y = 2 - (3*tmp_x) + (4*tmp_x*tmp_x) - (6*tmp_x*tmp_x*tmp_x);

                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.parent_graph[i] = (graphics_x);
                        Window.parent_graph[i + 1] = (graphics_y);
                    }

                    Window.point_list.add(new Point(-4, 462));
                    Window.point_list.add(new Point(-3, 209));
                    Window.point_list.add(new Point(-2, 72));
                    Window.point_list.add(new Point(-1, 15));
                    Window.point_list.add(new Point(0, 2));
                    Window.point_list.add(new Point(1, -3));
                    Window.point_list.add(new Point(2, -55));
                    Window.point_list.add(new Point(3, -133));
                    Window.point_list.add(new Point(4, -330));
                    Window.points_counter = 18;
                    h = 1;

                    start_interpolate.doClick();

                }
                if(functionSelector==6){
                    refresh.doClick();
                    Window.parent_counter=1;

                    min_x=-40;
                    max_x=40;
                    min_y=-377718;
                    max_y=390522;
                    for(int i=0;i<2700;i=i+2) {

                        float tmp_x = -40 + (4f * i / 135f);
                        float tmp_y = 2 - (3*tmp_x) + (4*tmp_x*tmp_x) - (6*tmp_x*tmp_x*tmp_x);

                        int graphics_x = Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                        int graphics_y = 650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650));

                        Window.parent_graph[i] = (graphics_x);
                        Window.parent_graph[i + 1] = (graphics_y);
                    }

                    Window.point_list.add(new Point(-40, 390522));
                    Window.point_list.add(new Point(-30, 165692));
                    Window.point_list.add(new Point(-20, 49662));
                    Window.point_list.add(new Point(-10, 6432));
                    Window.point_list.add(new Point(0, 2));
                    Window.point_list.add(new Point(10, -5628));
                    Window.point_list.add(new Point(20, -16458));
                    Window.point_list.add(new Point(30, -158488));
                    Window.point_list.add(new Point(40, -377718));
                    Window.points_counter = 18;
                    h = 10;

                    start_interpolate.doClick();

                }
                if (functionSelector == 7){
                    Window.parent_counter=0;
                        Window.point_list.removeAll(Window.point_list);
                        Window.final_list.removeAll(Window.final_list);
                        Window.points_counter=0;
                        f.Clear();
                        f.Draw();
                        first_field.setEnabled(true);
                        third_field.setEnabled(true);
                        coordinates.setEnabled(false);
                        find_some_x.setEnabled(false);
                        enter.setEnabled(true);
                        f.coordinates.setText("Click for find the value for the abcyss clicked");
                        status = 0;
                    Window.parent_counter=1;
                }

                enter.setEnabled(false);
                find_some_x.setEnabled(true);
                coordinates.setEnabled(true);

            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrease();
                f.Draw();
                Window.zoom++;
                for (int i = 0; i < 2698; i = i + 2) {
                    Window.parent_graph[i] = 0;
                    Window.parent_graph[i+1] = 0;
                }
                Window.parent_counter=0;
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start_interpolate.doClick();
                for (int i = 0; i < Window.zoom-1; i++) {
                    decrease();
                }
                if (Window.zoom>=1)
                Window.zoom--;
            }
        });


        coordinates.setEnabled(false);



        this.setLayout(new FlowLayout());
        this.add(first_field);
        this.add(second_field);
        this.add(status_message);
        this.add(third_field);
        this.add(enter);
        this.add(start_interpolate);
        this.add(find_some_x);
        this.add(y_was_found);
        this.add(coordinates);
        this.add(refresh);
        this.add(minus);
        this.add(plus);
        this.add(comboBox);

        first_field.setBounds(50, 5, 80, 20);
        second_field.setBounds(140,5,80,20);
        status_message.setBounds(230,5,250,20);
        third_field.setBounds(470,5,50,20);
        enter.setBounds(550, 5, 145, 20);
        start_interpolate.setBounds(710,5,155,20);
        coordinates.setBounds(880,5,150,20);
        find_some_x.setBounds(1050,5,60,20);
        y_was_found.setBounds(1120,5,250,20);
        refresh.setBounds(1380,5,100,56);
        minus.setBounds(1200,35,40,20);
        plus.setBounds(1250,35,45,20);
        comboBox.setBounds(50, 40, 250, 20 );

        this.setVisible(true);
        this.setLayout(null);

    }

    public void decrease(){
        for (int i = 0; i <= 2700; i=i+2) {
            Window.graphics_list[i]= (Window.graphics_list[i]/2)+300;
            Window.graphics_list[i+1]= (Window.graphics_list[i+1]/2)+150;
        }
        for (int i = 0; i < Window.points_counter; i=i+2) {
            Window.entered_graphics_list[i]= (Window.entered_graphics_list[i]/2)+300;
            Window.entered_graphics_list[i+1]= (Window.entered_graphics_list[i+1]/2)+150;
        }

        }
    }


