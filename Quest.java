import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Quest extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final String[] questList = {
        "Red Letter Day", "Friend of the People", "All Bark", "Playthings", "The White-Winged Wonder",
        "The Fruits of Her Labors", "Blacksmith's Blues", "Cut from the Cloth", "The Root of the Problem",
        "Carving Out a Niche", "Weird Science", "For Great Justice", "Blacksmith's Blues II", "A Bone to Pick",
        "Payback", "Smooth Like Butler", "An Eye for an Eye", "On Balance", "Rekindling the Flame",
        "Carved in Stone", "Trading Places", "For Great Justice II", "Please Sir, Can I Have Some Morbol",
        "Tears of Mercy", "Blacksmith's Blues III", "Even Weirder Science", "Rekindling the Flame II",
        "Under New Management", "Duty Undying - Kretov Location", "Lines in the Sand", "Trading Places II",
        "Blacksmith's Blues IV", "Lines in the Sand II", "Laid to Rest", "Duty Undying II",
        "Under New Management II", "Three's Company", "Where There's a Will", "Nobody's Tool",
        "Trial and Error", "Aiming High", "More than Words", "An Inconvenient Truth", "A Tail to Tell",
        "Priceless", "Silver Linings"
    };
    private final ArrayList<String> completedQuests = new ArrayList<>();

    public Quest() {
        super("Quests");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(14, 0, 0));

        JLabel label = new JLabel("Quests", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 48));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(14, 0, 0));

        JButton mainButton = new JButton("Back to Main Menu");
        mainButton.setPreferredSize(new Dimension(250, 60));
        mainButton.setFont(new Font("Arial", Font.PLAIN, 24));
        mainButton.setBackground(new Color(64, 74, 86));
        mainButton.setForeground(Color.WHITE);

        mainButton.addActionListener(e -> {
            new Main();
            dispose();
        });

        JPanel questCheckbox = new JPanel();
        JCheckBox[] boxes = new JCheckBox[questList.length];
        questCheckbox.setLayout(new BoxLayout(questCheckbox, BoxLayout.Y_AXIS));
        questCheckbox.setBackground(new Color(64, 74, 86));

        try (BufferedReader reader = new BufferedReader(new FileReader("completedQuests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                completedQuests.add(line.trim());
            }
        } catch (IOException b) {
            b.printStackTrace();
        }

        for (int i = 0; i < questList.length; i++) {
            boxes[i] = new JCheckBox(questList[i]);
            if (completedQuests.contains(questList[i])) {
                boxes[i].setSelected(true);
            }
            boxes[i].setBackground(new Color(64, 74, 86));
            boxes[i].setForeground(Color.WHITE);
            boxes[i].setFont(new Font("Arial", Font.PLAIN, 16));
            questCheckbox.add(boxes[i]);
        }

        JScrollPane scroll = new JScrollPane(questCheckbox);
        scroll.setPreferredSize(new Dimension(500, screenSize.height - 150));

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(14, 0, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(100, 40));
        submitButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("completedQuests.txt")) {
                for (JCheckBox box : boxes) {
                    if (box.isSelected()) {
                        writer.write(box.getText() + "\n");
                    }
                }
            } catch (IOException a) {
                a.printStackTrace();
            }
        });

        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(new Color(64, 74, 86));
        submitPanel.add(submitButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(64, 74, 86));
        rightPanel.add(scroll, BorderLayout.CENTER);
        rightPanel.add(submitPanel, BorderLayout.SOUTH);

        leftPanel.add(mainButton, BorderLayout.NORTH);

        add(label, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setBounds(0, 0, screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
