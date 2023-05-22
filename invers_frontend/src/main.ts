import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from '@/App.vue'

import { Game } from '@/game/controller/Game';

var game = new Game();

const pinia = createPinia();
const app = createApp(App);

app.use(pinia);
//app.config.globalProperties.$game = game;

app.mount('#app');