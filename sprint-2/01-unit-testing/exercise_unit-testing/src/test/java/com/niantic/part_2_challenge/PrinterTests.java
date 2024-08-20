package com.niantic.part_2_challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrinterTests
{
    private Printer printer;
    private int sheets = 500;
    private int toner = 1000;

    @BeforeEach
    public void setup()
    {
        printer = new Printer(sheets, toner);
    }

    @Test
    public void print_shouldReduceSheets_whenCalled()
    {
        // ARRANGE
        int pages = 100;
        int expectedSheetsLeft = 400;

        // ACT
        printer.print(pages);
        int actualSheetsLeft = printer.getSheets();

        // ASSERT
        assertEquals(expectedSheetsLeft, actualSheetsLeft, "Max sheets: 500 - Printed sheets: 100 - 400 should be left");
    }

    @Test
    public void print_shouldReduceToner_whenCalled()
    {
        // ARRANGE
        int pages = 100;
        int expectedTonerLeft = 900;

        // ACT
        printer.print(pages);
        int actualTonerLeft = printer.getToner();

        // ASSERT
        assertEquals(expectedTonerLeft, actualTonerLeft, "Max toner: 1000 - Printed sheets: 100 - 900 should be left");
    }

    @Test
    public void print_shouldReturn_theNumberOfPagesThatWerePrinted()
    {
        // ARRANGE
        int pages = 100;
        int expectedPagesPrinted = pages;

        // ACT
        int actualPagesPrinted = printer.print(pages);

        // ASSERT
        assertEquals(expectedPagesPrinted, actualPagesPrinted, "Because we printed 100 pages.");
    }

    @Test
    public void print_shouldNotPrint_whenSheetsBelowZero()
    {
        // ARRANGE
        int pages = 2000;
        int expectedSheetsLeft = 0;

        // ACT
        printer.print(pages);
        int actualSheetsLeft = printer.getSheets();

        // ASSERT
        assertEquals(expectedSheetsLeft, actualSheetsLeft, "Because sheets should not fall below zero.");
    }

    @Test
    public void print_shouldNotPrint_whenPagesToPrintIsLessThanZero()
    {
        // ARRANGE
        int pages = -50;
        int expectedPagesPrinted = 0;

        // ACT
        int actualPagesPrinted = printer.print(pages);

        // ASSERT
        assertEquals(expectedPagesPrinted, actualPagesPrinted, "Because you cannot print negative copies.");
    }

    @Test
    public void replaceToner_shouldResetToner_toMaxCapacity()
    {
        // ARRANGE
        int expectedTonerAmount = 1000;

        // ACT
        printer.replaceToner();
        int actualTonerAmount = printer.getToner();

        // ASSERT
        assertEquals(expectedTonerAmount, actualTonerAmount, "Because you replaced toner to max capacity");
    }

    @Test
    public void addPaper_shouldAddSheets_byAmountSpecified()
    {
        // ARRANGE
        int pagesToPrint = 200;
        int paperToAdd = 100;
        int expectedSheetsLeft = 400;

        // ACT
        printer.print(pagesToPrint);
        printer.addPaper(paperToAdd);
        int actualSheetsLeft = printer.getSheets();

        // ASSERT
        assertEquals(expectedSheetsLeft, actualSheetsLeft, "Max sheets: 500 - Sheets printed: 200 - Sheets added: 100 - Sheets in the printer should be 400");
    }

    @Test
    public void addPaper_shouldNotAddSheets_beyondMaxSheetCapacity()
    {
        // ARRANGE
        int paperToAdd = 100;
        int expectedSheetsLeft = 500;

        // ACT
        printer.addPaper(paperToAdd);
        int actualSheetsLeft = printer.getSheets();

        // ASSERT
        assertEquals(expectedSheetsLeft, actualSheetsLeft, "Max capacity: 500 - Sheets in the printer should not exceed max capacity");
    }

    @Test
    public void addPaper_shouldNotAddSheets_ifSheetsToAddAreNegative()
    {
        // ARRANGE
        int pagesToPrint = 200;
        int paperToAdd = -100;
        int expectedSheetsLeft = 300;

        // ACT
        printer.print(pagesToPrint);
        printer.addPaper(paperToAdd);
        int actualSheetsLeft = printer.getSheets();

        // ASSERT
        assertEquals(expectedSheetsLeft, actualSheetsLeft, "Max capacity: 500 - Sheets printed: 200 - Sheets added: -100 - Cannot add a negative amount of paper");
    }
}