<template>
    <div class="login" clearfix >
        <div class="login-wrap">
            <el-row type="flex" justify="center">
                <el-form ref="loginForm" :model="user" :rules="rules" status-icon label-width="80px">
                    <h3>登录</h3>
                    <hr>
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model="user.username" placeholder="请输入用户名" prefix-icon></el-input>
                    </el-form-item>
                    <el-form-item id="password" prop="password" label="密码">
                        <el-input v-model="user.password" show-password placeholder="请输入密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-upload" @click="doLogin()">登 录</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
        </div>
    </div>
</template>
   
<script>
import { defineExpose, ref } from "vue"
import { check } from "../api/api"
import global from "./global";

export default {
    name: "login",
    data() {
        return {
            user: {
                username: "",
                password: ""
            },
            global: global,
        };
    },
    created() { },
    methods: {
        doLogin() {
            if (!this.user.username) {
                this.$message.error("请输入用户名！");
                return;
            } else if (!this.user.password) {
                this.$message.error("请输入密码！");
                return;
            } else {
                check(this.user.username, this.user.password).then(result => {
                    if (result && result.data == "1") {
                        global.islogin=true
                        // localStorage.setItem('user', this.user);
                        global.username=this.user.username
                        global.password=this.user.password
                    } else {
                        this.$message.error("您输入的用户名或密码错误！");
                    }
                })
            }
        }
    }
};
</script>
   
<style scoped>
.login {
    width: 100%;
    height: 740px;
    background-size: cover;
    overflow: hidden;
}

.login-wrap {
    background-size: cover;
    width: 400px;
    height: 300px;
    margin: 215px auto;
    overflow: hidden;
    padding-top: 10px;
    line-height: 40px;
}

#password {
    margin-bottom: 5px;
}

h3 {
    color: #0babeab8;
    font-size: 24px;
}

hr {
    background-color: #444;
    margin: 20px auto;
}

a {
    text-decoration: none;
    color: #aaa;
    font-size: 15px;
}

a:hover {
    color: coral;
}

.el-button {
    width: 80%;
    margin-left: -50px;
}
</style>