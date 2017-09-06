package ag.springcoloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

////@Configuration
////@EnableAutoConfiguration
////@RestController
//@SpringBootApplication
//@EnableDiscoveryClient
//public class App {
//	
////	@Value("${config.name}")
////	String name = "World";
////
////	@RequestMapping("/")
////	public String home() {
////		return "Hello " + name;
////	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(App.class, args);
//	}
//}

//@Controller
//@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigServer
public class App {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}