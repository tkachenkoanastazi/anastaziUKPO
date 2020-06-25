import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class ExprCheckTest {
    @Test
    public void normal() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="aa\\*";
            ExprCheck check =new ExprCheck();
            boolean result=check.check(str);
            Assert.assertEquals(false,result);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    @Test
    public void starPlus() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="aa*+aaa";
            ExprCheck check =new ExprCheck();
            boolean result=check.check(str);
            Assert.assertEquals(true,result);
        }, () -> "Тест выполняется больше 1000 ms");
    }
    @Test
    public void plusStar() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String str="aa+*aaa";
            ExprCheck check =new ExprCheck();
            boolean result=check.check(str);
            Assert.assertEquals(true,result);
        }, () -> "Тест выполняется больше 1000 ms");
    }



}