<script setup>
</script>

<template>
  <div class="container content-field">
    <div class="card">
      <div class="card-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<style scoped>
div.container.content-field {
  margin-top: 30px;
}

.card {
  background-color: rgba(255, 255, 255, 0.63); /* 使用 rgba 设置颜色和透明度 */
}
</style>