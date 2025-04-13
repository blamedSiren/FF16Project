import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main extends JFrame{
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Main(){
        super("Main Menu");
        setLayout(new BorderLayout()); // Use BorderLayout for overall window layout
        JLabel label = new JLabel("Main Menu", SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 550, 450)); // FlowLayout for center alignment and spacing between buttons
        
        JButton questButton = new JButton("Quests");
        JButton eikonButton = new JButton("Eikons");
        JButton editSkills = new JButton("Edit Skills");

        questButton.setPreferredSize(new Dimension(250, 500));
        questButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quest();
                dispose();
            }
        });

        editSkills.setPreferredSize(new Dimension(250, 500));
        editSkills.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
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
        
                // Read current skills from the file and mark them as selected
                ArrayList<String> existingSkills = new ArrayList<>();
                try (BufferedReader r = new BufferedReader(new FileReader("skills.txt"))) {
                    String line;
                    while ((line = r.readLine()) != null) {
                        existingSkills.add(line.trim());  // Collect existing skills
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        
                // Create the JFrame for updating skills
                JFrame skill = new JFrame("Update Skills");
                JCheckBox[] boxes = new JCheckBox[skillsList.size()];
                JPanel checkboxPanel = new JPanel();
                checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS)); // Stack checkboxes vertically
        
                // Create checkboxes for all skills
                for (int i = 0; i < skillsList.size(); i++) {
                    boxes[i] = new JCheckBox(skillsList.get(i));
                    // Set the checkbox selected if the skill is in the existing skills list
                    if (existingSkills.contains(skillsList.get(i))) {
                        boxes[i].setSelected(true);
                    }
                    checkboxPanel.add(boxes[i]);
                }
        
                // Add submit button
                JButton submit = new JButton("Submit");
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Append selected skills back to the file
                        try (FileWriter writer = new FileWriter("skills.txt", true)) {
                            for (int i = 0; i < boxes.length; i++) {
                                if (boxes[i].isSelected() && !existingSkills.contains(boxes[i].getText())) {
                                    writer.write(boxes[i].getText() + "\n"); // Only append if not already in the file
                                }
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        skill.dispose(); // Close the skill window
                    }
                });
        
                // Set layout and add components
                JPanel buttonPanel2 = new JPanel();
                buttonPanel2.add(submit);
                skill.add(checkboxPanel, BorderLayout.CENTER);
                skill.add(buttonPanel2, BorderLayout.SOUTH); // Submit button at the bottom
        
                // Final JFrame setup
                skill.setSize(500, 600); // Adjust size to fit checkboxes and button
                skill.setLocation((screenSize.width - skill.getWidth()) / 2, (screenSize.height - skill.getHeight()) / 2);
                skill.setVisible(true);
                skill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close without exiting app
            }
        });
        
        

        eikonButton.setPreferredSize(new Dimension(250, 500));
        eikonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new EikonList();
            }
        });

        buttonPanel.add(questButton);
        buttonPanel.add(editSkills);
        buttonPanel.add(eikonButton);

        add(label, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setBounds(0,0, screenSize.width, screenSize.height);
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
