import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame{
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Main(){
        super("Main Menu");
        setLayout(null);
        JLabel label = new JLabel("Main Menu");
        JButton questButton = new JButton("Quests");
        JButton eikonButton = new JButton("Eikons");
        
        label.setBounds(850, 50, 200, 50);

        questButton.setSize(200, 400);
        questButton.setBounds(200, 350, questButton.getWidth(), questButton.getHeight());

        questButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quest();
                dispose();
             }
          });
          eikonButton.setSize(200, 400);
          eikonButton.setBounds(1300, 350, eikonButton.getWidth(), eikonButton.getHeight());

          eikonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new EikonList();
                dispose();
            }
          });

          add(label);
          add(questButton);
          add(eikonButton);

          setBounds(0,0, screenSize.width, screenSize.height);
          setVisible(true);
          
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
        





    /* 
       public static void main(String[] args) {


      JPanel panel = new JPanel();
      frame.getContentPane();
      JButton button = new JButton("Eikons");
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new EikonList("Eikons");
            frame.dispose(); // Optional: Close the first frame
        }
    });
      button.setSize(200, 400);
      button.setBounds(1300, 350, button.getWidth(), button.getHeight());
      panel.setLayout(null);
      panel.add(button);
      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setSize(H, W);
      frame.setVisible(true);
   }
   */
}
