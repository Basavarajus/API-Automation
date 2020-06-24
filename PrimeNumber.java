import java.util.Scanner;
public class PrimeNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter any number: ");
        int num = scan.nextInt();
        boolean flag = false;
        for(int i = 2; i <= num/2; ++i)
        {
            // condition for nonprime number
            if(num % i == 0)
            {
                flag = true;
                break;
            }
        }

        if (!flag)
            System.out.println(num + " is a prime number.");
        else
            System.out.println(num + " is not a prime number.");
    }
}

/*
	Cases
 -chech if the number is not a decimal no.
- check if the number is not a negative no.
- if the number is a natural number a greater than 6 then,
-if it can be converted to the form 6k-1or
6k+1,then it is prime no.else not.
- for natural nos. less than 6: 2,3 7 5 are prime nos. to
be taken as granted. */