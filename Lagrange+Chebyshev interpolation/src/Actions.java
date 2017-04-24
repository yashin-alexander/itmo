import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;



public class Actions extends JPanel {


    JLabel status_message;
    JTextField first_field;
    JTextField second_field;
    JTextField add_field;
    JSlider slider = new JSlider(2, 100, 5);
    //JSlider number_of_points;slider.setMajorTickSpacing(2);



    public JButton refresh = new JButton("Refresh");
    public JButton start_interpolate = new JButton("Start Interpolate");
    public JButton minus = new JButton("-");
    public JButton plus = new JButton("+");
    public JButton add = new JButton("add");


    String functions[] = {
            "sin(x)", "sin^2(x)+sin^2(x)", "2-3x+4x^2-6x^3", "sin(x)-cos(x)", "16sin^3(x)", "Add button"};

    int status = 0;
    int howmuch = 0;
    int howmuchentered=0;


    public static double min_x;
    public static double min_y;
    public static double max_x;
    public static double max_y;
    public static double h = 0;
    Window w;
    Field f;

    Actions(Window w, Field f) {

        this.f = f;
        this.w = w;

        this.setPreferredSize(new Dimension(1500, 72));
        this.setBackground(Color.decode("#2ecc71"));


        status_message = new JLabel("Enter the borders, number of points, choose the function");
        first_field = new JTextField(10);
        second_field = new JTextField(10);
        add_field = new JTextField(10);
        //number_of_points = new JSlider(10);



        JComboBox comboBox = new JComboBox(functions);


        start_interpolate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (a.getActionCommand().equals("Start Interpolate")) {

                    refresh.doClick();
                    f.Clear();


                    float a_border=0;
                    float b_border=0;
                    try {
                        a_border = Float.valueOf(first_field.getText());
                        b_border = Float.valueOf(second_field.getText());
                    }
                    catch (NumberFormatException e){
                        status_message.setText("Input error");
                    }

                    if (a_border > b_border)
                        a_border = a_border + b_border - (b_border = a_border);

                    if (a_border == b_border)
                        status_message.setText("Input error");

                    Method.ChebyshevPolynomialValue(a_border,b_border,slider.getValue(),comboBox.getSelectedIndex());
                    Iterator<Point> iter = Window.point_list.iterator();

                    min_x = Window.point_list.get(0).getX();
                    max_x = Window.point_list.get(0).getX();
                    min_y = Window.point_list.get(0).getY();
                    max_y = Window.point_list.get(0).getY();

                    while (iter.hasNext()) {
                        Point point = iter.next();
                        double tmp_x = point.getX();
                        double tmp_y = point.getY();

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

                    Window.final_list = Method.MakeFunction(Window.point_list, b_border-a_border, a_border);


                    for (int i = 0; i <= 2700; i = i + 2) {
                        //System.out.printf("IUHFERF\n");
                        double tmp_x = Window.final_list.get(i).getX();
                        double tmp_y = Window.final_list.get(i).getY();


                        //System.out.printf("Total_value of %f = %f\n", tmp_x, tmp_y);

                        int graphics_x = (int)(Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350)));
                        int graphics_y = (int)(650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650)));

                        Window.graphics_list[i] = (graphics_x);
                        Window.graphics_list[i + 1] = (graphics_y);


                    }

                }

                Iterator<Point> iter = Window.point_list.iterator();        //вывод точек
                for(int i=0; iter.hasNext();i=i+2){
                    Point point = iter.next();
                    double tmp_x = point.getX();
                    double tmp_y = point.getY();

                    int graphics_x = (int)Math.round((tmp_x - min_x) / ((max_x - min_x) / 1350));
                    int graphics_y = (int)(650 - Math.round((tmp_y - min_y) / ((max_y - min_y) / 650)));
                    Window.entered_graphics_list[i]=(graphics_x);
                    Window.entered_graphics_list[i + 1] = (graphics_y);
                    Window.points_counter++;
                    Window.points_counter++;
                    System.out.printf("x y %f %f\n", tmp_x, tmp_y);
                }
                f.Draw();

            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.point_list.removeAll(Window.point_list);
                Window.final_list.removeAll(Window.final_list);
                Window.points_counter = 0;
                howmuchentered = 0;
                howmuch=0;

                f.Clear();
                f.Draw();
                f.ClearBlack();
                first_field.setEnabled(true);
                slider.setEnabled(true);
                comboBox.setEnabled(true);
                add.setEnabled(false);
                add_field.setEnabled(false);



                f.coordinates.setText("Click for find the value for the abcyss clicked");
                status_message.setText("Enter the borders, number of points, choose the function");
                status = 0;
                Window.zoom = 0;

            }

        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Decrease();
                f.Draw();
                Window.zoom++;
                for (int i = 0; i < 2698; i = i + 2) {
                    Window.parent_graph[i] = 0;
                    Window.parent_graph[i + 1] = 0;
                }
                Window.parent_counter = 0;
                plus.setEnabled(true);
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start_interpolate.doClick();
                for (int i = 0; i < Window.zoom - 1; i++) {
                    Decrease();
                }
                if (Window.zoom >= 1)
                    Window.zoom--;
                plus.setEnabled(false);
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedIndex()==5){

                    System.out.println("Hello");
                    refresh.doClick();

                    add.setEnabled(true);
                    add_field.setEnabled(true);

                    comboBox.setEnabled(false);
                    slider.setEnabled(false);

                    float a_border = Float.valueOf(first_field.getText());
                    float b_border = Float.valueOf(second_field.getText());

                    if (a_border == b_border)
                        status_message.setText("Input error");
                        ///refresh.doClick();


                    howmuch =  slider.getValue();


                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(howmuchentered<howmuch){
                    Window.entered_points[howmuchentered]=Float.valueOf(add_field.getText());
                    howmuchentered++;}
                if (howmuchentered==howmuch){
                    start_interpolate.doClick();
                    add.setEnabled(false);
                    add_field.setEnabled(false);
                }

            }
        });


        slider.setMajorTickSpacing(4);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        this.setLayout(new FlowLayout());
        this.add(first_field);
        this.add(second_field);

        this.add(slider);
        this.add(status_message);

        this.add(start_interpolate);
        this.add(refresh);
        this.add(minus);
        this.add(plus);
        this.add(comboBox);

        this.add(add_field);
        this.add(add);

        add_field.setBounds(1150,30,30,30);
        add.setBounds(1065,30,70,30);
        add_field.setEnabled(false);
        add.setEnabled(false);

        first_field.setBounds(50, 5, 80, 20);
        second_field.setBounds(140, 5, 80, 20);
        status_message.setBounds(230, 5, 450, 20);
        slider.setBounds(50, 28, 1000, 42);

        start_interpolate.setBounds(650, 5, 180, 20);
        comboBox.setBounds(850, 5, 250, 20);
        refresh.setBounds(1380, 5, 100, 56);
        minus.setBounds(1200, 10, 45, 35);
        plus.setBounds(1250, 10, 45, 35);

        this.setVisible(true);
        this.setLayout(null);

    }

    public void Decrease() {
        for (int i = 0; i <= 2700; i = i + 2) {
            Window.graphics_list[i] = (Window.graphics_list[i] / 2) + 300;
            Window.graphics_list[i + 1] = (Window.graphics_list[i + 1] / 2) + 150;
        }
        for (int i = 0; i < Window.points_counter; i = i + 2) {
            Window.entered_graphics_list[i] = (Window.entered_graphics_list[i] / 2) + 300;
            Window.entered_graphics_list[i + 1] = (Window.entered_graphics_list[i + 1] / 2) + 150;
        }

    }
}


