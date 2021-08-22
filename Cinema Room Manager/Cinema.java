package cinema;
import java.util.Scanner;

public class Cinema {
    
    public static void fillCinema(int rows, int cols, char[][] cinema) {
        cinema[0][0] = ' ';
        
        for(int i = 0; i <= rows; i ++) {
            for(int j = 0; j <= cols; j++) {
                if(i == 0 && j != 0) {
                    cinema[i][j] = (char)(j + '0');
                } else if(j == 0 && i != 0) {
                    cinema[i][j] = (char)(i + '0');
                } else if(i != 0 && j != 0){
                    cinema[i][j] = 'S';
                }
            }
        }
    }
    
    public static int calculateTotalIncome(int rows, int cols) {
        int totalIncome = 0;
        if(rows * cols <= 60) {
            totalIncome = rows * cols * 10;
        }
        else {
            int helper = rows / 2;
            totalIncome = helper * cols * 10 + (rows - helper) * cols * 8;
        }
        return totalIncome;
    }
    
    public static int calculatePrice(int rows, int cols, int seatRow) {
        int price = 0;
        if(rows * cols <= 60) {
            price = 10;
        } else if(seatRow <= rows / 2){
            price = 10;  
        } else {
            price = 8;
        }
            
        System.out.println("Ticket price: $" + price);
        return price;
    }
    
    public static void printCinema(int rows, int cols, char[][] cinema) {
        System.out.println("Cinema:");
        
        for(int i = 0; i <= rows; i ++) {
            for(int j = 0; j <= cols; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {        
        Scanner scanner = new Scanner(System.in); 
        int rows, cols, seatRow, seatCol, totalIncome = 0, income = 0;
    
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        
        System.out.println("Enter the number of seats in each row:");
        cols = scanner.nextInt();
        
        char[][] cinema = new char[rows + 1][cols + 1];
        fillCinema(rows, cols, cinema);
        totalIncome = calculateTotalIncome(rows, cols);
        
        while(true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            
            int choose = scanner.nextInt();
            
            switch(choose) {
                case 1:
                    printCinema(rows, cols, cinema);
                    break;
                
                case 2:
                    while (true) {
                        System.out.println("Enter a row number:");
                        seatRow = scanner.nextInt();
                        
                        System.out.println("Enter a seat number in that row:");
                        seatCol = scanner.nextInt();

                        if (seatRow < 0 || seatCol < 0 || seatRow > rows || seatCol > cols) {
                            System.out.println("Wrong input!");
                        } else if(cinema[seatRow][seatCol] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            break;
                        }
                    }
                    
                    cinema[seatRow][seatCol] = 'B';
                    income += calculatePrice(rows, cols, seatRow);
                    break;
                    
                case 0:
                    return;
                    
                case 3:
                    int taken = 0;
                    for(int i = 0; i <= rows; i++) {
                        for (int j = 0; j <= cols; j++) {
                            if (cinema[i][j] == 'B') {
                                taken++;
                            }
                        }
                    }
                    
                    double perc = taken == 0 ? 0 : (double)(taken * 100) / (rows * cols);
                    System.out.printf("Number of purchased tickets: %d %n", taken);
                    System.out.printf("Percentage: %.2f%% %n", perc);
                    System.out.printf("Current income: $%d %n", income);
                    System.out.printf("Total income: $%d %n", totalIncome);
            }    
        }
        
    }
}