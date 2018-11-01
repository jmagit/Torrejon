package application;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		if (!Platform.isSupported(ConditionalFeature.SCENE3D))
			return;
		demo2(primaryStage);
	}

	public void demo1(Stage primaryStage) {
		PhongMaterial mat = new PhongMaterial();
		// materiales basados en colores
		//mat.setSpecularColor(Color.ORANGE);
		//mat.setDiffuseColor(Color.RED);
		mat.setSpecularPower(64);
		Image imageDiffuse = new Image(getClass().getResource( "textura_diffuse.jpg").toExternalForm());
		Image imageNormal = new Image(getClass().getResource( "textura_normal.jpg").toExternalForm());
		mat.setDiffuseMap(imageDiffuse);
		mat.setBumpMap(imageNormal);


//		Shape3D figura = new Box(300, 300, 300);
		//Shape3D figura = new Sphere(300);
		Shape3D figura = new Cylinder(150, 300);
		//Shape3D figura = new Cylinder(150, 300, 3); // Prismas
		figura.setMaterial(mat);
		figura.setRotationAxis(new Point3D(1, 1, 0));
		figura.setRotate(45);
		Shape3D figura2 = new Sphere(100);
		figura2.setMaterial(mat);
		figura2.setRotationAxis(new Point3D(1, 1, 0));
		figura2.setRotate(45);

		Color colorLuz = Color.WHITESMOKE;

		PointLight light = new PointLight(colorLuz);
		light.setTranslateX(-350);
		light.setTranslateY(+180);
		light.setTranslateZ(-500);

		// Layout con la figura y el punto de luz
		Group root = new Group(figura, /*figura2, */ light);

		// crear la escena, true para activar el buffer de profundidad
		Scene scene = new Scene(root, 1280, 768, true, SceneAntialiasing.BALANCED);

		// crear una cámara en perspectiva y añadir a la escena
		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateX(scene.getWidth() / -2.0);
		camera.setTranslateY(scene.getHeight() / -2.0);
		scene.setCamera(camera);
		
		RotateTransition rt = new RotateTransition( Duration.seconds(10), root);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setFromAngle(0);
		rt.setToAngle(360);
		rt.setAxis(new Point3D(1, 1, 1));
		rt.play();

		primaryStage.setTitle("Gráficos 3D");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void demo2(Stage primaryStage) {
		PhongMaterial mat = new PhongMaterial();
		// materiales basados en colores
		//mat.setSpecularColor(Color.ORANGE);
		//mat.setDiffuseColor(Color.RED);
		mat.setSpecularPower(64);
		Image imageDiffuse = new Image(getClass().getResource( "textura_diffuse.jpg").toExternalForm());
		Image imageNormal = new Image(getClass().getResource( "textura_normal.jpg").toExternalForm());
		mat.setDiffuseMap(imageDiffuse);
		mat.setBumpMap(imageNormal);

		Shape3D[] figuras = new Shape3D[] { new Cylinder(75, 150), new Box(150, 150, 150), new Sphere(75) };

		for (int i=0; i < figuras.length; ++i) {
			Shape3D figura = figuras[i];
			figura.setMaterial(mat);
			figura.setRotationAxis(new Point3D(1, 1, 0));
			figura.setRotate(45);
			figura.setTranslateX((i + 1) * 220);
		}

		Color colorLuz = Color.WHITESMOKE;

		PointLight light = new PointLight(colorLuz);
		light.setTranslateX(-350);
		light.setTranslateY(+180);
		light.setTranslateZ(-500);
		PointLight light2 = new PointLight(colorLuz);
		light2.setTranslateX(800);
		light2.setTranslateY(-100);
		light2.setTranslateZ(-1000);
		
		// Layout con la figura y el punto de luz
		Group root = new Group(figuras);
		root.getChildren().add(light);
		root.getChildren().add(light2);

		// crear la escena, true para activar el buffer de profundidad
		Scene scene = new Scene(root, 1280, 768, true, SceneAntialiasing.BALANCED);

		// crear una cámara en perspectiva y añadir a la escena
		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateX(scene.getWidth() / -2.0);
		camera.setTranslateY(scene.getHeight() / -2.0);
		scene.setCamera(camera);
		
		RotateTransition rt = new RotateTransition(Duration.seconds(10), root);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setFromAngle(0);
		rt.setToAngle(360);
		rt.setAxis(new Point3D(1, 1, 0));
		rt.play();
		
		RotateTransition rt2 = new RotateTransition(Duration.seconds(5), figuras[1]);
		rt2.setCycleCount(Animation.INDEFINITE);
		rt2.setFromAngle(0);
		rt2.setToAngle(360);
		rt2.setAxis(new Point3D(1, 1, 0));
		rt2.play();


		primaryStage.setTitle("Gráficos 3D");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
