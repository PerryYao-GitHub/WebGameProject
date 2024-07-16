const WEB_GAME_OBJS = [];  // 定义游戏对象类和对象管理数组

export class WebGame {
    constructor() {
        WEB_GAME_OBJS.push(this);
        this.timeDelta = 0;  // 用于存储每帧之间的时间间隔
        this.hasCalledStrated = false;  // 表示是否已经调用了 start 方法
    }

    // 定义游戏对象的生命周期方法
    start() {  // 只执行一次
        // 这个方法应该被子类重写以实现具体逻辑
    }

    update() {  // 每一帧执行一次, 所有的 update() 函数都是实现动画效果
        // 这个方法应该被子类重写以实现具体逻辑
    }

    // 销毁游戏对象
    beforeDestroy() {  // 在游戏对象被销毁之前调用, 用于执行清理和释放资源的逻辑
        // 这个方法应该被子类重写以实现具体逻辑
    }

    destroy() {
        this.beforeDestroy();

        for (let i in WEB_GAME_OBJS) {
            const obj = WEB_GAME_OBJS[i];
            if (obj === this) {
                WEB_GAME_OBJS.splice(i);  // 从obj list中删除当前 obj
                break;
            }
        }
    }
}

// 主循环和动画帧更新
let lastTimeStamp;

// step(timeStamp): 这是一个递归调用的函数, 用于驱动游戏的主循环. 在每一帧更新时, 遍历 WEB_GAME_OBJS 数组, 依次调用游戏对象的 start() 或 update() 方法
const step = timeStamp => {   // step 函数的参数 timeStamp 是由 requestAnimationFrame 函数自动传入的
    for (let obj of WEB_GAME_OBJS) {
        if (!obj.hasCalledStrated) {
            obj.hasCalledStrated = true;
            obj.start();
        } else {
            obj.timeDelta = timeStamp - lastTimeStamp;
            obj.update();
        }
    }

    lastTimeStamp = timeStamp;
    requestAnimationFrame(step); // 请求下一帧动画
}
requestAnimationFrame(step);  // 开始游戏循环: requestAnimationFrame(step): 使用浏览器提供的 requestAnimationFrame 方法来请求下一帧动画, 确保游戏循环持续进行