import org.junit.jupiter.api.*;

public class JunitCycleTest {
    // 전체 테스트를 시작하기 전에 1회 실행
    // : static 키워드로 고정됭 값을 실행
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    // 테스트 케이스를 시작하기 전 마다 실행
    // : public 설정
    @BeforeEach
    public void beforeEach(){
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test3");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }
    // 전체 테스트를 마치고 종료하기 전에 1회 실행하기 때문에
    // static 메서드로 선언
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    //테스트 케이스를 종료하기 전마다 실행
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
