
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;




class EikonList extends JFrame{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel currentSkillLabel = null;
    public EikonList(){
        super("Eikons");
        setLayout(null);
        JLabel label = new JLabel("Eikon List");
        JButton mainButton = new JButton("Back to Main Menu");
        JButton ranButton = new JButton("Randomize Eikons");
        File userSkills = new File("skills.txt");
        
        
        if(userSkills.length() == 0){
            new startupSkills();
            dispose();
        }
        else if(userSkills.length() != 0){
        label.setBounds((screenSize.width / 2) - 50, screenSize.height / 8, 200, 50);

        mainButton.setSize(200, 400);
        mainButton.setBounds((screenSize.width / 6) - 225, (screenSize.height * 1/3) + 25, mainButton.getWidth(), mainButton.getHeight());

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
             }
          });

        ranButton.setSize(200, 400);
        ranButton.setBounds((screenSize.width / 2) + 850, (screenSize.height / 3) + 5, ranButton.getWidth(), ranButton.getHeight());

        ranButton.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                String skillText = randomize();
                JLabel skill = new JLabel();
                if(currentSkillLabel != null){
                    getContentPane().remove(currentSkillLabel);
                }
                skill.setText("<html>" + skillText.replace("\n", "<br>") + "</html>");
                skill.setSize(750, 750);
                skill.setBounds((screenSize.width / 2) - 100, (screenSize.height / 2) - 400, 750, 750);
                add(skill);
                currentSkillLabel = skill;
                revalidate();
                repaint();
                setVisible(true);
            }
        });
        add(label);
        add(mainButton);
        add(ranButton);


        setBounds(0,0, screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    private String randomize(){
        try{
        BufferedReader reader = new BufferedReader(new FileReader("skills.txt"));
        ArrayList<String> userSkills = new ArrayList<>();
        ArrayList<String> eikons = new ArrayList<>(7);
        eikons.add("Phoenix");
        String line;
        Random rand = new Random();

            while ((line = reader.readLine()) != null){
                userSkills.add(line);
            }
            reader.close();
        if(userSkills.contains("Gouge")){
            eikons.add("Garuda");
        }
        if(userSkills.contains("Pile Drive")){
            eikons.add("Ramuh");
        }
        if(userSkills.contains("Windup")){
            eikons.add("Titan");
        }
        if(userSkills.contains("Impulse")){
            eikons.add("Bahamut");
        }
        if(userSkills.contains("Ice Age")){
            eikons.add("Shiva");
        }
        if(userSkills.contains("Gungir")){
            eikons.add("Odin");
        }

        int eikon1 = rand.nextInt(eikons.size());
        String eikon1String = eikons.get(eikon1);
        ArrayList<String> eikon1Skills = new ArrayList<>();
        eikons.remove(eikon1);

        int eikon2 = rand.nextInt(eikons.size());
        String eikon2String = eikons.get(eikon2);
        ArrayList<String> eikon2Skills = new ArrayList<>();
        eikons.remove(eikon2);

        int eikon3 = rand.nextInt(eikons.size());
        String eikon3String = eikons.get(eikon3);
        ArrayList<String> eikon3Skills = new ArrayList<>();
        eikons.remove(eikon3);

        for(int x = 0; x < 3 && x < userSkills.size(); x++){
            int p = rand.nextInt(userSkills.size());
            String skill = userSkills.get(p);
            eikon1Skills.add(skill);
            userSkills.remove(p);
        }

        for(int x = 0; x < 3 && x < userSkills.size(); x++){
            int p = rand.nextInt(userSkills.size());
            String skill = userSkills.get(p);
            eikon2Skills.add(skill);
            userSkills.remove(p);
        }

        for(int x = 0; x < 3 && x < userSkills.size(); x++){
            int p = rand.nextInt(userSkills.size());
            String skill = userSkills.get(p);
            eikon3Skills.add(skill);
            userSkills.remove(p);
        }

        String skills = eikon1String + "\n  " + 
        eikon1Skills.get(0) + "\n   " + 
        eikon1Skills.get(1) + "\n   " +
        eikon1Skills.get(2) +"\n" +
        eikon2String + "\n  " + 
        eikon2Skills.get(0) +"\n    " +
        eikon2Skills.get(1) + "\n   " +
        eikon2Skills.get(2) +"\n" +
        eikon3String + "\n  " +
        eikon3Skills.get(0) + "\n   " +
        eikon3Skills.get(1) + "\n   " +
        eikon3Skills.get(2) +"\n" 
        ;   

        System.out.println(skills);

        return skills;
        }
        catch(IOException e){
            
        }
        return "";
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
    File userSkills = new File("skills.txt");

    // Create an array of JCheckBox for each skill
    JCheckBox[] boxes = new JCheckBox[skills.length];
    JPanel checkboxPanel = new JPanel();
    JButton submit = new JButton();
    public startupSkills() {
        
        // Set Layout for JPanel to make the checkboxes stack properly
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < skills.length; i++) {
            boxes[i] = new JCheckBox(skills[i]);
            checkboxPanel.add(boxes[i]);
        }

        
        // Configure the JFrame
        JFrame startup = new JFrame("Startup Skills");
        startup.setLayout(new BorderLayout());
        startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startup.add(checkboxPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submit);
        startup.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set the size and location of the frame
        startup.setSize(1000, 1000);  // Adjust size as needed
        startup.setLocation((screenSize.width - startup.getWidth()) / 2, (screenSize.height - startup.getHeight()) / 2);

        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updateUserSkills();
                new EikonList();
                dispose();
            }
        });
        submit.setSize(400, 400);
        startup.setVisible(true);
    }

    private void updateUserSkills(){
        try(FileWriter writer = new FileWriter("skills.txt", true)){
            for(int i = 0; i < skills.length; i++){
                if(boxes[i].isSelected()){
                    writer.write(skills[i] + "\n");
                }
            }   
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
