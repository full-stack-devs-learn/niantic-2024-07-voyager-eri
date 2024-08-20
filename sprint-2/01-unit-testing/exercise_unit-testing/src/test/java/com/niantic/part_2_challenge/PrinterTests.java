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

        assertEquals(expectedPagesPrinted, actualPagesPrinted, "Because we printed 100 pages.");
    }

    @Test
    public void print_shouldNotPrint_whenSheetsBelowZero()
    {
        
    }
}