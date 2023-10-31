package edu.storeservice.core.ch1;

import edu.storeservice.core.ch1.iocexample.service.EmailService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class IocContainerTest {

    //ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanName = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("스프링 빈을 역할에 따라 구분")
    void findRoleBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            //Role ROLE_SUPPORT: 보조적으로 빈의 역할을 지정
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("ROLE_APPLICATION: name=" + beanDefinitionName + " object=" + bean);
            }

        }
    }

    @Test
    @DisplayName("pure 객체 확인")
    void pureObjectTest(){

        AppConfig appConfig = new AppConfig();

        //호출할 때마다 다른 객체 생성
        EmailService emailService1 = appConfig.emailService();
        EmailService emailService2 = appConfig.emailService();

        System.out.println("emailService1 = " + emailService1);
        System.out.println("emailService2 = " + emailService2);

        //emailService1 != emailService2
        assertThat(emailService1).isNotSameAs(emailService2);

    }

    @Test
    @DisplayName("싱글톤 빈 확인")
    void singletonTest(){

        //호출할 때마다 같은 객체 생성
        EmailService emailService1 = ac.getBean("emailService", EmailService.class);
        EmailService emailService2 = ac.getBean("emailService", EmailService.class);

        System.out.println("emailService1 = " + emailService1);
        System.out.println("emailService2 = " + emailService2);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean);

        assertThat(emailService1).isSameAs(emailService2);
    }


}
