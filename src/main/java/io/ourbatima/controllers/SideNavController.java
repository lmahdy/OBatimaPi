package io.ourbatima.controllers;

import io.ourbatima.core.Context;
import io.ourbatima.core.controls.icon.IconContainer;
import io.ourbatima.core.controls.icon.Icons;
import io.ourbatima.core.exceptions.NavigationException;
import io.ourbatima.core.interfaces.ActionView;
import io.ourbatima.core.model.Utilisateur.Utilisateur;
import io.ourbatima.core.model.ViewUtils;
import io.ourbatima.core.services.DrawerBehavior;
import io.ourbatima.core.view.SimpleView;
import io.ourbatima.core.view.View;
import io.ourbatima.core.view.layout.DialogContainer;
import io.ourbatima.core.view.layout.creators.TutorialCreator;
import io.ourbatima.views.TutorialUnderstanding;
import io.ourbatima.views.controls.*;
import io.ourbatima.views.layout.AccordionViewPresCreator;
import io.ourbatima.views.layout.TabPanePresCreator;
import io.ourbatima.views.layout.TitledPanePresCreator;
import io.ourbatima.views.tutorial.NewsLetter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Map;
import java.io.File;
import java.io.IOException;
/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2023
 */
public class SideNavController extends ActionView {

    public VBox faa;
    public ToggleButton contra;
    @FXML private StackPane root;
    @FXML private ToggleGroup group;
    @FXML private Button arrowButton;
    @FXML
    private Label RoleUser;

    @FXML
    private Text userNameText;
    @FXML
    private Text userEmailText;
    private Utilisateur currentUser;


    public void setBackgroundColorBasedOnRole(String role) {
        switch (role) {
            case "Artisan", "GestionnaireStock", "Client":
                root.setStyle("-fx-background-color: #c5a814;"); // Jaune
                break;
            case "Constructeur", "Admin":
                root.setStyle("-fx-background-color: #000000;"); // Gris
                break;
            // Noir
            default:
                root.setStyle("-fx-background-color: white;"); // Par défaut, blanc
                break;
        }
    }



    @FXML
    private void goDash() throws NavigationException {
        context.routes().nav("dash");
    }

    @FXML
    private void goNewsletter() {
        go("view_news", new NewsLetter(context));
    }

    @FXML
    private void goUnderstanding() {
        go("tutorial_understanding", new TutorialUnderstanding(context));
    }

    @FXML
    private void goButton() {
        go("button", new ButtonPresCreator(context));
    }

    @FXML
    private void goCheckBox() {
        go("view_check", new CheckBoxPresCreator(context));
    }

    @FXML
    private void goComboBox() {
        go("view_check", new ComboBoxPresCreator(context));
    }

    @FXML
    private void goHyperlink() {
        go("view_hyperlink", new HyperlinkPresCreator(context));
    }

    @FXML
    private void goLabels() {
        go("view_label", new LabelPresCreator(context));
    }

    @FXML
    private void goSlider() {
        go("slider", new SliderPresCreator(context));
    }

    @FXML
    private void goMenuButton() {
        go("menu_button", new MenuButtonPresCreator(context));
    }

    @FXML
    private void goListView() {
        go("view_list", new ListViewCreator(context));
    }

    @FXML
    private void goMediaView() {
        go("mediaView", new MediaViewPresCreator(context));
    }

    @FXML
    private void goPassword() {
        go("view_pass", new PasswordPresCreator(context));
    }

    @FXML
    private void goTableView() {
        go("view_table", new TableViewPresCreator(context));
    }

    @FXML
    private void goTextField() {
        go("view_label", new TextFieldPresCreator(context));
    }

    @FXML
    private void goTreeView() {
        go("view_tree", new TreeViewPresCreator(context));
    }

    @FXML
    private void goSwitch() {
        go("view_tree", new ToggleButtonPresCreator(context));
    }

    @FXML
    private void goLogin() {
        context.routes().setView("login");
    }

    @FXML
    private void goHero() {
        context.routes().nav("hero");
    }

    @FXML
    private void goErrorPage() {
        context.routes().nav("view"); // obvious error to force navigate
    }

    @FXML
    private void goPricing() {
        context.routes().nav("pricing"); // obvious error to force navigate
    }

    @FXML
    private void goFrequently() {
        context.routes().nav("frequently"); // obvious error to force navigate
    }

    @FXML
    private void goTitledPane()  {
        go("carousel", new TitledPanePresCreator(context));
    }

    @FXML
    private void goAccordion() {
        go("carousel", new AccordionViewPresCreator(context));
    }

    @FXML
    private void goProgressbar() {
        go("carousel", new ProgressBarPresCreator(context));
    }

    @FXML
    private void goCarousel() {
        go("carousel", new CarouselViewPresCreator(context));
    }

    @FXML
    private void goRating() {
        go("rating", new RatingPresCreator(context));
    }

    @FXML
    private void goMenuBar() {
        go("menu_bar", new MenuBarPresCreator(context));
    }

    @FXML
    private void goRadioButton() {
        go("radio_button", new RadioButtonPresCreator(context));
    }

    @FXML
    private void goChoiceBox() {
        go("choice_box", new ChoiceBoxPresCreator(context));
    }

    @FXML
    private void goSpinner() {
        go("spinner", new SpinnerPresCreator(context));
    }
    @FXML
    private void goColorPicker() {
        go("color_picker", new ColorPickerPresCreator(context));
    }

    @FXML
    private void goDatePicker() {
        go("date_picker", new DatePickerPresCreator(context));
    }

    @FXML
    private void goPagination() {
        go("pagination", new PaginationPresCreator(context));
    }

    @FXML
    private void goTreeTableView() {
        go("tree_table_view", new TreeTableViewPresCreator(context));
    }

    @FXML
    private void goHtmlEditor() {
        go("html_editor", new HtmlEditorViewPresCreator(context));
    }

    @FXML
    private void goTabPane() {
        go("tab_pane", new TabPanePresCreator(context));
    }

    @FXML
    private void goFooter() {
        context.routes().nav("footer");
    }

    @FXML
    private void goTestimonials() {
        context.routes().nav("testimonials");
    }

    @FXML
    private void goAbout() {
        context.routes().nav("about");
    }

    @FXML
    private void goAboutDash() {
        context.routes().nav("about_dash");
    }

    @FXML
    private void goBlog() {
        context.routes().nav("blog");
    }


    private final VBox boxUserDialog = new VBox();

    private Button createBtn(String text, EventHandler<ActionEvent> event) {

        Button btnProfile = new Button(text);
        btnProfile.setMaxWidth(Double.MAX_VALUE);
        btnProfile.getStyleClass().addAll("btn-option", "btn-flat", "no-border");

        btnProfile.setAlignment(Pos.CENTER_LEFT);
        btnProfile.setPadding(new Insets(10));
        btnProfile.setOnAction(event);

        btnProfile.addEventFilter(MouseEvent.MOUSE_CLICKED, event1 -> context.flow().close());
        return btnProfile;
    }

    private void configLayout() {

        Button btnProfile = createBtn("Profile", event -> {
//            upadteContent(context, "profile");

            context.routes().nav("profile");
            removeFocus();
        });
        btnProfile.setGraphic(new IconContainer(Icons.ACCOUNT_CIRCLE));
        Button btnSettings = createBtn("Settings", event -> {
            context.routes().nav("settings");
            removeFocus();
        });
        btnSettings.setGraphic(new IconContainer(Icons.SETTINGS_FILLED));
        Button btnLogout = createBtn("Logout", event -> {
//                upadteContent(context, "profile");
        });
        btnLogout.setGraphic(new IconContainer(Icons.LOGOUT));

        boxUserDialog.getChildren().setAll(btnProfile, btnSettings, new Separator(), btnLogout);

//        boxUser.setOnMouseClicked(event -> context.flow()
////                    .getPopup()
////                    .size(300, 150)
////                    .moveX(200)
//                .content(
//                        new DialogContainer(boxUserDialog)
//                                .size(200, 100)
//                )
//                .show(Pos.BOTTOM_LEFT, boxUser, 140));
    }

    @FXML
    private void goDataTable() {
        context.routes().nav("data_table");
    }

    @FXML
    private void goArtisan() {
        context.routes().nav("artisan_list");
    }
    @FXML
    private void goEquipe() {
        context.routes().nav("equipelist");
    }

    @FXML
    private void goConstructeur() {
        context.routes().nav("constructeur_list");
    }

    @FXML
    private void goClient() {
        context.routes().nav("client_list");
    }

    @FXML
    private void goGestionnaireStock() {
        context.routes().nav("gestionnaire_stock_list");
    }
    @FXML
    private void openUserPreferences() {
        context.flow()
//                    .getPopup()
//                    .size(300, 150)
//                    .moveX(200)
                .content(
                        new DialogContainer(boxUserDialog)
                                .size(200, 100)
                )
                .show(Pos.BOTTOM_RIGHT, arrowButton, 10, -120);
    }

    public void removeFocus() {
        if (group.selectedToggleProperty().get() != null)
            group.getSelectedToggle().setSelected(false);
    }

    private void go(String name, TutorialCreator tutorialCreator) {
        context.routes().putAndGo(new SimpleView(name, tutorialCreator));
    }

    private void createProblemView(){
        Circle rectangle = new Circle();
        rectangle.setFill(new ImagePattern(
                new Image(context.getResource("style/img/404.png").toExternalForm())
        ));
        rectangle.setRadius(200);
        context.routes().putAndGo(new SimpleView("error_404", new StackPane(rectangle)
        ));
    }

    private DrawerBehavior behavior;

    @Override
    public void onInit(Context context)  {
        if (SessionManager.getUtilisateur().getRole()!=Utilisateur.Role.Admin){
            faa.getChildren().remove(contra);
        }
        Utilisateur currentUser = SessionManager.getUtilisateur();
        if (currentUser == null) {
            System.err.println("Aucun utilisateur connecté !");
            return; // Arrêter l'exécution si l'utilisateur n'est pas connecté
        }

        // Vérifiez que les champs texte sont initialisés
        if (userNameText != null && userEmailText != null) {
            userNameText.setText(currentUser.getNom() + " " + currentUser.getPrenom());
            userEmailText.setText(currentUser.getEmail());
            RoleUser.setText(currentUser.getRole().toString());

        } else {
            System.err.println("Erreur: userNameText ou userEmailText non initialisé");
            RoleUser.setText("Inconnu");

        }

        // Appliquer la couleur de fond en fonction du rôle
        String userRole = String.valueOf(currentUser.getRole());
        setBackgroundColorBasedOnRole(userRole);

        // Initialiser le comportement du drawer
        Platform.runLater(() -> {
            behavior = new DrawerBehavior(root, group);
        });

        // Configurer le layout
        configLayout();

        super.onInit(context);
    }
    public void setUser(Utilisateur utilisateur) {
    }







    public void goAddStock(ActionEvent actionEvent) {
        context.routes().nav("addstock");
    }

    public void goviewstock(ActionEvent actionEvent) {
        context.routes().nav("stock_table");
    }

    public void goAddfournisseur(ActionEvent actionEvent) {
        context.routes().nav("Fournisseur");
    }

    public void goviewfournisseur(ActionEvent actionEvent) {
        context.routes().nav("fournisseur_table");
    }

    public void goAddArticle(ActionEvent actionEvent) {
        context.routes().nav("Article");
    }

    public void goviewArticle(ActionEvent actionEvent) {
        context.routes().nav("articlelist");
    }

    public void launchbot(ActionEvent actionEvent) {
    }

    public void gotoplan(ActionEvent actionEvent) {
        context.routes().nav("ListPlan");

    }
    public void gotoplanf(ActionEvent actionEvent) {
        context.routes().nav("ListTaches");
    }
    public void gotoSavedPlans(ActionEvent actionEvent) {
        context.routes().nav("SavedPlans");
    }

    public void gotoAjoutProjet(ActionEvent actionEvent) {
        context.routes().nav("ajoutProjet");
    }

    public void gotoAfficherProjet(ActionEvent actionEvent) {
        View afficherProjetView = ViewUtils.loadView(context, "afficherProjet");
        context.routes().putAndGo(afficherProjetView);
    }

    public void gotoAjouterEtapeProjet(ActionEvent actionEvent) {
        context.routes().nav("ajoutEtapeProjet");

    }

    public void gotoAfficherEtapeProjet(ActionEvent actionEvent) {
        View afficherEtapeProjetView = ViewUtils.loadView(context, "afficherEtapeProjet");
        context.routes().putAndGo(afficherEtapeProjetView);
    }



    public void gotoAfficherTerrain(ActionEvent actionEvent) {
        View afficherTerrainView = ViewUtils.loadView(context, "afficherTerrain");
        context.routes().putAndGo(afficherTerrainView);
    }


    public void gotoAfficherContrats(ActionEvent event) {

        context.routes().nav("ListContrats");

    }

    public void gotoAfficherAbonnemant(ActionEvent event) {
        context.routes().nav("ListAbonnement");
    }


    public void gotoWeather(ActionEvent actionEvent) {
        context.routes().nav("Weather");
    }

    public void gotoDashboard(ActionEvent actionEvent) {
        context.routes().nav("Dashboard");
    }

    public void gotochat(ActionEvent actionEvent) {
        context.routes().nav("Chatbot");
    }

    public void gotoContratclient(ActionEvent event) {
        context.routes().nav("ContatsClient");
    }
    public void goListeStock(ActionEvent actionEvent) {
        context.routes().nav("stock_table");
    }

    public void goAjouterStock(ActionEvent actionEvent) {
        context.routes().nav("addstock");
    }

    public void goListeFournisseurs(ActionEvent actionEvent) {
        context.routes().nav("fournisseur_table");
    }

    public void goAjouterFournisseur(ActionEvent actionEvent) {
        context.routes().nav("Fournisseur");
    }

    public void goListeArticles(ActionEvent actionEvent) {
        context.routes().nav("articlelist");
    }

    public void goAjouterArticle(ActionEvent actionEvent) {
        context.routes().nav("Article");
    }

    public void BOT(ActionEvent actionEvent) {


        String pythonExe = "python"; // or "python3" depending on your setup

        // Path to the bot.py script
        String scriptPath = "C:/last version/OBatimaPi/src/main/resources/bot.py"; // Adjust this path as needed

        // Create a process builder
        ProcessBuilder processBuilder = new ProcessBuilder(pythonExe, scriptPath);

        // Optional: Set working directory if necessary
        processBuilder.directory(new File("src/main/resources"));

        try {
            // Start the process
            Process process = processBuilder.start();

            // Optionally, you can handle the output and errors of the script
            // InputStream inputStream = process.getInputStream();
            // InputStream errorStream = process.getErrorStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}