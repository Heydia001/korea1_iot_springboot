package org.example.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 에너테이션
// SpringBoot 에 대한 기본 설정을 제공

// @SpringBootConfiguration
// SpringBoot 관련 설정 - @Configuration 을 상속 받음

// @ComponentScan
// 사용자가 등록한 빈을 읽고 등록
// @Component 라는 어노테이션을 가진 클래스를 찾아 빈으로 등록하는 역할
// @Configuration: 설정파일 등록
// @Controller, @RestController: 라우터
// @Service: 비지니스 로직

// @EnableAutoConfiguration
// : 스트링부트에서 자동 구성을 활성화 하는 애너테이션


@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
        // SpringApplication.run()
        // 어플리케이션 실행
        // - 첫 번째 인자: 스프링 부트3 어플리케이션의 메인 클래스로 활용할 클래스
        // - 두 번째 인자: 커멘드 라인의 인수를 전달
    }

//    public CommandLineRunner run(ApplicationContext ctx) {
//        return args -> {
//            BookStore2 store2 = ctx.getBean(BookStore2.class);
//            store2.display();
//        }
//    }
}
