package vn.edu.hcmus.student.sv19127337.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127337.Dictionary
 * Created by ltpbao
 * Date 12/27/2021 - 6:05 PM
 * Description: ...
 */
public class UI extends JFrame {
    public void createAndShowGUI(Dictionary Dict) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane(), Dict);

        frame.pack();
        frame.setVisible(true);
    }

    public static void addComponentsToPane(Container pane, Dictionary Dict) {
        Vector<String> hist = new Vector<String>();
        Dictionary originDict = Dict;

        JPanel menu = new JPanel();

        JButton _1sBySlang = new JButton("Search by Slang");
        JButton _2sByMeaning = new JButton("Search by Definition");
        JButton _3history = new JButton("History");
        JButton _4addSlang = new JButton("Add a Slang word");
        JButton _5editSlang = new JButton("Edit a Slang word");
        JButton _6deleteSlang = new JButton("Delete a Slang word");
        JButton _7reset = new JButton("Reset dictionary");
        JButton _8randSlang = new JButton("On this day Slang word");
        JButton _9randSlang = new JButton("Guessing definition");
        JButton _10randSlang = new JButton("Guessing slang");

        _1sBySlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel sBySlang = new JPanel();

                JTextField slang = new JTextField(20);

                JTextArea def = new JTextArea(5, 20);

                JButton find = new JButton("Search");
                find.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sl = slang.getText();
                        hist.add(sl);
                        Vector<String> result = Dict.searchBySlang(sl);


                        for (String s : result) {
                            def.append(s + "\n");
                            def.setCaretPosition(def.getDocument().getLength());
                        }
                    }
                });

                JButton clear = new JButton("Clear");
                clear.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        def.selectAll();
                        def.replaceSelection("");
                    }
                });

                JFrame _1 = new JFrame();
                sBySlang.add(new JLabel("Slang:"));
                sBySlang.add(slang);
                sBySlang.add(new JLabel("Definition:"));
                sBySlang.add(def);
                sBySlang.add(find);
                sBySlang.add(clear);

                _1.setContentPane(sBySlang);
                _1.setTitle("Search by Slang");
                _1.pack();
                _1.setVisible(true);
            }
        });

        _2sByMeaning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel sByMeaning = new JPanel();

                JTextField def = new JTextField(20);

                JTextArea slang = new JTextArea(5, 20);

                JButton find = new JButton("Search");
                find.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sl = def.getText();
                        hist.add(sl);
                        Vector<String> result = Dict.searchByMeaning(sl);

                        int count = 0;
                        for (String s : result) {
                            if (count == 5) break;
                            count ++;
                            slang.append(s + "\n");
                            slang.setCaretPosition(slang.getDocument().getLength());

                        }
                    }
                });

                JButton clear = new JButton("Clear");
                clear.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        slang.selectAll();
                        slang.replaceSelection("");
                    }
                });

                JFrame _2 = new JFrame();
                sByMeaning.add(new JLabel("Definition:"));
                sByMeaning.add(def);
                sByMeaning.add(new JLabel("Slang:"));
                sByMeaning.add(slang);
                sByMeaning.add(find);
                sByMeaning.add(clear);

                _2.setContentPane(sByMeaning);
                _2.setTitle("Search by Definition");
                _2.pack();
                _2.setVisible(true);
            }
        });

        _3history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel history = new JPanel();
                JTextArea out = new JTextArea(20, 10);

                for (String s : hist) {
                    out.append(s + "\n");
                    out.setCaretPosition(out.getDocument().getLength());
                }

                JFrame _3 = new JFrame();
                history.add(out);

                _3.setContentPane(history);
                _3.setTitle("History");
                _3.pack();
                _3.setVisible(true);
            }
        });

        _4addSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel addSl = new JPanel();

                JTextField slang = new JTextField(20);
                JTextField meaning = new JTextField(20);
                JButton addBtn = new JButton("Add");

                addBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sl = slang.getText();
                        String mn = meaning.getText();
                        Dict.addWord(sl, mn);

                        slang.setText("Success");
                        meaning.setText("");
                    }
                });

                JFrame _4 = new JFrame();

                addSl.add(new JLabel("Slang: "));
                addSl.add(slang);
                addSl.add(new JLabel("Meaning: "));
                addSl.add(meaning);
                addSl.add(addBtn);

                _4.setContentPane(addSl);
                _4.setTitle("Add a slang word");
                _4.pack();
                _4.setVisible(true);
            }
        });

        _5editSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel editSl = new JPanel();

                JTextField slang = new JTextField(20);
                JTextField newSlang = new JTextField(20);
                JTextField meaning = new JTextField(30);
                JButton editBtn = new JButton("Edit");

                editBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sl = slang.getText();
                        String newSl = newSlang.getText();
                        String mn = meaning.getText();

                        boolean check = Dict.editWord(sl, newSl, mn);

                        if (check == false) {
                            slang.setText("No result");
                            newSlang.setText("");
                            meaning.setText("");
                        }

                        else {
                            slang.setText("Success");
                            newSlang.setText("");
                            meaning.setText("");
                        }
                    }
                });

                JFrame _5 = new JFrame();

                editSl.add(new JLabel("Slang: "));
                editSl.add(slang);
                editSl.add(new JLabel("New slang: "));
                editSl.add(newSlang);
                editSl.add(new JLabel("Meaning: "));
                editSl.add(meaning);
                editSl.add(editBtn);

                _5.setContentPane(editSl);
                _5.setTitle("Add a slang word");
                _5.pack();
                _5.setVisible(true);
            }
        });

        _6deleteSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel delSl = new JPanel();

                JTextField slang = new JTextField(20);
                JButton delBtn = new JButton("Delete");

                delBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sl = slang.getText();

                        boolean check = Dict.deleteWord(sl);

                        if (check == false) {
                            slang.setText("No result");
                        }

                        else {
                            slang.setText("Success");
                        }
                    }
                });

                JFrame _6 = new JFrame();

                delSl.add(new JLabel("Slang: "));
                delSl.add(slang);
                delSl.add(delBtn);

                _6.setContentPane(delSl);
                _6.setTitle("Add a slang word");
                _6.pack();
                _6.setVisible(true);
            }
        });

        _7reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dict.setDict(originDict);

                JPanel success = new JPanel();
                JTextField tf = new JTextField();
                tf.setText("Success");

                JFrame _7 = new JFrame();

                success.add(tf);

                _7.setContentPane(success);
                _7.setTitle("Success");
                _7.pack();
                _7.setVisible(true);
            }
        });

        _8randSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = Dict.randSlang();

                String randSl = Dict.getSlang().get(ind);
                String mean = Dict.getMeaning().get(ind);

                JPanel otdSl = new JPanel();

                JTextArea ta = new JTextArea(5, 20);
                ta.append(randSl + "     " + mean);
                ta.setCaretPosition(ta.getDocument().getLength());

                JFrame _8 = new JFrame();

                otdSl.add(ta);

                _8.setContentPane(otdSl);
                _8.setTitle("On this day slang");
                _8.pack();
                _8.setVisible(true);
            }
        });

        _9randSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel _9rand = new JPanel();

                JButton ans1 = new JButton();
                JButton ans2 = new JButton();
                JButton ans3 = new JButton();
                JButton ans4 = new JButton();

                String correctChoice = new String("");
                String choice1 = new String("");
                String choice2 = new String("");
                String choice3 = new String("");

                int ind = Dict.randSlang();

                String slang = Dict.getSlang().get(ind);
                correctChoice = Dict.getMeaning().get(ind);

                int a, b, c;

                do {
                    a = Dict.randSlang();
                    b = Dict.randSlang();
                    c = Dict.randSlang();
                } while (a == ind || b == ind || c == ind || a == b || b == c || c == a);

                choice1 = Dict.getMeaning().get(a);
                choice2 = Dict.getMeaning().get(b);
                choice3 = Dict.getMeaning().get(c);

                ans1.setText(choice1);
                ans2.setText(correctChoice);
                ans3.setText(choice2);
                ans4.setText(choice3);

                JTextArea ta = new JTextArea(1, 10);
                ta.append(slang);
                ta.setCaretPosition(ta.getDocument().getLength());

                ans2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ta.selectAll();
                        ta.replaceSelection("");
                        ta.append("Correct!");
                        ta.setCaretPosition(ta.getDocument().getLength());
                    }
                });

                JFrame _9 = new JFrame();

                _9rand.add(ta);
                _9rand.add(ans1);
                _9rand.add(ans2);
                _9rand.add(ans3);
                _9rand.add(ans4);

                _9.setContentPane(_9rand);
                _9.setTitle("Choose correct meaning");
                _9.pack();
                _9.setVisible(true);
            }
        });

        _10randSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel _10rand = new JPanel();

                JButton ans1 = new JButton();
                JButton ans2 = new JButton();
                JButton ans3 = new JButton();
                JButton ans4 = new JButton();

                String correctChoice = new String("");
                String choice1 = new String("");
                String choice2 = new String("");
                String choice3 = new String("");

                int ind = Dict.randSlang();

                String mean = Dict.getMeaning().get(ind);
                correctChoice = Dict.getSlang().get(ind);

                int a, b, c;

                do {
                    a = Dict.randSlang();
                    b = Dict.randSlang();
                    c = Dict.randSlang();
                } while (a == ind || b == ind || c == ind || a == b || b == c || c == a);

                choice1 = Dict.getSlang().get(a);
                choice2 = Dict.getSlang().get(b);
                choice3 = Dict.getSlang().get(c);

                ans1.setText(choice1);
                ans2.setText(correctChoice);
                ans3.setText(choice2);
                ans4.setText(choice3);

                JTextArea ta = new JTextArea(1, 10);
                ta.append(mean);
                ta.setCaretPosition(ta.getDocument().getLength());

                ans2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ta.selectAll();
                        ta.replaceSelection("");
                        ta.append("Correct!");
                        ta.setCaretPosition(ta.getDocument().getLength());
                    }
                });

                JFrame _10 = new JFrame();

                _10rand.add(ta);
                _10rand.add(ans1);
                _10rand.add(ans2);
                _10rand.add(ans3);
                _10rand.add(ans4);

                _10.setContentPane(_10rand);
                _10.setTitle("Choose correct meaning");
                _10.pack();
                _10.setVisible(true);
            }
        });

        pane.setLayout(new GridLayout(5, 2));

        _1sBySlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_1sBySlang);
        _2sByMeaning.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_2sByMeaning);
        _3history.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_3history);
        _4addSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_4addSlang);
        _5editSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_5editSlang);
        _6deleteSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_6deleteSlang);
        _7reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_7reset);
        _8randSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_8randSlang);
        _9randSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_9randSlang);
        _10randSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(_10randSlang);

    }
}
