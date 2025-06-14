package io.ourbatima.core.interfaces;

import io.ourbatima.core.Context;
import io.ourbatima.core.impl.IContext;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public abstract class ActionView implements Initializable {


    public Context context = null;
    public IContext icontext = null;


    // Méthode pour recevoir le contexte de navigation

    public void onEnter() {} ;

    public void onExit() {};

    public void onInit(Context context)  {
        this.context = context;
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
