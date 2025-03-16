import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Quest extends JFrame{
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Quest(){
        super("Quests");
        setLayout(null);
        JLabel label = new JLabel("Quests");
        JButton mainButton = new JButton("Back to Main Menu");

        label.setBounds((screenSize.width / 2) - 50, screenSize.height / 8, 200, 50);

        mainButton.setSize(200, 400);
        mainButton.setBounds(screenSize.width / 15, screenSize.height / 3, mainButton.getWidth(), mainButton.getHeight());

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
             }
          });
        
        add(label);
        add(mainButton);

        setBounds(0,0, screenSize.width, screenSize.height);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
