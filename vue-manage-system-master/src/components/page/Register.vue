<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">后台管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="登录名">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        type="password"
                        placeholder="密码"
                        v-model="param.password"
                        @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="patientName">
                    <el-input v-model="param.patientName" placeholder="真实姓名">
                        <el-button slot="prepend" icon="el-icon-lx-friend"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="patientAge">
                    <el-input v-model="param.patientAge" placeholder="年龄">
                        <el-button slot="prepend" icon="el-icon-lx-filter"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="patientTelephone">
                    <el-input v-model="param.patientTelephone" placeholder="电话号码">
                        <el-button slot="prepend" icon="el-icon-lx-addressbook"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="patientIdentitycard">
                    <el-input v-model="param.patientIdentitycard" placeholder="身份证号">
                        <el-button slot="prepend" icon="el-icon-lx-profile"></el-button>
                    </el-input>
                </el-form-item>
                 <el-form-item prop="patientSex">
                     <el-radio v-model="param.patientSex" label="男" border>男</el-radio>
                     <el-radio v-model="param.patientSex" label="女" border>女</el-radio>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">注册</el-button>
                </div>
                <p class="login-tips">Tips : 请认真填写以上信息。</p>
            </el-form>
        </div>
    </div> 

</template>


<script>
export default {
    data: function() {
        return {
            param: {
                username: 'usercode',
                password: '123123',
                patientName: 'username',
                patientSex: '男',
                patientAge: '20',
                patientTelephone: '18200684453',
                patientIdentitycard: '440883199911065039'
            },
            rules: {
                username: [{ required: true, message: '请输入登录名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                patientName: [{ required: true, message: '请输入真实姓名', trigger: 'blur' }],
                patientSex: [{ required: true, message: '请选择性别', trigger: 'blur' }],
                patientAge: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
                patientTelephone: [{ required: true, message: '请输入电话号码', trigger: 'blur' }],
                patientIdentitycard: [{ required: true, message: '请输入身份证号', trigger: 'blur' }]
            },
        };
    },
    methods: {
        submitForm() {
            this.$refs.login.validate(valid => {
                var that = this;
                if (valid) {
                    this.$axios.post('/register', {
                    withCredentials:true,
                    patientCode: this.param.username,
                    patientName: this.param.patientName,
                    patientPassword: this.param.password,
                    patientSex: this.param.patientSex,
                    patientAge: this.param.patientAge,
                    patientTelephone: this.param.patientTelephone,
                    patientIdentitycard: this.param.patientIdentitycard
                })
                .then(function (response) {
                    if(response.data.code=="true"){
                        that.$router.push({ path: '/login' });
                    }else{
                        document.getElementsByClassName('login-tips')[0].innerText="Error: 用户名已注册";
                        document.getElementsByClassName('login-tips')[0].style.color="red";
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
                } else {
                    this.$message.error('请输入账号和密码');
                    console.log('error submit!!');
                    return false;
                }
            });
        },
    }
};
</script>


<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../../assets/img/login-bg.jpg);
    background-size: 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #000;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: green;
}
</style>