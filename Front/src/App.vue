<template>
    <span>
        <span id="login" v-if="!global.islogin">
            <Login/>
        </span>
        <div id="app" v-else>
            <el-tabs v-model="activeName" class="tabs">
                <el-tab-pane label="chat" name="chat" class="chat">
                    <Chat />
                </el-tab-pane>
                <el-tab-pane label="Log" name="log">
                    <Alllog :key="global.refreshKey1" v-if="global.username=='admin'"/>
                    <Log :key="global.refreshKey2" v-else/>
                </el-tab-pane>
            </el-tabs>
        </div>
    </span>
</template>

<script>
import { ref,onMounted } from 'vue'
import Log from './components/log.vue'
import Chat from './components/chat'
import global from './components/global'
import Login from './components/login.vue'
import Alllog from './components/alllog.vue'
export default {
    components: {
        Log,
        Chat,
        Login,
        Alllog,
    },
    
    data() {
        return {
            activeName: ref('chat'),
            global: global,
            timer: null,
        }
    },
    mounted() {
        this.timer = setInterval(() => {
            this.global.islogin=!this.global.islogin
            this.global.islogin=!this.global.islogin
        }, 500)
    },
    methods:{

    }
}
</script>
<style>
.tabs {
    width: 100%;
    height: 830px;
}
</style>
