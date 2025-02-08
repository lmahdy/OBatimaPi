/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.OurBatima.core.impl.layout;

import io.OurBatima.core.impl.IRoot;
import io.OurBatima.core.interfaces.WrapperContainer;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  05/03/2023
 */
public class Flow implements WrapperContainer {

    private final IRoot root;
    private Region content;

    private final Insets padding = new Insets(5);

    public Flow(IRoot root) {
        this.root = root;
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) close();
        });
    }

    public Flow content(Region region) {
        this.content = region;
//        if (region.getPrefWidth() == -1 || region.getPrefHeight() == -1) {
//                region.setPrefSize(300, 400);
//            region.setMaxSize(region.getPrefWidth(), region.getPrefHeight());
//        }

        content.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            if (root.getChildren().size() <= 2) return;
            if (event.getPickResult().getIntersectedNode().getStyleClass().contains("gn-badge")) return;
            if (!opened) return;
            close();
        });
        return this;
    }

    private boolean opened = true;
    @Override
    public void close() {
        opened = false;
        if (root.getChildren().contains(content)) {
            root.getChildren().remove(this.content);
        }
    }

    @Override
    public void show() {
//        AnimationFX animationFX = new Pulse(this.content);
//        animationFX.setSpeed(1.8);
//        animationFX.play();
    }

    public void show(Pos direction, Node target) {
        show(direction, target, 0, 0);
    }

    public void show(Pos direction, Node target, double x) {
        show(direction, target, x, 0);
    }

    public void show(Pos pos, Node target, double x, double y) {
        opened = true;

        if (root.getChildren().contains(content)) {
            return;
        }

        if(root.getChildren().stream().anyMatch(p -> p.getStyleClass().contains("container"))) {
            root.getChildren().remove(root.getChildren().size()-1);
            return;
        }


        content.setTranslateX(0);
        content.setTranslateY(0);

        root.getChildren().add( root.getChildren().size()-1, content);
        content.toFront();

        content.getStyleClass().add("depth-1");
        content.setPadding(new Insets(10));

        Bounds bounds = target.localToScene(target.getLayoutBounds());

        switch (pos) {
            case TOP_LEFT -> {
                content.setTranslateX(bounds.getMinX() - content.getMaxWidth());
                content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case TOP_CENTER -> {
                content.setTranslateX(
                        bounds.getMinX() -
                                (center(bounds.getWidth() , content.getMaxWidth()))
                );
                content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case TOP_RIGHT -> {
                content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case CENTER_RIGHT -> {
                content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                content.setTranslateY( (bounds.getMaxY() - bounds.getHeight()) -
                        content.getMaxHeight()/2);
            }
            case BOTTOM_RIGHT -> {
                content.setTranslateX((bounds.getMinX() + bounds.getWidth()));
                content.setTranslateY(bounds.getMinY() + bounds.getHeight());
            }
            case BOTTOM_CENTER -> {
                content.setTranslateX(bounds.getMinX() -
                        (center(bounds.getWidth(), content.getMaxWidth())) );
                content.setTranslateY(bounds.getMaxY());
            }
            case BOTTOM_LEFT -> {
                content.setTranslateX(bounds.getMinX() - content.getMaxWidth());
                content.setTranslateY(bounds.getMaxY());
            }

            case CENTER_LEFT -> {
                content.setTranslateX((bounds.getMinX() - content.getMaxWidth()));
                content.setTranslateY( (bounds.getMaxY() - bounds.getHeight()) -
                        content.getMaxHeight()/2);
            }
            case CENTER -> {
                content.setTranslateX(
                        bounds.getMinX() -
                                (center(bounds.getWidth() , content.getMaxWidth()))
                );
                content.setTranslateY((bounds.getMinY()) - (content.getMaxHeight()/2));
            }
            case BASELINE_LEFT -> {
                content.setTranslateY(bounds.getMinY());
                content.setTranslateX(bounds.getMinX() - content.getMaxWidth());
            }
            case BASELINE_CENTER -> {
                content.setTranslateX(
                        bounds.getMinX() -
                                (center(bounds.getWidth() , content.getMaxWidth()))
                );
                content.setTranslateY(bounds.getMinY());
            }

            case BASELINE_RIGHT -> {
                content.setTranslateY(bounds.getMinY());
                content.setTranslateX(bounds.getMaxX());
            }
        }


        content.setTranslateX(content.getTranslateX() + x);
        content.setTranslateY(content.getTranslateY() + y);
//
        Bounds contentBounds = content.localToScene(content.getLayoutBounds());

        if (isOutsideInTopLeft(contentBounds)) {
            relocateLeft();
            relocateTop();
        } else if (isOutsideInTopRight(contentBounds)) {
            relocateTop();
            relocateRight(bounds);
        } else if(isOutsideInBottomRight(contentBounds)) {
            relocateRight(bounds);
            relocateBottom(bounds);
        } else if (isOutsideBottomLeft(contentBounds)) {
            relocateBottom(bounds);
            relocateLeft();
        } else if (isOutsideInTop(contentBounds)) { // se passa a lnha do topo
            relocateTop();
        } else if(isOutsideRight(contentBounds)) { // se passa a linha da dirita
            relocateRight(contentBounds);
        } else if(isOutsideBottom(contentBounds)) { // se passa linha do fundo
            relocateBottom(contentBounds);
        } else if(isOutsideLeft(contentBounds)) { // se passa da linha da esquerda
            relocateLeft();
        }
    }

    protected void relocateTop() {
        content.setTranslateY(0 + padding.getTop());
    }
    protected void relocateRight(Bounds bounds) {
        content.setTranslateX((content.getTranslateX() - restInRight(bounds)) - padding.getRight());
    }
    protected void relocateBottom(Bounds bounds) {
        content.setTranslateY((content.getTranslateY() - restInBottom(bounds)) - padding.getBottom());
    }
    protected void relocateLeft() {
        content.setTranslateX(0 + padding.getLeft());
    }

    protected boolean isOutsideInTopLeft(Bounds bounds) {
        return isOutsideLeft(bounds) && bounds.getMinY() < 0;
    }

    protected boolean isOutsideInTopRight(Bounds bounds) {
        return bounds.getMinY() < 0 && isOutsideRight(bounds);
    }

    protected boolean isOutsideInBottomRight(Bounds bounds) {
        return isOutsideBottom(bounds) && isOutsideRight(bounds);
    }

    protected boolean isOutsideBottomLeft(Bounds bounds) {
        return isOutsideLeft(bounds) && isOutsideBottom(bounds);
    }

    protected boolean isOutsideLeft(Bounds bounds) {
        return bounds.getMinX() < 0;
    }

    protected boolean isOutsideRight(Bounds bounds) {
        return (bounds.getMaxX() + content.getMaxWidth()) > root.getWidth();
    }

    protected boolean isOutsideInTop(Bounds bounds) {
        return bounds.getMaxY() < 0;
    }

    protected boolean isOutsideBottom(Bounds bounds) {
        return (bounds.getMaxY() + content.getMaxHeight()) > root.getHeight();
    }

    protected double restInRight(Bounds bounds) {
        return  (bounds.getMaxX() + content.getMaxWidth()) - root.getWidth();
    }

    protected double restInBottom(Bounds bounds) {
        return  (bounds.getMaxY() + content.getMaxHeight()) - root.getHeight();
    }

    protected double clamp(double one, double two) {
        return Math.max(one, two) - Math.min(one, two);
    }

    protected double center(double one, double two) {
        return (clamp(one, two) / 2);
    }
}
