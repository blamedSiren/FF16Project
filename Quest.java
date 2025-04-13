import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Quest extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final String[] questList = {
        "Red Letter Day",
        "Friend of the People",
        "All Bark",
        "Playthings",
        "The White-Winged Wonder",
        "The Fruits of Her Labors",
        "Blacksmith's Blues",
        "Cut from the Cloth",
        "The Root of the Problem",
        "Carving Out a Niche",
        "Weird Science",
        "For Great Justice",
        "Blacksmith's Blues II",
        "A Bone to Pick",
        "Payback",
        "Smooth Like Butler",
        "An Eye for an Eye",
        "On Balance",
        "Rekindling the Flame",
        "Carved in Stone",
        "Trading Places",
        "For Great Justice II",
        "Please Sir, Can I Have Some Morbol",
        "Tears of Mercy",
        "Blacksmith's Blues III",
        "Even Weirder Science",
        "Rekindling the Flame II",
        "Under New Management",
        "Duty Undying - Kretov Location",
        "Lines in the Sand",
        "Trading Places II",
        "Blacksmith's Blues IV",
        "Lines in the Sand II",
        "Laid to Rest",
        "Duty Undying II",
        "Under New Management II",
        "Three's Company",
        "Where There's a Will",
        "Nobody's Tool",
        "Trial and Error",
        "Aiming High",
        "More than Words",
        "An Inconvenient Truth",
        "A Tail to Tell",
        "Priceless",
        "Silver Linings"
    };
    public Quest() {
        super("Quests");
        setLayout(new BorderLayout()); // Two rows: One for the label, one for the buttons
        JLabel label = new JLabel("Quests", SwingConstants.CENTER);

        // Create a panel for the button with GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 350, 450)); // 1 row, 1 column

        JButton mainButton = new JButton("Back to Main Menu");
        mainButton.setPreferredSize(new Dimension(250, 500));

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
            }
        });

        JPanel questCheckbox = new JPanel();
        JCheckBox[] boxes = new JCheckBox[questList.length];
        
        questCheckbox.setLayout(new BoxLayout(questCheckbox, BoxLayout.Y_AXIS));
        questCheckbox.setPreferredSize(new Dimension(500, 500));
        for(int i = 0; i < questList.length; i++){
            boxes[i] = new JCheckBox(questList[i]);
            questCheckbox.add(boxes[i]);
        }
        JScrollPane scroll = new JScrollPane(questCheckbox);
        buttonPanel.add(mainButton);

        add(label);         // Add label in the first row
        add(buttonPanel);   // Add button panel in the second row
        add(scroll, BorderLayout.CENTER);

        setBounds(0, 0, screenSize.width, screenSize.height);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
