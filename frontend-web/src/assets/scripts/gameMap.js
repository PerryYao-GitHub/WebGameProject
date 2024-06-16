import { WebGame } from "@/assets/scripts/webGame";
import { Wall } from "@/assets/scripts/wall";
import { Snake } from "@/assets/scripts/snake";

export class GameMap extends WebGame {
    constructor(ctx, parent) {
        super();
        /*
        在 GameMap.vue 中:
        <script setup>
        import { GameMap } from "@/assets/scripts/GameMap";
        import { ref, onMounted } from "vue";

        const parent = ref(null);
        const canvas = ref(null);

        onMounted(() => {
          new GameMap(canvas.value.getContext('2d'), parent.value);  // onMounted(() => { ... }): Vue 3 的生命周期钩子, 在组件挂载后执行指定的逻辑. 创建 GameMap 实例, 传入 canvas 的 2D 上下文和父元素引用
        })
        </script>

        <template>
          <div ref="parent" class="GameMap">
            <canvas ref="canvas" tabindex="0">

            </canvas>
          </div>
        </template>

        <style scoped>
        div.GameMap {
          width: 100%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
        }
        </style>

        ctx 是 canvas.getContext('2d') 方法返回的 2D 渲染上下文对象. ctx 对象可以理解为一根画笔.
        canvas 是 HTML5 <canvas> 元素的 DOM 对象. 用于在网页上绘制图形.
        getContext('2d') 方法返回一个用于在画布上绘制 2D 图形的上下文对象.
        ctx 提供了一系列的绘图方法和属性, 例如 fillRect(), strokeRect(), drawImage() 等, 用于在 <canvas> 上进行绘制操作.

        parent 是一个 DOM 元素的引用, 通常用来表示包含 <canvas> 元素的父级元素. 在 GameMap.vue 中就是 <div ref="parent" class="GameMap">
        在 Vue 组件中，可以使用 ref 来获取到 DOM 元素的引用，从而在 JavaScript 中对其进行操作。
        在这个例子中，parent 用于计算和设置 <canvas> 元素的尺寸，以确保适应其父元素的大小。
        */
        this.ctx = ctx;
        this.parent = parent;

        // 棋盘布局
        this.unitLength = 0;  // 整个 canvas 的单位边长
        this.nRows = 15;  // nRows 个单位边长作为 canvas 的长
        this.nCols = 18;  // nCols 个单位边长作为 canvas 的宽

        // this.walls = [];  // GameMap 对象中所有的墙对象列表
        this.innerWallsCount = 30;  // 内部墙的总数
        this.wallsMat = [];

        this.snakes = [
            new Snake({id: 0, color: "#FFDD88", row: this.nRows - 2, col: 1}, this),
            new Snake({id: 1, color: "#FF8899", row: 1, col: this.nCols - 2}, this),
        ];
    }

    ifConnected(mazeMat, sx, sy, tx, ty) {  // 是否连通; flood fill 算法
        if (sx === tx && sy === ty) return true;
        mazeMat[sx][sy] = true;

        let dx = [-1, 0, 1, 0], dy = [0, 1, 0, -1];
        for (let d = 0; d < 4; d ++) {
            let x = sx + dx[d], y = sy + dy[d];
            if (!mazeMat[x][y] && this.ifConnected(mazeMat, x, y, tx, ty)) return true;
        }
        return false;
    }

    buildWalls() {  // 建墙
        this.wallsMat = [];
        // 四周建墙
        for (let row = 0; row < this.nRows; row ++) {
            this.wallsMat[row] = [];
            for (let col = 0; col < this.nCols; col ++) {
                if (col === 0 || col === this.nCols - 1 || row === 0 || row === this.nRows - 1) {  // 棋盘四周用墙封死
                    this.wallsMat[row][col] = true;
                    new Wall(row, col, this);  // 在 (row, col) 处建墙 (画图的过程在 Wall 类中完成了)
                } else this.wallsMat[row][col] = false;
            }
        }

        // 棋盘内部随机建墙, 同时禁止在左下角和右上角建墙
        for (let i = 0; i < this.innerWallsCount / 2; i ++) {  // 一共要得到 this.innerWallsCount / 2 个合法的 (row, col) 坐标
            for (let j = 0; j < 100; j ++) {  // 对于每一个墙, 最多随机 100 次, 将其合法位置找出
                let row = parseInt(Math.random() * this.nRows);
                let col = parseInt(Math.random() * this.nCols);
                let rowDual = this.nRows - 1 - row;  // 以棋盘中心对称出现, 游戏公平, 对偶的 row
                let colDual = this.nCols - 1 - col;  // 对偶的 col

                // 判断位置合法性
                if (this.wallsMat[row][col] || this.wallsMat[rowDual][colDual]) continue;  // 如果 (row, col) 处已经有墙 (不合法), 调过这次随机, 进入下一次随机
                if (row === this.nRows - 2 && col === 1 || rowDual === this.nRows - 2 && colDual === 1) continue;  // 随机到了蛇的出生点 (不合法), 进入下一次随机
                this.wallsMat[row][col] = this.wallsMat[rowDual][colDual] = true;  // 如果随机合法, 将合法的位置标记成为 true

                // 判断地图的连通性
                const wallsMatCopy = JSON.parse(JSON.stringify(this.wallsMat));
                if (!this.ifConnected(wallsMatCopy, this.nRows - 2, 1, 1, this.nCols - 2)) return false;

                new Wall(row, col, this);  // 在合法的位置建墙
                new Wall(rowDual, colDual, this);  // 在合法位置的对偶位置建墙
                break;  // 一旦找到合法的位置以及对偶位置, 结束随机, 继续寻找下一组合法的位置
            }
        }
        return true;
    }

    addListeningEvents() {  // 读取键盘操作 在 GameMap.vue 中要加入 <canvas ref="canvas" tabindex="0"/>
        this.ctx.canvas.focus();

        const [snake0, snake1] = this.snakes;
        this.ctx.canvas.addEventListener("keydown", e => {
            if (e.key === 'w') snake0.setDirection(0);
            else if (e.key === 'd') snake0.setDirection(1);
            else if (e.key === 's') snake0.setDirection(2);
            else if (e.key === 'a') snake0.setDirection(3);
            else if (e.key === 'ArrowUp') snake1.setDirection(0);
            else if (e.key === 'ArrowRight') snake1.setDirection(1);
            else if (e.key === 'ArrowDown') snake1.setDirection(2);
            else if (e.key === 'ArrowLeft') snake1.setDirection(3);

        });
    }

    start() {
        super.start();
        for (let i = 0; i < 100; i ++) if (this.buildWalls()) break;
        this.addListeningEvents();
    }

    updateSize() {
        // canvas 的长和宽 (以 unitLength 为单位边长)
        this.unitLength = Math.min(this.parent.clientWidth / this.nCols, this.parent.clientHeight / this.nRows);
        this.ctx.canvas.width = this.unitLength * this.nCols;
        this.ctx.canvas.height = this.unitLength * this.nRows;
    }

    ifReady() {
        for (const snake of this.snakes) {  // 要两蛇同时 idle 才算 ready
            if (snake.status !== "idle") return false;  // 当蛇处于move和die状态时返回false
            if (snake.direction === -1) return false;
        }
        return true;
    }

    nextStep() {
        for (const snake of this.snakes) snake.nextStep();
    }

    ifCellValid(cell) {  // 检测合法性
        if (this.wallsMat[cell.row][cell.col]) return false;  // 有墙的位置不合法

        // 有蛇的 cells 的位置不合法
        for (const snake of this.snakes) {
            let snakeLength = snake.cells.length;
            if (!snake.ifTailIncrease()) snakeLength --;  // 当蛇尾前进时, 蛇尾之前的位置不要判断
            for (let i = 0; i < snakeLength; i ++) if (snake.cells[i].row === cell.row && snake.cells[i].col === cell.col) return false;  // 有蛇的 cell 的位置不合法
        }
        return true;
    }

    update() {
        super.update();
        this.updateSize();
        if (this.ifReady()) this.nextStep();
        this.render();
    }

    render() {
        // 渲染出棋盘色 ctx 对象可以理解为一根画笔
        const color0 = "#7799CC", color1 = "#77BBDD";
        for (let row = 0; row < this.nRows; row ++)
            for (let col = 0; col < this.nCols; col ++) {
                if ((row + col) % 2 === 0) this.ctx.fillStyle = color0;  // 使用 .fillStyle 选择颜色
                else this.ctx.fillStyle = color1;

                this.ctx.fillRect(col * this.unitLength, row * this.unitLength, this.unitLength, this.unitLength);  // .fillRect 画方块 (棋盘格子)
            }
    }
}