package PDF.Edit;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.UnitValue;

public class editPdf {

    public static void main(String[] args) throws Exception {
        String inputPdf = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\3L Burgundy 406272 1.pdf";                                      // your existing PDF
        String outputPdf = "output.pdf";                                                                                                          // new PDF with image
        String imagePath = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\logo 1.png";                                                   // image to insert

        PdfReader reader = new PdfReader(inputPdf);
        PdfWriter writer = new PdfWriter(outputPdf);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        Document document = new Document(pdfDoc);

        Image img = new Image(ImageDataFactory.create(imagePath));
        img.setAutoScale(false);                                                                                                                  // optional: you can set scale manually
        img.setWidth(UnitValue.createPointValue(99));                                                                                             // width in points
        img.setHeight(UnitValue.createPointValue(52));                                                                                            // height in points

        // Get bottom left position
        PdfPage page = pdfDoc.getFirstPage();
        Rectangle pageSize = page.getPageSize();
        float x = pageSize.getLeft() + 30;      // bottom (left)                                                                                                 // 10 pts margin from left
        float y = pageSize.getBottom() + 30;    // (bottom)                                                                                                 // 10 pts margin from bottom

        
        img.setFixedPosition(1, x, y);                                                                                                            // page 1, position (x, y)

        document.add(img);
        document.close();

        System.out.println("Image added to PDF at bottom left.");
    }




}
