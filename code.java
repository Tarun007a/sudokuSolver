import java.util.Scanner;
interface methodsForSudoku{
    boolean isValid(char[][] board);
    void sudokuSolver(char[][] board);
}
class sudoku implements methodsForSudoku{

    public static int check = 0;//tell's us weather sudoku is solved or not
    public boolean isValid(char[][] board){//tells the solution you made is correct or not
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char num = board[i][j];
                if(num == '.'){//throw an exception here
                    System.out.println("fill the sudoku first");
                }
                board[i][j] = '.';
                if(!valid(board,i,j,num))return false;
                board[i][j] = num;
            }
        }
        return false;
    }


    public void sudokuSolver(char[][] board) {
        solve(board,0,0);
        check = 0;
    }


public void solve(char[][] board , int row , int col){
    if(row == 9){
        check = 1;
        return;
    }

    else if(board[row][col] != '.'){
        if(col == 8) solve(board , row + 1 , 0);
        else solve(board , row , col + 1);
    }

    else{
        for(char i = '1' ; i <= '9' ; i++){
            if(valid(board , row , col , i)){
                board[row][col] = i;
                if(col == 8) solve(board , row + 1 , 0);
                else solve(board , row , col + 1);
                if(check == 1) return;
                board[row][col] = '.';
            }
        }
    }
}


public boolean valid(char[][]board , int row , int  col , char ch){
    for(int j = 0 ; j < 9 ; j++){
        if(board[row][j] == ch) return false;
    }
    for(int i = 0 ; i < 9 ; i++){
        if(board[i][col] == ch) return false;
    }
    row = row / 3 * 3;
    col = col / 3 * 3;
    for(int i = row ; i < row + 3 ; i++){
        for(int j = col ; j < col + 3 ; j++){
            if(board[i][j] == ch) return false;
        }
    }
    return true;
}
}
public class sudokuSolverProject {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[9][9];
        //char[][] board = [['5','3','.','.','7','.','.','.'],['6','.','.','1','9','5','.','.'],['. ','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.','3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['.','.','.','.','8','.','.','7','9']];
        System.out.println("I can do 2 things check weather the solution you made is correct or solve a sudoku.");
        System.out.println("Enter 1 for check.");
        System.out.println("Enter 2 for solution of sudoku.");
        System.out.println("Enter 3 for exit.");
        System.out.println("If you enter 1 you should put all the values otherwise you will ger error");
        while(true){
            sudoku obj = new sudoku();
            System.out.print("Enter number : ");
            int choice = sc.nextInt();
            System.out.println("Enter the value's of board using a space to separate them");
            System.out.println("Enter . if there is no value");
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    board[i][j] = sc.next().charAt(0);
                }
            }


            if(choice == 1){
                System.out.println(obj.isValid(board));
            }
            else if(choice == 2){
                obj.sudokuSolver(board);
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println();
                }
            }
            else break;
        }
    }
}
