package TaskTacking.Controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AuthorController {
    @FXML
    public void handleBack(){
        try {
            FXRouter.goTo("General");
        }
        catch (IOException e){
            System.err.println("ไปที่หน้า general ไม่ได้");
        }
    }
    @FXML
    public void handleCommands(){
        try {
            FXRouter.goTo("Commands");
        }
        catch (IOException e){
            System.err.println("ไปที่หน้า commands ไม่ได้");
        }
    }
}
