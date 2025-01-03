#include <iostream>
#include <array>
#include <cmath>
#include <utility>

class Canvas
{
public:
    static inline const uint16_t CANVAS_WIDTH = 20;
    static inline const uint16_t CANVAS_HEIGHT = 20;
    static inline const int CANVAS_EMPTY_SPACE = '.';
    static inline const char CANVAS_DEFAULT_COLOR = '#';
    using Array2d = std::array<std::array<char, CANVAS_WIDTH>, CANVAS_HEIGHT>;
 
private:
    void fillCanvasBoundaries();
    
public:
    Canvas() { fillCanvasBoundaries(); }
    void drawCanvas();
    Array2d& get_canvas2d() { return canvas2d; }
    
private:
    Array2d canvas2d;
};

void Canvas::fillCanvasBoundaries()
{
    for(auto& row : canvas2d)
        row.fill(CANVAS_EMPTY_SPACE);
    
    int i = 1;
    for(; i<CANVAS_HEIGHT-1; ++i)
        canvas2d[i][0] = '|';
    
    int j = 0;
    for(; j<CANVAS_WIDTH; ++j)
        canvas2d[CANVAS_HEIGHT-1][j] = '-';
        
    i = CANVAS_HEIGHT-2;
    for(; i>=1; --i)
        canvas2d[i][CANVAS_WIDTH-1] = '|';
        
    j = CANVAS_WIDTH-1;
    for(; j>=0; --j)
        canvas2d[0][j] = '-';
}

void Canvas::drawCanvas()
{
    for(int i=0; i<CANVAS_HEIGHT; ++i)
    {
        for(int j=0; j<CANVAS_WIDTH; ++j)
            std::cout<< canvas2d[i][j] << " ";
        std::cout<< "\n";
    }
    std::cout<< "\n";
}
////////////////////////////////////////////

class Point
{
public:
    Point(uint16_t _x, uint16_t _y) 
        : x(_x), y(_y) {}

    bool operator==(const Point& other) const { return x == other.getX() && y == other.getY(); }
    bool operator!=(const Point& other) const { return !(*this==other); }

    void setX(uint16_t _x) { x = _x; }
    uint16_t getX() const { return x; };
    
    void setY(uint16_t _y) { y = _y; }
    uint16_t getY() const { return y; };
private:
    uint16_t x;
    uint16_t y;
};
////////////////////////////////////////////

class Line
{
public:
    Line(const Point& start, const Point& stop)
    {
        set_start(start);
        set_stop(stop);
    }
    
    void set_start(const Point& point);
    const Point& get_start_point() const { return startPoint; }
    void set_stop(const Point& point);
    const Point& get_stop_point() const { return stopPoint; }
    bool is_point() { return startPoint == stopPoint; }
    
private:
    Point startPoint{1,1};
    Point stopPoint{1,1};
};

void Line::set_start(const Point& point)
{
    if(startPoint == point)
        return;
    
    startPoint = std::move(point);
}

void Line::set_stop(const Point& point)
{
    if(stopPoint == point)
        return;
    
    stopPoint = std::move(point);
}
////////////////////////////////////////////

class Figure
{
public:
    virtual void draw(Canvas& canvas, char color = Canvas::CANVAS_DEFAULT_COLOR) = 0;
    void clear_drawing(Canvas& canvas);
    
    virtual void set_height(uint16_t _height) { height = _height; };
    uint16_t get_height() { return height; }
    
    virtual void set_width(uint16_t _width) { width = _width; };
    uint16_t get_width() { return width; }
    
    void set_start_point(Point&& _point);
    
    virtual float get_area() { return width * height; };
    void set_color(char _color) { color = _color; }
    [[nodiscard]] const Point& get_position();
    
    void move(const Line& vector, Canvas& canvas);
    
private:
    std::pair<int, int> get_vertical_horizontal_shift(const Line& vector);
    
protected:
    uint16_t height = 0;
    uint16_t width = 0;
    Point startPoint{0,0};
    char color = Canvas::CANVAS_DEFAULT_COLOR;
    
private:
    bool alreadyOnCanvas = false;
};

void Figure::clear_drawing(Canvas& canvas)
{
    draw(canvas, Canvas::CANVAS_DEFAULT_COLOR);
}

void Figure::set_start_point(Point&& point)
{
    if(point.getX() <= 0)
        point.setX(1);
        
    if(point.getY() <= 0)
        point.setY(1);
        
    startPoint = std::move(point);
}

const Point& Figure::get_position()
{
    return Point(startPoint.getX(), 
               std::min(Canvas::CANVAS_HEIGHT, (uint16_t)(startPoint.getX()+height)));
}

std::pair<int, int> Figure::get_vertical_horizontal_shift(const Line& vector)
{
    const Point& startPoint = vector.get_start_point();
    const Point& stopPoint = vector.get_stop_point();
    
    return std::make_pair(stopPoint.getX()-startPoint.getX(), stopPoint.getY()-startPoint.getY());
}

void Figure::move(const Line& vector, Canvas& canvas)
{
    std::pair<int, int> shift = get_vertical_horizontal_shift(vector);
    
    char temp_color = color;
    draw(canvas, Canvas::CANVAS_EMPTY_SPACE);
    set_start_point(Point(startPoint.getX()+shift.first, startPoint.getY()+shift.second));
    draw(canvas, temp_color);
}
////////////////////////////////////////////

class Square : public Figure
{
public:
    void draw(Canvas& canvas, char color = Canvas::CANVAS_DEFAULT_COLOR) override;
    
    void set_height(uint16_t _height) override
    {
        height = _height;
        width = _height;
    }
    
    void set_width(uint16_t _height) override
    {
        height = _height;
        width = _height;
    }
};

void Square::draw(Canvas& canvas, char color_)
{
    if(color != color_)
        set_color(color_);
    
    uint16_t startX = startPoint.getX();
    uint16_t startY = startPoint.getY();
    
    uint16_t endX = std::min(Canvas::CANVAS_HEIGHT-1, (startX+height));
    uint16_t endY = std::min(Canvas::CANVAS_WIDTH-1, (startY+width));
    
    for(int i = startX; i<endX; ++i)
        for(int j = startY; j<endY; ++j)
            canvas.get_canvas2d()[i][j] = color_;
}
////////////////////////////////////////////

class Rectangle : public Figure
{
public:
    void draw(Canvas& canvas, char color = Canvas::CANVAS_DEFAULT_COLOR) override;
};

void Rectangle::draw(Canvas& canvas, char color_)
{
    if(color != color_)
        set_color(color_);
    
    uint16_t startX = startPoint.getX();
    uint16_t startY = startPoint.getY();
    
    uint16_t endX = std::min(Canvas::CANVAS_HEIGHT-1, (startX+height));
    uint16_t endY = std::min(Canvas::CANVAS_WIDTH-1, (startY+width));
    
    for(int i = startX; i<endX; ++i)
        for(int j = startY; j<endY; ++j)
            canvas.get_canvas2d()[i][j] = color_;
}
////////////////////////////////////////////

class QuarterSquareTriangle : public Figure
{
public:
    void draw(Canvas& canvas, char color) override;
    float get_area() override { return 0.5 * width * height; }
    
    void set_height(uint16_t _height) override;
    void set_width(uint16_t _width) override;
};

void QuarterSquareTriangle::draw(Canvas& canvas, char color_)
{
    if(color != color_)
        set_color(color_);
    
    uint16_t startX = startPoint.getX();
    uint16_t startY = startPoint.getY();
    
    uint16_t endX = std::min(Canvas::CANVAS_HEIGHT-1, (startX+height));
    uint16_t endY = std::min(Canvas::CANVAS_WIDTH-1, (startY+width));
    
    int counter = 0;
    uint16_t mid = std::ceil(width/2);
    for(int i=startX; i<endX; ++i)
    {
        for(int j=startY; j<endY-2*counter; ++j)
        {
            canvas.get_canvas2d()[i][j+counter] = color_;
        }
        ++counter;
    }
}

void QuarterSquareTriangle::set_height(uint16_t _height)
{
    height = _height;
    width = _height;
}

void QuarterSquareTriangle::set_width(uint16_t _width)
{
    height = _width;
    width = _width;
}
////////////////////////////////////////////

class HalfSquareTriangle : public Figure
{
public:
    void draw(Canvas& canvas, char color) override;
    float get_area() override { return 0.5 * width * height; };
    
    void set_height(uint16_t _height) override;
    void set_width(uint16_t _width) override;
};

void HalfSquareTriangle::draw(Canvas& canvas, char color_)
{
    if(color != color_)
        set_color(color_);
    
    uint16_t startX = startPoint.getX();
    uint16_t startY = startPoint.getY();
    
    uint16_t endX = std::min(Canvas::CANVAS_HEIGHT-1, (startX+height));
    uint16_t endY = std::min(Canvas::CANVAS_WIDTH-1, (startY+width));
    
    for(int i = startX; i<endX; ++i)
        for(int j = startY; j<=i+startY-startX; ++j)
            canvas.get_canvas2d()[i][j] = color_;
}

void HalfSquareTriangle::set_height(uint16_t _height)
{
    height = _height;
    width = _height;
}

void HalfSquareTriangle::set_width(uint16_t _width)
{
    height = _width;
    width = _width;
}
////////////////////////////////////////////

int main()
{
    Canvas canvas;
    
    Square square;
    square.set_width(5);
    square.set_start_point(Point(1,1));
    square.draw(canvas);
    
    Line line(Point(1,2), Point(3,3));
    square.move(line, canvas);
    
    Rectangle rectangle;
    rectangle.set_height(2);
    rectangle.set_width(3);
    rectangle.set_start_point(Point(10,12));
    rectangle.draw(canvas, '+');
    
    HalfSquareTriangle halfSquareTriange;
    halfSquareTriange.set_height(4);
    halfSquareTriange.set_start_point(Point(9, 1));
    halfSquareTriange.draw(canvas, '*');
    
    QuarterSquareTriangle quarterSquareTriangle;
    quarterSquareTriangle.set_width(7);
    quarterSquareTriangle.set_start_point(Point(14, 10));
    quarterSquareTriangle.draw(canvas, '@');
    
    canvas.drawCanvas();
}