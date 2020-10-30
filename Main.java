import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JPanel{
     JButton buttons[] = new JButton[9];
     int alternate = 0;                                         //if this number is a even, then put a X. If it's odd, then put an O

    public Main(){
        setLayout(new GridLayout(3,3));
        initializeButtons();
    }

    public void initializeButtons(){
        for(int i = 0; i <9 ; i++){
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].setBackground(Color.darkGray);
            //buttons[i].setForeground(Color.yellow);
            buttons[i].setFont(new Font("Arial",Font.BOLD,40));
            buttons[i].addActionListener(new ButtonListener());

            add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
            //because this whole class is a JPanel already
        }
    }
    // when a button is clicked, it generates an ActionEvent.
    // Thus, each button needs an ActionListener. When it is clicked,
    // it goes to this listener class that I have created and goes to the actionPerformed method.
    // There (and in this class), we decide what we want to do.
    public void resetButtons(){
        alternate = 0;
        for(int i = 0; i < 9; i++)
            buttons[i].setText("");
    }
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(alternate%2 == 0) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.red);
            }
            else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.yellow);
            }
            char chk = checkForWin();
            boolean isWon = false;
            if(chk == 'X'){
                isWon = true;
                JOptionPane.showMessageDialog(null,"X WON!");
                resetButtons();
            }
            else if(chk == 'O'){
                isWon = true;
                JOptionPane.showMessageDialog(null,"O WON!");
                resetButtons();
            }
            if(alternate>=8 && !isWon){
                JOptionPane.showMessageDialog(null, "MATCH DRAW!");
                resetButtons();
            }
            if(alternate>=8 || isWon){
                JOptionPane.showConfirmDialog(null, "Game Over.");
                resetButtons();
            }

            alternate++;

        }


        public char checkForWin(){
            /**   Reference: the button array is arranged like this as the board
             *      0 | 1 | 2
             *      3 | 4 | 5
             *      6 | 7 | 8
             */
            //horizontal win check
            if( checkAdjacent(0,1) && checkAdjacent(1,2) ) {
                System.out.println(buttons[0].getText());
                if(buttons[0].getText().equals("X")) {
                    return 'X';
                }
                else {
                    return 'O';
                }
            }
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) ) {
                if(buttons[3].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8)) {
                if(buttons[6].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }
                //vertical win check
            else if ( checkAdjacent(0,3) && checkAdjacent(3,6)) {
                if(buttons[0].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7)) {
                if(buttons[1].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8)) {
                if(buttons[2].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }

                //diagonal win check
            else if ( checkAdjacent(0,4) && checkAdjacent(4,8)){
                if(buttons[0].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }
            else if ( checkAdjacent(2,4) && checkAdjacent(4,6)) {
                if(buttons[2].getText().equals("X"))
                    return 'X';
                else
                    return 'O';
            }
            else
                return '#';
        }

        public boolean checkAdjacent(int a, int b){
            if ( buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("") )
                return true;
            else
                return false;
        }

    }

    public static void main(String[] args){
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new Main());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}
