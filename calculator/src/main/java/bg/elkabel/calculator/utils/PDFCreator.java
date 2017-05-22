package bg.elkabel.calculator.utils;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.Cell;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PDFCreator {

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private RequestPropertiesBuilder requestPropertiesBuilder;

	public byte[] createRequestDocument(RequestProperties requestProperties) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Resource fileResource = resourceLoader.getResource("classpath:font/CODE2000.TTF");

//Saving the document
		try (PDDocument document = new PDDocument()) {

			PDType0Font font = PDType0Font.load(document, fileResource.getFile());
			for (int i = 0; i < requestProperties.getMultiplier(); i++) {
				PDPage page = new PDPage(PDRectangle.A4);
				PDPage page2 = new PDPage(PDRectangle.A4);
				page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
				page2.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
				document.addPage(page);
				document.addPage(page2);

				PDPageContentStream twistMachine = this.createTwistContent(document, page, requestProperties, font);
				PDPageContentStream pullMachine = this.createPullContent(document, page2, requestProperties, font);

				twistMachine.close();
				pullMachine.close();

			}
			//Saving the document
			//document.save(new File("").getAbsolutePath() + "\\src\\main\\resources\\my_doc.pdf");

			document.save(baos);
			document.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return baos.toByteArray();
	}

	private PDPageContentStream createTwistContent(PDDocument document, PDPage page, RequestProperties requestProperties, PDType0Font font) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

//Dummy Table
		contentStream.beginText();
		//Setting the font to the Content stream  
		contentStream.setFont(font, 24);

		//Setting the position for the line 
		contentStream.newLineAtOffset(400, 550); // middle of top page

		//Adding text in the form of string 
		contentStream.showText("МАШИНА _____");
		contentStream.endText();
		contentStream.beginText();

		//Setting the position for the line 
		contentStream.newLineAtOffset(400, 520); // middle of top page

		//Adding text in the form of string 
		contentStream.showText("Жило : " + requestProperties.getConductorName());

		contentStream.endText();
		contentStream.beginText();

		contentStream.newLineAtOffset(20, 480); // middle of top page

		//Adding text in the form of string 
		contentStream.showText(String.valueOf(requestProperties.getLenght()));
		
		contentStream.endText();

		contentStream.beginText();

		contentStream.newLineAtOffset(70, 400); // middle of top page

		//Adding text in the form of string 
		contentStream.showText("ЗАРЯДКА");

		contentStream.endText();
		//Ending the content stream

		// Make sure that the content stream is closed:
		float margin = 150;
		// starting y position is whole page height subtracted by top and bottom margin
		float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
		// we want table across whole page width (subtracted by left and right margin ofcourse)
		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

		boolean drawContent = true;
		float yStart = yStartNewPage;
		float bottomMargin = 70;
		// y position is your coordinate of top left corner of the table
		float yPosition = 500;

		BaseTable batchТable = new BaseTable(390, yStartNewPage, bottomMargin, tableWidth, margin - 90, document, page, true, drawContent);

		Row<PDPage> batchHeaderRow = batchТable.createRow(15f);

		Cell<PDPage> batchHeaderCell = batchHeaderRow.createCell(10, "КОШ 1:");
		batchHeaderCell.setFont(font);
		batchHeaderCell = batchHeaderRow.createCell(10, "КОШ 2:");
		batchHeaderCell.setFont(font);
		batchHeaderCell = batchHeaderRow.createCell(10, "КОШ 3:");
		batchHeaderCell.setFont(font);
		batchТable.addHeaderRow(batchHeaderRow);

		int firstCageLenght = (int) (requestProperties.getLenght() * this.requestPropertiesBuilder.REDUCE);
		Row<PDPage> batchRow = batchТable.createRow(15f);
		Cell<PDPage> batchCell = batchRow.createCell(10, "7 x " + firstCageLenght);
		batchCell.setFont(font);
		batchCell = batchRow.createCell(10, "12 x " + (firstCageLenght - this.requestPropertiesBuilder.REDUCE_PER_CAGE));
		batchCell.setFont(font);
		batchCell = batchRow.createCell(10, "18 x " + (firstCageLenght - (this.requestPropertiesBuilder.REDUCE_PER_CAGE * 2)));
		batchCell.setFont(font);
		batchТable.addHeaderRow(batchRow);

		batchТable.draw();

		BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin + 120, document, page, true, drawContent);

		Row<PDPage> headerRow = table.createRow(15f);

		Cell<PDPage> cell = headerRow.createCell(20, "Дата");
		cell.setFont(font);
		cell = headerRow.createCell(10, "Смяна");
		cell.setFont(font);
		cell = headerRow.createCell(10, "Работен N");
		cell.setFont(font);
		cell = headerRow.createCell(20, "Работник");
		cell.setFont(font);
		cell = headerRow.createCell(10, "Количество");
		cell.setFont(font);
		cell = headerRow.createCell(30, "ВРЕМЕ");
		cell.setFont(font);
		cell.setColspanCell(drawContent);
		table.addHeaderRow(headerRow);

		Row<PDPage> rows = table.createRow(15f);
		cell = rows.createCell(60, "");
		cell.setFont(font);
		cell.setColspanCell(drawContent);
		cell = rows.createCell(10, "КГ/КМ");
		cell.setFont(font);
		cell = rows.createCell(9, "зареждане");
		cell.setFont(font);
		cell = rows.createCell(12, "производство");
		cell.setFont(font);
		cell = rows.createCell(9, "ОБЩО");
		cell.setFont(font);

		for (int i = 0; i < 25; i++) {
			Row<PDPage> dummyRow = table.createRow(15f);
			cell = dummyRow.createCell(20, "");
			cell = dummyRow.createCell(10, "");
			cell = dummyRow.createCell(10, "");
			cell = dummyRow.createCell(20, "");
			cell = dummyRow.createCell(10, "");
			cell = dummyRow.createCell(9, "");
			cell = dummyRow.createCell(12, "");
			cell = dummyRow.createCell(9, "");

		}

		table.draw();
		return contentStream;
	}

	private PDPageContentStream createPullContent(PDDocument document, PDPage page, RequestProperties requestProperties, PDType0Font font) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

//Dummy Table
		contentStream.beginText();
		//Setting the font to the Content stream  
		contentStream.setFont(font, 24);

		//Setting the position for the line 
		contentStream.newLineAtOffset(400, 550); // middle of top page

		//Adding text in the form of string 
		contentStream.showText("МАШИНА _____");
		contentStream.endText();
		contentStream.beginText();

		//Setting the position for the line 
		contentStream.newLineAtOffset(700, 550); // middle of top page

		//Adding text in the form of string 
		contentStream.showText(requestProperties.getMaterial().name());

		contentStream.endText();
		contentStream.beginText();

		//Setting the position for the line 
		contentStream.newLineAtOffset(400, 520); // middle of top page

		//Adding text in the form of string 
		contentStream.showText("Сечение : " + requestProperties.getCoreName());

		contentStream.endText();

		// Make sure that the content stream is closed:
		float margin = 150;
		// starting y position is whole page height subtracted by top and bottom margin
		float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
		// we want table across whole page width (subtracted by left and right margin ofcourse)
		float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

		boolean drawContent = true;
		float yStart = yStartNewPage;
		float bottomMargin = 70;
		// y position is your coordinate of top left corner of the table
		float yPosition = 500;

		BaseTable batchТable = new BaseTable(480, yStartNewPage, bottomMargin, tableWidth, margin - 90, document, page, true, drawContent);

		int firstCageLenght = (int) (requestProperties.getLenght() * this.requestPropertiesBuilder.REDUCE);
		Row<PDPage> batchRow = batchТable.createRow(15f);
		Cell<PDPage> batchCell = batchRow.createCell(10, "7 x " + firstCageLenght);
		batchCell.setFont(font);
		batchCell = batchRow.createCell(10, "12 x " + (firstCageLenght - this.requestPropertiesBuilder.REDUCE_PER_CAGE));
		batchCell.setFont(font);
		batchCell = batchRow.createCell(10, "18 x " + (firstCageLenght - (this.requestPropertiesBuilder.REDUCE_PER_CAGE * 2)));
		batchCell.setFont(font);
		batchТable.addHeaderRow(batchRow);

		batchТable.draw();

		BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin + 120, document, page, true, drawContent);

		Row<PDPage> headerRow = table.createRow(15f);

		Cell<PDPage> cell = headerRow.createCell(20, "Дата");
		cell.setFont(font);
		cell = headerRow.createCell(10, "Смяна");
		cell.setFont(font);
		cell = headerRow.createCell(10, "Работен N");
		cell.setFont(font);
		cell = headerRow.createCell(20, "Работник");
		cell.setFont(font);
		cell = headerRow.createCell(10, "Количество");
		cell.setFont(font);
		cell = headerRow.createCell(30, "ВРЕМЕ");
		cell.setFont(font);
		cell.setColspanCell(drawContent);
		table.addHeaderRow(headerRow);

		Row<PDPage> rows = table.createRow(15f);
		cell = rows.createCell(60, "");
		cell.setFont(font);
		cell.setColspanCell(drawContent);
		cell = rows.createCell(10, "КГ/КМ");
		cell.setFont(font);
		cell = rows.createCell(9, "зареждане");
		cell.setFont(font);
		cell = rows.createCell(12, "производство");
		cell.setFont(font);
		cell = rows.createCell(9, "ОБЩО");
		cell.setFont(font);

		for (int i = 0; i < 25; i++) {
			Row<PDPage> dummyRow = table.createRow(15f);
			cell = dummyRow.createCell(20, "");
			cell = dummyRow.createCell(10, "");
			cell = dummyRow.createCell(10, "");
			cell = dummyRow.createCell(20, "");
			cell = dummyRow.createCell(10, "");
			cell = dummyRow.createCell(9, "");
			cell = dummyRow.createCell(12, "");
			cell = dummyRow.createCell(9, "");

		}

		table.draw();
		return contentStream;
	}
}
