package PanoViewer.Utils;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/*
 * 
 */
/**
 *
 * @author kshan
 */
public class imageutils {
  public static BufferedImage getFlipedImage(BufferedImage img) {
    int height = img.getHeight(null);
    int width = img.getWidth(null);
    WritableRaster raster
            = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 4,
                    null);
    ComponentColorModel colorModel = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[]{8, 8, 8, 8}, true, false, // bits, has Alpha, isAlphaPreMultiplied
            ComponentColorModel.TRANSLUCENT, // transparency
            DataBuffer.TYPE_BYTE);
    BufferedImage newImage = new BufferedImage(colorModel, raster, false, null);
    AffineTransform gt = new AffineTransform();
    gt.translate(0, height);
    gt.scale(1, -1d);
    Graphics2D g = newImage.createGraphics();
    g.transform(gt);
    g.drawImage(img, null, null);
    g.dispose();
    return newImage;
  }
}
