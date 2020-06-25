import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ParserWhiteTest {
    Parser parser = new Parser();
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    @Test
    public void dot_end() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () ->
        {
            String str="a.";
            long length = parser.lengthOfSuperExpression(str);
            char[][] result=parser.exprParser(str,(int)length);
            char[][] actual=new char[2][2];
            actual[0]="a.".toCharArray();
            actual[1][0]=2;
            actual[1][1]=2;
            assertEquals( actual, result);
        }, () -> "Тест выполняется больше 1000 ms");
    }

    @Test
    public void lonelyDot_notEnd() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="a.aa";
            long length = parser.lengthOfSuperExpression(str);
            char[][] result=parser.exprParser(str,(int)length);
            char[][] actual=new char[2][4];
            actual[0]="a.aa".toCharArray();
            actual[1][0]=2;
            actual[1][1]=2;
            actual[1][2]=2;
            actual[1][3]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    @Test
    public void oneMoreDot_notEnd() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="a.+aa";
            long length = parser.lengthOfSuperExpression(str);
            char[][] result=parser.exprParser(str,(int)length);
            char[][] actual=new char[2][4];
            actual[0]="a.aa".toCharArray();
            actual[1][0]=2;
            actual[1][1]=1;
            actual[1][2]=2;
            actual[1][3]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    @Test
    public void symbol_end() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="aaaa";
            long length = parser.lengthOfSuperExpression(str);
            char[][] result=parser.exprParser(str,(int)length);
            char[][] actual=new char[2][4];
            actual[0]="aaaa".toCharArray();
            actual[1][0]=2;
            actual[1][1]=2;
            actual[1][2]=2;
            actual[1][3]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    @Test
    public void oneMoreSymbol_notEnd() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="a+aa";
            long length = parser.lengthOfSuperExpression(str);
            char[][] result=parser.exprParser(str,(int)length);
            char[][] actual=new char[2][3];
            actual[0]="aaa".toCharArray();
            actual[1][0]=1;
            actual[1][1]=2;
            actual[1][2]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    public void zeroMoreSymbol_notEnd() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="a*aa";
            long length = parser.lengthOfSuperExpression(str);
            char[][] result=parser.exprParser(str,(int)length);
            System.out.println(result[0].length);
            for(int i=0;i<result.length;i++){
                System.out.println(result[0][i]);
                System.out.println(result[1][i]);
            }
            char[][] actual=new char[2][3];
            actual[0]="aaa".toCharArray();
            actual[1][0]=0;
            actual[1][1]=2;
            actual[1][2]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }

}