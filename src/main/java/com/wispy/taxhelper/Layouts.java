package com.wispy.taxhelper;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Leonid_Poliakov
 */
public class Layouts {
    public static ConstraintsBuilder layout() {
        return new ConstraintsBuilder();
    }

    public static class ConstraintsBuilder {
        private int gridX = 0;
        private int gridY = 0;
        private int gridWidth = 1;
        private int gridHeight = 1;
        private double weightX = 1;
        private double weightY = 1;
        private int fill = GridBagConstraints.NONE;
        private int anchor = GridBagConstraints.CENTER;
        private int padTop = 0;
        private int padRight = 0;
        private int padBottom = 0;
        private int padLeft = 0;

        public ConstraintsBuilder grid(int x, int y) {
            gridX = x;
            gridY = y;
            return this;
        }

        public ConstraintsBuilder fillAll() {
            fill = GridBagConstraints.BOTH;
            return this;
        }

        public ConstraintsBuilder fillHorizontal() {
            fill = GridBagConstraints.HORIZONTAL;
            return this;
        }

        public ConstraintsBuilder fillVertical() {
            fill = GridBagConstraints.VERTICAL;
            return this;
        }

        public ConstraintsBuilder fillNone() {
            fill = GridBagConstraints.NONE;
            return this;
        }

        public ConstraintsBuilder pad(int pad) {
            padTop = padRight = padBottom = padLeft = pad;
            return this;
        }

        public ConstraintsBuilder padTop(int pad) {
            padTop = pad;
            return this;
        }

        public ConstraintsBuilder padRight(int pad) {
            padRight = pad;
            return this;
        }

        public ConstraintsBuilder padBottom(int pad) {
            padBottom = pad;
            return this;
        }

        public ConstraintsBuilder padLeft(int pad) {
            padLeft = pad;
            return this;
        }

        public ConstraintsBuilder anchorTop() {
            this.anchor = GridBagConstraints.NORTH;
            return this;
        }

        public ConstraintsBuilder anchorBottom() {
            this.anchor = GridBagConstraints.SOUTH;
            return this;
        }

        public ConstraintsBuilder weight(double x, double y) {
            weightX = x;
            weightY = y;
            return this;
        }

        public GridBagConstraints build() {
            GridBagConstraints result = new GridBagConstraints();
            result.gridx = gridX;
            result.gridy = gridY;
            result.gridwidth = gridWidth;
            result.gridheight = gridHeight;
            result.weightx = weightX;
            result.weighty = weightY;
            result.fill = fill;
            result.anchor = anchor;
            result.insets = new Insets(padTop, padLeft, padBottom, padRight);
            return result;
        }

    }
}