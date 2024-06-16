import { WebGame } from "@/assets/scripts/webGame";

export class Wall extends WebGame {
    constructor(row, col, gameMap) {
        super();
        this.row = row;
        this.col = col;
        this.gameMap = gameMap;
        this.color = "#779977";
    }

    update() {
        super.update();
        this.render();
    }

    render() {
        const unitLength = this.gameMap.unitLength;  // GameMap类中的单位长度
        const ctx = this.gameMap.ctx;  // GameMap类中的ctx对象

        ctx.fillStyle = this.color;
        ctx.fillRect(this.col * unitLength, this.row * unitLength, unitLength, unitLength);
    }
}