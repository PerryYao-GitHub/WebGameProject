import { WebGame } from "@/assets/scripts/webGame";
import { Cell } from "@/assets/scripts/cell";

export class Snake extends WebGame {
    constructor(info, gameMap) {  // 构造函数传入两参数, 一个是蛇的相关信息字典, 另一个是一个 GameMap 实例
        // info : {id: 0, color: "#77BBDD", row: this.rows - 2, col: 1}
        super();
        this.id = info.id;
        this.color = info.color;
        this.gameMap = gameMap;

        // 存放蛇的身体
        this.cells = [new Cell(info.row, info.col)];  // 蛇的身体是一些列 cells

        // 蛇的移动
        this.nextCell = null;  // 蛇头的下一步移动位置
        this.speed = 5;
        this.direction = -1;  // 0, 1, 2, 3 表示上右下左
        this.status = "idle";  // idle 静止, move 移动, die 死亡

        this.dRow = [-1, 0, 1, 0];  // 移动方向的行偏移量
        this.dCol = [0, 1, 0, -1];  // 移动方向的列偏移量

        this.step = 0;
        this.eps = 1e-2;

        // 蛇的眼睛的细节
        this.eyeDirection = 0;
        if (this.id === 1) this.eyeDirection = 2;
        this.dRowEye = [
            [-1,  1],
            [ 1,  1],
            [ 1, -1],
            [-1, -1],
        ];
        this.dColEye = [
            [-1, -1],
            [-1,  1],
            [1,   1],
            [1,  -1],
        ];
    }

    start() {
        super.start();
    }

    setDirection(d) {
        this.direction = d;
    }

    ifTailIncrease() {  // 检测蛇尾长度是否增加
        if (this.step <= 10) return true;
        else if (this.step % 3 === 1) return true;
        else return false;
    }

    nextStep() {  // 将蛇的状态变成下一步
        const d = this.direction;
        this.nextCell = new Cell(this.cells[0].row + this.dRow[d], this.cells[0].col + this.dCol[d]);
        this.eyeDirection = d;
        this.direction = -1;
        this.status = "move";
        this.step ++;

        const snakeLength = this.cells.length;
        for (let i = snakeLength; i > 0; i--) this.cells[i] = JSON.parse(JSON.stringify(this.cells[i - 1]));

        if (!this.gameMap.ifCellValid(this.nextCell)) this.status = "die";  // 如果下一步的蛇头是不合法的就死亡
    }

    updateMove() {  // 蛇的移动动画
        const dx = this.nextCell.x - this.cells[0].x;
        const dy = this.nextCell.y - this.cells[0].y;
        const distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < this.eps) {  // 蛇到了目的地
            this.cells[0] = this.nextCell;  // 将next蛇头赋值给当前蛇头
            this.nextCell = null;
            this.status = "idle";  // 将蛇的状态变成静止

            if (!this.ifTailIncrease()) this.cells.pop();  // 如果蛇不变长, 到达目的地后, 删去蛇尾
        } else {
            // 蛇头的运动动画
            const moveDistance = this.speed * this.timeDelta / 1000;
            this.cells[0].x += moveDistance * dx / distance;
            this.cells[0].y += moveDistance * dy / distance;

            // 蛇尾的运动动画 (如果蛇尾不增加); 如果蛇变长, 蛇尾不用动
            if (!this.ifTailIncrease()) {
                const snakeLength = this.cells.length;
                const tailCell = this.cells[snakeLength - 1];
                const tailTargetCell = this.cells[snakeLength - 2];
                const dxTail = tailTargetCell.x - tailCell.x;
                const dyTail = tailTargetCell.y - tailCell.y;
                tailCell.x += moveDistance * dxTail / distance;
                tailCell.y += moveDistance * dyTail / distance;
            }
        }
    }

    update() {
        super.update();
        if (this.status === "move") this.updateMove();  // 当状态是move的时候才执行动画
        this.render();
    }

    render() {
        const unitLength = this.gameMap.unitLength;
        const ctx = this.gameMap.ctx;

        ctx.fillStyle = this.color;
        if (this.status === "die") ctx.fillStyle = "white";

        // 画蛇的身体
        for (const cell of this.cells) {
            ctx.beginPath();
            ctx.arc(cell.x * unitLength, cell.y * unitLength, unitLength / 2 * 0.8, 0, Math.PI * 2);  // 画圈
            ctx.fill();
        }
        // 用长方形填充相邻的蛇身体
        for (let i = 1; i < this.cells.length; i ++) {
            const aCell = this.cells[i], bCell = this.cells[i - 1];
            if (Math.abs(aCell.x - bCell.x) < this.eps && Math.abs(aCell.y - bCell.y) < this.eps) continue;
            else if (Math.abs(aCell.x - bCell.x) < this.eps) ctx.fillRect((aCell.x - 0.4) * unitLength, Math.min(aCell.y, bCell.y) * unitLength, unitLength * 0.8, Math.abs(aCell.y - bCell.y) * unitLength);
            else ctx.fillRect(Math.min(aCell.x, bCell.x) * unitLength, (aCell.y - 0.4) * unitLength, Math.abs(aCell.x - bCell.x) * unitLength, unitLength * 0.8);
        }

        // 画蛇眼睛
        ctx.fillStyle = "black";
        for (let i = 0; i < 2; i ++) {
            const xEye = (this.cells[0].x + this.dRowEye[this.eyeDirection][i] * 0.25) * unitLength;
            const yEye = (this.cells[0].y + this.dColEye[this.eyeDirection][i] * 0.25) * unitLength;

            ctx.beginPath();
            ctx.arc(xEye, yEye, unitLength * 0.1, 0, Math.PI * 2);
            ctx.fill();
        }
    }
}