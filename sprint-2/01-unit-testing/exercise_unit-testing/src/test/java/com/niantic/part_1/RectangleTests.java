package com.niantic.part_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTests
{
    private Rectangle rectangle;

    @BeforeEach
    public void setup()
    {
        rectangle = new Rectangle();
    }

    @Test
    public void newRectangle_shouldHaveWidth5Height5_whenCreated()
    {
        // ARRANGE
        int expectedWidth = 5;
        int expectedHeight = 5;

        // ACT
        int actualWidth = rectangle.getWidth();
        int actualHeight = rectangle.getHeight();

        // ASSERT
        assertEquals(expectedWidth, actualWidth, "Because a default rectangle should have width of 5");
        assertEquals(expectedHeight, actualHeight, "Because a default rectangle should have height of 5");
    }

    @Test
    public void getArea_shouldReturn_theProductOfWidthAndHeight()
    {
        // ARRANGE
        int width = 3;
        int height = 4;
        int expectedArea = 12;

        // ACT
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        int actualArea = rectangle.getArea();

        //ASSERT
        assertEquals(expectedArea, actualArea, "Because it should have calculated width * height, where width = 3 and height = 4");
    }

    @Test
    public void getPerimeter_shouldReturn_thePerimeterOfRectangle()
    {
        // ARRANGE
        int width = 3;
        int height = 4;
        int expectedPerimeter = 14;

        // ACT
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        int actualPerimeter = rectangle.getPerimeter();

        // ASSERT
        assertEquals(expectedPerimeter, actualPerimeter, "Because it should have calculated  ((2 x width) + (2 x height)), where width = 3 and height = 4.");
    }

    @Test
    public void getArea_shouldReturnZero_whenWidthOrHeightIsZeroOrLess()
    {
        // ARRANGE
        int width = -3;
        int height = -4;
        int expectedArea = 0;

        // ACT
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        int actualArea = rectangle.getArea();

        // ASSERT
        assertEquals(expectedArea, actualArea, "Because a rectangle with width or height <= 0 cannot have an area");
    }

    @Test
    public void getPerimeter_shouldReturnZero_whenWidthOrHeightIsZeroOrLess()
    {
        // ARRANGE
        int width = -3;
        int height = -4;
        int expectedPerimeter = 0;

        // ACT
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        int actualPerimeter = rectangle.getPerimeter();

        // ASSERT
        assertEquals(expectedPerimeter, actualPerimeter, "Because a rectangle with width or height <= 0 cannot have a perimeter");
    }
}