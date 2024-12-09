package cg.task;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseFiller {
    public void fillEllipseByBoundingBox(GraphicsContext gc, int x, int y, int width, int height, Color color1, Color color2) {
        int xc = x + width / 2;  // центер по х и у
        int yc = y + height / 2;
        int a = width / 2;
        int b = height / 2;

        fillEllipse(gc, xc, yc, a, b, color1, color2);
    }
    public void fillEllipse(GraphicsContext gc, int xc, int yc, int a, int b, Color color1, Color color2) {
        int x = 0;
        int y = b;
        int a2 = a * a;
        int b2 = b * b;
        int p = (int) (b2 - a2 * b + 0.25 * a2);

        while (b2 * x <= a2 * y) {
            fillSymmetricLines(gc, xc, yc, x, y, a, b, color1, color2);
            if (p < 0) {
                x++;
                p += b2 * (2 * x + 1);
            } else {
                x++;
                y--;
                p += b2 * (2 * x + 1) - a2 * (2 * y);
            }
        }

        p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);

        while (y >= 0) {
            fillSymmetricLines(gc, xc, yc, x, y, a, b, color1, color2);
            if (p > 0) {
                y--;
                p -= a2 * (2 * y + 1);
            } else {
                y--;
                x++;
                p += b2 * (2 * x) - a2 * (2 * y + 1);
            }
        }
    }

    private void fillSymmetricLines(GraphicsContext gc, int xc, int yc, int x, int y, int a, int b, Color color1, Color color2) {
        drawGradientLine(gc, xc - x, xc + x, yc + y, a, b, yc, color1, color2);
        drawGradientLine(gc, xc - x, xc + x, yc - y, a, b, yc, color1, color2);
    }

    private void drawGradientLine(GraphicsContext gc, int x1, int x2, int y, int a, int b, int centerY, Color color1, Color color2) {
        double distanceFromCenter = Math.abs((double) (y - centerY));
        // нелинейная фунция - сигмоида
        double normalizedDistance = distanceFromCenter / b; // rasto9nie ot 0 do 1
        double factor = 1 / (.35 + Math.exp((normalizedDistance - 0.5)));

        Color interpolatedColor = color1.interpolate(color2, factor);

        gc.setStroke(interpolatedColor);
        gc.strokeLine(x1, y, x2, y);
    }


}
