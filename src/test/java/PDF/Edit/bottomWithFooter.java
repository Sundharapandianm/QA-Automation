package PDF.Edit;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.UnitValue;

public class bottomWithFooter {
	public static void main(String[] args) throws Exception {

		String inputPdf = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\Resilux Drawing - B1000E5R.pdf";
		String outputPdf = "output.pdf";
		String imagePath = "C:\\Users\\SundharaPandianM\\Workspace\\Edit10\\src\\main\\java\\logo with footer.png";

		// Setup PDF
		PdfReader reader = new PdfReader(inputPdf);
		PdfWriter writer = new PdfWriter(outputPdf);
		PdfDocument pdfDoc = new PdfDocument(reader, writer);
		Document document = new Document(pdfDoc);

		// Load the footer image
		Image footerImage = new Image(ImageDataFactory.create(imagePath));
		footerImage.setAutoScale(false);

		float imageWidth = 470;
		float imageHeight = 60;
		float bottomMargin = 30;

		footerImage.setWidth(UnitValue.createPointValue(imageWidth));
		footerImage.setHeight(UnitValue.createPointValue(imageHeight));

		// Loop through all pages
		int numberOfPages = pdfDoc.getNumberOfPages();
		for (int i = 1; i <= numberOfPages; i++) {
			PdfPage page = pdfDoc.getPage(i);
			Rectangle pageSize = page.getPageSize();

			float x = (pageSize.getWidth() - imageWidth) / 2;
			float y = bottomMargin;

			Image image = new Image(ImageDataFactory.create(imagePath));
			image.setAutoScale(false);
			image.setWidth(UnitValue.createPointValue(imageWidth));
			image.setHeight(UnitValue.createPointValue(imageHeight));
			image.setFixedPosition(i, x, y);
			document.add(image);
		}

		document.close();
		System.out.println("Footer image inserted at bottom of every page.");
	}

}
