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
        setLayout(null);
        JLabel label = new JLabel("Main Menu");
        JButton questButton = new JButton("Quests");
        JButton eikonButton = new JButton("Eikons");
        JButton editSkills = new JButton("Edit Skills");
        
        label.setBounds((screenSize.width / 2) - 50, screenSize.height / 8, 200, 50);

        questButton.setSize(200, 400);
        questButton.setBounds((screenSize.width / 12), (screenSize.height / 3) + 2, questButton.getWidth(), questButton.getHeight());

        questButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quest();
                dispose();
             }
          });

          editSkills.setSize(200, 400);
          editSkills.setBounds(screenSize.width / 6, (screenSize.height / 3) + 2, editSkills.getWidth(), editSkills.getHeight());

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
                try(FileReader userSkills = new FileReader("skills.txt")){
                    BufferedReader r = new BufferedReader(userSkills);
                    String l;
                    while((l =r.readLine()) != null){
                        for(String p: skills){
                            if(l.equals(p)){
                                skillsList.remove(p);
                            }
                        }
                    }   
                    JFrame skill = new JFrame("Update Skills");
                    JCheckBox[] boxes = new JCheckBox[skills.length];
                    JPanel checkboxPanel = new JPanel();
                    JButton submit = new JButton();
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.add(submit);
                    skill.add(checkboxPanel);
                    skill.add(buttonPanel, BorderLayout.SOUTH); 
                    // Set Layout for JPanel to make the checkboxes stack properly
                    //checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
                    for (int i = 0; i < skills.length; i++) {
                        boxes[i] = new JCheckBox(skillsList.get(i));
                        checkboxPanel.add(boxes[i]);
                    }
                    
                    try(BufferedReader reader = new BufferedReader(userSkills)){
                        String currentLine;
                        String line;
                        while((line = reader.readLine()) != null){
                            for(int i = 0; i < boxes.length; i++){
                                currentLine = boxes[i].getText();
                                if(line.equals(currentLine)){
                                    continue;
                                }
                                else{
                                    try(FileWriter writer = new FileWriter("skills.txt", true)){
                                        if(boxes[i].isSelected()){
                                        writer.write(skillsList.get(i) + "\n");
                                        }           
                                    }
                                }
                            }
                        }
                        reader.close();
                    }
                    skill.setSize(1000, 1000);  // Adjust size as needed
                    skill.setLocation((screenSize.width - skill.getWidth()) / 2, (screenSize.height - skill.getHeight()) / 2);
                    submit.setSize(400, 400);
                    skill.setVisible(true);
                    skill.setBounds(0, 0, screenSize.width, screenSize.height);
                }
                catch(IOException p){
                    System.out.println(p);
                }
                
            }
        });
          eikonButton.setSize(200, 400);
          eikonButton.setBounds((screenSize.width / 2) + 850, (screenSize.height / 3) + 5, eikonButton.getWidth(), eikonButton.getHeight());

          eikonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new EikonList();
                
            }
          });

          add(label);
          add(questButton);
          add(editSkills);
          add(eikonButton);

          setBounds(0,0, screenSize.width, screenSize.height);
          setVisible(true);
          
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
