import java.security.SecureRandom;
import java.util.Scanner;

public class Main 
{
    private static final String Uppercase_Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String Lowercase_Chars = "abcdefghijklmnopqrstuvwxyz";
    private static final String Numbers = "0123456789";
    private static final String Spl_Chars =  "!@#$%^&*()-_=+[]{}|;:,.<>?";

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        // Getting User Input
        System.out.print("Enter the length of the password: ");
        int passwordLength = sc.nextInt();
        if(passwordLength<5) 
        {
            System.out.println("\n Your password strength is weak");
        } else if(passwordLength<8)
        {
            System.out.println("\n Your password strength is medium");
        } else 
        {
            System.out.println("\n Your password strength is strong");
        }

        System.out.println("Choose the character types to include in the password");
        System.out.println("1 Uppercase letters");
        System.out.println("2 Lowercase letters");
        System.out.println("3 Numbers");
        System.out.println("4 Symbols");
        System.out.println("Enter the numbers corresponding to the character types ");
        String charTypes = sc.next();

        boolean includeUppercase = charTypes.contains("1");
        boolean includeLowercase = charTypes.contains("2");
        boolean includeNumbers = charTypes.contains("3");
        boolean includeSpecialChars = charTypes.contains("4");

        // Error Handling
        if (passwordLength < 1 || (!includeUppercase && !includeLowercase && !includeNumbers && !includeSpecialChars)) 
        {
            System.out.println("Invalid inputs. Please try again.");
            return;
        }

        // Password Generation
        String password = generatePassword(passwordLength, includeUppercase, includeLowercase,includeNumbers, includeSpecialChars);

        // Display Result
        System.out.println("Your Generated Password is " + "'" + password + "'");
        
    }

    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSpecialChars)
    {
        StringBuilder password = new StringBuilder();
        String validChars = "";

        if (includeUppercase) 
        {
            validChars += Uppercase_Chars;
            password.append(getRandomChar(Uppercase_Chars));
        }

        if (includeLowercase) 
        {
            validChars += Lowercase_Chars;
            password.append(getRandomChar(Lowercase_Chars));
        }

        if (includeNumbers) 
        {
            validChars += Numbers;
            password.append(getRandomChar(Numbers));
        }

        if (includeSpecialChars) 
        {
            validChars += Spl_Chars;
            password.append(getRandomChar(Spl_Chars));
        }

        SecureRandom random = new SecureRandom();
        int remainingLength = length - password.length();

        for (int i = 0; i < remainingLength; i++)
        {
            password.append(getRandomChar(validChars));
        }

        // Shuffle the password to ensure random distribution of characters
        for (int i = password.length() - 1; i > 0; i--)
        {
            int j = random.nextInt(i + 1);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }

        return password.toString();
    }
    

    private static char getRandomChar(String charSet)
    {
        SecureRandom random = new SecureRandom();
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }
}