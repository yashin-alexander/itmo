import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Window {

    public JPanel menu;
    public Field field;
    public static ArrayList<Point> point_list = new ArrayList<>();
    public static ArrayList<Float> final_list = new ArrayList<>();
    public static int[] graphics_list = new int[2800];
    public static int[] entered_graphics_list = new int[200];

    public static int points_counter=0;
    public static int[] parent_graph= new int[2800];
    public static int parent_counter=0;
    public static int color=0;
    public static int zoom= 0;
    public int[] getMass(){
        return graphics_list;
    }

    Window() {
        makeGUI();
    }

    private void makeGUI() {
        JFrame total = new JFrame("Interpolation");

        total.getContentPane().setLayout(new BorderLayout());
        total.setSize(1500,810);
        total.setResizable(false);
        total.setVisible(true);
        total.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        field = new Field(this);
        total.add(field,BorderLayout.CENTER);
        menu = new Actions(this,field);
        total.add(menu,BorderLayout.NORTH);
    }
}