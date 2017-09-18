package mainfrm;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Maze3D2DPanel extends HBox {

	Group root = new Group();
	Xform triadGroup = null;
	Xform moleculeGroup = null;
	Xform sixFacesGroup = null;
	Xform triMeshGroup = null;
	Xform teapotGroup = null;
	Xform threeFacesGroup = null;
	Xform helixGroup = null;
	Xform mazeGridGroup = null;
	final Xform world = new Xform();
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
	private static final double CAMERA_INITIAL_DISTANCE = -450;
	private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
	private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double ROTATION_SPEED = 2.0;
	private static final double TRACK_SPEED = 0.3;

	double mousePosX;
	double mousePosY;
	double mouseOldX;
	double mouseOldY;
	double mouseDeltaX;
	double mouseDeltaY;

	public Maze3D2DPanel()
	{
		root.getChildren().add(world);
		root.setDepthTest(DepthTest.ENABLE);

		// buildScene();
		buildCamera();
		buildMazeGrid();

		SubScene subScene = new SubScene(root, 800, 800, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.GREY);
		handleMouse(subScene);

		subScene.setCamera(camera);

		subScene.heightProperty().bind(this.heightProperty());
		subScene.widthProperty().bind(this.widthProperty());
		this.getChildren().add(subScene);

		Polygon p = new Polygon();
	}

	private void buildCamera() {
		System.out.println("buildCamera()");
		root.getChildren().add(cameraXform);
		cameraXform.getChildren().add(cameraXform2);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);

		camera.setNearClip(CAMERA_NEAR_CLIP);
		camera.setFarClip(CAMERA_FAR_CLIP);
		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
		cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
		cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
	}

	private void buildMazeGrid() {
		mazeGridGroup = new MazeGrid();
		mazeGridGroup.setVisible(false);
		world.getChildren().addAll(mazeGridGroup);
	}

	public void handleMouse(SubScene subScene) {
		System.out.println("handleMouse");
		subScene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent me) {
				System.out.println("handleMouse: onMousePressed");
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		subScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent me) {
				mouseOldX = mousePosX;
				mouseOldY = mousePosY;
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseDeltaX = (mousePosX - mouseOldX);
				mouseDeltaY = (mousePosY - mouseOldY);

				double modifier = 1.0;

				if (me.isControlDown()) {
					modifier = CONTROL_MULTIPLIER;
				}
				if (me.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if (me.isPrimaryButtonDown()) {
					cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*modifier*ROTATION_SPEED);
					cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*modifier*ROTATION_SPEED);
				}
				else if (me.isSecondaryButtonDown()) {
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
					camera.setTranslateZ(newZ);
				}
				else if (me.isMiddleButtonDown()) {
					cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);
					cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);
				}
			}
		});
	}

	public void ShowMazeGrid(boolean _show)
	{
		mazeGridGroup.setVisible(_show);
	}

	private double	dXRotationDelta = 0.0;
	private double	dYRotationDelta = 0.0;
	private double	dZRotationDelta = 1.0;

	public void SetXRotationDelta(double _delta)
	{
		dXRotationDelta = _delta;
	}

	public void SetYRotationDelta(double _delta)
	{
		dYRotationDelta = _delta;
	}
	public void SetZRotationDelta(double _delta)
	{
		dZRotationDelta = _delta;
	}

	private void doSomething()
	{
		cameraXform.rx.setAngle(cameraXform.rx.getAngle() + dXRotationDelta);
		cameraXform.ry.setAngle(cameraXform.ry.getAngle() + dYRotationDelta);
		cameraXform.rz.setAngle(cameraXform.rz.getAngle() + dZRotationDelta);
	}

	private Timeline timeline = new Timeline(new KeyFrame(
			Duration.millis(25),
			ae -> doSomething()));

	public void StartRotation(boolean _startRotation)
	{
		System.out.println("RotationStarted: " + _startRotation);

		if(_startRotation) {
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}
		else {
			timeline.stop();
		}
	}

	public void OnKeyPressed(KeyEvent event)
	{
		System.out.println("DisplayPanel: OnKeyPressed");
		switch (event.getCode()) {
			case Z:
				cameraXform2.t.setX(0.0);
				cameraXform2.t.setY(0.0);
				camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
				cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
				cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
				break;
//			case X:
//				triadGroup.setVisible(!triadGroup.isVisible());
//				break;
//			case C:
//				sixFacesGroup.setVisible(!sixFacesGroup.isVisible());
//				break;
//			case V:
//				moleculeGroup.setVisible(!moleculeGroup.isVisible());
//				break;
		}
	}
}
