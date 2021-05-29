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

  public static byte[] getRGBAPixelData(BufferedImage img) {
    byte[] imgRGBA;
    int height = img.getHeight(null);
    int width = img.getWidth(null);
    WritableRaster raster
            = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 4,
                    null);
    ComponentColorModel colorModel = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[]{8, 8, 8, 8}, true, false, // bits, has Alpha, isAlphaPreMultiplied
            ComponentColorModel.TRANSLUCENT, // transparency
            DataBuffer.TYPE_BYTE); // data transfer type
    BufferedImage newImage = new BufferedImage(colorModel, raster, false, null);
// use an affine transform to "flip" the image to conform to OpenGL orientation.
// In Java the origin is at the upper left. In OpenGL the origin is at the lower left.
    AffineTransform gt = new AffineTransform();
    gt.translate(0, height);
    gt.scale(1, -1d);
    Graphics2D g = newImage.createGraphics();
    g.transform(gt);
    g.drawImage(img, null, null);
    g.dispose();
    DataBufferByte dataBuf = (DataBufferByte) raster.getDataBuffer();
    imgRGBA = dataBuf.getData();
    return imgRGBA;
  }
}
