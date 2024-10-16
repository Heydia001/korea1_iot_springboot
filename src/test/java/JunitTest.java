import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JunitTest {
    @DisplayName("1 + 2 = 3 이다.")  // Test의 이름을 명시
    @Test // 해당 에너테이션을 붙인 메서드는 테스트를 직접 수행하는 메서드
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a+b);
    }

//    @DisplayName("1 + 3 = 4")
//    @Test
//    public void junitFailedTest() {
//        int a = 1;
//        int b = 3;
//        int sum = 3;
//
//        Assertions.assertEquals(sum, a+b);
//    }
}
