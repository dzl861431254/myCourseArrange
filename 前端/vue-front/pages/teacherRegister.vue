<template>
  <div class="main" style="width:450px">
    <div class="title">
      <a href="/index">登录</a>
      <span>·</span>
      <a class="" href="/studentRegister">学生注册</a>
      <span>·</span>
      <a class="active" href="/teacherRegister">教师注册</a>
    </div>
    <div class="sign-up-container">
      <el-form ref="userForm" :model="params">
        <el-form-item class="input-prepend restyle" prop="teacherName" :rules="[{ required: true, message: '请输入用户名称', trigger: 'blur' }]">
          <div>
            <el-input type="text" placeholder="用户名" v-model="params.teacherName"/>
            <i class="iconfont icon-user"/>
          </div>
        </el-form-item>
        <el-form-item class="input-prepend restyle no-radius" prop="phoneNum" :rules="[{ required: true, message: '请输入手机号码', trigger: 'blur' },{validator: checkPhone, trigger: 'blur'}]">
          <div>
            <el-input type="text" placeholder="手机号" v-model="params.phoneNum"/>
            <i class="iconfont icon-phone"/>
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="passwd" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="设置密码" v-model="params.passwd"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-up-button" value="注册" @click="register()">
        </div>
        <p class="sign-up-msg">
          点击 “注册” 即表示您同意并愿意遵守简书
          <br>
          <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">用户协议</a>
          和
          <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">隐私政策</a> 。
        </p>
      </el-form>

      <!-- 更多注册方式 -->
      <div class="more-sign">
        <h6>社交帐号直接注册</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" href="http://huaan.free.idcfengye.com/api/ucenter/wx/login"><i
            class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import register from '@/api/register'

export default {
     layout: 'sign',
    data() {
      return {
        params: {
          phoneNum: '', //手机号
          teacherName: '', //昵称
          passwd: ''  //密码
        },
  
      }
    },
    methods: {
     
      //注册方法
      submitRegister() {
        register.registerMember(this.params)
                .then(response => {
                  //提示
                  if(response !== undefined) {
                    this.$message({
                      type: 'success',
                      message: "注册成功"
                    })
                    //跳转到登陆页面
                    this.$router.push({path: '/login'})
                    }     
                })
      },
      //获取验证码
      register() {
        register.teacherRegister(this.params)
                .then(response => {
                  //提示
                  if(response !== undefined) {
                    this.$message({
                      type: 'success',
                      message: "注册成功"
                    })
                    //跳转到登陆页面
                    this.$router.push({path: '/login'})
                    }     
                })
      },
      //验证手机号码格式
      checkPhone (rule, value, callback) {
        if (!(/^1[34578]\d{9}$/.test(value))) {
          return callback(new Error('手机号码格式不正确'))
        }
        return callback()
      }
    }
}
</script>