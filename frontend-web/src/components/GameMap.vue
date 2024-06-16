<script setup>
// import { GameMap } from "@/assets/scripts/GameMap";
import { ref, onMounted } from "vue";

const parent = ref(null);
const canvas = ref(null);

onMounted(() => {
  // new GameMap(canvas.value.getContext('2d'), parent.value);
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