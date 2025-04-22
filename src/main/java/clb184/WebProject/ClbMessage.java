package clb184.WebProject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClbMessage {
    
    @GetMapping("/Msg")
    public String YieldMessage() {
        return "メセージ";
    }
}
