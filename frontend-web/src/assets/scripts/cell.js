export class Cell {
    constructor(row, col) {
        this.row = row;
        this.col = col;
        // 转换为 canvas 坐标
        this.x = col + 0.5;  // 相当于圆心的坐标, 所以要加 0.5
        this.y = row + 0.5;
    }
}