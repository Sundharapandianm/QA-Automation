package PDF.Edit;

import java.io.IOException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.UnitValue;

public class rodatePdf {
	public static void main(String[] args) throws Exception {

		String inputPdf = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\TE38mm.pdf";
		String outputPdf = "output.pdf";
		String imagePath = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\logo 1.png";

		PdfDocument pdfDoc = new PdfDocument(new PdfReader(inputPdf), new PdfWriter(outputPdf));
		Document document = new Document(pdfDoc);

		Image footerImage = new Image(ImageDataFactory.create(imagePath));
		footerImage.setAutoScale(false);
		footerImage.setWidth(UnitValue.createPointValue(150));
		footerImage.setHeight(UnitValue.createPointValue(100));

		for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
			PdfPage page = pdfDoc.getPage(i);
			Rectangle mediaBox = page.getMediaBox();

			// Swap width and height
			float newWidth = mediaBox.getHeight();
			float newHeight = mediaBox.getWidth();

			PdfFormXObject pageCopy = page.copyAsFormXObject(pdfDoc);
			page.setMediaBox(new Rectangle(-250, 0, newWidth, newHeight)); // adjust the right/left (-250),// top/bottom(0) // actual pdf

			PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
			AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(90));
			transform.translate(0, -mediaBox.getWidth());
			canvas.concatMatrix(transform);
			canvas.addXObjectAt(pageCopy, 0, 0);

			// Add image (logo/footer)
			Image img = new Image(ImageDataFactory.create(imagePath));
			img.setAutoScale(false);
			float imgWidth = 99;
			float imgHeight = 52;
			img.setWidth(UnitValue.createPointValue(imgWidth));
			img.setHeight(UnitValue.createPointValue(imgHeight));

		
	        Rectangle pageSize = page.getPageSize();
	        float margin = 70;
	        float x = pageSize.getRight() - 186 - margin; // Right edge (mid number is height of the pic)
	        float y = pageSize.getBottom() + margin;     // Right Bottom

			img.setFixedPosition(i, x, y);
			document.add(img);
		}

		document.close();
		System.out.println("PDF rotated and image added.");
	}

}
