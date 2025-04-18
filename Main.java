import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Main extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Main() {
        super("Main Menu");
        getContentPane().setBackground(new Color(14, 0, 0));
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Main Menu", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, screenSize.height / 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(14, 0, 0));

        int buttonWidth = screenSize.width / 7;
        int buttonHeight = screenSize.height / 2;
        int hGap = screenSize.width / 7;
        int vGap = screenSize.height / 12;

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, hGap, vGap));

        JButton questButton = new JButton("Quests");
        JButton eikonButton = new JButton("Eikons");
        JButton editSkills = new JButton("Edit Skills");

        JButton[] buttons = { questButton, eikonButton, editSkills };
        for (JButton btn : buttons) {
            btn.setFont(new Font("Arial", Font.PLAIN, screenSize.height / 40));
            btn.setBackground(new Color(64, 74, 86));
            btn.setForeground(Color.WHITE);
            btn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        }

        questButton.addActionListener(e -> {
            new Quest();
            dispose();
        });

        eikonButton.addActionListener(e -> {
            new EikonList();
            dispose();
        });

        editSkills.addActionListener(e -> {
            String[] skills = {
                "Will o' the Wykes", "Limit Break", "Ignition", "Rising Flames", "Heatwave",
                "Flames of Rebirth", "Phoenix Shift", "Scarlet Cyclones", "Aerial Blast", "Rook's Gambit",
                "Deadly Embrace", "Wicked Wheel", "Gouge", "Pile Drive", "Lightning Rod", "Blind Justice",
                "Judgement Bolt", "Thunderstorm", "Raging Fists", "Windup", "Earthen Fury", "Titanic Block",
                "Upheaval", "Wings of Light", "Flare Breath", "Impulse", "Satellite", "Gigaflare", "Diamond Dust",
                "Rime", "Ice Age", "Mesmerize", "Cold Snap", "Gungnir", "Heaven's Cloud", "Rift Slip",
                "Arm of Darkness", "Dancing Steel"
            };

            ArrayList<String> skillsList = new ArrayList<>();
            Collections.addAll(skillsList, skills);

            ArrayList<String> existingSkills = new ArrayList<>();
            try (BufferedReader r = new BufferedReader(new FileReader("skills.txt"))) {
                String line;
                while ((line = r.readLine()) != null) {
                    existingSkills.add(line.trim());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JFrame skill = new JFrame("Update Skills");
            skill.getContentPane().setBackground(new Color(64, 74, 86));
            JCheckBox[] boxes = new JCheckBox[skillsList.size()];

            JPanel checkboxPanel = new JPanel();
            checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
            checkboxPanel.setBackground(new Color(64, 74, 86));

            for (int i = 0; i < skillsList.size(); i++) {
                boxes[i] = new JCheckBox(skillsList.get(i));
                if (existingSkills.contains(skillsList.get(i))) {
                    boxes[i].setSelected(true);
                }
                boxes[i].setBackground(new Color(64, 74, 86));
                boxes[i].setForeground(Color.WHITE);
                boxes[i].setFont(new Font("Arial", Font.PLAIN, screenSize.height / 60));
                checkboxPanel.add(boxes[i]);
            }

            JScrollPane scrollPane = new JScrollPane(checkboxPanel);
            scrollPane.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height / 2));

            JButton submit = new JButton("Submit");
            submit.setBackground(new Color(14, 0, 0));
            submit.setForeground(Color.WHITE);
            submit.addActionListener(ev -> {
                try (FileWriter writer = new FileWriter("skills.txt")) {
                    for (JCheckBox box : boxes) {
                        if (box.isSelected()) {
                            writer.write(box.getText() + "\n");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                skill.dispose();
            });

            JPanel buttonPanel2 = new JPanel();
            buttonPanel2.setBackground(new Color(64, 74, 86));
            buttonPanel2.add(submit);

            skill.add(scrollPane, BorderLayout.CENTER);
            skill.add(buttonPanel2, BorderLayout.SOUTH);
            skill.setSize(screenSize.width / 3 + 50, screenSize.height / 2 + 100);
            skill.setLocationRelativeTo(null);
            skill.setVisible(true);
            skill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });

        buttonPanel.add(questButton);
        buttonPanel.add(editSkills);
        buttonPanel.add(eikonButton);

        add(label, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setBounds(0, 0, screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
