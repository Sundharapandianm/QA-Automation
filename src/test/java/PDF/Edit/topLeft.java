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

public class topLeft {
	public static void main(String[] args) throws Exception {
        
	      String inputPdf = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\WC 1000398M BORDELAISE GRAND VIN Green (ChG) Cork 1.5L 1.pdf";                                                // your existing PDF
	      String outputPdf = "output.pdf";                                                                                                          // new PDF with image
	      String imagePath = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\logo 1.png";                                                   // image to insert

	      PdfReader reader = new PdfReader(inputPdf);
	      PdfWriter writer = new PdfWriter(outputPdf);
	      PdfDocument pdfDoc = new PdfDocument(reader, writer);
	      Document document = new Document(pdfDoc);

	      Image img = new Image(ImageDataFactory.create(imagePath));
	      img.setAutoScale(false);                                                                                                                  // optional: you can set scale manually
	      img.setWidth(UnitValue.createPointValue(99));           //change the width on the bottom code of size calculation                                                                                  // width in points
	      img.setHeight(UnitValue.createPointValue(52));                                                                                            // height in points

	      // Get bottom left position
	      PdfPage page = pdfDoc.getFirstPage();
	      Rectangle pageSize = page.getPageSize();
	                                                                                  

	      
	   // Calculate top-left position
//	        float margin = 10;
	        float x = pageSize.getLeft() - 1;                         // top left
	        float y = pageSize.getTop() - 99 + 30;                    // ((mid number is height of the pic))
	      
	      
	      img.setFixedPosition(1, x, y);                                                                                                            // page 1, position (x, y)

	      document.add(img);
	      document.close();

	      System.out.println("Image added to PDF at bottom left.");
	  }

}
