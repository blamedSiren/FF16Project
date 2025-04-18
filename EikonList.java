import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

class EikonList extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel currentSkillLabel = null;

    public EikonList() {
        super("Eikons");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(14, 0, 0));
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Eikon List", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(14, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 40, 20, 40);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JButton mainButton = createResponsiveButton("Back to Main Menu", e -> {
            new Main();
            dispose();
        });

        JButton ranButton = createResponsiveButton("Randomize Eikons", e -> {
            String skillText = randomize();
            if (currentSkillLabel != null) {
                getContentPane().remove(currentSkillLabel);
            }
            JLabel skill = new JLabel("<html>" + skillText.replace("\n", "<br>") + "</html>");
            skill.setForeground(Color.WHITE);
            skill.setFont(new Font("Arial", Font.PLAIN, 24));
            skill.setHorizontalAlignment(SwingConstants.CENTER);
            skill.setVerticalAlignment(SwingConstants.TOP);
            skill.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
            currentSkillLabel = skill;
            add(skill, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        gbc.gridx = 0; gbc.gridy = 0;
        buttonPanel.add(mainButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(ranButton, gbc);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        File userSkills = new File("skills.txt");
        if (userSkills.length() == 0) {
            new startupSkills();
            dispose();
        }
    }

    private JButton createResponsiveButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 28));
        button.setBackground(new Color(64, 74, 86));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setMinimumSize(new Dimension(200, 80));
        button.addActionListener(action);
        return button;
    }

    private String randomize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("skills.txt"))) {
            ArrayList<String> userSkills = new ArrayList<>();
            ArrayList<String> eikons = new ArrayList<>();
            eikons.add("Phoenix");

            String line;
            Random rand = new Random();
            while ((line = reader.readLine()) != null) {
                userSkills.add(line);
            }

            if (userSkills.contains("Gouge")) eikons.add("Garuda");
            if (userSkills.contains("Pile Drive")) eikons.add("Ramuh");
            if (userSkills.contains("Windup")) eikons.add("Titan");
            if (userSkills.contains("Impulse")) eikons.add("Bahamut");
            if (userSkills.contains("Ice Age")) eikons.add("Shiva");
            if (userSkills.contains("Gungnir")) eikons.add("Odin");

            if (eikons.size() < 3 || userSkills.size() < 9) return "Not enough skills or eikons.";

            ArrayList<String> output = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String eikon = eikons.remove(rand.nextInt(eikons.size()));
                ArrayList<String> pickedSkills = new ArrayList<>();
                for (int j = 0; j < 3 && !userSkills.isEmpty(); j++) {
                    pickedSkills.add(userSkills.remove(rand.nextInt(userSkills.size())));
                }
                output.add(eikon + "\n  " + String.join("\n   ", pickedSkills));
            }

            return String.join("\n", output);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading skills.";
        }
    }
}

class startupSkills extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    String[] skills = {
        "Will o' the Wykes", "Limit Break", "Ignition", "Rising Flames", "Heatwave",
        "Flames of Rebirth", "Phoenix Shift", "Scarlet Cyclones", "Aerial Blast", "Rook's Gambit",
        "Deadly Embrace", "Wicked Wheel", "Gouge", "Pile Drive", "Lightning Rod", "Blind Justice",
        "Judgement Bolt", "Thunderstorm", "Raging Fists", "Windup", "Earthen Fury", "Titanic Block",
        "Upheaval", "Wings of Light", "Flare Breath", "Impulse", "Satellite", "Gigaflare", "Diamond Dust",
        "Rime", "Ice Age", "Mesmerize", "Cold Snap", "Gungnir", "Heaven's Cloud", "Rift Slip", 
        "Arm of Darkness", "Dancing Steel"
    };
    JCheckBox[] boxes = new JCheckBox[skills.length];

    public startupSkills() {
        super("Startup Skills");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 800));
        getContentPane().setBackground(new Color(14, 0, 0));
        setLayout(new BorderLayout());

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBackground(new Color(64, 74, 86));

        for (int i = 0; i < skills.length; i++) {
            boxes[i] = new JCheckBox(skills[i]);
            boxes[i].setBackground(new Color(64, 74, 86));
            boxes[i].setForeground(Color.WHITE);
            boxes[i].setFont(new Font("Arial", Font.PLAIN, 16));
            checkboxPanel.add(boxes[i]);
        }

        JScrollPane scrollPane = new JScrollPane(checkboxPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.BOLD, 18));
        submit.setBackground(new Color(14, 0, 0));
        submit.setForeground(Color.WHITE);
        submit.setContentAreaFilled(false);
        submit.setOpaque(true);
        submit.setPreferredSize(new Dimension(150, 50));
        submit.addActionListener(e -> {
            updateUserSkills();
            new EikonList();
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(64, 74, 86));
        buttonPanel.add(submit);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateUserSkills() {
        try (FileWriter writer = new FileWriter("skills.txt")) {
            for (JCheckBox box : boxes) {
                if (box.isSelected()) {
                    writer.write(box.getText() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
