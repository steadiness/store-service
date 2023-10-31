package edu.storeservice.core.ch1.diexample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleControllerTest {

    @Autowired
    SampleController sampleController;

    @Test
    @DisplayName("DI 방법 4가지")
    void diTest(){
        sampleController.send();
    }

}