<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">后台管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        type="password"
                        placeholder="password"
                        v-model="param.password"
                        @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div>
                <el-radio-group v-model="radio7" size="small">
                    <el-radio label="1" border>用户</el-radio>
                    <el-radio label="2" border>职员</el-radio>
                </el-radio-group>
                </div>
               
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <p class="login-tips">Tips : 请输入登录名和密码。</p>
            </el-form>
        </div>
    </div> 

</template>


<script>
export default {
   
    data: function() {
        return {
            param: {
                username: 'admin',
                password: '123123',
            },
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
            },
            radio7: '1',
        };
    },
    methods: {
        submitForm() {
            this.$refs.login.validate(valid => {
                if (valid) {
                    console.log(this.radio7);
                    var that = this;
                    if(this.radio7=="1"){
                        this.$axios.post('/login', {
                        withCredentials:true,
                        patientCode: this.param.username,
                        patientPassword: this.param.password
                        })
                        .then(function (response) {
                            console.log(response);
                            if(response.data.code=="true"){
                                localStorage.setItem('ms_username', response.data.result.patientCode);
                                console.log(response);
                                that.$router.push({ path: '/patient' });
                            }else{
                                    document.getElementsByClassName('login-tips')[0].innerText="Error: 用户名或密码错误";
                                    document.getElementsByClassName('login-tips')[0].style.color="red";
                                }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

                    }else{
                        this.$axios.post('/staffLogin', {
                        withCredentials:true,
                        staffCode: this.param.username,
                        staffPassword: this.param.password,
                        })
                        .then(function (response) {
                            console.log(response);
                            if(response.data.code=="true"){
                                localStorage.setItem('ms_username', response.data.result.staffCode);
                                localStorage.setItem('userid', response.data.result.staffId);
                                localStorage.setItem('departmentId', response.data.result.staffDepartmentId);
                                if(response.data.result.staffRoleId=="1"){
                                    that.$router.push({ path: '/master' });
                                }else if(response.data.result.staffRoleId=="2"){
                                    that.$router.push({ path: '/outdoctor' });
                                }else if(response.data.result.staffRoleId=="3"){
                                    that.$router.push({ path: '/innerdoctor' });
                                }else{
                                    that.$router.push({ path: '/nurse' });
                                }   
                            }else{
                                document.getElementsByClassName('login-tips')[0].innerText="Error: 用户名或密码错误";
                                document.getElementsByClassName('login-tips')[0].style.color="red";
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    }
                   
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