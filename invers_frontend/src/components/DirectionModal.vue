<template>
    <div class="modal" v-show="showModal">
        <div class="modal-content">
            <h3>Choose Direction</h3>
            <p>{{ modalText }}</p>
            <div class="modal-buttons">
                <button @click="handleClose(1)">Up</button>
                <button @click="handleClose(2)">Down</button>
                <button @click="handleClose(3)">Left</button>
                <button @click="handleClose(4)">Right</button>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { reactive } from 'vue';
import { Direction } from '@/game/model/Direction';

export default {
    props: {
        showModal: Boolean,
        modalText: String,
    },
    setup(props, context) {
        const state = reactive({
            direction: Direction.UP
        });

        const handleClose = (direction: number) => {
            switch(direction) {
                case 1:
                    state.direction = Direction.UP
                    break;
                case 2:
                    state.direction = Direction.DOWN
                    break;
                case 3:
                    state.direction = Direction.LEFT
                    break;
                case 4:
                    state.direction = Direction.RIGHT
                    break;
            }
            state.direction = direction;
            context.emit('direction-selected', direction);
        };

        return {
            handleClose,
            state,
        };
    }
};
</script>

<style>
button {
    width: 100px;
    height: 60px;
    margin: 5px;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
}

.modal-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}
</style>