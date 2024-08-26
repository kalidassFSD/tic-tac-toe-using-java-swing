import javax.swing.*;
import java.awt.*;

public class TicTacToeGame  {
    public static void main(String[] args) {

        TicTacToe frame = new TicTacToe();
    }
    public static class TicTacToe extends JFrame {

        JLabel label=new JLabel();
        JPanel panel=new JPanel();
        JPanel board= new JPanel();
        JButton[][] button = new JButton[3][3];
        String currentPlayer="X";
        boolean gameOver = false;
        int draw=0;
        public TicTacToe(){

                setVisible(true);
                setSize(500,500);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop the program while hit the x
                setLocationRelativeTo(null);  // for center appearance of the frame in window
                setResizable(false); //user can't resize the window
            //top label
                label.setForeground(Color.white);
                label.setBackground(Color.DARK_GRAY);
                label.setOpaque(true);
                label.setFont(new Font("TimesNewRoman",Font.BOLD,50));
                label.setText("Tic Tac Toe");
                label.setHorizontalAlignment(JLabel.CENTER);

                panel.setLayout(new BorderLayout());
                panel.add(label);
                add(panel,BorderLayout.NORTH);
                //board layout
                board.setLayout(new GridLayout(3,3));
                board.setBackground(Color.GRAY);
                add(board);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j <3 ; j++) {
                    JButton box = new JButton();
                    button[i][j] = box;
                    board.add(box);

                    box.setForeground(Color.white);
                    box.setBackground(Color.darkGray);
                    box.setFont(new Font("TimesNewRoman",Font.BOLD,120));
                    box.setFocusable(false);


                    box.addActionListener(ae -> {
                        if(gameOver)
                              return;
                        if(box.getText().isEmpty()) {

                            box.setText(currentPlayer);
                            winCheck();

                            if(!gameOver && draw!=9){
                                draw++;
                                if (currentPlayer.equals("X"))
                                    currentPlayer = "O";

                                else
                                    currentPlayer = "X";
                                if(draw==9)
                                    label.setText("Game Draw");
                                else
                                   label.setText(currentPlayer + "s Turn");
                            }

                        }
                  });
                }
            }
        }
         void winCheck() {
            for (int i = 0, j = 0; i < 3; i++) {
                //horizontal win
                if (button[i][j].getText().equals(button[i][j + 1].getText()) && button[i][j + 2].getText().equals(button[i][j].getText())
                        && !button[i][j].getText().isEmpty() && !button[i][j + 1].getText().isEmpty() && !button[i][j + 2].getText().isEmpty())
                {
                    gameOver = true;
                    for (int k = 0; k < 3; k++) {
                        winner(button[i][k]);
                    }


                }
                //vertical win
                else if (button[j][i].getText().equals(button[j + 1][i].getText()) && button[j][i].getText().equals(button[j + 2][i].getText())
                        && !button[j][i].getText().isEmpty() && !button[j + 1][i].getText().isEmpty() && !button[j + 2][i].getText().isEmpty())
                {
                    gameOver = true;
                    for (int k = 0; k < 3; k++) {
                        winner(button[k][i]);
                    }

                }
            }
            //diagnol win
             if(button[0][0].getText().equals(button[1][1].getText()) && button[1][1].getText().equals(button[2][2].getText())
                     && !button[1][1].getText().equals("")&& !button[0][0].getText().equals("")&& !button[2][2].getText().equals(""))
             {
                 gameOver = true;
                 for (int k = 0; k < 3; k++) {
                     winner(button[k][k]);
                 }

             }
            // anti-diagnol win
             else if(button[0][2].getText().equals(button[1][1].getText()) && button[1][1].getText().equals(button[2][0].getText())
                     && !button[2][0].getText().equals("")&& !button[1][1].getText().equals("")&& !button[2][0].getText().equals(""))
             {
                 gameOver = true;
                 winner(button[0][2]);
                 winner(button[1][1]);
                 winner(button[2][0]);
             }

        }
        void winner(JButton button){
            button.setForeground(Color.green);
            label.setText(currentPlayer +"'s wins");
        }

    }
}


