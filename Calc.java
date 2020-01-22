import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Calc {

    private Scanner scan = new Scanner(System.in);

    public String request() throws InputException
    {
        System.out.println("Введите выражение");
        String expres = scan.nextLine();
        String[] expres_mas = expres.split("(?!^)\\b");
        if (isFormat(expres_mas)){
            return treatment(expres_mas);
        } else {
            throw new InputException("incorrect input");
        }
    }

    private String treatment(String[] expr){
        if(Roman.isRoman(expr[0]))
        {
            int a = Roman.romanToArabic(expr[0]);
            int b = Roman.romanToArabic(expr[2]);
            char oper = expr[1].charAt(0);
            int result = this.calculate(a,b,oper);
            return Roman.arabicToRoman(result);
        }

        int a = parseInt(expr[0]);
        int b = parseInt(expr[2]);
        char oper = expr[1].charAt(0);

        return String.valueOf(this.calculate(a,b,oper));

    }

    private boolean isFormat(String[] expr)
    {
        expr[1] = expr[1].trim();
        if (expr.length == 3)
        {
            if (expr[1].matches("\\*|/|\\+|-")) {
                if (Roman.isRoman(expr[0]) && Roman.isRoman(expr[2])) {
                    return true;
                }else if (expr[0].matches("[1-9]|10")&& expr[2].matches("[1-9]|10"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCorrectAnswer(double answer)
    {
        return answer % 1 == 0 && answer > 0;
    }

    private int calculate(int a, int b, char oper) throws CalculateException
    {
        double result = 0;
        switch (oper)
        {
            case '+':
                result = a+b;
                break;
            case '-':
                result = a-b;
                break;
            case '*':
                result = a*b;
                break;
            case '/':
                result = (double) a/b;
                break;
            default:
                result =  -1;
        }
        if (this.isCorrectAnswer(result))
        {
            return (int)result;
        } else {
            throw new CalculateException("incorrect answer");
        }
    }
}
