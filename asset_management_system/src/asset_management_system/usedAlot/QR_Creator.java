
package asset_management_system.usedAlot;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.*;

public class QR_Creator {
    
    public void QRGen(String id,String assetCode,String assetName) throws WriterException, IOException{
        String QR_CODE_IMAGE_PATH = "./qr_images/MyQRCode.png";
        
        int height=350;
        int width=350;
        String filePath="Asset"+id+".png";
        String forEncoding="Asset id="+id+" , Asset Code="+assetCode+" ,Asset Name="+assetName;
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(forEncoding, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
    
}
