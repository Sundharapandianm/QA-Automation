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

public class topRight {
	public static void main(String[] args) throws Exception {

		String inputPdf = "/Users/sundarpradhu/eclipse-workspace/Edit/src/main/java/WC 1000398M BORDELAISE GRAND VIN Green (ChG) Cork 1.5L.pdf";
		String outputPdf = "output.pdf";
		String imagePath = "/Users/sundarpradhu/eclipse-workspace/Edit/src/main/java/logo.png";

		// Setup PDF
		PdfReader reader = new PdfReader(inputPdf);
		PdfWriter writer = new PdfWriter(outputPdf);
		PdfDocument pdfDoc = new PdfDocument(reader, writer);
		Document document = new Document(pdfDoc);

		// Load image
		Image img = new Image(ImageDataFactory.create(imagePath));
		img.setAutoScale(false);

		float imageWidth = 99;
		float imageHeight = 52;
		float margin = 10;

		img.setWidth(UnitValue.createPointValue(imageWidth));
		img.setHeight(UnitValue.createPointValue(imageHeight));

		// Get page and size
		PdfPage page = pdfDoc.getFirstPage();
		Rectangle pageSize = page.getPageSize();

		// Calculate top-right position
		float x = pageSize.getRight() - imageWidth - margin; 
		float y = pageSize.getTop() - imageHeight - margin; 

		// Set image position
		img.setFixedPosition(1, x, y);
		document.add(img);

		document.close();
		System.out.println("Image inserted at top right corner.");
	}

}
