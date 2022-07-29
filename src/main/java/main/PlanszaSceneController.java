package main;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.Random;

public class PlanszaSceneController {
    public Button bt00;
    public Button bt10;
    public Button bt20;
    public Button bt01;
    public Button bt11;
    public Button bt21;
    public Button bt02;
    public Button bt12;
    public Button bt22;
    public Label gracz;
    public Label ruch;

    public static int movment = 0;
    public Label wynik;
    public CheckBox typPlayer;
    public CheckBox typPlayer2;
    //1 is x
    //-1 is y
    int[] decision = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    static char plansza[][] = {{ '_', '_', '_' },
            { '_', '_', '_' },
            { '_', '_', '_' }};

    public void whoWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (decision[0] + decision[1] + decision[2] == 30 ||
                decision[3] + decision[4] + decision[5] == 30 ||
                decision[6] + decision[7] + decision[8] == 30 ||
                decision[0] + decision[3] + decision[6] == 30 ||
                decision[1] + decision[4] + decision[7] == 30 ||
                decision[2] + decision[5] + decision[8] == 30 ||
                decision[6] + decision[4] + decision[2] == 30 ||
                decision[0] + decision[4] + decision[8] == 30
        ) {
            wynik.setText("WYGRYWA O");
            alert.setContentText("Wygrywa O");
            alert.setHeaderText("Koniec Gry");
            alert.show();

        } else if (decision[0] + decision[1] + decision[2] == -30 ||
                decision[3] + decision[4] + decision[5] == -30 ||
                decision[6] + decision[7] + decision[8] == -30 ||
                decision[0] + decision[3] + decision[6] == -30 ||
                decision[1] + decision[4] + decision[7] == -30 ||
                decision[2] + decision[5] + decision[8] == -30 ||
                decision[6] + decision[4] + decision[2] == -30 ||
                decision[0] + decision[4] + decision[8] == -30) {
            wynik.setText("WYGRYWA X");
            alert.setContentText("Wygrywa X");
            alert.setHeaderText("Koniec Gry");
            alert.show();

        } else {
            wynik.setText("REMIS");
            alert.setContentText("Remis");

        }
        if (decision[0] != 0
                && decision[1] != 0
                && decision[2] != 0
                && decision[3] != 0
                && decision[4] != 0
                && decision[5] != 0
                && decision[6] != 0
                && decision[7] != 0
                && decision[8] != 0) {
            alert.setHeaderText("Koniec Gry");
            alert.show();
        }
    }

    //  f0 || f1 || f2
    //  = = + = = + = =
    //  f3 || f4 || f5
    //  = = + = = + = =
    //  f6 || f6 || f8

    public void randomMovment(int zakazana) {
        Random random = new Random();
        if (decision[0] == 0
                || decision[1] == 0
                || decision[2] == 0
                || decision[3] == 0
                || decision[4] == 0
                || decision[5] == 0
                || decision[6] == 0
                || decision[7] == 0
                || decision[8] == 0) {
            int r = random.nextInt(9);
            if (r != zakazana) {
                //POLE f0
                if (r == 0 && decision[0] == 0) {
                    decision[0] = -10;
                    bt00.setText("SI");
                    bt00.setDisable(true);
                }

                //POLE f1
                else if (r == 1 && decision[1] == 0) {
                    decision[1] = -10;
                    bt01.setText("SI");
                    bt01.setDisable(true);

                }

                //POLE f2
                else if (r == 2 && decision[1] == 0) {
                    decision[2] = -10;
                    bt02.setText("SI");
                    bt02.setDisable(true);

                }

                //Pole f3
                else if (r == 3 && decision[3] == 0) {
                    decision[3] = -10;
                    bt10.setText("SI");
                    bt10.setDisable(true);

                }

                //Pole f4
                else if (r == 4 && decision[4] == 0) {
                    decision[4] = -10;
                    bt11.setText("SI");
                    bt11.setDisable(true);

                }

                //Pole f5
                else if (r == 5 && decision[5] == 0) {
                    decision[5] = -10;
                    bt12.setText("SI");
                    bt12.setDisable(true);
                }
                //POLE f6
                else if (r == 6 && decision[6] == 0) {
                    decision[6] = -1;
                    bt20.setText("SI");
                    bt20.setDisable(true);
                }

                //Pole f7
                else if (r == 7 && decision[7] == 0) {
                    decision[7] = -10;
                    bt21.setText("SI");
                    bt21.setDisable(true);
                }
                //Pole f8
                else if (r == 8 && decision[8] == 0) {
                    decision[8] = -10;
                    bt22.setText("SI");
                    bt22.setDisable(true);
                } else {
                    randomMovment(r);
                }

                movment = 0;
                gracz.setText("X");
            } else {
                randomMovment(r);
            }
        } else {
            System.out.println("Brak wolnych pól");
        }
    }


    static class Move {
        int row, col;
    }



    static char player = 'o', opponent = 'x';

    //sprawdzamy czy pole jest puste
    static Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

   //sprawdzanie wygrango
    static int evaluate(char b[][]) {
        //sprawdzanie wierszy
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == player)
                    return -10;
                else if (b[row][0] == opponent)
                    return +10;
            }
        }

        // sprawdzanie kolumn
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == player)
                    return -10;

                else if (b[0][col] == opponent)
                    return +10;
            }
        }

        //sprawdzanie przekontnej
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return -10;
            else if (b[0][0] == opponent)
                return +10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return -10;
            else if (b[0][2] == opponent)
                return +10;
        }

       //remis
        return 0;
    }


    //funkcja minmax
    static int minimax(char board[][],
                       int depth, Boolean isMax) {
        int score = evaluate(board);

        // max val
        if (score == 10)
            return score;

        // min val
        if (score == -10)
            return score;

        // Remis
        if (isMovesLeft(board) == false)
            return 0;

        // max ruch
        if (isMax) {
            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == '_') {
                        // ruch komputera
                        board[i][j] = player;

                        // sprawdz max
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // minmalizer ruch
        else {
            int best = 1000;

        //przeleć po komurkach
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // czy puste
                    if (board[i][j] == '_') {
                        // ruch człowieka
                        board[i][j] = opponent;

                        // Cwybieranie minimum
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    //zwreaca najlepszy ruch dla gracza

    static Move findBestMove(char board[][]) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == '_') {
                    // Make the move
                    board[i][j] = player;


                    int moveVal = minimax(board, 3, false);

                    board[i][j] = '_';
                    // Jeśli wartość tymczasowa
                    // jest wieksza od najlepszej zamień ją
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return bestMove;
    }


    public void clikField0(ActionEvent actionEvent) {
        //gracz X
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("O");
            ruch.setText(String.valueOf(movment));
            bt00.setText("X");
            decision[0] = 10;
            plansza[0][0]='x';
            bt00.setDisable(true);

            if (typPlayer.isSelected()) {
                randomMovment(0);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                   if(move.col==0){
                       decision[0] = -10;
                       bt00.setText("SI");
                       bt00.setDisable(true);
                       plansza[0][0]='o';
                   }else if(move.col==1){
                       decision[1] = -10;
                       bt01.setText("SI");
                       bt01.setDisable(true);
                       plansza[0][1]='o';
                   }
                   else if(move.col==2){
                       decision[2] = -10;
                       bt02.setText("SI");
                       bt02.setDisable(true);
                       plansza[0][2]='o';
                   }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz 2

        else {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));
            gracz.setText("X");


            bt00.setText("0");
            decision[0] = -10;
            bt00.setDisable(true);

            whoWin();

        }
    }

    public void clikField1(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt01.setText("X");
            decision[1] = 10;
            plansza[0][1]='x';
            bt01.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(1);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt01.setText("0");
            decision[1] = -10;
            bt01.setDisable(true);
            whoWin();
        }
    }

    public void clikField2(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt02.setText("X");
            decision[2] = 10;
            plansza[0][2]='x';
            bt02.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(2);
            }
            else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt02.setText("0");
            decision[2] = -10;
            bt02.setDisable(true);

        }
    }

    public void clikField3(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt10.setText("X");
            decision[3] = 10;
            plansza[1][0]='x';
            bt10.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(3);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt10.setText("0");
            decision[3] = -10;
            bt10.setDisable(true);
            whoWin();
        }

    }

    public void clikField4(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt11.setText("X");
            decision[4] = 10;
            plansza[1][1]='x';
            bt11.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(4);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));
            bt11.setText("0");
            decision[4] = -10;
            bt11.setDisable(true);

        }
    }

    public void clikField5(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt12.setText("X");
            decision[5] = 10;
            plansza[1][2]='x';
            bt12.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(5);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt12.setText("0");
            decision[5] = -10;
            bt12.setDisable(true);
            whoWin();
        }

    }

    public void clikField6(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt20.setText("X");
            decision[6] = 10;
            plansza[2][0]='x';
            bt20.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(6);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt20.setText("0");
            decision[6] = -10;
            bt20.setDisable(true);

        }
    }

    public void clikField7(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt21.setText("X");
            plansza[2][1]='x';
            decision[7] = 10;
            bt21.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(7);
            }else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt21.setText("0");
            decision[7] = -10;
            bt21.setDisable(true);
            whoWin();
        }
    }

    public void clikField8(ActionEvent actionEvent) {
        //gracz 0
        if (movment % 2 == 0) {
            movment++;
            gracz.setText("o");
            ruch.setText(String.valueOf(movment));

            bt22.setText("x");
            decision[8] = 10;
            plansza[2][2]='x';
            bt22.setDisable(true);
            if (typPlayer.isSelected()) {
                randomMovment(8);
            } else if (typPlayer2.isSelected()) {
                Move move=findBestMove(plansza);
                System.out.printf("ROW: %d COL: %d\n\n",
                        move.row, move.col );
                //wiersz pierwsz
                if(move.row==0){
                    if(move.col==0){
                        decision[0] = -10;
                        bt00.setText("SI");
                        bt00.setDisable(true);
                        plansza[0][0]='o';
                    }else if(move.col==1){
                        decision[1] = -10;
                        bt01.setText("SI");
                        bt01.setDisable(true);
                        plansza[0][1]='o';
                    }
                    else if(move.col==2){
                        decision[2] = -10;
                        bt02.setText("SI");
                        bt02.setDisable(true);
                        plansza[0][2]='o';
                    }
                }
                //wiersz drugi
                else if(move.row==1){
                    if(move.col==0){
                        decision[3] = -10;
                        bt10.setText("SI");
                        bt10.setDisable(true);
                        plansza[1][0]='o';
                    }else if(move.col==1){
                        decision[4] = -10;
                        bt11.setText("SI");
                        bt11.setDisable(true);
                    }
                    else if(move.col==2){
                        decision[5] = -10;
                        bt12.setText("SI");
                        bt12.setDisable(true);
                        plansza[1][2]='o';
                    }
                }
                //wiersz trzeci
                else if(move.row==2){
                    if(move.col==0){
                        decision[6] = -10;
                        bt20.setText("SI");
                        bt20.setDisable(true);
                        plansza[2][0]='o';
                    }else if(move.col==1){
                        decision[7] = -10;
                        bt21.setText("SI");
                        bt21.setDisable(true);
                        plansza[2][1]='o';
                    }
                    else if(move.col==2){
                        decision[8] = -10;
                        bt22.setText("SI");
                        bt22.setDisable(true);
                        plansza[2][2]='o';
                    }
                }
                movment++;

            }
            ruch.setText(String.valueOf(movment));
            whoWin();
        }
        //gracz X
        else {
            movment++;
            gracz.setText("x");
            ruch.setText(String.valueOf(movment));

            bt22.setText("0");
            decision[8] = -10;
            bt22.setDisable(true);
            whoWin();
        }
    }

    public void newGame(ActionEvent actionEvent) {
        bt00.setText("");
        bt00.setDisable(false);
        bt10.setText("");
        bt10.setDisable(false);
        bt20.setText("");
        bt20.setDisable(false);
        bt01.setText("");
        bt01.setDisable(false);
        bt11.setText("");
        bt11.setDisable(false);
        bt21.setText("");
        bt21.setDisable(false);
        bt02.setText("");
        bt02.setDisable(false);
        bt12.setText("");
        bt12.setDisable(false);
        bt22.setText("");
        bt22.setDisable(false);
        movment = 0;
        ruch.setText(String.valueOf(movment));
        gracz.setText("X");
        decision = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        plansza=new char[][]{{ '_', '_', '_' },
            { '_', '_', '_' },
            { '_', '_', '_' }};
    }
}
