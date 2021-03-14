package TaskTacking.Controllers;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CommandsController {
    @FXML
    Pane addingPane , editPane ,filterPane ,addingShowPane ,editShowPane ,filterShowPane, choicePane;
    @FXML
    ImageView backButton ;
    @FXML
    public void handleBack(){
        choicePane.setVisible(true);
        addingShowPane.setVisible(false);
        editShowPane.setVisible(false);
        filterShowPane.setVisible(false);
        backButton.setVisible(false);
    }
    @FXML public void enterAdding(){addingPane.setOpacity(1); editPane.setOpacity(0.5); filterPane.setOpacity(0.5);}
    @FXML public void enterEdit(){editPane.setOpacity(1); filterPane.setOpacity(0.5); addingPane.setOpacity(0.5);}
    @FXML public void enterFilter(){filterPane.setOpacity(1); editPane.setOpacity(0.5); addingPane.setOpacity(0.5);}
    @FXML public void handleAdding(){
        addingShowPane.setVisible(true);
        editShowPane.setVisible(false);
        filterShowPane.setVisible(false);
        backButton.setVisible(true);
    }
    @FXML public void handleEdit(){
        addingShowPane.setVisible(false);
        editShowPane.setVisible(true);
        filterShowPane.setVisible(false);
        backButton.setVisible(true);
    }
    @FXML public void handleFilter(){
        addingShowPane.setVisible(false);
        editShowPane.setVisible(false);
        filterShowPane.setVisible(true);
        backButton.setVisible(true);
    }
    @FXML public void handleHome(){
        try {
            FXRouter.goTo("General");
        }
        catch (IOException e){
            System.err.println("ไปที่หน้า main ไม่ได้");
        }
    }
}
