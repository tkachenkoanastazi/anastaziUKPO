import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ParserWhiteTest {
    Parser parse = new Parser();
    @Test
    public void dot_end() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () ->
        {
            String str="a.";
            char[][] result=parse.exprParser(str);
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
            char[][] result=parse.exprParser(str);
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
            char[][] result=parse.exprParser(str);
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
    public void plusOrStar() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="*";
            char[][] result=parse.exprParser(str);
            char[][] actual=new char[2][4];
            actual[0]=" ".toCharArray();
            actual[1][0]=2;
            actual[1][1]=0;
            actual[1][2]=2;
            actual[1][3]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    @Test
    public void symbol_end() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="aaaa";
            char[][] result=parse.exprParser(str);
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

            char[][] result=parse.exprParser(str);
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
            char[][] result=parse.exprParser(str);
            char[][] actual=new char[2][3];
            actual[0]="aaa".toCharArray();
            actual[1][0]=0;
            actual[1][1]=2;
            actual[1][2]=2;
            assertEquals(result, actual);
        }, () -> "Тест выполняется больше 1000 ms");
    }

}