import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JPanel {
    Window w;
    float x,y;
    public JLabel coordinates = new JLabel("Click for find the value for the abcyss clicked");

    Field(Window w) {
        this.w=w;
        this.setPreferredSize(new Dimension(1500, 800));
        this.add(coordinates);
        coordinates.setBounds(680,50,140,20);
        repaint();

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                x=((((Actions.max_x-Actions.min_x)/1350))*(e.getX()-50)+Actions.min_x);
                y=(Method.Calculation(Window.final_list, x, Actions.h, Window.point_list.get(0).getX()));
                coordinates.setText("("+x+" ; "+y+")");
                if (Window.zoom!=0)
                    coordinates.setText("Move zoom out to get the coordinates");




            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);



        Graphics2D g2 = (Graphics2D)g;





        g2.setStroke(new BasicStroke(3));

            g2.setColor(Color.decode("#3498db"));



        //int[] mass = w.getMass();
        for (int i = 0; i <= 2698; i=i+2){
            g2.drawLine(Window.graphics_list[i]+50, Window.graphics_list[i+1]+60, Window.graphics_list[i+2]+50, Window.graphics_list[i+3]+60);

        }
        for (int i = 0; i < Window.points_counter; i=i+2){         //отрисовка точек
            g2.setColor(Color.decode("#e74c3c"));
            g2.setStroke(new BasicStroke(8));
            g2.drawLine(Window.entered_graphics_list[i]+50, Window.entered_graphics_list[i+1]+60, Window.entered_graphics_list[i]+50, Window.entered_graphics_list[i+1]+60);
        }


        if(Window.parent_counter==1) {
            for (int i = 0; i < 2698; i = i + 2) {         //отрисовка точек
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1));
                g2.drawLine(Window.parent_graph[i] + 50, Window.parent_graph[i + 1] + 60, Window.parent_graph[i] + 50, Window.parent_graph[i + 1] + 60);
            }
        }
    }

    public void Draw(){


        repaint();
    }

    public void Clear(){

        for (int i=0;i<2800;i++) {
            Window.graphics_list[i] = 0;
        }
        for (int i=0; i<200;i++)
            Window.entered_graphics_list[i] = 0;
    }

    public void ClearBlack() {

        for (int i = 0; i < 2800; i++) {
            Window.parent_graph[i] = 0;
        }
    }
}
