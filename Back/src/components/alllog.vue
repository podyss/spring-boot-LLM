<!-- https://element-plus.org/zh-CN/ -->
<template>
    <el-container class="layout-container-demo" style="height: 700px">
        <el-aside width="200px">
            <el-menu :default-active="options[0]" @select="handleSelect">
                <el-menu-item-group v-for="item in options">
                    <el-menu-item :index="item" v-text="item"/>
                </el-menu-item-group>
            </el-menu>
        </el-aside>

        <el-container>
            <el-main>
                <el-scrollbar>
                    <el-table :data="tableData.value" :key="tableData.value" style="width: 1300px;">
                        <el-table-column prop="username" label="Username" width="100" />
                        <el-table-column prop="model" label="Model" width="150" />
                        <el-table-column prop="date" label="Date" width="200" />
                        <el-table-column prop="question" label="Question" width="300" />
                        <el-table-column prop="content" label="Content" width="400"/>
                        <el-table-column>
                            <template #default="scope">
                                <el-button size="small" type="danger" @click="handleDelete(scope.row.id, scope.row.model)">Delete</el-button>
                            </template>
                        </el-table-column> 
                        
                    </el-table>
                </el-scrollbar>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import { ref, onMounted,  } from 'vue'
import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue'
import { mapAllLog, delLog } from '../api/api'
export default {
    name: 'log',
    data(){
        return {
            options: ["llama3.2","gemma2:2b","qwen2.5:7b","phi3.5:3.8b"],
        }
    },
    setup(){
        var allData = ref([])
        const tableData = ref([])
        const loadData = () => {
            mapAllLog({}).then(result => {
                allData.value = result.data
                tableData.value = result.data["llama3.2"]
            })
        }
        onMounted(() => {
            loadData()
        })
        return {
            tableData: tableData,
            allData: allData,
        }
    },
    mounted(){
        mapAllLog({}).then(result => {
            this.allData.value = result.data
            this.tableData.value = result.data["llama3.2"]
        })
    },
    methods: {
        load(){
            mapAllLog({}).then(result => {
                this.allData.value = this.result.data
                this.tableData.value = this.result.data["llama3.2"]
            })
        },
        handleSelect(index){
            this.tableData.value = this.allData.value[index]
        },
        handleDelete(id, model){
            delLog(id)
            var arr = this.allData.value[model].filter((item)=>{
                return item.id != id
            })
            this.allData.value[model] = arr
            this.tableData.value = arr
        },
    }
}

</script>
  
<style scoped>
.layout-container-demo .el-header {
    position: relative;
    background-color: var(--el-color-primary-light-7);
    color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
    color: var(--el-text-color-primary);
    background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
    border-right: none;
}

.layout-container-demo .el-main {
    padding: 0;
}

.layout-container-demo .toolbar {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    right: 20px;
}</style>