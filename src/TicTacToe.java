import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 650;
	JFrame frame = new JFrame("Tic-tac-toe");
	JLabel textLabel = new JLabel();
	JPanel textPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JButton[][] bord = new JButton[3][3];
	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX;
	boolean gameOver = false;
	TicTacToe(){
		frame.setVisible(true);
		frame.setSize(boardWidth,boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		textLabel.setBackground(Color.darkGray);
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("Arial", Font.BOLD, 50));
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText("tic-tac-toe");
		textLabel.setOpaque(true);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textLabel);
		frame.add(textPanel,BorderLayout.NORTH);
		boardPanel.setLayout(new GridLayout(3,3));
		boardPanel.setBackground(Color.darkGray);
		frame.add(boardPanel);
		for (int r = 0 ; r<3;r++) {
			for (int c = 0 ; c<3;c++) {
				JButton tile = new JButton();
				bord[r][c] = tile;
				boardPanel.add(tile);
				tile.setBackground(Color.darkGray);
				tile.setForeground(Color.white);
				tile.setFont(new Font("Arial" , Font.BOLD, 50));
				tile.setFocusable(false);
				tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            checkWinner1();
                            checkWinner2();
                            checkWinner3();
                            checkWinner4();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }

                    }
                });
				
			}
		}
		JButton restartButton = new JButton("Restart");
		restartButton.setBackground(Color.DARK_GRAY);
		restartButton.setForeground(Color.white);
		
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        frame.add(restartButton, BorderLayout.SOUTH);
	}
	void checkWinner1() {
	    for(int r = 0; r < 3; r++) {
	        if(bord[r][0].getText().equals("")) continue;
	        if(bord[r][0].getText().equals(bord[r][1].getText()) && bord[r][1].getText().equals(bord[r][2].getText())) {
	            for(int i = 0; i < 3; i++) {
	                setWinner(bord[r][i]);
	            }
	            gameOver = true;
	            return;
	        }
	    }
	}
	void checkWinner2() {
		for (int r = 0 ; r<3; r++) {
			if (bord[0][r].getText().equals("")) continue;
			if(bord[0][r].getText() == bord[1][r].getText() && bord[1][r].getText() == bord[2][r].getText() ) {
				for (int i = 0 ; i < 3 ; i++) {
					setWinner(bord[i][r]);
				}
				gameOver = true;
				return;
			}
		}
	}
	void checkWinner3() {
	    if (!bord[0][0].getText().equals("")) {
	        if (bord[0][0].getText().equals(bord[1][1].getText()) && bord[1][1].getText().equals(bord[2][2].getText())) {
	            for (int i = 0; i < 3; i++) {
	                setWinner(bord[i][i]);
	            }
	            gameOver = true;
	            return;
	        }
	    }
	}
	void checkWinner4() {
	    if (!bord[2][2].getText().equals("")) {
	        if (bord[0][2].getText().equals(bord[1][1].getText()) && bord[1][1].getText().equals(bord[2][0].getText())) {
	            for (int i = 0; i < 3; i++) {
	                setWinner(bord[i][2-i]);
	            }
	            gameOver = true;
	            return;
	        }
	    }
	}

	
	

	void setWinner(JButton tile) {
		tile.setBackground(Color.gray);
		tile.setForeground(Color.green);
		textLabel.setText(currentPlayer + "is the winner");
	}
	void resetGame() {
		for(int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j<3; j ++ ) {
				bord[i][j].setText("");
				bord[i][j].setBackground(Color.DARK_GRAY);
				bord[i][j].setForeground(Color.white);
			}
		}
		gameOver = false ; 
		currentPlayer = "X";
		textLabel.setText(currentPlayer + "'s turn.");
	}
}