import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


class EikonList extends JFrame{

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public EikonList(){
        super("Eikons");
        setLayout(null);
        JLabel label = new JLabel("Eikon List");
        JButton mainButton = new JButton("Back to Main Menu");
        JButton ranButton = new JButton("Randomize Eikons");

        label.setBounds(850, 50, 200, 50);

        mainButton.setSize(200, 400);
        mainButton.setBounds(200, 350, mainButton.getWidth(), mainButton.getHeight());

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
             }
          });

        ranButton.setSize(200, 400);
        ranButton.setBounds(1300, 350, ranButton.getWidth(), ranButton.getHeight());

        add(label);
        add(mainButton);
        add(ranButton);


        setBounds(0,0, screenSize.width, screenSize.height);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        
    }
}