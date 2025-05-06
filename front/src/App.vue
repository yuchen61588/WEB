<template>
  <div id="app">
    <router-view />
    <div class="music" :style="{ height: panelHeight + 'px' }">
      <button class="toggle-btn" @click="toggleMusicPanel">
        <el-icon>
          <component :is="isCollapsed ? ArrowUpBold : ArrowDownBold" />
        </el-icon>
      </button>
      <iframe
        frameborder="no"
        border="0"
        marginwidth="0"
        marginheight="0"
        width="330"
        height="450"
        src="http://music.163.com/outchain/player?type=0&id=8851573986&auto=1&height=430"
      ></iframe>
    </div>
  </div>
</template>
<script setup>
import { ref } from "vue";
import { ArrowUpBold, ArrowDownBold } from "@element-plus/icons-vue";
import { computed } from "vue";
const isCollapsed = ref(true);
const panelHeight = computed(() => (isCollapsed.value ? 20 : 450));
const iframeHeight = computed(() => panelHeight.value - 20); // 留有边距

const toggleMusicPanel = () => {
  isCollapsed.value = !isCollapsed.value;
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.music {
  position: fixed;
  bottom: 0;
  right: 20px;
  z-index: 1000;
  transition: all 0.3s ease;
}

.music.collapsed {
  opacity: 0.9;
}

.toggle-btn {
  position: absolute;
  top: -30px;
  left: 50%;
  transform: translateX(-50%);
  width: 40px;
  height: 40px;
  background: #fff;
  border-radius: 50%;
  box-shadow: 0 2px 8px rgba(255, 255, 255, 0.5);
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}

.toggle-btn:hover {
  background: #f5f5f5;
  box-shadow: 0 3px 12px rgba(255, 255, 255, 0.15);
}

@media (max-width: 800px) {
  .music {
    right: calc(50% - 165px);
    bottom: 20px;
  }

  .toggle-btn {
    top: auto;
    bottom: 100%;
    margin-bottom: -6px;
  }

  .music.collapsed {
    bottom: -20px;
  }
}
</style>
